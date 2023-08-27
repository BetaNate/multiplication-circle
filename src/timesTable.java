import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class timesTable {


    ArrayList<Circle> points = new ArrayList<>();

    public void createCircle(Pane drawing, int pointAmt) {
        int centerX = 400;
        int centerY = 200;

        int pointRadius = 1;
        int circleRadius = 150;

        for(int i = 0; i < pointAmt; i++) {
            double degrees = 360 * i / pointAmt;
            double angle = Math.toRadians(degrees);

            double xOffset = centerX + (Math.cos(angle) * circleRadius);
            double yOffset = centerY + (Math.sin(angle) * circleRadius);

            Circle point = new Circle(xOffset, yOffset, pointRadius);
            point.setFill(Color.WHITE);
            points.add(point);

            System.out.print(points.get(i));

            drawing.getChildren().add(points.get(i));
        }
    }

        //Method for making times tables
        public void generateLines(double multVal, ArrayList<Circle> points) {

        //Errors claim that these variables are not permitted to be private, ask professor.
        //final int pointArray[] = new int[points];
        final double timesArray[] = new double[points.size() + 1];

        //If the value is larger than the points of the circle, modulus for next point.
        //This method is accurate for small values, unknown for larger values.
        for (int i = 0; i < timesArray.length; i++) {
            if (i*multVal >= points.size()) {
                timesArray[i] = (i*multVal) % points.size();
            }
            else {
                timesArray[i] = i*multVal;
            }
        }
    }

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