import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.util.Duration;

/*
 * Author: Nathan J. Rowe
 * CS 351L Fall 2023, Project 1: Times Table Visualization
 * Class: AnimateCircle, call from timesTableMain.java
 * Abstract: Method for animating circle progression.
 * Uses a timeline and timer to increment multiplication Slider
 * by increment value.
 *
 */

public class AnimateCircle {

    
    /*
     * ---------------------------
     *       CIRCLE ANIMATION
     * ---------------------------
     */
    public Timeline animateLines(Slider tableVal, Button animCtrl) {
        Duration timer = Duration.millis(100);
        Timeline animate = new Timeline(
            new KeyFrame(timer, event -> {
                tableVal.increment();
                if (tableVal.getValue() >= tableVal.getMax()) {
                    tableVal.setValue(tableVal.getMin());
                }
            })
        );
         
        animate.setCycleCount(Timeline.INDEFINITE);
        animate.play();

        return animate;
    }
}
