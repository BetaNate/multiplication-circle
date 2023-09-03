## Name
CS 351L Fall 2023 Project 1: Times Table Visualization
Author: Nathan J. Rowe

## Description

<h2>How To Start</h2>
Run the program from the Main class, ***timesTableMain.java***. Running the
program from circleGenerator.java or AnimateCircle.java will likely not work.

<h2>Input Controls and Their Meanings</h2>

***Times Table Value:*** Adjusts the Multiplication Value (i.e what value points are
multiplied to create lines). Minumum is 2.0, Maximum is 360.0.

***Number of Points:*** Points around the circle. Minimum is 10 points, Maximum is 360 points.

***Increment Amount:***Value that Times Table Value is increased by during each animation step.
*Note to Reader: Rather than the maximum being 10.0, the maximum was set to 9.9 to avoid errors
caused by the Times Table Value going above 360, which occurs at 10.0 but NOT 9.9.*

***Frames per Second:***Duration between each animation step. 1 Frame per Second ~ 1000 milliseconds
per frame. (*30 frames ~ 33.33333 milliseconds per frame*).

***Color:***Ten color variations are available.
*Rave: Blue and Magenta*
*Vanguard: Dark Cyan and Orange Red*
*Plant-Aquatic: Seagreen and Green*
*LemonLime: Yellow and Chartreuse*
*FireBall: Red and Orange*
*BlueHues: Cyan and Dark Cyan*
*IronMan: Fire Brick and Golden Rod*
*Grape & Vine: Indigo and Lawn Green*
*Plant-Earth: Saddle Brown and Spring Green*
*Bleached Moss: Pale Green and Pale Turquoise*


**Initial Parameters**
Points: 10
Multiplication Value: 2.0
FPS (Frames per Second): 10
Increment: 0.1
Color: Rave

**Oher Notes from Author**
The are no known bugs, nothing seems to break the set parameters.
There could be an issue with window size depending on the screen. Resizing the window should
automatically adjust to your screen size. 