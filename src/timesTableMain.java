import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


public class timesTableMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private final int drawHeight = 550;
    private final int drawWidth = 800;
    private final int optSize = 50;
    private final int pointMin = 10;
    private final int pointMax = 360;

    private int points;
    private int multVal;
    private ArrayList<Circle> circle = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Times Table Visualization");

        //Initialize Variables
        //These variables cannot be listed as private; Illegal Parameter

        //Box Initializtion
        BorderPane root = new BorderPane();


/* 
* ---------------------------
*        USER CONTROLS
* --------------------------- 
*/ 
        Pane drawing = new Pane();
        VBox options = new VBox(10);
        HBox pointInput = new HBox(8);
        HBox valInput = new HBox(8);


        //Text box input for setting points
        Label pointLabel = new Label("Number of Points: ");
        TextField pointsAmt = new TextField("10");
        Button submitP = new Button("Submit");

        //Slider for setting times table value;
        Slider tableVal = new Slider(2,360,2);
        tableVal.setShowTickMarks(true);
        tableVal.setShowTickLabels(true);
        tableVal.setBlockIncrement(0.1f);
        Label valLabel = new Label("Times Table Value: ");
        Label value = new Label(Double.toString(tableVal.getValue()));

        //Time increment slider
        //Increase in value increases jumped values
        Slider incrementSlider = new Slider(1,10,1);
        tableVal.setShowTickMarks(true);
        tableVal.setShowTickLabels(true);
        tableVal.setBlockIncrement(0.1f);
        Label incLabel = new Label("T: ");
        Label incValue = new Label(Double.toString(incrementSlider.getValue()));

        //FPS slider
        //Change delay between increments
        Slider fpsSlider = new Slider(1,10,1);
        tableVal.setShowTickMarks(true);
        tableVal.setShowTickLabels(true);
        tableVal.setBlockIncrement(0.1f);
        Label fpsLabel = new Label("T: ");
        Label fpsValue = new Label(Double.toString(fpsSlider.getValue()));


        //Animation control
        Button animCtrl = new Button("Play/Pause");

/*
 * ---------------------------
 *       GUI DISPLAY
 * ---------------------------
 */
        pointInput.getChildren().addAll(pointLabel,pointsAmt, submitP);
        valInput.getChildren().addAll(valLabel, value);
        
        options.getChildren().addAll(valInput,tableVal,pointInput,animCtrl);



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


/*
 * ------------------------------
 *       CIRCLE ANIMATION
 * ------------------------------
 */
        //Create instance of timesTable class
        timesTable generator = new timesTable();

         //Create circle in drawing pane
        points = Integer.parseInt(pointsAmt.getText());
        multVal = (int) Math.round(tableVal.getValue());
        circle = generator.createCircle(drawing, points);
        generator.createLines(drawing, multVal, circle);

        //Event listener for multValue slider
        tableVal.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
                  value.textProperty().setValue(
                       String.format("%.1f", newValue.doubleValue()));

                  drawing.getChildren().clear();
                  circle = generator.createCircle(drawing, points);
                  generator.createLines(drawing, newValue.intValue(), circle);
              }
        });

        //Event listener for time increment slider
        incrementSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
                  incValue.textProperty().setValue(
                       String.format("%d", newValue.intValue()));

                  
              }
        });

        //Event listener for fps slider
        fpsSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
                  fpsValue.textProperty().setValue(
                       String.format("%d", newValue.intValue()));

                  
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
                circle = generator.createCircle(drawing, points);
                generator.createLines(drawing, multVal, circle);
            }
        });

    }
}