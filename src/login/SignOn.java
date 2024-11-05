package login;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.security.NoSuchProviderException;
import java.security.NoSuchAlgorithmException;

public class SignOn {
	public static boolean add_user(String username, String password) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:database/java-trainer.db");
			Statement statement = conn.createStatement();
			ResultSet getUserInfo = statement.executeQuery("SELECT * FROM user_login_data " + 
			                                               "WHERE LoginName = '" + username + "';");
			if(getUserInfo.next() != false) {
				System.out.println("Username taken.");
				return false;
			}
			try {
				String[] hashed_password_plus_salt = CreateHash.getSecurePassword(password);
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
			if(getUserInfo.next() == false) {
				System.out.println("Username does not exists.");
				return false;
			}
			String database_password = getUserInfo.getString("PasswordHash");
			String salt = getUserInfo.getString("PasswordSalt");
			try {
				String hashed_password = CreateHash.getSecurePassword(password, salt);
				if(hashed_password.equals(database_password)) {
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
}
