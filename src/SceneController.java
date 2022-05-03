import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class SceneController implements Initializable{
	@FXML
	private TableView<User>table_users;
	@FXML
	private TableColumn<User,String>col_assignment;
	@FXML
	private TableColumn<User,String>col_class;
	@FXML
	private TableColumn<User,String>col_description;
	@FXML
	private TableColumn<User,Date>col_duedate;
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private TextField assignmentName;
	@FXML
	private TextField assignmentDueDate;
	@FXML
	private TextField assignmentClassName;
	@FXML
	private TextField assignmentList;

	public static ObservableList<User> getData(){
		Connect con= new Connect();
		ObservableList<User>list=FXCollections.observableArrayList();
		String user = UserGUI.getUsername();
		try {
			String sql="select * from \"assignments\"";
			ResultSet rs=Connect.st.executeQuery(sql);
			
			while(rs.next()) {
				if (user.equals(rs.getString("username"))) {
					list.add(new User(rs.getString("assign_name"), rs.getString("class_name"), rs.getString("assign_desc"), rs.getString("assign_due")));
				}
			}
		}catch(SQLException sqle) {
			System.err.println(sqle);
		}
		return list;
		
	}
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		col_assignment.setCellValueFactory(new PropertyValueFactory<User, String>("assignmentName"));
		col_class.setCellValueFactory(new PropertyValueFactory<User, String>("className"));
		col_description.setCellValueFactory(new PropertyValueFactory<User, String>("description"));
		col_duedate.setCellValueFactory(new PropertyValueFactory<User, Date>("dueDate"));
		
		table_users.setItems(getData());
	}
	@FXML
	public void handleHome(ActionEvent event)throws IOException{
		root=FXMLLoader.load(getClass().getResource("HWPhomePageHome.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
}
	@FXML
	public void handleAssignment(ActionEvent event)throws IOException{
			root=FXMLLoader.load(getClass().getResource("HWPhomePageAssignments.fxml"));
			stage=(Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
	}
	@FXML
	public void handleClasses(ActionEvent event)throws IOException{
		root=FXMLLoader.load(getClass().getResource("HWPhomePageClasses.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
}
	@FXML
	public void handleGroups(ActionEvent event)throws IOException{
		root=FXMLLoader.load(getClass().getResource("HWPhomePageGroups.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
}
	@FXML
	public void handleCalendar(ActionEvent event)throws IOException{
		root=FXMLLoader.load(getClass().getResource("HWPhomePageCalendar.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
}
	@FXML
	public void handleNewAssignment(ActionEvent event)throws IOException{
		root=FXMLLoader.load(getClass().getResource("HWPnewAssignment.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void handleNewClass(ActionEvent event)throws IOException{
		root=FXMLLoader.load(getClass().getResource("HWPnewClass.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void handleNew(ActionEvent event)throws IOException{
		root=FXMLLoader.load(getClass().getResource("HWPnewPage.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void handleCreateNewAssignment(ActionEvent event)throws IOException{
        Connect con=new Connect();
        String name=assignmentName.getText();
        String date=assignmentDueDate.getText();
        String className=assignmentClassName.getText();
        System.out.println(name);
        System.out.println(date);
        System.out.println(className);
        String username = UserGUI.getUsername();
        root=FXMLLoader.load(getClass().getResource("HWPassignmentCreated.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

        try {
            String sql="select * from \"assignments\"";
            ResultSet rs=Connect.st.executeQuery(sql);

            sql="insert into \"assignments\"(\"username\", \"assign_name\", \"assign_due\", \"class_name\")values('"+username+"','"+name+"','"+date+"','"+className+"')";
            int i=Connect.st.executeUpdate(sql);
            if(i>0) {
                System.out.println("Successful Insert");
            }
        }catch(SQLException sqle) {
            System.err.println(sqle);
        }
    }

}