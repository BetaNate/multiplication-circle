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

    private final Color[] circleColors = {Color.BLUE, 
                                          Color.PURPLE,
                                          Color.MAGENTA,
                                          Color.RED, 
                                          Color.ORANGERED,
                                          Color.ORANGE,
                                          Color.YELLOW,
                                          Color.CHARTREUSE,
                                          Color.GREEN,
                                          Color.SKYBLUE};
    private int colorIndex = 0;
    

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
        public void createLines(Pane drawing, double multVal) {

        if(lines.isEmpty() == false) {
            lines.clear();
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
            line.setStroke(lineColor(line));

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


    private Line linePrev = new Line();

    public Color lineColor(Line line) {
        if(colorIndex >= circleColors.length) {
            colorIndex = 0;
        }

        Color currColor = circleColors[colorIndex];
        if(linePrev.getStroke() == currColor) {
            colorIndex++;
        }
        linePrev = line;
        return currColor;
    }
        
}