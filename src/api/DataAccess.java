package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataAccess {
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	//Constructor
	public DataAccess(String[] cio) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// Établir la connexion avec la BDD
		this.con = DriverManager.getConnection("jdbc:mysql://"+cio[0]+":"+cio[1]+"/"+cio[2], cio[3], cio[4]);
	
	}
	
	public void query(String req) throws Exception{
		// Create statement
		this.st = this.con.createStatement();
		
		// Lauch request
		this.rs = this.st.executeQuery(req);
	}

	@Override
	public String toString() {
		String txt = "";
		return txt;
	}
	
	
	
	
}
