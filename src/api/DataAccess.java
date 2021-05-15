package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccess {
	// Médiateur entre la base de donnée et l'application RentCar
	
	//Note :
	//	- Si vous n'utilisez pas MySql WorkBench veuillez faire les modifications nécessaires
	//	  dans la méthode setUpSGBD.
	
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
		
		//Initialisation des différents paramètres lié à la connexion au SGBD
		setUpSGBD(cio);
		
		//Chargement du pilote
		try {
			Class.forName(this.pilote);			
		}catch(ClassNotFoundException e) {
			stopApplication("Impossible de charger le pilote " + this.pilote);
		}
		
		// Établir la connexion avec la BDD
		displayMessage("Connexion à la base de données");
		try {
			this.con = DriverManager.getConnection(url, login, mdp);			
		}catch(SQLException e) {
			stopApplication("Connection à la base de donnée impossible");
		}
	
	}
	
	private void setUpSGBD(String[] cio) {
		// Note :
		// cio[0] : Addresse
		// cio[1] : Port
		// cio[2] : Nom base de donnée
		// cio[3] : Login utilisateur base de donnée
		// cio[4] : Mot de passe utilisateur base de donnée

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
		//Fonction qui permet de lancer des requêtes sql de type SELECT
		displayMessage("Execution de la requête...");
		try {
			// Create statement
			this.st = this.con.createStatement();
			
			// Lance la requête
			this.rs = this.st.executeQuery(req);
			
			//Affiche le résultat de la requête
			showResults();
		}catch(SQLException e) {
			stopApplication("Une anomalie est survenue lors de l'execution de la requête\n");
		}
	}
	
	public void showResults() {
		//Affiche le résultat de la requête
		
		ResultSet rs = this.rs;
		
		displayMessage("Résultats de la requête :\n");
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
			System.out.println("Une erreur est survenue lors de la déconnexion avec la base de donnée");
		}
	}
	
	public static void displayMessage(String message) {
		System.out.println(message);
	}
}
