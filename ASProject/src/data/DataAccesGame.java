package data;

import org.hibernate.Session;

import domain.Game;
import domain.GameController;
import domain.InitSessionFactory;
import domain.Level;
import domain.Player;

public class DataAccesGame implements GameController{
	@Override
	public Game getGame(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public static void main(String[] args) {
		
	}


	@Override
	public void createGames(Level level, Player player) {
		Session session = InitSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		Game game = new Game(false,false,100, level, player); // 100 tries number
		level.getGames().add(game); //agrego al level el nuevo game;
		player.getGames().add(game); // agrego al player el game
		session.save(game);
		session.getTransaction().commit();
		session.close();
	}


}
