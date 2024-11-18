package login;

import java.util.ArrayList;
import recommend.Exercise;

public class User {
	private String username;
	private int user_id;
	private int age;
	private int height;
	private int weight;
	private String goals;
	private int frequency;
	private String level;
	
	ArrayList<Exercise> previous_exercises;
	
	public User(String u, int id, int a, int h, int w, String g, int f, String l, ArrayList<Exercise> p_e) {
		username = u;
		user_id = id;
		age = a;
		height = h;
		weight = w;
		goals  = g;
		frequency = f;
		level = l;
		previous_exercises = p_e;
	}
	
	public void setUsername(String u) {
		username = u;
	}
	
	public void setID(int id) {
		user_id = id;
	}
	
	public void setAge(int a) {
		age = a;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public void setWeight(int w) {
		weight = w;
	}
	
	public void setGoals(String g) {
		goals = g;
	}
	
	public void setFrequency(int f) {
		frequency = f;
	}
	
	public void setLevel(String l) {
		level = l;
	}
	
	public void setPreviousExercises(ArrayList<Exercise> p_e) {
		previous_exercises = p_e;
	}
	
	public int getID() {
		return user_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public ArrayList<Exercise> getPreviousExcercises(){
		return previous_exercises;
	}
	
	public String getGoals() {
		return goals;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public String getLevel() {
		return level;
	}
	
	public String toString() {
		String statement = "Username: " + username + " Age: " + age + " Height: " + height + " Weight: " + weight + " Level: " + level;
		for(Exercise current : previous_exercises) {
			statement += " Title: " + current.get_title();
		}
		return statement;
	}
}