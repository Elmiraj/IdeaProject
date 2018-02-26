package models;

public class PassengerGroup {
    /* Destination of the passenger group */
    private String destination;
    /* Number of people in the passenger group */
    private  int numPeople;

    /**
     * Constructor
     * @param destination destination
     * @param numPeople number of people
     * */

    public PassengerGroup(String destination, int numPeople) {
        this.destination = destination;
        this.numPeople = numPeople;
    }

    /**
    * Get the destination
    * @return destination
    * */
    public String getDestination() {
        return destination;
    }

    /**
     * Set the destination
     * @param destination destination
     * */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Get the number of people
     * @return number of people
     * */
    public int getNumPeople() {
        return numPeople;
    }

    /**
     * Set the number of people
     * @param numPeople number of people
     * */
    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    /**
     * Overwrite the method toString
     * @return passenger group to string
     * */
    @Override
    public String toString() {
        String PG = "";
//        Add the destination
        PG = destination + "\n";
//        add the number of people
        String numPeople = "";
        if(this.getNumPeople() == 1){
            numPeople = "1 person";
        }else{
            numPeople = this.getNumPeople() + "people";
        }
        PG += numPeople;
        return PG;
    }
}
