package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccess {
	// M�diateur entre la base de donn�e et l'application RentCar
	
	//Note :
	//	- Si vous n'utilisez pas MySql WorkBench veuillez faire les modifications n�cessaires
	//	  dans la m�thode setUpSGBD.
	
	//Variables
	private Connection con;
	private String     pilote = "com.mysql.cj.jdbc.Driver"; 
	private ResultSet  rs;
	private Statement  st;
	private String     url;
	private String     login;
	private String     mdp;
	
	//Constructor
	public DataAccess(String[] cio) throws Exception {
		
		//Initialisation des diff�rents param�tres li� � la connexion au SGBD
		setUpSGBD(cio);
		
		//Chargement du pilote
		try {
			Class.forName(this.pilote);			
		}catch(ClassNotFoundException e) {
			stopApplication("Impossible de charger le pilote " + this.pilote);
		}
		
		// �tablir la connexion avec la BDD
		displayMessage("Connexion � la base de donn�es");
		try {
			this.con = DriverManager.getConnection(url, login, mdp);			
		}catch(SQLException e) {
			stopApplication("Connection � la base de donn�e impossible");
		}
	
	}
	
	private void setUpSGBD(String[] cio) {
		// Note :
		// cio[0] : Addresse
		// cio[1] : Port
		// cio[2] : Nom base de donn�e
		// cio[3] : Login utilisateur base de donn�e
		// cio[4] : Mot de passe utilisateur base de donn�e

		//Instanciation du pilote
		this.pilote = "com.mysql.cj.jdbc.Driver";
		
		//Instanciation de l'url
		this.url = "jdbc:mysql://" + cio[0]+":"+cio[1]+"/"+cio[2];
		
		//Instanciation du login
		this.login = cio[3];
		
		//Instanciation du mdp
		this.mdp = cio[4];
	}
	
	public void prepareSQLRequest() {
		
	}
	
	public void query(String req){
		//Fonction qui permet de lancer des requ�tes sql de type SELECT
		displayMessage("Execution de la requ�te...");
		try {
			// Create statement
			this.st = this.con.createStatement();
			
			// Lance la requ�te
			this.rs = this.st.executeQuery(req);
			
			//Affiche le r�sultat de la requ�te
			showResults();
		}catch(SQLException e) {
			stopApplication("Une anomalie est survenue lors de l'execution de la requ�te\n");
		}
	}
	
	public void showResults() {
		//Affiche le r�sultat de la requ�te
		
		ResultSet rs = this.rs;
		
		displayMessage("R�sultats de la requ�te :\n");
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int nbCols = rsmd.getColumnCount();
			boolean encore = rs.next();
			while(encore) {
				for(int i = 1; i < nbCols + 1; i++) {
					System.out.print(rs.getString(i) + " ");
				}
				System.out.println("");
				encore = rs.next();
			}
			
		}catch(SQLException e){
			stopApplication(e.getMessage());
		}
	}
	
	public static void stopApplication(String message) {
		System.err.println(message);
		System.exit(99);
	}
	
	public void disconnectBdd() {
		try {
			this.con.close();
			this.rs.close();
			this.st.close();
		} catch (SQLException e) {
			System.out.println("Une erreur est survenue lors de la d�connexion avec la base de donn�e");
		}
	}
	
	public static void displayMessage(String message) {
		System.out.println(message);
	}
}
