package domain;

import presentation.PresentationController;

public class CUPlayGame {

	private final PresentationController presentationController;
	private Player player;
	
	public CUPlayGame(PresentationController presentationController){
		this.presentationController = presentationController;
	}
	public void authentication(String username, String password) throws Exception{
		CULogin login = new CULogin();
		login.login(username,password);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
