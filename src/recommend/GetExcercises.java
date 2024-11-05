package recommend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetExcercises {
<<<<<<< HEAD
	public static void main(String args[]) {
		ArrayList<Excercise> my_excercises = get_excercises("Strength", "Chest", "Beginner");
		for(Excercise e : my_excercises) {
			System.out.println(e.body_part);
		}
	}
	
=======
>>>>>>> 1d4f3531d13fc6fcb5a09401a83e3b3cb9ff7871
	public static ArrayList<Excercise> get_excercises(String type, String body_part, String level) {
		try { 
			Connection conn = DriverManager.getConnection("jdbc:sqlite:database/java-trainer.db");
			Statement statement = conn.createStatement();
			ResultSet result =  statement.executeQuery("SELECT * FROM workout WHERE type = '" + type 
					                                   + "' AND body_part = '" + body_part + "' AND level = '" + level + "'");
			ArrayList<Excercise> my_excercises = new ArrayList<Excercise>();
			if(result.isBeforeFirst()) {
				while(result.next()) {
					my_excercises.add(new Excercise(result.getString("title"),
													result.getString("desc"),
													result.getString("type"),
													result.getString("body_part"),
													result.getString("equipment"),
													result.getString("level"),
													result.getFloat("rating")));
				}
			}
			if(result != null) {
				result.close();
			} 
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
			return my_excercises;
			
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
}
