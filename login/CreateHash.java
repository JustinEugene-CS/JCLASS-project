package login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class CreateHash {
	public static String getSecurePassword(String passwordToHash, String salt) 
			throws NoSuchAlgorithmException, NoSuchProviderException
	{
		String hashedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(salt.getBytes());
			
			byte[] bytes = md.digest(passwordToHash.getBytes());
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100, 16).substring(1));
			}
			
			hashedPassword = sb.toString();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hashedPassword;
	}
	
	public static String [] getSecurePassword(String passwordToHash) 
			throws NoSuchAlgorithmException, NoSuchProviderException
	{
		String hashedPassword = null;
		String salt = getSalt();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(salt.getBytes());
			
			byte[] bytes = md.digest(passwordToHash.getBytes());
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100, 16).substring(1));
			}
			
			hashedPassword = sb.toString();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String [] passwordInfo = {hashedPassword, salt};
		return passwordInfo;
	}
	
	public static String getSalt() 
			throws NoSuchAlgorithmException, NoSuchProviderException 
	{
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		
		byte[] salt = new byte[16];
		
		sr.nextBytes(salt);
		
		return salt.toString();
	}
}
