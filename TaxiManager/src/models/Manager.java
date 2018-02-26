package models;

import java.util.Observable;

public class Manager extends Observable implements Runnable{
//    List of windows
    private WindowList windowList;
//    Passenger group list
    private PassengerGroupList passengerGroupList;
//    Taxi list
    private  TaxiList taxiList;
//    Window threads
    private Thread [] windows;
//    Program finished or not
    private boolean finished;
//    Current speed
    private int speed;
//    Max speed
    private static final int MAX_SPEED = 5;
//    Min speed
    private static final int MIN_SPEED = 1;

    /*
    * Constructor
    * */
    public Manager(){
//        Initiate variable
        passengerGroupList = new PassengerGroupList(this);
        taxiList = new TaxiList(this);
        windowList = new WindowList(this);
        speed = 1;
    }

}
