import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.layout.Pane;

public class circleGenerator {


    ArrayList<Circle> points = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();
    int trackLines = 1;

    private final Color[] circleColors = {Color.BLUE, Color.LIGHTBLUE,Color.TURQUOISE,
                                          Color.CYAN, Color.DARKCYAN,
                                          Color.SEAGREEN,Color.GREEN,
                                          Color.CHARTREUSE,Color.GREENYELLOW,
                                          Color.YELLOW, Color.GREENYELLOW,
                                          Color.CHARTREUSE,Color.GREEN,
                                          Color.SEAGREEN,Color.DARKCYAN,
                                          Color.CYAN,Color.TURQUOISE,Color.LIGHTBLUE,Color.BLUE};
    private int colorIndex = 1;
    

    public void createCircle(Pane drawing, int pointAmt) {

        if (points.isEmpty() == false) {
            points.clear();
        }

        double centerX = drawing.getWidth() / 2;
        double centerY = drawing.getHeight() / 2;

        int pointRadius = 1;
        int circleRadius = 150;

        Circle circle = new Circle(centerX, centerY, circleRadius);
        circle.setStroke(Color.GREY);

        drawing.getChildren().add(circle);

        for(int i = 0; i < pointAmt; i++) {
            double degrees = 360 * i / pointAmt;
            double angle = Math.toRadians(degrees);

            double xOffset = centerX - (Math.cos(angle) * circleRadius);
            double yOffset = centerY + (Math.sin(angle) * circleRadius);

            Circle point = new Circle(xOffset, yOffset, pointRadius);
            point.setFill(Color.TRANSPARENT);
            points.add(point);

            drawing.getChildren().add(points.get(i));
        }

    }

        //Method for making lines
        /*Input: multVal: Times table value, points: Circle made from createCircle()
                 drawing: Pane needed for displaying lines on GUI
        */
        public void createLines(Pane drawing, double multVal, int trackInc) {
            if(colorIndex >= (circleColors.length - 1)) {
                colorIndex = 1;
            }
            if((0.1*trackLines) > 1) {
                 trackLines = 1;
            }

            if(lines.isEmpty() == false) {
                lines.clear();
                trackLines++;
            }
        if(trackInc % 10 == 0) {
            colorIndex++;
        }

        //Initialize variables
        //Will store the position of the start and end points for each line
        double lineStartX;
        double lineStartY;
        double lineEndX;
        double lineEndY;

        //Loop through
        for(int i = 0; i < points.size(); i++) {

            //Store starting point and get position
            Circle pointStart = new Circle();
            pointStart= points.get(i);
            lineStartX = pointStart.getCenterX();
            lineStartY = pointStart.getCenterY();
            
            Circle pointEnd = new Circle();
           
            pointEnd = roundEnd(i, multVal);
            lineEndX = pointEnd.getCenterX();
            lineEndY = pointEnd.getCenterY();

            //Make new line
            Line line = new Line();

            //set start position
            line.setStartX(lineStartX);
            line.setStartY(lineStartY);

            //set end position
            line.setEndX(lineEndX);
            line.setEndY(lineEndY);

            //Set display properties
            if(lines.size() <= (points.size() * (0.1*trackLines))) {
                line.setStroke(circleColors[colorIndex - 1]);
            }
            else {
                line.setStroke(circleColors[colorIndex]);
            }

            //Store and display lines
            lines.add(line);
            drawing.getChildren().add(lines.get(i));
            
        }
    }

/*
* ------------------------
*   FINDING END OF LINE
* ------------------------
*/

     //if end value is > pointAmt, modulo to get final point
            // else get point at the end value
    public Circle roundEnd(int point, double multVal) {
        Circle pointEndPos = new Circle();

        if ((int)Math.round((point*multVal)) >= points.size()) {
            pointEndPos = points.get(((int)Math.round((point*multVal)) % points.size()));
        }
        else {
            pointEndPos = points.get((int)Math.round((point*multVal)));
        }

        return pointEndPos;
    }
        
}