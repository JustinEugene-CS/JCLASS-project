package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import login.CurrentUserSession;

import java.sql.PreparedStatement;

public class StoreUserInfo {
	public static boolean store_user_info(int age, int height, int weight, String goals, int frequency, String level) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:database/java-trainer.db");
			String insert_statement = "INSERT INTO user_personal_data (UserID, Age, Weight, Height, Goals, Freq, Level) " +
									  "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement filled_insert_statement = conn.prepareStatement(insert_statement);
			filled_insert_statement.setInt(1, CurrentUserSession.getCurrentSession().getCurrentUser().getID());
			filled_insert_statement.setInt(2, age);
			filled_insert_statement.setInt(3, height);
			filled_insert_statement.setInt(4, weight);
			filled_insert_statement.setString(5, goals);
			filled_insert_statement.setInt(6, frequency);
			filled_insert_statement.setString(7, level);
			filled_insert_statement.executeUpdate();
			return true;
		} catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
}
