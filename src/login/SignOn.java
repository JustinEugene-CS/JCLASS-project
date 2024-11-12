package login;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.security.NoSuchProviderException;
import java.security.NoSuchAlgorithmException;
import recommend.Exercise;
import java.util.ArrayList;

public class SignOn {
	public static boolean add_user(String username, String password) {
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/java-trainer.db")){	
			String get_user_data = "SELECT * FROM user_login_data WHERE LoginName = ?";
			
			try(PreparedStatement query = conn.prepareStatement(get_user_data)){
				query.setString(1, username);
				try(ResultSet getUserInfo = query.executeQuery()){
					// Returns false if the user name is already in the database
					if(getUserInfo.next()) {
						System.out.println("Username taken.");
						return false;
					}
					
					try {
						String[] hashed_password_plus_salt = CreateHash.getSecurePassword(password);
						String inserts = "INSERT INTO user_login_data (LoginName, PasswordHash, PasswordSalt)"
								       + "VALUES (?, ?, ?)";
						
						try(PreparedStatement insert = conn.prepareStatement(inserts)){
							insert.setString(1, username);
							insert.setString(2, hashed_password_plus_salt[0]);
							insert.setString(3, hashed_password_plus_salt[1]);
							insert.executeUpdate();
							return true;
						}
					} catch (NoSuchProviderException | NoSuchAlgorithmException e) {
						System.out.println(e);
						return false;
					}
				}
				
			}
		} catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean existing_user_login(String username, String password) {
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database/java-trainer.db")){
			String get_user_data = "SELECT * FROM user_login_data WHERE LoginName = ?";
			
			try(PreparedStatement query = conn.prepareStatement(get_user_data)){
				query.setString(1, username);
				
				try(ResultSet getUserInfo = query.executeQuery()){
					if(!getUserInfo.next()) {
						System.out.println("Username does not exists.");
						return false;
					}
					
					int UserID = getUserInfo.getInt("UserID");
					String database_password = getUserInfo.getString("PasswordHash");
					String salt = getUserInfo.getString("PasswordSalt");
					
					String hashed_password = CreateHash.getSecurePassword(password, salt);
					if(hashed_password.equals(database_password)) {
						setCurrentUser(username, UserID, conn);
						System.out.println(CurrentUserSession.getCurrentSession().getCurrentUser());
						return true;
					} else {
						return false;
					}
				} catch(NoSuchProviderException | NoSuchAlgorithmException e) {
					System.out.println(e);
					return false;
				}
			} 
		} catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	private static void setCurrentUser(String username, int UserID, Connection conn) {
		String get_user_data = "SELECT age, height, weight, goals, freq, level FROM user_personal_data WHERE UserID = ?";
		String get_previous_exercises = "SELECT w.*, pw.weight FROM workout w " + 
										"JOIN previous_workouts pw ON pw.workoutid=w.id " +
										"WHERE pw.UserID = ?";
		int userAge = 0;
		int userHeight = 0;
		int userWeight = 0;
		String userGoals = null;
		int userFrequency = 0;
		String userLevel = null;
		ArrayList<Exercise> previous_excercises = new ArrayList<Exercise>();
		
		try (PreparedStatement user_query = conn.prepareStatement(get_user_data)){
			user_query.setInt(1, UserID);
			try(ResultSet getUserPersonalInfo = user_query.executeQuery()){
				if(getUserPersonalInfo.isBeforeFirst()) {
					userAge = getUserPersonalInfo.getInt(1);
					userHeight = getUserPersonalInfo.getInt(2);
					userWeight = getUserPersonalInfo.getInt(3);
					userGoals = getUserPersonalInfo.getString(4);
					userFrequency = getUserPersonalInfo.getInt(5);
					userLevel = getUserPersonalInfo.getString(6);
					
				}
			}
		} catch(SQLException e) {
			System.out.println("Error retrieving personal data: " + e);
		}
			
		try(PreparedStatement workout_query = conn.prepareStatement(get_previous_exercises)){
			workout_query.setInt(1, UserID);
			try(ResultSet getPreviousWorkout = workout_query.executeQuery()){
				if(getPreviousWorkout.isBeforeFirst()) {
					while(getPreviousWorkout.next()) {
							previous_excercises.add(new Exercise(getPreviousWorkout.getString("title"),
							getPreviousWorkout.getString("desc"),
							getPreviousWorkout.getString("type"),
							getPreviousWorkout.getString("body_part"),
							getPreviousWorkout.getString("equipment"),
							getPreviousWorkout.getString("level"),
							getPreviousWorkout.getFloat("rating"),
							getPreviousWorkout.getInt("weight")));
					}
				} 
			}
		} catch(SQLException e) {
			System.out.println("Error retrieving workout data: " + e);
		}
		
		User current_user = new User(username,
									 UserID,
									 userAge,
									 userHeight,
									 userWeight,
									 userGoals,
									 userFrequency,
									 userLevel,
									 previous_excercises);
		CurrentUserSession.getCurrentSession().setCurrentUser(current_user);
		}
}
