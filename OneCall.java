/*
 * The OneCall class tracks the scheduled calls, in epoch time.
 * 
 * 
 * 
 * @author Madeleine Song
 * Ameelio Software Engineering Internship Challenge
 * 4 April 2021
 */


public class OneCall {
    // so, this class keeps track of ONLY the start and end times of the SCHEDULED slots 

    //  in epoch time
    long start;
    long end;
    
    
    //  constructor
    public OneCall() {
        start = 0; 
        end = 0;
    } 
    
    //  constructor
    public OneCall(long s, long e) {
        start = s;
        end = e;
    }

    //  constructor
    public long getStart() {
        return start;
    }

    //  constructor
    public void setStart(long start) {
        this.start = start;
    }

    //  constructor
    public long getEnd() {
        return end;
    }

    //  constructor
    public void setEnd(long end) {
        this.end = end;
    }
    
}
