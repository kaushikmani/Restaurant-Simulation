/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE6431;

/**
 *
 * @author kaushikmani
 */
public class Utils {
    
    //Getting the time in the required format (HH:MM)
    //Assume 1 minute = 100ms
    public String getTime(){
        long currentTime = System.currentTimeMillis();
        //Converting the time to seconds
        long timeSpent = (currentTime - Restaurant.startTime)/100;
        
        //Initialzing variables for formatted time string
        String formattedTimeString, hourString, minuteString;
        long minutes, hours = 0;
        
        if(timeSpent >= 60){
             hours = timeSpent/60;
        }
        minutes = timeSpent % 60;
        
        hourString = String.format("%02d", hours);
        minuteString = String.format("%02d", minutes);
        
        formattedTimeString = hourString + ":" + minuteString;
        
        return formattedTimeString;
    }
    
}
