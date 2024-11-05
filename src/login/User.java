package login;

import java.util.ArrayList;
import recommend.Excercise;

public class User {
	private String username;
	private int age;
	private int height;
	private int weight;
	
	ArrayList<Excercise> previous_excercises;
	
	public User(String u, int a, int h, int w, ArrayList<Excercise> p_e) {
		username = u;
		age = a;
		height = h;
		weight = w;
		previous_excercises = p_e;
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
	
	public ArrayList<Excercise> getPreviousExcercises(){
		return previous_excercises;
	}
}
