import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public class timesTable {

    //Method for making times tables
    public void generateCircle(double multVal, int points) {

        //Errors claim that these variables are not permitted to be private, ask professor.
        final int pointArray[] = new int[points];
        final double timesArray[] = new double[points];

        for (int i = 0; i < pointArray.length; i++) {
            pointArray[i] = i;
        }

        for (int j = 0; j < timesArray.length; j++) {
            if (j*multVal > points) {
                timesArray[j] = (j*multVal) % points;
            }
            else {
                timesArray[j] = j*multVal;
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