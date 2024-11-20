package login;

public class CurrentUserSession {
	private static CurrentUserSession instance;
	private User current_user;
	
	private CurrentUserSession() {};
	
	public static CurrentUserSession getCurrentSession() {
		if(instance == null) {
			instance = new CurrentUserSession();
		}
		return instance;
	}
	
	public void setCurrentUser(User c_u) {
		current_user = c_u;
	}
	
	public User getCurrentUser() {
		return current_user;
	}
}
