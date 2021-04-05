/*
 * The OneAvailability class creates a data type containing the total information 
 *  of one day's visitation hours (when they begin, and how long they last for). 
 * 
 * @author Madeleine Song
 * Ameelio Software Engineering Internship Challenge
 * 4 April 2021
 */

public class OneAvailability {
    //  so, this class keeps track of the start and end time (OneDay)
    //  as well as the duration 
    //  of a given visitation slot
    
    //  in both 'visitation-hour-time' and 'epoch-time'. 
    
    // for 'v-h-t'
    private OneDay day;
    private int duration;
    // for 'e-t'
    private long epochStart;
    private long epochEnd;
    
    
    // constructor
    public OneAvailability() {
        day = new OneDay();
        duration = 0;
        epochStart = 0;
        epochEnd = 0;
    }
    
    //  constructor
    public OneAvailability(OneDay o, int dur, long eS, long eE) {
        day.setDay(o.getDay());
        day.setHour(o.getHour());
        day.setMinute(o.getMinute());
        
        duration = dur;
        
        epochStart = eS;
        epochEnd = eE;
    }
    
    public OneDay getDayDetails() {
        return day;
    }
     
    public int getDuration() {
        return duration;
    } 
    
    public void setDuration(int dur) {
        duration = dur;
    }
    
    public void setAvailability(OneAvailability o) {
        day.setDay(o.getDayDetails().getDay());
        day.setHour(o.getDayDetails().getHour());
        day.setMinute(o.getDayDetails().getMinute());
        duration = o.getDuration();
    }
    
    /*   check if a given slot 'o' is a visitation slot
     *   either 'o' exactly matches a visitation slot, or it is subsumed within a larger visitation slot
     * 
     *   Param OneAvailability slot
     *   Return true if a given slot 'o' is a visitation slot. false otherwise
     */
    public boolean equals(OneAvailability o) {
        // if the hour-minute of 'day' exactly matches the hour-minute of 'OneAvailability o'
        if (o.getDayDetails().getDay() == day.getDay()) {
            if (o.getDayDetails().getHour() == day.getHour()) {
                if (o.getDayDetails().getMinute() == day.getMinute()) {
                    return true;
                }
            }
        }
        // if the hour-minute of 'day' subsumes the hour-minute of 'day'
        // example: DAY is 4-6PM Monday, 'O' is 5-5:30PM Monday
        if (o.getDayDetails().getDay() == day.getDay()) {
            
            // get start times
            double oStart = o.getDayDetails().getHour() + (o.getDayDetails().getMinute() / 60);
            double dayStart = day.getHour() + (day.getMinute() / 60);

            double oEnd = oStart + millsToHours(o.getDuration());
            double dayEnd = dayStart + duration;
            
            if ((oStart >= dayStart) && (oEnd <= dayEnd)) {
                return true;
            }
        }
        return false;
    }
    
    public static double millsToHours(int ms) {
        int hours = ms / (1000 * 60 * 60);
        return hours;
    }
    
    public long getEpochStart() {
        return epochStart;
    }
    
    public long getEpochEnd() {
        return epochEnd;
    }
    

}
