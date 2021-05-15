package command;

import api.DataAccess;
import main.Controler;

public class ConnectUser implements Command {
	private DataAccess api;
	private Controler ctrl;
	
	public ConnectUser(DataAccess api, Controler ctrl) {
		// Instancie notre m�diateur avec la base de donn�e
		this.api = api;
		this.ctrl = ctrl;
	}
	
	// Connecte l'utilisateur
	@Override
	public void execute() {
		// 1. R�cup�re le mdp de l'utilisateur dans la base de donn�e
		
		// 2. Compare le mdp fourni par l'utilisateur et le mot de passe dans la bdd
		
	}

	// D�connecte l'utilisateur
	@Override
	public void unexecute() {
		ctrl.setUpProfile("disconnected");
		api.disconnectBdd();
	}

}
