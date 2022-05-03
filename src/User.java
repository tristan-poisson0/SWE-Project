
public class User {

	private String assignmentName;
	private String className;
	private String description;
	private String dueDate;
	
	public User(String assignmentName, String className, String description, String dueDate) {
		this.assignmentName=assignmentName;
		this.className=className;
		this.description=description;
		this.dueDate=dueDate;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public String getClassName() {
		return className;
	}
	public String getDescription() {
		return description;
	}
	public String getDueDate() {
		return dueDate;
	}
}
