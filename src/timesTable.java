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
    //private final int pointArray[] = new int[(int)tableSize.getValue()];

    //Method for making times tables
    public int[] generateConnections(int points[]) {

        final int timesArray[] = new int[points.length + 1];

        for (int i = 0; i < timesArray.length; i++) {
            timesArray[i] = i*points.length;
        }
        return timesArray; 
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