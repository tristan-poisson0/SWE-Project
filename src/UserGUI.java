import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserGUI extends Application {
	
	private TextField firstName = new TextField();
	private TextField lastName = new TextField();
	private static TextField userName = new TextField();
	private TextField passWord = new TextField();
	private TextField passWord2 = new TextField();
	private TextField emailAddress = new TextField();
	private Button conti=new Button("Continue");
	private Button createAccount = new Button("Create New Account");
	private Button login = new Button("Log in");
	private Button createNew= new Button("New User? Create an Account!");
	
	public static String getUsername() {
		return userName.getText();
	}
	
	@Override //Override the start method in the Application class
	public void start(Stage primaryStage) {
		
		GridPane gridPane0 = new GridPane();
		gridPane0.setHgap(5);
		gridPane0.setVgap(5);
		gridPane0.add(conti, 0, 3);
		gridPane0.setAlignment(Pos.CENTER);
		gridPane0.add(new Label("Welcome to Homework Planner!"), 0, 0);
		gridPane0.add(new Label("Created By:"), 0, 1);
		gridPane0.add(new Label("Jeffrey Hernandez, Tristan Poisson, DeJonte July"), 0, 2);
		  
		  //process event 
		  conti.setOnAction(e -> logIn(primaryStage));
		  
		// Create a scene and place it in the stage
	       Scene scene = new Scene(gridPane0, 400, 250);
		   primaryStage.setTitle("Homework Planner"); // Set title
		   primaryStage.setScene(scene); // Place the scene in the stage
		   primaryStage.show(); // Display the stage
		  }
		
			
	public void logIn(Stage stage) {
		Stage logIn=new Stage();
		stage.close();
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("User Name:"), 0, 2);
		gridPane.add(userName, 1, 2);
		gridPane.add(new Label("Password:"), 0, 3);
		gridPane.add(passWord, 1, 3);
		gridPane.add(login, 0, 5);
		gridPane.add(createNew, 0, 6);
		
		  //Set properties for UI
		  gridPane.setAlignment(Pos.CENTER);
		  firstName.setAlignment(Pos.BOTTOM_LEFT);
		  lastName.setAlignment(Pos.BOTTOM_LEFT);
		  userName.setAlignment(Pos.BOTTOM_LEFT);
		  passWord.setAlignment(Pos.BOTTOM_LEFT);
		  emailAddress.setAlignment(Pos.BOTTOM_LEFT);
		  
		  //process event 
		  login.setOnAction(d -> { 
			  if (userName.getText().isEmpty() || passWord.getText().isEmpty()) {
				  loginErrorMessage(logIn);
			  } 
			  else {
				  accountChecker(logIn, userName, passWord);
			  }
		  });
		  
		  createNew.setOnAction(e ->createNewAccount(logIn));
		  
		// Create a scene and place it in the stage
	       Scene scene = new Scene(gridPane, 400, 250);
		   logIn.setTitle("Log In"); // Set title
		   logIn.setScene(scene); // Place the scene in the stage
		   logIn.show(); // Display the stage
	}
	
	public void loginErrorMessage (Stage stage) {
		Stage logInErr=new Stage();
		stage.close();
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("**Error: one or more fields not filled**"), 0, 1);
		gridPane.add(new Label("User Name:"), 0, 2);
		gridPane.add(userName, 1, 2);
		gridPane.add(new Label("Password:"), 0, 3);
		gridPane.add(passWord, 1, 3);
		gridPane.add(login, 0, 5);
		gridPane.add(createNew, 0, 6);
		
		  //Set properties for UI
		  gridPane.setAlignment(Pos.CENTER);
		  firstName.setAlignment(Pos.BOTTOM_LEFT);
		  lastName.setAlignment(Pos.BOTTOM_LEFT);
		  userName.setAlignment(Pos.BOTTOM_LEFT);
		  passWord.setAlignment(Pos.BOTTOM_LEFT);
		  emailAddress.setAlignment(Pos.BOTTOM_LEFT);
		  		 
		  //process event 
		 login.setOnAction(d -> { 
			  if (userName.getText().isEmpty() || passWord.getText().isEmpty()) {
				  loginErrorMessage(logInErr);
			  } 
			  else {
				  accountChecker(logInErr, userName, passWord);
			  }
		  });

		  createNew.setOnAction(e ->createNewAccount(logInErr));

		  
		   //Create a scene and place it in the stage
	       Scene scene = new Scene(gridPane, 400, 250);
		   logInErr.setTitle("Log In"); // Set title
		   logInErr.setScene(scene); // Place the scene in the stage
		   logInErr.show(); // Display the stage
	}

	
	public void accountChecker(Stage stage, TextField user, TextField pass) {
		Stage accountCheck = new Stage();
		String userN=user.getText();
		String passW=PasswordHashing.hash(pass.getText());
		System.out.println(userN);
		try {
			String sql="select * from \"users\"";
			ResultSet rs=Connect.st.executeQuery(sql);
			while(rs.next()) {
				String Username=rs.getString("username");
				String Password=rs.getString("password");

				if(userN.equalsIgnoreCase(Username)) {
					System.out.println("Found it");
					if(passW.equalsIgnoreCase(Password)) {
						System.out.println("Password matches");
						stage.close();
						Stage welcome=new Stage();
						String str=userName.getText();
						
						GridPane gridPane2=new GridPane();
						gridPane2.setAlignment(Pos.CENTER);
						Scene scene2=new Scene(gridPane2, 400, 250);
						gridPane2.setHgap(5);
						gridPane2.setVgap(5);
						gridPane2.add(new Label("Welcome "+str), 0, 0);
						gridPane2.add(conti, 0, 1);
						
						conti.setOnAction(e->homePage(welcome));
						
						welcome.setTitle("Welcome! "+str);
						welcome.setScene(scene2);
						welcome.show();
						break;
					}
					else if (!passW.equalsIgnoreCase(Password)) {passwordDNE(accountCheck); stage.close(); break;}
				}
			} if (!userN.equalsIgnoreCase(rs.getString("username")) && rs.next()==false) {usernameDNE(accountCheck); stage.close();}
			
		} catch(SQLException sqle) {
			System.err.println(sqle);
		}
	}
	
	public void usernameDNE (Stage stage) {
		Stage accDNE=new Stage();
		stage.close();
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("**Error: Username incorrect**"), 0, 1);
		gridPane.add(new Label("User Name:"), 0, 2);
		gridPane.add(userName, 1, 2);
		gridPane.add(new Label("Password:"), 0, 3);
		gridPane.add(passWord, 1, 3);
		gridPane.add(login, 0, 5);
		gridPane.add(createNew, 0, 6);
		
		  //Set properties for UI
		  gridPane.setAlignment(Pos.CENTER);
		  firstName.setAlignment(Pos.BOTTOM_LEFT);
		  lastName.setAlignment(Pos.BOTTOM_LEFT);
		  userName.setAlignment(Pos.BOTTOM_LEFT);
		  passWord.setAlignment(Pos.BOTTOM_LEFT);
		  emailAddress.setAlignment(Pos.BOTTOM_LEFT);
		  		 
		  //process event 
		 login.setOnAction(d -> { 
			  if (userName.getText().isEmpty() || passWord.getText().isEmpty()) {
				  loginErrorMessage(accDNE);
			  } 
			  else {
				  accountChecker(accDNE, userName, passWord);
			  }
		  });

		  createNew.setOnAction(e ->createNewAccount(accDNE));

		  
		   //Create a scene and place it in the stage
	       Scene scene = new Scene(gridPane, 400, 250);
	       accDNE.setTitle("Log In"); // Set title
	       accDNE.setScene(scene); // Place the scene in the stage
	       accDNE.show(); // Display the stage
	}
	
	public void passwordDNE (Stage stage) {
		Stage passDNE=new Stage();
		stage.close();
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.add(new Label("**Error: Password incorrect**"), 0, 1);
		gridPane.add(new Label("User Name:"), 0, 2);
		gridPane.add(userName, 1, 2);
		gridPane.add(new Label("Password:"), 0, 3);
		gridPane.add(passWord, 1, 3);
		gridPane.add(login, 0, 5);
		gridPane.add(createNew, 0, 6);
		
		  //Set properties for UI
		  gridPane.setAlignment(Pos.CENTER);
		  firstName.setAlignment(Pos.BOTTOM_LEFT);
		  lastName.setAlignment(Pos.BOTTOM_LEFT);
		  userName.setAlignment(Pos.BOTTOM_LEFT);
		  passWord.setAlignment(Pos.BOTTOM_LEFT);
		  emailAddress.setAlignment(Pos.BOTTOM_LEFT);
		  		 
		  //process event 
		 login.setOnAction(d -> { 
			  if (userName.getText().isEmpty() || passWord.getText().isEmpty()) {
				  loginErrorMessage(passDNE);
			  } 
			  else {
				  accountChecker(passDNE, userName, passWord);
			  }
		  });

		  createNew.setOnAction(e ->createNewAccount(passDNE));

		  
		   //Create a scene and place it in the stage
	       Scene scene = new Scene(gridPane, 400, 250);
	       passDNE.setTitle("Log In"); // Set title
	       passDNE.setScene(scene); // Place the scene in the stage
	       passDNE.show(); // Display the stage
	}


			
	public void homePage(Stage stage) {
		Stage home=new Stage();
		stage.close();
		try {
			Parent root=FXMLLoader.load(getClass().getResource("HWPhomePageHome.fxml"));
			Scene scene= new Scene(root);
			home.setScene(scene);
			home.show();
		}catch(Exception e) {e.printStackTrace();}
	}
	public void createNewAccount(Stage stage) {
				Stage createNew=new Stage();
				stage.close();
				
				GridPane gridPane3=new GridPane();
				gridPane3.setAlignment(Pos.CENTER);
				Scene scene3=new Scene(gridPane3, 400, 250);
				gridPane3.setHgap(5);
				gridPane3.setVgap(5);
				gridPane3.add(new Label("First Name:"), 0, 0);
				gridPane3.add(firstName, 1, 0);
				gridPane3.add(new Label("Last Name:"), 0, 1);
				gridPane3.add(lastName, 1, 1);
				gridPane3.add(new Label("User Name:"), 0, 2);
				gridPane3.add(userName, 1, 2);
				gridPane3.add(new Label("Password:"), 0, 3);
				gridPane3.add(passWord, 1, 3);
				gridPane3.add(new Label("Re-Enter Password:"), 0, 4);
				gridPane3.add(passWord2, 1, 4);
				gridPane3.add(new Label("Email Address:"), 0, 5);
				gridPane3.add(emailAddress, 1, 5);
				gridPane3.add(createAccount, 1, 6);
				
				createAccount.setOnAction(e-> { 
					  if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || userName.getText().isEmpty() || passWord.getText().isEmpty() || passWord2.getText().isEmpty() || emailAddress.getText().isEmpty()) {
						  newEmptyError(createNew);
					  } 
					  if (!passWord.getText().equals(passWord2.getText())) {
						  passMatchError(createNew);
					  }
					  if (JavaMailUtil.isValid(emailAddress.getText()) == false) {
						  emailInvalidError(createNew);
					  }
					  else {
						  accountCreated(createNew, userName, passWord, firstName, lastName, emailAddress);
					  }
				  });
				
				createNew.setTitle("New Account");
				createNew.setScene(scene3);
				createNew.show();
			}
	
	public void newEmptyError (Stage stage) {
		Stage emptyErr=new Stage();
		stage.close();
		
		GridPane gridPane3=new GridPane();
		gridPane3.setAlignment(Pos.CENTER);
		Scene scene3=new Scene(gridPane3, 400, 250);
		gridPane3.setHgap(5);
		gridPane3.setVgap(5);
		gridPane3.add(new Label("**Error: one or more fields not filled**"), 0, 0);
		gridPane3.add(new Label("First Name:"), 0, 1);
		gridPane3.add(firstName, 1, 1);
		gridPane3.add(new Label("Last Name:"), 0, 2);
		gridPane3.add(lastName, 1, 2);
		gridPane3.add(new Label("User Name:"), 0, 3);
		gridPane3.add(userName, 1, 3);
		gridPane3.add(new Label("Password:"), 0, 4);
		gridPane3.add(passWord, 1, 4);
		gridPane3.add(new Label("Re-Enter Password:"), 0, 5);
		gridPane3.add(passWord2, 1, 5);
		gridPane3.add(new Label("Email Address:"), 0, 6);
		gridPane3.add(emailAddress, 1, 6);
		gridPane3.add(createAccount, 1, 7);
		
		createAccount.setOnAction(e-> { 
			  if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || userName.getText().isEmpty() || passWord.getText().isEmpty() || passWord2.getText().isEmpty() || emailAddress.getText().isEmpty()) {
				  newEmptyError(emptyErr);
			  } 
			  if (!passWord.getText().equals(passWord2.getText())) {
				  passMatchError(emptyErr);
			  }
			  else {
				  accountCreated(emptyErr, userName, passWord, firstName, lastName, emailAddress);			  }
		  });
		
		emptyErr.setTitle("New Account");
		emptyErr.setScene(scene3);
		emptyErr.show();
	}
	public void emailInvalidError (Stage stage) {
		Stage emailErr=new Stage();
		stage.close();
		
		GridPane gridPane3=new GridPane();
		gridPane3.setAlignment(Pos.CENTER);
		Scene scene3=new Scene(gridPane3, 400, 250);
		gridPane3.setHgap(5);
		gridPane3.setVgap(5);
		gridPane3.add(new Label("**Error: Email Address Invalid**"), 0, 0);
		gridPane3.add(new Label("First Name:"), 0, 1);
		gridPane3.add(firstName, 1, 1);
		gridPane3.add(new Label("Last Name:"), 0, 2);
		gridPane3.add(lastName, 1, 2);
		gridPane3.add(new Label("User Name:"), 0, 3);
		gridPane3.add(userName, 1, 3);
		gridPane3.add(new Label("Password:"), 0, 4);
		gridPane3.add(passWord, 1, 4);
		gridPane3.add(new Label("Re-Enter Password:"), 0, 5);
		gridPane3.add(passWord2, 1, 5);
		gridPane3.add(new Label("Email Address:"), 0, 6);
		gridPane3.add(emailAddress, 1, 6);
		gridPane3.add(createAccount, 1, 7);
		
		createAccount.setOnAction(e-> { 
			  if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || userName.getText().isEmpty() || passWord.getText().isEmpty() || passWord2.getText().isEmpty() || emailAddress.getText().isEmpty()) {
				  newEmptyError(emailErr);
			  } 
			  if (!passWord.getText().equals(passWord2.getText())) {
				  passMatchError(emailErr);
			  }
			  if (JavaMailUtil.isValid(emailAddress.getText()) == false) {
				  emailInvalidError(emailErr);
			  }
			  else {
				  accountCreated(emailErr, userName, passWord, firstName, lastName, emailAddress);			  }
		  });
		
		emailErr.setTitle("New Account");
		emailErr.setScene(scene3);
		emailErr.show();
	}

	public void passMatchError (Stage stage) {
		Stage passError=new Stage();
		stage.close();
		
		GridPane gridPane3=new GridPane();
		gridPane3.setAlignment(Pos.CENTER);
		Scene scene3=new Scene(gridPane3, 400, 250);
		gridPane3.setHgap(5);
		gridPane3.setVgap(5);
		gridPane3.add(new Label("**Error: Passwords do not match**"), 0, 0);
		gridPane3.add(new Label("First Name:"), 0, 1);
		gridPane3.add(firstName, 1, 1);
		gridPane3.add(new Label("Last Name:"), 0, 2);
		gridPane3.add(lastName, 1, 2);
		gridPane3.add(new Label("User Name:"), 0, 3);
		gridPane3.add(userName, 1, 3);
		gridPane3.add(new Label("Password:"), 0, 4);
		gridPane3.add(passWord, 1, 4);
		gridPane3.add(new Label("Re-Enter Password:"), 0, 5);
		gridPane3.add(passWord2, 1, 5);
		gridPane3.add(new Label("Email Address:"), 0, 6);
		gridPane3.add(emailAddress, 1, 6);
		gridPane3.add(createAccount, 1, 7);
		
		createAccount.setOnAction(e-> { 
			  if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || userName.getText().isEmpty() || passWord.getText().isEmpty() || passWord2.getText().isEmpty() || emailAddress.getText().isEmpty()) {
				  newEmptyError(passError);
			  } 
			  if (!passWord.getText().equals(passWord2.getText())) {
				  passMatchError(passError);
			  }
			  else {
				  accountCreated(passError, userName, passWord, firstName, lastName, emailAddress);			  }
		  });
		
		passError.setTitle("New Account");
		passError.setScene(scene3);
		passError.show();
	}


			
	public void accountCreated(Stage stage, TextField user, TextField pass, TextField firstName, TextField lastName, TextField emailAddress) {
				Stage accountCreated=new Stage();
				stage.close();
				String userN=user.getText();
				String passW=PasswordHashing.hash(pass.getText());
				String firstN=firstName.getText();
				String lastN=lastName.getText();
				String emailAd=emailAddress.getText();
				
				try {
					String sql="select * from \"users\"";
					ResultSet rs=Connect.st.executeQuery(sql);
					
					sql="insert into \"users\"(\"username\", \"password\", \"first_name\", \"last_name\", \"email_address\")values(\'"+userN+"\',\'"+passW+"\',\'"+firstN+"\',\'"+lastN+"\',\'"+emailAd+"\')";
					int i=Connect.st.executeUpdate(sql);
					if(i>0) {
						System.out.println("Successful Insert");
					}
				}catch(SQLException sqle) {
					System.err.println(sqle);
				}
				
				GridPane gridPane4=new GridPane();
				gridPane4.setAlignment(Pos.CENTER);
				Scene scene4=new Scene(gridPane4, 400, 250);
				gridPane4.setHgap(5);
				gridPane4.setVgap(5);
				gridPane4.add(new Label("Account Created"), 0, 0);
				gridPane4.add(conti, 0, 1);
				
				conti.setOnAction(e->logIn(accountCreated));
				accountCreated.setTitle("Done");
				accountCreated.setScene(scene4);
				accountCreated.show();
			}

	
	
	
	public static void main(String[] args) {
		Connect con=new Connect();
		launch(args);
	}
	
	

}