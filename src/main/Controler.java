package main;
import api.*;

public class Controler {

	public static void main(String[] args) throws Exception {
		String[] connectInfo = {"127.0.0.1", "3306", "efrei", "User1", "123"};
		
		// api permet de se connecter à la BDD :
		DataAccess api = new DataAccess(connectInfo);

		// permet de faire des requêtes à la base de donnée
		api.query("SELECT * FROM ELEVES");
	}

}
