/*
 * The OneAvailability class creates a data type containing the hour, minute, and second that a particular day's visitation hours begin. 
 * 
 * @author Madeleine Song
 * Ameelio Software Engineering Internship Challenge
 * 4 April 2021
 */


public class OneDay {
    // so, this class keeps track of ONLY the start time for a visitation slot.
    
    //  in visitation-hour-time.
    
    private int day;
    private int hour;
    private int minute; //even 24 hours in milliseconds still is constrained by the range of type int
    
    //  constructor
    public OneDay() {
        day = 0;
        hour = 0;
        minute = 0;
    }
    
    // constructor 
    public OneDay(int d, int h, int m) {
        day = d;
        hour = h;
        minute = m;
    }
    
    public int getDay() {
        return day;
    }
     
    public int getHour() {
        return hour;
    } 
    
    public int getMinute() {
        return minute;
    }
    
    public void setDay(int d) {
        day = d;
    }
    
    public void setHour(int h) {
        hour = h;
    }
    
    public void setMinute(int m) {
        minute = m;
    }
}
