import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.animation.Animation.Status;

/*
 * Author: Nathan J. Rowe
 * CS 351L Fall 2023, Project 1: Times Table Visualization
 * Class: Main Class, call from here
 * Abstract: Visualizes a times table with given number of points
 * Main Class creates scene, user controls and listeners
 * Circle creation in circleGenerator.java, animation in
 * AnimateCircle.java
 */
public class timesTableMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private final int drawHeight = 450;
    private final int drawWidth = 800;
    private final int optSize = 50;
    private final int pointMin = 10;
    private final int pointMax = 360;
    private final circleGenerator generator = new circleGenerator();
    private final AnimateCircle animate = new AnimateCircle();

    private int points;
    private int multVal;
    private int colorIndex = 1;
    private int framesToMilli = 100;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Times Table Visualization");
        primaryStage.centerOnScreen();

        //Box Initializtion
        BorderPane root = new BorderPane();
/* 
* ---------------------------
*        USER CONTROLS
* --------------------------- 
*/ 
        Pane drawing = new Pane();
        VBox options = new VBox(10);
        options.setAlignment(Pos.CENTER);
    
        VBox pointInput = new VBox(8);
        VBox valInput = new VBox(8);
        HBox colorInput = new HBox(8);
        colorInput.setAlignment(Pos.BASELINE_CENTER);
        VBox fpsInput = new VBox(8);
        VBox incrementInput = new VBox(8);
        HBox layerOne = new HBox(20);
        layerOne.setAlignment(Pos.BASELINE_CENTER);
        HBox layerTwo = new HBox(20);
        layerTwo.setAlignment(Pos.BASELINE_CENTER);



        //Text box input for setting points
        Label pointLabel = new Label("Number of Points: ");
        TextField pointsAmt = new TextField("10");
        Button submitP = new Button("Submit");

        //Slider for setting times table value;
        Slider tableVal = new Slider(2,360,2);
        tableVal.setShowTickMarks(true);
        tableVal.setShowTickLabels(true);
        tableVal.setBlockIncrement(0.1);
        Label valLabel = new Label("Times Table Value: " + Double.toString(tableVal.getValue()));

        //Time increment slider
        //Increase in value increases jumped values
        Slider incrementSlider = new Slider(0.1,9.9,0.1);
        incrementSlider.setShowTickMarks(true);
        incrementSlider.setShowTickLabels(true);
        incrementSlider.setBlockIncrement(0.1f);
        Label incLabel = new Label("Increment Amount: " + Double.toString(incrementSlider.getValue()));

        //FPS slider
        //Change delay between increments
        Slider fpsSlider = new Slider(1,30,10);
        fpsSlider.setShowTickMarks(true);
        fpsSlider.setShowTickLabels(true);
        fpsSlider.setBlockIncrement(0.1f);
        Label fpsLabel = new Label("Frames Per Second: " + Double.toString(fpsSlider.getValue()));

        ObservableList<String> colorOptions = FXCollections.observableArrayList(
        "Rave","Vanguard","Plant-Aquatic",
                 "LemonLime", "FireBall", "BlueHues", "IronMan",
                 "Grape & Vine", "Plant-Earth", "Bleached Moss");
        final ComboBox colorBox = new ComboBox<>(colorOptions);
        Label colorLabel = new Label("Color: ");


        //Animation control
        Button animCtrl = new Button("Play/Pause");

/*
 * ---------------------------
 *       GUI DISPLAY
 * ---------------------------
 */
        pointInput.getChildren().addAll(pointLabel,pointsAmt, submitP);
        valInput.getChildren().addAll(valLabel, tableVal);
        fpsInput.getChildren().addAll(fpsLabel, fpsSlider);
        incrementInput.getChildren().addAll(incLabel, incrementSlider);
        colorInput.getChildren().addAll(colorLabel, colorBox);

        layerOne.getChildren().addAll(valInput, pointInput);
        layerTwo.getChildren().addAll(incrementInput, fpsInput);
        
        options.getChildren().addAll(
            layerOne, layerTwo,
            colorInput,animCtrl);
        options.setMargin(animCtrl,  new Insets(10, 10, 10, 10));

        //Setup Pane for drawing
        drawing.setStyle("-fx-background-color: Black");
        drawing.setMinWidth(drawWidth);
        drawing.setMinHeight(drawHeight);

        //Set size for option pane from optSize
        options.setMinHeight(optSize);

        root.setCenter(drawing);
        root.setTop(options);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight((drawHeight + optSize));
        primaryStage.setMinWidth(drawWidth);
        primaryStage.show();
        //Create timeline
        Timeline animation = animate.animateLines(tableVal, animCtrl);

        //Exit processes after window closes
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });


/*
 * ------------------------------
 *       CIRCLE CONTROLS
 * ------------------------------
 */
        
        //Get initial values on startup
        points = Integer.parseInt(pointsAmt.getText());
        multVal = (int) Math.round(tableVal.getValue());


        //Event listener for multValue slider
        tableVal.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
                  valLabel.textProperty().setValue(
                       "Times Table Value: " + String.format("%.1f", newValue.doubleValue()));

                  drawing.getChildren().clear();
                  generator.createCircle(drawing, points);
                  generator.createLines(drawing, newValue.doubleValue(), colorIndex);
              }
        });

        //Event listener for multiplication increment slider
        incrementSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
                  incLabel.textProperty().setValue(
                       "Increment Amount: " + String.format("%.1f", newValue.doubleValue()));
                  tableVal.setBlockIncrement(newValue.doubleValue());
              }
        });

          //Event listener for fps slider
        fpsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
                  fpsLabel.textProperty().setValue(
                       "Frames Per Second: " + String.format("%d", newValue.intValue()));
                  framesToMilli = 1000 / newValue.intValue();
                  animation.setRate(animation.getCycleDuration().toMillis() / framesToMilli);
              }
        });

        //Event Listener for points input
        //If input is greater than 360, output will default to 360
        pointsAmt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    pointsAmt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //Event listener for change in points
        submitP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                if (Integer.parseInt(pointsAmt.getText()) > pointMax) {
                    points = pointMax;
                }
                else if (Integer.parseInt(pointsAmt.getText()) < pointMin) {
                    points = pointMin;
                }
                else {
                points = Integer.parseInt(pointsAmt.getText());
                }

                drawing.getChildren().clear();
                multVal = (int) Math.round(tableVal.getValue());
                generator.createCircle(drawing, points);
                generator.createLines(drawing, multVal, colorIndex);
            }
        });

        //Choose color option
        //Each case chooses a set of two colors from circleGenerator's
        //color array. View circleGenerator for implementation
        colorBox.setOnAction(new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent e) {
                switch(colorBox.getValue().toString()) {
                    case "Rave": colorIndex = 1;
                                 break;
                    case "Vanguard": colorIndex = 3;
                                     break;
                    case "Plant-Aquatic": colorIndex = 5;
                                          break;
                    case "LemonLime": colorIndex = 7;
                                      break;
                    case "FireBall": colorIndex = 9;
                                     break;
                    case "BlueHues": colorIndex = 11;
                                     break;
                    case "IronMan": colorIndex = 13;
                                    break;
                    case "Grape & Vine": colorIndex = 15;
                                         break;
                    case "Plant-Earth": colorIndex = 17;
                                        break;
                    case"Bleached Moss": colorIndex = 19;
                                         break;
                }
                generator.createLines(drawing, multVal, colorIndex);
            }
        });

         //Play & Pause Animation control
        animCtrl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(animation.getStatus() == Status.RUNNING) {
                    animation.pause();
                }
                else {
                    animation.play();
                }
            }
        });
    }
}