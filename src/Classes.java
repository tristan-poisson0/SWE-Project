import java.io.IOException;

import javafx.scene.control.*;
public class Classes {
	private String className;
	private String classCode;
	private Button button;
	
	public Classes(String className, String classCode) {
		this.className = className;
		this.classCode = classCode;
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
	public void setButton(Button button) {
		this.button=button;
	}
	public Button getButton() {
		return button;
	}

}
