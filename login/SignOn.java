package login;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.security.NoSuchProviderException;
import java.security.NoSuchAlgorithmException;

public class SignOn {
	public static void test() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/database/java-trainer.db");
			Statement statement = conn.createStatement();
			ResultSet test = statement.executeQuery("SELECT * FROM user_login_data");
			while(test.next()) {
				int id = test.getInt(1);
				String name = test.getString(2);
				String hash = test.getString(3);
				String salt = test.getString(4);
				System.out.println(name + " " + hash + " " + salt);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void add_user(String username, String password) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/database/java-trainer.db");
			Statement statement = conn.createStatement();
			try {
				String[] hashed_password_plus_salt = CreateHash.getSecurePassword(password);
				System.out.println("Hashed Password: " + hashed_password_plus_salt[0]);
				System.out.println("Salt: " + hashed_password_plus_salt[1]);
				statement.executeUpdate("INSERT INTO user_login_data (LoginName, PasswordHash, PasswordSalt) "
						+ "VALUES ('" + username + "', '" + hashed_password_plus_salt[0] + "', '" 
						+ hashed_password_plus_salt[1] + "');");
			} catch(NoSuchProviderException | NoSuchAlgorithmException e) {
				System.out.println(e);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
	}

}
