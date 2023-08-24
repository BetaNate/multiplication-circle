import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class timesTableMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Times Table Visualization");

        double size = 800;

        Pane drawing = new Pane();
        drawing.setStyle("-fx-background-color: Black");
        drawing.setMinWidth(size);
        drawing.setMinHeight(size);

        timesTable table = new timesTable();

        Scene scene = new Scene(drawing, size, size);
        primaryStage.setScene(scene);
        primaryStage.show();

        // int val = 5;
       // int[][] mat = table.generateTable(val);
        // Loop through all rows
        //for (int i = 0; i < mat.length; i++)
 
            // Loop through all elements of current row
          //  for (int j = 0; j < mat[i].length; j++)
          //      System.out.print(mat[i][j] + " ");
    }
}