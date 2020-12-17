
public class currentUser {
	public currentUser() {
	}
	private String id ;
	private String pw ;
	private String name;
	public currentUser(String id, char[] pw, String name) {
		this.id = id;
		this.pw = new String(pw);
		this.name = name;
	}
	String getId() {
		return id;
	}
	String getPassword() {
		return pw;
	}
	String getName() {
		return name;
	}
}
