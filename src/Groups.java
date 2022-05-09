import java.io.IOException;

import javafx.scene.control.*;
public class Groups {
	private String className;
	private String classCode;
	private String groupName;
	private Button button;
	
	public Groups(String className, String classCode, String groupName) {
		this.className = className;
		this.classCode = classCode;
		this.groupName= groupName;
		this.button=new Button("View Class");
		SceneController a=new SceneController();
		button.setOnAction(e->{
			try {
				a.handleViewClass(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	public String getClassName() {
		return className;
	}
	public String getClassCode() {
		return className;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setButton(Button button) {
		this.button=button;
	}
	public Button getButton() {
		return button;
	}
}
