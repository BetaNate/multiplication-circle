import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

/*
 * Author: Nathan J. Rowe
 * CS 351L Fall 2023, Project 1: Times Table Visualization
 * Class: circleGenerator, call from timesTableMain.java
 * Abstract: Methods for displayings circle points, lines
 *           and color from user input.
 *
 */

public class circleGenerator {


    private final ArrayList<Circle> points = new ArrayList<>();
    private final ArrayList<Line> lines = new ArrayList<>();

    //Initialize variables
    private final int pointRadius = 1;
    private final int circleRadius = 150;
    private final Color[] circleColors = {Color.BLUE, Color.MAGENTA,
                                          Color.DARKCYAN, Color.ORANGERED,
                                          Color.SEAGREEN,Color.GREEN,
                                          Color.YELLOW,Color.CHARTREUSE,
                                          Color.RED, Color.ORANGE,
                                          Color.CYAN, Color.DARKCYAN,
                                          Color.FIREBRICK, Color.GOLDENROD,
                                          Color.INDIGO, Color.LAWNGREEN,
                                          Color.SADDLEBROWN, Color.SPRINGGREEN,
                                          Color.PALEGREEN, Color.PALETURQUOISE};

    //createLines: store the position of the start and end points for each line
    private double lineStartX;
    private double lineStartY;
    private double lineEndX;
    private double lineEndY;
    

    public void createCircle(Pane drawing, int pointAmt) {

        if (points.isEmpty() == false) {
            points.clear();
        }

        final double centerX = drawing.getWidth() / 2;
        final double centerY = drawing.getHeight() / 2;

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
    public void createLines(Pane drawing, double multVal, int colorIndex) {

        if(lines.isEmpty() == false) {
            lines.clear();
        }

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
        if(lines.size() <= (points.size() / 2)) {
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