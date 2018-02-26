package models;

import java.util.Observable;

public class Window extends Observable implements Runnable {
    /* ID of window */
    private int windowID;
    /* Current journey of the window */
    private Journey currentJourney;
}
