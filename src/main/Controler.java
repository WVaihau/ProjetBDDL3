package main;
import api.*;

public class Controler {
	private DataAccess api;
	private String status = "disconnected";
	
	public static void main(String[] args) throws Exception {
		String[] connectInfo = {"127.0.0.1", "3306", "efrei", "User1", "123"};
		
		// api permet de se connecter � la BDD :
		DataAccess api = new DataAccess(connectInfo);

		// permet de faire des requ�tes � la base de donn�e
		api.query("SELECT * FROM ELEVES");
	}
	
	//Constructeur
	public Controler(String[] args) throws Exception {
		this.api = new DataAccess(args);
	}
	
	public void setUpProfile(String status) {
		this.status = status;
	}

}
