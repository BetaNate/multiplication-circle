import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class timesTable {


    ArrayList<Circle> points = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();

    public ArrayList createCircle(Pane drawing, int pointAmt) {
        double centerX = drawing.getWidth() / 2;
        double centerY = drawing.getHeight() / 2;

        int pointRadius = 1;
        int circleRadius = 150;

        for(int i = 0; i < pointAmt; i++) {
            double degrees = 360 * i / pointAmt;
            double angle = Math.toRadians(degrees);

            double xOffset = centerX - (Math.cos(angle) * circleRadius);
            double yOffset = centerY + (Math.sin(angle) * circleRadius);

            Circle point = new Circle(xOffset, yOffset, pointRadius);
            point.setFill(Color.WHITE);
            points.add(point);

            drawing.getChildren().add(points.get(i));
        }

        
        return points;
    }

        //Method for making lines
        /*Input: multVal: Times table value, points: Circle made from createCircle()
                 drawing: Pane needed for displaying lines on GUI
        */
        public void createLines(Pane drawing, int multVal, ArrayList<Circle> points) {

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
            //if end value is > pointAmt, modulo to get final point
            // else get point at the end value
            if (i*multVal >= points.size()) {
                //Store end point and get position
                pointEnd = points.get(((i*multVal) % points.size()));
                lineEndX = pointEnd.getCenterX();
                lineEndY = pointEnd.getCenterY();
            }
            else {
                pointEnd = points.get(i*multVal);
                lineEndX = pointEnd.getCenterX();
                lineEndY = pointEnd.getCenterY();
            }

            //Make new line
            Line line = new Line();

            //set start position
            line.setStartX(lineStartX);
            line.setStartY(lineStartY);

            //set end position
            line.setEndX(lineEndX);
            line.setEndY(lineEndY);

            //Set display properties
            line.setStroke(Color.WHITE);

            //Store and display lines
            lines.add(line);
            drawing.getChildren().add(lines.get(i));
        }
    }

    //Start method for timesTable
    public void start() {
        AnimationTimer timer = new AnimationTimer() {
            private Duration lastUpdate = Duration.of(0, ChronoUnit.NANOS);
            @Override
            public void handle(long now) {
                Duration nowDur = Duration.of(now, ChronoUnit.NANOS);
                if (nowDur.minus(lastUpdate).toMillis() > 25) {
                    lastUpdate = nowDur;    
                  //  generateConnections(pointArray);
                }
            }
        };
        timer.start();
    }
}