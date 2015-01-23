package domain;

import org.hibernate.Session;

import presentation.PresentationController;



public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				Session session = InitSessionFactory.getSessionFactory().getCurrentSession();
				//First all: create users NEW
				RegisteredUserController userCtrl = DataFactoriaController.getInstance().getUserController();
				userCtrl.createRegisteredUsers();
				LevelController levelCtrl = DataFactoriaController.getInstance().getLevelController();
				levelCtrl.createLevels();
				
				PresentationController cp = new PresentationController();
				cp.showLogin(true);
			}
		});
	}
}
