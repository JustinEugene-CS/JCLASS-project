package recommend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import login.CurrentUserSession;

public class StoreExercises {
	public static boolean store_exercise(int workout_id, int weight) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:database/java-trainer.db");
			String insert_statement = "INSERT INTO previous_workouts (UserID, WorkoutID, Weight) " +
									  "VALUES (?, ?, ?)";
			PreparedStatement filled_insert_statement = conn.prepareStatement(insert_statement);
			filled_insert_statement.setInt(1, CurrentUserSession.getCurrentSession().getCurrentUser().getID());
			filled_insert_statement.setInt(2, workout_id);
			filled_insert_statement.setInt(3, weight);
			filled_insert_statement.executeUpdate();
			return true;
		} catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
}
