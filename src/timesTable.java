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
    
    final Slider tableSize = new Slider(2,360,2);
    final Slider points = new Slider(0,360,5);

    final Label tableCaption = new Label ("Times Table Value:");
    final Label pointCaption = new Label ("Number of Points:");

    final Label tableValue = new Label (Double.toString(tableSize.getValue()));
    final Label pointValue = new Label (Double.toString(points.getValue()));

    final static Color textColor = Color.WHITE;

    int size = (int)tableSize.getValue();

    private int[][] generateTable(int size) {
        int[][] timesArray = new int[size][size];
        

        for (int row = 0; row < timesArray.length; row++) {
            for (int col = 0; col < timesArray[row].length; col++) {
                timesArray[row][col] = (row+1)*(col+1);
            }
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
                    generateTable(size);
                }
            }
        };
        timer.start();
    }
}