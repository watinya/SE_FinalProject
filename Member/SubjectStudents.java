package Member;
//存放課程學生:學號,名稱,分數
public class SubjectStudents {
	private String id;
	private String name;
	private String score;
	public SubjectStudents(String id, String name, String score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getScore() {
		return score;
	}
}//end class
