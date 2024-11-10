package recommend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetExercises {
	public static ArrayList<Exercise> get_exercises(String type, String body_part, String level) {
		try { 
			Connection conn = DriverManager.getConnection("jdbc:sqlite:database/java-trainer.db");
			Statement statement = conn.createStatement();
			ResultSet result =  statement.executeQuery("SELECT * FROM workout WHERE type = '" + type 
					                                   + "' AND body_part = '" + body_part + "' AND level = '" + level + "'");
			ArrayList<Exercise> my_exercises = new ArrayList<Exercise>();
			if(result.isBeforeFirst()) {
				while(result.next()) {
					my_excercises.add(new Exercise(result.getString("title"),
													result.getString("desc"),
													result.getString("type"),
													result.getString("body_part"),
													result.getString("equipment"),
													result.getString("level"),
													result.getFloat("rating"),
								                                        0));
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
