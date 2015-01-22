package domain;

import data.DataAccesLevel;
import data.DataAccesPlayer;
import data.DataAccesUser;


public class DataFactoriaController {
	private static DataFactoriaController instance = new DataFactoriaController();
	private PlayerController playerController = new DataAccesPlayer(); ;
	private RegisteredUserController userController = new DataAccesUser();
	private LevelController levelController = new DataAccesLevel();
	
	private DataFactoriaController(){
		playerController = new DataAccesPlayer();
		userController = new DataAccesUser();
		levelController =  new DataAccesLevel();
	}
	public static DataFactoriaController getInstance() {
		return instance;
	}
	public static void setInstance(DataFactoriaController instance) {
		DataFactoriaController.instance = instance;
	}
	public PlayerController getPlayerController() {
		return playerController;
	}
	public RegisteredUserController getUserController() {
		return userController;
	}
	public LevelController getLevelController() {
		return levelController;
	}
	public void setLevelController(LevelController levelController) {
		this.levelController = levelController;
	}
	
	
}
