package it.unicam.cs.MarcoTorquati;


import it.unicam.cs.MarcoTorquati.api.*;
import it.unicam.cs.MarcoTorquati.api.models.*;
import it.unicam.cs.MarcoTorquati.api.utils.*;
import it.unicam.cs.MarcoTorquati.utilities.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.*;
import javafx.scene.transform.*;
import javafx.stage.*;
import javafx.util.*;

import java.io.*;
import java.util.*;

/**
 * This is the main controller class for the Robot Simulation application.
 */
public class RobotSimulation {
    @FXML
    public HBox container;
    @FXML
    private Group group;
    @FXML
    private Pane pane;
    @FXML
    private TextArea programTextArea;
    @FXML
    private Button createRobotButton;
    private Controller controller;
    private int totalRobotsCreated = 0;
    private final Map<Robot, ImageView> robotImageViewMap;
    private final Map<Circle, Text> circleTextMap;
    private final Map<Shape, Text> shapesTextMap;
    private CoordinatesTranslator coordinatesTranslator;
    private double dragStartX;
    private double dragStartY;

    /**
     * Default constructor.
     * Initializes the various maps used in the controller.
     */
    public RobotSimulation() {
        this.robotImageViewMap = new HashMap<>();
        circleTextMap = new HashMap<>();
        shapesTextMap = new HashMap<>();

    }

    /**
     * Initializes the controller.
     * This method is called automatically by JavaFX.
     */
    public void initialize() {
        initController();
        initPane();
        initGroup();
        pane.setOnMousePressed(mouseEvent -> {
            dragStartX = mouseEvent.getX();
            dragStartY = mouseEvent.getY();
        });

        pane.setOnMouseDragged(mouseEvent -> {
            double offsetX = mouseEvent.getX() - dragStartX;
            double offsetY = mouseEvent.getY() - dragStartY;
            dragStartX = mouseEvent.getX();
            dragStartY = mouseEvent.getY();
            group.setTranslateX(group.getTranslateX() + offsetX);
            group.setTranslateY(group.getTranslateY() + offsetY);
        });
    }

    /**
     * Initializes the Controller object.
     */
    private void initController() {
        this.controller = new Controller();
    }

    /**
     * Initializes the pane and sets up the coordinate translator.
     */
    private void initPane() {
        clipChildren(pane);
        Platform.runLater(() -> this.coordinatesTranslator = new CoordinatesTranslator(this.pane.getHeight(), this.pane.getWidth()));
    }

    /**
     * Initializes the group and adds a Translate transform.
     */
    private void initGroup() {
        Translate translate = new Translate();
        this.group.getTransforms().add(translate);
    }

    /**
     * Clips the children of the given pane to a rounded rectangle.
     *
     * @param pane The pane whose children are to be clipped.
     */
    private void clipChildren(Pane pane) {
        final Rectangle outputClip = new Rectangle();
        outputClip.setArcWidth(20);
        outputClip.setArcHeight(20);
        outputClip.widthProperty().bind(pane.widthProperty());
        outputClip.heightProperty().bind(pane.heightProperty());
        pane.setClip(outputClip);

    }

    /**
     * Sets the exit configuration for the application.
     * When the stage receives a close request, this method ensures the application exits.
     *
     * @param stage The primary stage of the application.
     */
    public void setExitConfiguration(Stage stage) {
        stage.setOnCloseRequest(v -> {
            Platform.exit();
        });
    }

    /**
     * Checks if robots have been loaded into the simulation.
     *
     * @return True if no robots are loaded, otherwise False.
     */
    private boolean areRobotsLoaded() {
        return this.robotImageViewMap.isEmpty();
    }

    /**
     * Updates the positions of the robots on the JavaFX pane.
     * This method iterates through the list of robots retrieved from the controller,
     * calculates the new coordinates for each robot, and creates a new animation timeline
     * to move the robot to its new position.
     */
    private void updateImageViews() {
        robotImageViewMap.forEach((robot, imageView) -> {
            Point target = this.coordinatesTranslator.translateToScreenCoordinates(robot.getPosition());
            Text associatedText = circleTextMap.get(robot);

            if (associatedText != null) {
                List<KeyValue> keyValues = getAnimations(imageView, associatedText, robot, target);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), keyValues.toArray(new KeyValue[0])));
                timeline.play();
                checkChangeText(associatedText, robot, target);
            } else {
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                        new KeyValue(imageView.xProperty(), target.getX()),
                        new KeyValue(imageView.yProperty(), target.getY())
                ));
                timeline.play();
            }
        });
    }

    /**
     * Checks and updates the text label associated with a robot if necessary.
     *
     * @param text   The Text object representing the label.
     * @param robot  The Robot object whose label we are checking.
     * @param target The Point object representing the robot's screen position.
     */
    private void checkChangeText(Text text, Robot robot, Point target) {
        if (!robot.getSignaledLabel().equalsIgnoreCase(text.getText())) {
            text.setText(robot.getSignaledLabel());
            text.setLayoutX(this.getXPositionOfText(target.getX(), text));
            text.setLayoutY(this.getYPositionOfText(target.getY(), text));
        }
    }

    /**
     * Updates the positions of the shapes on the JavaFX pane.
     * This method iterates through the list of shapes retrieved from the controller,
     * calculates the new coordinates for each shape, and creates a new animation timeline
     * to move the shape to its new position.
     */
    private List<KeyValue> getAnimations(ImageView imgView, Text t, Robot r, Point target) {
        List<KeyValue> keyValues = new LinkedList<>();
        keyValues.add(new KeyValue(imgView.xProperty(), target.getX()));
        keyValues.add(new KeyValue(imgView.yProperty(), target.getY()));

        Point robotTranslation = this.coordinatesTranslator.translateToScreenCoordinates(r.getPosition());

        if ((robotTranslation.getX() != this.getXPositionOfText(target.getX(), t))
                || (robotTranslation.getY() != this.getYPositionOfText(target.getY(), t))) {
            keyValues.add(new KeyValue(t.layoutXProperty(), this.getXPositionOfText(target.getX(), t)));
            keyValues.add(new KeyValue(t.layoutYProperty(), this.getYPositionOfText(target.getY(), t)));
        }
        checkChangeText(t, r, target);

        return keyValues;
    }

    /**
     * Translates the coordinates of a shape into screen coordinates, adjusting for the dimensions
     * of the shape.
     *
     * @param shape The shape whose coordinates need to be translated.
     * @return A Tuple containing the x and y coordinates adjusted for the shape's dimensions.
     */
    private Tuple<Double, Double> getCoordinatesOfRectangle(IShape shape) {
        Point screenCoordinates = this.coordinatesTranslator.translateToScreenCoordinates(shape.getCoordinates());
        double x = screenCoordinates.getX() - (shape.getDimensions().item1() / 2);
        double y = screenCoordinates.getY() - (shape.getDimensions().item2() / 2);
        return Tuple.of(x, y);
    }

    /**
     * Resets the simulation state. Clears all maps, re-initializes the controller,
     * clears all graphical elements and re-enables any disabled buttons.
     */
    private void reset() {

        this.robotImageViewMap.clear();

        this.circleTextMap.clear();
        this.shapesTextMap.clear();
        this.controller = new Controller();

        this.group.getChildren().clear();
        createRobotButton.setDisable(false);
        this.programTextArea.setText("");
        createRobotButton.setDisable(false);
        this.container.getChildren().stream().filter(Node::isDisable).forEach(y -> y.setDisable(false));

    }

    /**
     * Calculates the x-coordinate for placing a Text object centered horizontally
     * at a given x-coordinate.
     *
     * @param x    The x-coordinate where the Text should be centered.
     * @param text The Text object to be placed.
     * @return The x-coordinate where the left edge of the Text should be placed.
     */
    private double getXPositionOfText(double x, Text text) {
        return x - text.getLayoutBounds().getWidth() / 2;
    }

    /**
     * Calculates the y-coordinate for placing a Text object at a given y-coordinate.
     *
     * @param y    The y-coordinate where the Text should be placed.
     * @param text The Text object to be placed.
     * @return The y-coordinate where the baseline of the Text should be placed.
     */
    private double getYPositionOfText(double y, Text text) {
        return y + text.getLayoutBounds().getHeight() / 4;
    }

    /**
     * Displays an error alert dialog with a specified text message.
     *
     * @param text The message to be displayed in the alert dialog.
     */
    private void showErrorAlert(String text) {
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle("Errore");
        a.setHeaderText(null);
        a.initOwner(group.getScene().getWindow());
        a.setContentText(text);
        a.show();
    }

    /**
     * Displays a dialog box that allows the user to input an integer value. The dialog box
     * includes optional validation to ensure that the input is within a specific range.
     *
     * @param headerText    The text displayed at the top of the dialog box.
     * @param hasUpperLimit A boolean indicating whether the input should be limited to
     *                      a specific upper limit (1 to 100) or not.
     * @return An OptionalInt containing the user's input, if valid; otherwise, an empty OptionalInt.
     */
    private OptionalInt showInputIntegerDialog(String headerText, boolean hasUpperLimit) {

        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Input");
        dialog.setHeaderText(headerText);


        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);


        TextField textField = new TextField();
        dialog.getDialogPane().setContent(textField);


        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);


        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                if (hasUpperLimit) {
                    okButton.setDisable(value < 1 || value > 100);
                } else {
                    okButton.setDisable(value <= 0);
                }
            } catch (NumberFormatException e) {
                okButton.setDisable(true);
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                try {
                    return Integer.parseInt(textField.getText());
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        });

        Optional<Integer> result = dialog.showAndWait();
        return result.map(OptionalInt::of).orElseGet(OptionalInt::empty);
    }

    /**
     * Iterates through the list of shapes retrieved from the controller
     * and calls the appropriate method to draw each shape.
     */
    private void drawShapes() {
        this.controller.getShapes().forEach(this::drawShapes);
    }

    /**
     * Determines the type of shape (circular or rectangular)
     * based on its dimensions and calls the appropriate method to draw it.
     *
     * @param shape The shape to be drawn.
     */
    private void drawShapes(IShape shape) {
        if (shape.getDimensions().item2() == -1) {
            drawCircularShape(shape);
        } else {
            drawRectangularShape(shape);
        }
    }

    /**
     * Draws a rectangular shape on the JavaFX pane.
     * The method calculates the coordinates for the shape,
     * creates a new Rectangle object, sets its properties, and
     * adds it to the group for rendering.
     *
     * @param shape The shape information used to draw the rectangle.
     */
    private void drawRectangularShape(IShape shape) {
        Tuple<Double, Double> coordinates = this.getCoordinatesOfRectangle(shape);
        Rectangle rectangle = new Rectangle(coordinates.item1(), coordinates.item2(),
                shape.getDimensions().item1(), shape.getDimensions().item2());
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.GREEN);
        rectangle.setStrokeWidth(2);
        Text text = this.createTextFromShape(rectangle, shape.getLabel());
        this.shapesTextMap.put(rectangle, text);
        this.group.getChildren().addAll(rectangle, text);
    }

    /**
     *
     */
    private void drawCircularShape(IShape shape) {
        Circle circle = new Circle(shape.getDimensions().item1());
        Point screenPoint = this.coordinatesTranslator.translateToScreenCoordinates(shape.getCoordinates());
        circle.setCenterX(screenPoint.getX());
        circle.setCenterY(screenPoint.getY());
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLUE);
        circle.setStrokeWidth(2);
        Text text = this.createTextFromShape(circle, shape.getLabel());
        this.shapesTextMap.put(circle, text);
        this.group.getChildren().addAll(circle, text);
    }

    /**
     * Draws the robots on the JavaFX pane.
     * The method iterates through the list of robots retrieved from the controller,
     * creates a new ImageView object for each robot, sets its properties, and
     * adds it to the group for rendering.
     */
    private void drawRobots() {
        Image robotIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/robot.png")));
        this.controller.getRobots().forEach(robot -> {
            ImageView imageView = new ImageView(robotIcon);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            Point screenPoint = this.coordinatesTranslator.translateToScreenCoordinates(robot.getPosition());
            imageView.setX(screenPoint.getX());
            imageView.setY(screenPoint.getY());
            this.robotImageViewMap.put(robot, imageView);
            this.group.getChildren().add(imageView);
        });
    }

    /**
     * Creates a text label for a given shape (either Circle or Rectangle) and sets its properties.
     *
     * @param shape The shape for which the text label is to be created.
     * @param label The text content for the label.
     * @return The created Text object.
     */
    private Text createTextFromShape(Shape shape, String label) {
        Text text = new Text(label);
        text.setFont(Font.font("Arial", 16));
        if (true) {
            text.setFill(Color.BLACK);
        } else {
            text.setFill(Color.BLACK);
        }

        if (shape instanceof Circle circle) {
            text.setLayoutX(circle.getCenterX() - text.getLayoutBounds().getWidth() / 2);
            text.setLayoutY(circle.getCenterY() + text.getLayoutBounds().getHeight() / 4);
        } else if (shape instanceof Rectangle rectangle) {
            text.setLayoutX(rectangle.getX());
            text.setLayoutY(rectangle.getY() + rectangle.getLayoutBounds().getHeight() / 2);
        }
        return text;
    }



    /**
     * Opens a file dialog for selecting a file.
     * This generic function allows customization of the dialog title, file extension, and description.
     *
     * @param mouseEvent The MouseEvent that triggered the file dialog.
     * @param title      The title for the file dialog.
     * @param extension  The allowed file extensions for the dialog.
     * @param description The description for the allowed file extensions.
     * @return The File object for the selected file, or null if the dialog was cancelled.
     */
    private File openFileDialog(MouseEvent mouseEvent, String title, String extension, String description) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(description, extension));
        return fileChooser.showOpenDialog(((Node) mouseEvent.getSource()).getScene().getWindow());
    }

    /**
     * Opens a file dialog specifically for importing shapes.
     * This method utilizes the openFileDialog function and sets predefined parameters for shapes.
     *
     * @param mouseEvent The MouseEvent that triggered the file dialog.
     * @return The File object for the selected shape file, or null if the dialog was cancelled.
     */
    private File openFileDialogForShapes(MouseEvent mouseEvent) {
        return this.openFileDialog(mouseEvent, "Seleziona il file da cui importare le figure",
                "*.se", "Robots Shapes");
    }

    /**
     * Opens a file dialog specifically for importing a list of commands for robots.
     * This method utilizes the openFileDialog function and sets predefined parameters for robot programs.
     *
     * @param mouseEvent The MouseEvent that triggered the file dialog.
     * @return The File object for the selected program file, or null if the dialog was cancelled.
     */
    private File openFileDialogForProgram(MouseEvent mouseEvent) {
        return this.openFileDialog(mouseEvent, "Seleziona il file da cui importare la lista dei comandi",
                "*.txt", "Robots Program");
    }

    /**
     * Handles the Reset button click event.
     * This method calls the internal reset function to clear all the data and reset the simulation.
     *
     * @param mouseEvent The MouseEvent that triggered the reset action.
     */
    public void onResetClicked(MouseEvent mouseEvent) {
        System.out.println("onResetClicked called");
        this.reset();
        createRobotButton.setDisable(false);
    }

    /**
     * Handles the "Load Robots" button click event.
     * This method tries to load the robots from a file.
     * If the file is not valid, an error message is shown.
     *
     * @param mouseEvent The MouseEvent that triggered the load robots action.
     */
    public void onMouseShapesClicked(MouseEvent mouseEvent) {
        try {
            File selectedFile = this.openFileDialogForShapes(mouseEvent);
            if (selectedFile != null) {
                this.controller.readShapeList(selectedFile);
                this.drawShapes();
            }
        } catch (IOException | FollowMeParserException e) {
            this.showErrorAlert(e.getMessage());
        }
    }

    /**
     * Handles the "Execute" button click event.
     * This method tries to execute the next instruction for each robot.
     * If no robots are loaded, a warning dialog is shown.
     *
     * @param mouseEvent The MouseEvent that triggered the execute action.
     */
    public void onExecuteClicked(MouseEvent mouseEvent) {
        if (areRobotsLoaded()) {
            showWarningDialog();
            return;
        }
        try {

            this.controller.nextInstruction();
            this.updateImageViews();

        }
        catch (NullPointerException e) { Platform.runLater(() -> this.showErrorAlert("Devi prima aggiungere i movimenti")); }

    }

    /**
     * Handles the button click events related to loading robots and their programs.
     * This method checks the text of the clicked button to determine what action to take.
     * If the button says "Crea Robot", it will load the robots.
     * If the button says "Aggiungi Movimenti", it will load the robot movements.
     * If no robots have been loaded when trying to add movements, an error message is shown.
     *
     * @param mouseEvent The MouseEvent that triggered the action.
     */
    public void onLoadRobotsProgram(MouseEvent mouseEvent) {
        Button clickedButton = (Button) mouseEvent.getSource();
        String buttonText = clickedButton.getText();

        if ("Crea Robot".equals(buttonText)) {
            handleRobotLoading(mouseEvent);
        } else if ("Aggiungi Movimenti".equals(buttonText)) {
            if (this.controller.getRobots().isEmpty()) {

                Platform.runLater(() -> this.showErrorAlert("Devi prima aggiungere i Robot."));
            } else {
                handleProgramLoading(mouseEvent);
            }
        }
    }

    /**
     * Handles the robot loading logic.
     * Calls the RobotCreation method.
     * If the robots are successfully created, the drawRobots method is called.
     * If the number of robots is greater than or equal to 100, the "Crea Robot" button is disabled.
     *
     * @param mouseEvent The MouseEvent that triggered the robot loading action.
     */
    private void handleRobotLoading(MouseEvent mouseEvent) {
        Optional<Boolean> result = RobotCreation();
        if (result.isPresent() && result.get()) {
            this.drawRobots();
            createRobotButton.setDisable(true);
        }
    }

    /**
     * Handles the logic for program loading.
     * Calls the loadProgram method.
     *
     * @param mouseEvent The MouseEvent that triggered the program loading action.
     */
    private void handleProgramLoading(MouseEvent mouseEvent) {
        loadProgram(mouseEvent);
    }

    /**
     * Handles the "Execute Multiple Instructions" button click event.
     * Executes a series of instructions based on user input.
     * Shows a warning dialog if no robots are loaded.
     *
     * @param mouseEvent The MouseEvent that triggered the execute multiple instructions action.
     */
    public void onExecuteMultipleInstruction(MouseEvent mouseEvent) {
        if (areRobotsLoaded()) {
            showWarningDialog();
            return;
        }

        OptionalInt userInput = showInputIntegerDialog("Inserisci il numero di passi (solo cifre positive)", false);
        int numberOfInstruction = userInput.orElse(1);

        Thread thread = new Thread(this.executeMultipleInstruction(numberOfInstruction));
        thread.start();
    }

    /**
     * Displays a warning dialog with a predefined message.
     * Used to notify the user that robots and movements need to be added first.
     */
    private void showWarningDialog() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.initOwner(group.getScene().getWindow());
        alert.setContentText("Devi prima aggiungere i Robot e i movimenti");
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);
        alert.showAndWait();
    }

    /**
     * Handles the scroll event on the group of shapes and robots.
     * Consumes the scroll event and scales the group accordingly.
     *
     * @param scrollEvent The ScrollEvent that triggered the scaling action.
     */
    public void scrollGroupEvent(ScrollEvent scrollEvent) {
        scrollEvent.consume();
        double scaleFactor = (scrollEvent.getDeltaY() > 0) ? 1.1 : 1 / 1.1;
        Scale(scaleFactor);
    }

    /**
     * Handles the "Zoom In" button click event.
     * Scales the group to zoom in.
     *
     * @param mouseEvent The MouseEvent that triggered the zoom-in action.
     */
    public void onZoomInClicked(MouseEvent mouseEvent) {
        performZoom(1.1);
    }

    /**
     * Handles the "Zoom Out" button click event.
     * Scales the group to zoom out.
     *
     * @param mouseEvent The MouseEvent that triggered the zoom-out action.
     */
    public void onZoomOutClicked(MouseEvent mouseEvent) {
        performZoom(1/1.1);
    }

    /**
     * Performs a zooming operation based on the given scaling factor.
     * Invokes the Scale method to actually perform the scaling.
     *
     * @param scaleFactor The scaling factor to apply. A value greater than 1 zooms in, and a value less than 1 zooms out.
     */
    private void performZoom(double scaleFactor) {
        Scale(scaleFactor);
    }

    /**
     * Applies a scaling operation on the group of shapes and robots.
     * The scaling is performed around the center of the pane.
     *
     * @param scaleFactor The scaling factor to apply. A value greater than 1 zooms in, and a value less than 1 zooms out.
     */
    private void Scale(double scaleFactor) {
        double centerX = pane.getWidth() / 2;
        double centerY = pane.getHeight() / 2;

        Scale scale = new Scale();
        scale.setPivotX(centerX);
        scale.setPivotY(centerY);
        scale.setX(group.getScaleX() * scaleFactor);
        scale.setY(group.getScaleY() * scaleFactor);
        group.getTransforms().add(scale);
    }

    /**
     * Creates a Runnable task to execute multiple robot instructions in a separate thread.
     * The task executes the next instruction for each robot and updates their positions, with a pause of 1 second between each instruction.
     *
     * @param n The number of instructions to execute.
     * @return A Runnable task that can be executed in a separate thread.
     */
    private Runnable executeMultipleInstruction(int n) {
        return () -> {
            try {
                for (int i = 0; i < n; i++) {
                    this.controller.nextInstruction();
                    Platform.runLater(this::updateImageViews);
                    Thread.sleep(1000);
                }
            }
            catch (NullPointerException e) { Platform.runLater(() -> this.showErrorAlert("Devi prima aggiungere i movimenti")); }
            catch (InterruptedException | IllegalArgumentException e) { Platform.runLater(() -> this.showErrorAlert(e.getMessage())); }
        };
    }

    /**
     * Handles the creation of robots based on user input.
     * Displays a dialog box to the user asking for the number of robots to create.
     * The robots are generated randomly within the pane dimensions.
     *
     * @return An Optional<Boolean> that is:
     *         - true if the robots are successfully created,
     *         - false if the number of robots specified is outside the valid range,
     *         - empty if the dialog was cancelled or no input was given.
     */
    private Optional<Boolean> RobotCreation() {
        OptionalInt userInput = showInputIntegerDialog("Quanti robot vuoi creare (cifra tra 1 e 100)?", true);
        if (userInput.isPresent()) {
            int numberOfRobots = userInput.getAsInt();
            if (numberOfRobots >= 1 && numberOfRobots <= 100) {
                this.controller.generateRandomRobots(numberOfRobots,
                        new Point(-(pane.getWidth() / 2), -(pane.getHeight())),
                        new Point(pane.getWidth() / 2, pane.getHeight() / 2));
                totalRobotsCreated += numberOfRobots;
                checkDisableCreateRobotButton();
                return Optional.of(true);
            } else {
                return Optional.of(false);
            }
        }
        return Optional.empty();
    }

    private void checkDisableCreateRobotButton() {
        if (totalRobotsCreated >= 100) {
            createRobotButton.setDisable(true);
        } else {
            createRobotButton.setDisable(false);
        }
    }

    /**
     * Loads a program of instructions for the robots.
     * Opens a file dialog to allow the user to select a file containing the list of instructions.
     * Reads the instructions from the selected file and displays them in the program text area.
     * If an exception occurs (e.g., file not found, invalid format), an error alert is shown.
     *
     * @param mouseEvent The mouse event that triggered this action.
     */
    private void loadProgram(MouseEvent mouseEvent){
        try {
            File selectedFile = this.openFileDialogForProgram(mouseEvent);
            if (selectedFile != null) {
                List<String> lines = this.controller.readInstructionList(selectedFile);
                lines.forEach(x -> this.programTextArea.appendText(x + "\n"));
            }
        } catch (IOException | FollowMeParserException | IllegalArgumentException e) {
            this.showErrorAlert(e.getMessage());
        }
    }


}