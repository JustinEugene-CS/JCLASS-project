package login;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.security.NoSuchProviderException;
import java.security.NoSuchAlgorithmException;
import recommend.Excercise;
import java.util.ArrayList;

public class SignOn {
	public static boolean add_user(String username, String password) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:database/java-trainer.db");
			Statement statement = conn.createStatement();
			ResultSet getUserInfo = statement.executeQuery("SELECT * FROM user_login_data " + 
			                                               "WHERE LoginName = '" + username + "';");
			
			// Returns false if the user name is already in the database
			if(getUserInfo.next() != false) {
				System.out.println("Username taken.");
				return false;
			}
			try {
				// Hashes the user's password
				String[] hashed_password_plus_salt = CreateHash.getSecurePassword(password);
				// Stores hashed password to database
				statement.executeUpdate("INSERT INTO user_login_data (LoginName, PasswordHash, PasswordSalt) "
						+ "VALUES ('" + username + "', '" + hashed_password_plus_salt[0] + "', '" 
						+ hashed_password_plus_salt[1] + "');");
				return true;
			} catch(NoSuchProviderException | NoSuchAlgorithmException e) {
				System.out.println(e);
				return false;
			} finally {
				if(getUserInfo != null) {
					getUserInfo.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
		} catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean existing_user_login(String username, String password) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:database/java-trainer.db");
			Statement statement = conn.createStatement();
			ResultSet getUserInfo = statement.executeQuery("SELECT * FROM user_login_data " + 
														   "WHERE LoginName = '" + username + "'");
			if(!getUserInfo.isBeforeFirst()) {
				System.out.println("Username does not exists.");
				return false;
			}
			int UserID = getUserInfo.getInt("UserID");
			String database_password = getUserInfo.getString("PasswordHash");
			String salt = getUserInfo.getString("PasswordSalt");
			try {
				String hashed_password = CreateHash.getSecurePassword(password, salt);
				if(hashed_password.equals(database_password)) {
					setCurrentUser(username, UserID, statement);
					System.out.println(CurrentUserSession.getCurrentSession().getCurrentUser());
					return true;
				} else{
					return false;
				}
			} catch(NoSuchProviderException | NoSuchAlgorithmException e) {
				System.out.println(e);
				return false;
			} finally {
				if(getUserInfo != null) {
					getUserInfo.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
		
		} catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	private static void setCurrentUser(String username, int UserID, Statement statement) {
		try {
			ResultSet getUserPersonalInfo = statement.executeQuery("SELECT age, height, weight FROM user_personal_data " +
			                 									   "WHERE UserID = " + UserID);
			int userAge = 0;
			int userHeight = 0;
			int userWeight = 0;
			if(getUserPersonalInfo.isBeforeFirst()) {
				userAge = getUserPersonalInfo.getInt(1);
				userHeight = getUserPersonalInfo.getInt(2);
				userWeight = getUserPersonalInfo.getInt(3);
			}
			ResultSet getUserPreviousExcercises = statement.executeQuery("SELECT w.*, pw.weight FROM workout w " + 
																		 "JOIN previous_workouts pw ON pw.workoutid=w.id " +
																		 "WHERE pw.UserID = " + UserID);
			ArrayList<Excercise> previous_excercises = new ArrayList<Excercise>();
			if(getUserPreviousExcercises.isBeforeFirst()) {
				while(getUserPreviousExcercises.next()) {
				previous_excercises.add(new Excercise(getUserPreviousExcercises.getString("title"),
													  getUserPreviousExcercises.getString("desc"),
													  getUserPreviousExcercises.getString("type"),
													  getUserPreviousExcercises.getString("body_part"),
													  getUserPreviousExcercises.getString("equipment"),
													  getUserPreviousExcercises.getString("level"),
													  getUserPreviousExcercises.getFloat("rating"),
													  getUserPreviousExcercises.getInt("weight")));
				}
			}
			User current_user = new User(username, userAge, userHeight, userWeight, previous_excercises);
			CurrentUserSession.getCurrentSession().setCurrentUser(current_user);
		} catch(SQLException e) {
			System.out.println("Problem creating current user: " + e);
		}
	}
}
