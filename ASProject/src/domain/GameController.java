package domain;

public interface GameController {

	public Game getGame(Integer id);
	public void createGames(Level level, Player player);
}
