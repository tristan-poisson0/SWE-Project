import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
static Connection con; 
static Statement st; 

public Connect () {
	connect();
}

public static void connect() {
	try { 
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver Loaded Successfully");
		con = DriverManager.getConnection("jdbc:postgresql://localhost/Accounts","postgres","Hedhntr3");
		System.out.println("Successful Connection");
		st=con.createStatement();
		System.out.println("Statement created Successfully");
		System.out.println("Now, You can Access the Database");
	} catch (ClassNotFoundException cnfe) {
		System.err.println(cnfe);
	} catch (SQLException sqle) {
		System.err.println(sqle);
	}
}
}
