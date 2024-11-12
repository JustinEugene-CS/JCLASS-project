package recommend;

import login.User;
import java.util.ArrayList;

public class WorkoutPlan {
    private User user;
    private ArrayList<Exercise>[] week;
    private int duration;
    private int curWeek; //
    private String[] Name; //name of the workout, like Push, Pull, Cardio, 

    public WorkoutPlan(User user, ArrayList<Exercise>[] exercises, int duration) {
        this.user = user; //the specific user
        this.week = exercises; //all the exercises for that given week
        this.duration = duration; //shows how many times you repeat the week
        curWeek = 0;
        //week
        //duration
        //reps, sets, and rests
    }
    
    public ArrayList<Exercise> getWeek(int i) {
    	return week[i];
    }
    
    public int getDuration() {
    	return duration;
    }
    
    public String getName(int i) {
    	return Name[i]; 
    }
    
    public int getCur() {
    	return curWeek;
    }
    
    public void incCur() {
    	curWeek++;
    }
}
