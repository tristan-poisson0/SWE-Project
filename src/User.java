import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.*;
public class User {

	private String assignmentName;
	private String className;
	private String description;
	private String dueDate;
	private Button button;
	private Button button2;
	
	public User(String assignmentName, String className, String description, String dueDate) {
		this.assignmentName=assignmentName;
		this.className=className;
		this.description=description;
		this.dueDate=dueDate;
		this.button=new Button("View Class");
		this.button2=new Button("View bitch");
		SceneController a=new SceneController();
		button.setOnAction(e->{
			try {
				a.handleViewClass(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		/*
		button2.setOnAction(e->{
			try {
	            String sql="select * from \"assignments\"";
	            ResultSet rs=Connect.st.executeQuery(sql);
				if (className.toString().equals(rs.getString("class_name"))) {
					a.handleViewClass(e);
				}
			} catch (IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		*/

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
	public void setButton(Button button) {
		this.button=button;
	}
	public Button getButton() {
		return button;
	}
	/*
	public void setButton2(Button button2) {
		this.button2=button2;
	}
	public Button getButton2() {
		return button2;
	}
	*/

}
