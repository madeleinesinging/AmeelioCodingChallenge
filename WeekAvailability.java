/*
 * The WeekAvailability class tracks the week's visitation hours as well as which of those have already been taken. 
 * Hours can be added, updated, and removed.
 * 
 * 
 * 
 * @author Madeleine Song
 * Ameelio Software Engineering Internship Challenge
 * 4 April 2021
 */

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.ArrayList;

public class WeekAvailability {
    // so, this class keeps track of everything that has to do with a week.
    
    
    // in both 'visitation-hour-time' and 'epoch-time'

    private ArrayList<OneAvailability> daysAndDurs; // arraylist of all weekly availabilities
    private ArrayList<OneAvailability> availableDaysDurs; 
        //  arraylist of all taken weekly availabilities
        //  begins with all the slots in daysAndDurs. 
        //  each time a slot is claimed, we remove it from aviailableDaysDurs
    private OneAvailability[] visitationHours;
    
    //  constructor
    public WeekAvailability() {
        daysAndDurs = new ArrayList<OneAvailability>();
        availableDaysDurs = new ArrayList<OneAvailability>();
    }
    
    //  constructor that appends one day
    public WeekAvailability(OneAvailability day) {
        daysAndDurs = new ArrayList<OneAvailability>();
        availableDaysDurs = new ArrayList<OneAvailability>();

        daysAndDurs.add(day);
        availableDaysDurs.add(day);
    }
    
    //  constructor that appends an array of days
    public WeekAvailability(OneAvailability[] days) {
        daysAndDurs = new ArrayList<OneAvailability>();
        availableDaysDurs = new ArrayList<OneAvailability>();

        for (int i = 0; i < days.length; i++) {
            daysAndDurs.add(days[i]);
        }
        
        for (int i = 0; i < days.length; i++) {
            availableDaysDurs.add(days[i]);
        }
        
    }
    
    //  constructor that appends an ArrayList of days
    public WeekAvailability(ArrayList<OneAvailability> days) {
        daysAndDurs = new ArrayList<OneAvailability>();
        availableDaysDurs = new ArrayList<OneAvailability>();

        daysAndDurs.addAll(days);
        availableDaysDurs.addAll(days);
    }
    
    /*  Returns the total visitation hours (both those available and unavailable)
     *  Param: none
     *  Return: ArrayList of total visitation hours
     * 
     */
    public ArrayList<OneAvailability> getDaysDurs() {
       return daysAndDurs;
    }
    
    /*   Checks a given slot with all the available time slots. 
     *   If there is a match, then remove that slot from the ArrayList of availabilities.
     *   
     *   Param time slot
     *   Return true if a match is found. False otherwise.
     */
    public boolean checkAndRemove(OneAvailability slot) {
        for (int i = 0; i < daysAndDurs.size(); i++) {
            if (daysAndDurs.get(i).equals(slot) && (daysAndDurs.get(i).getDuration() != 0)) {
                availableDaysDurs.remove(slot);
                return true;
            }
        }
        return false;
    }
    
    /*  Checks whether a given time slot is a visitation hour.
     *  Param: a OneAvailability time slot 
     *  Return: index of the given time slot within the ArrayList of visitation hours.
     *        -1 if the time slot is not included in the visitation hours.
     *          
     */
    
    public int exists(OneAvailability slot) {
        for (int i = 0; i < daysAndDurs.size(); i++) {
            if (daysAndDurs.get(i).equals(slot) && (daysAndDurs.get(i).getDuration() != 0)) {
                return i;
            }
        }
        return -1;
    }
    
    /*  Converts week's visitation hours from ArrayList to Array
     *  Param: None
     *  Return: Array of week's visitation hours
     * 
     */
    public OneAvailability[] toArray() {
        visitationHours = new OneAvailability[daysAndDurs.size()];
        for (int i = 0; i < daysAndDurs.size(); i++) {
            visitationHours[i] = daysAndDurs.get(i);
        }
        return visitationHours;
    }
     
   
   /*
    *   1. Converts the scheduled calls to 'visitation-hour-time'
    *   2. Compares each scheduled call with the ArrayList of available calls (which begins as a duplicate of visitation hours)
    *   3. Removes all scheduled calls from the ArrayList of available calls
    *      At this point we have an ArrayList of available calls in 'visitation-hour-time'
    *   4. Converts the ArrayList of available calls in 'v-h-t' to an array of available calls in epoch time
    *   5. Returns that array
    *   
    *   Param OneAvailability[] visitationHours, OneCall[] scheduledCalls
    *   Return OneCall[] of available calls converted to epoch
    */
    public OneCall[] visitationHoursToOpenTimeSlots(OneAvailability[] visitationHours, OneCall[] scheduledCalls) {
        
        for (int i = 0; i < scheduledCalls.length; i++) {
            Date currentStart = new Date(scheduledCalls[i].getStart());            
            LocalDateTime dateStart = currentStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            
            Date currentEnd = new Date(scheduledCalls[i].getEnd());
            LocalDateTime date = currentEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            
            long duration = scheduledCalls[i].getEnd() - scheduledCalls[i].getStart();  //difference in epoch time (milliseconds) 
            int durationInt = Math.toIntExact(duration);
            
            DayOfWeek day = dateStart.getDayOfWeek();
            int dayOfWeek = day.getValue();
            if (dayOfWeek == 7) { //  Monday is 1. Sunday is 7. According to getValue()
                dayOfWeek = 0;
            }
            int hour = dateStart.getHour();
            int minute = dateStart.getMinute();
            
            OneDay tempDay = new OneDay(dayOfWeek, hour, minute);
            OneAvailability tempDayDur = new OneAvailability(tempDay, durationInt, scheduledCalls[i].getStart(), scheduledCalls[i].getEnd());
            checkAndRemove(tempDayDur);
        }
        
        OneCall[] epochCalls = new OneCall[availableDaysDurs.size()];
        for (int i = 0; i < availableDaysDurs.size(); i++) {
            epochCalls[i] = new OneCall(availableDaysDurs.get(i).getEpochStart(), availableDaysDurs.get(i).getEpochEnd());
        }
        return epochCalls;
        
    }


    
}
