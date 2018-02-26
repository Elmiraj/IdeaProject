package models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TaxiList {
    /* List of taxis */
    private ArrayList<Taxi> taxiList;
    /* Manager model */
    private Manager man;

    /* Constructor */
    public TaxiList(Manager manager){
        // Initiate variables
        this.taxiList = new ArrayList<Taxi>();
        this.man = manager;
    }

    /* Get the taxi list */

    public ArrayList<Taxi> getTaxiList() {
        return taxiList;
    }

    public void setTaxiList(ArrayList<Taxi> taxiList) {
        this.taxiList = taxiList;
    }

    /* Get the number of taxis in the list */

    public int getNumTaxi(){
        return this.taxiList.size();
    }

    /* Get the taxi from registration number */

    public Taxi getTaxibyRegNum(String RegNum){

        Taxi taxi = null;
        if(RegNum != null){
            Iterator<Taxi> taxiIterator = taxiList.iterator();
            while(taxiIterator.hasNext()){
                Taxi currentTaxi = taxiIterator.next();
                if(RegNum.equals(currentTaxi.getRegNum())){
                    taxi = currentTaxi;
                    break;
                }
            }
        }
        return taxi;
    }

    /* Add new taxi */
    public boolean addTaxi(Taxi t){
        boolean added = t != null ? taxiList.add(t) : false;
        // If added notify views
        if(added){
            man.updateViews();
        }
        return added;
    }
    /* TODO Init taxis */

    public void initTaxi(){
        int limit = 0;
//        Ask user to set a number of taxis superior to 0
        while(!(limit>0)){
            try{
                limit = Integer.parseInt(JOptionPane.showInputDialog("Number of taxi"))
            }catch(NumberFormatException e){
                System.out.println("Please enter a number");
            }
        }
//        TODO Create the number of taxi requiredd by the user
        while(limit>0){

        }
    }

    /* TODO Remove taxi from list */

    public  boolean removeTaxiByRegNum(String RegNum){
        Taxi taxiToRem = this.getTaxibyRegNum(RegNum);
        return taxiToRem != null ? taxiList.remove(taxiToRem) : false;
    }

    /* TODO Pop a taxi from list */

    public Taxi pop(){
        Taxi t = null;
//        if taxi in taxi, take the first one out of the list
        if(taxiList.size() > 0){
            t = taxiList.get(0);
            taxiList.remove(0);
        }
        return t;
    }

    /* TODO Get taxi list by regulation number */

    public String getTaxiListByRegNum(){
        StringBuilder sb = new StringBuilder("");
        Iterator<Taxi> it = taxiList.iterator();
        while(it.hasNext()){
            sb.append(it.next().toString() + "\n");
        }
        return sb.toString();
    }
}
