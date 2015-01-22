package domain;
import java.util.List;

public interface SquareController {
	//public void crearCasella(Partida partida, Integer f, Integer c);
	//public Square getCasella(Game idPartida, int nf, int nc);
	//public List<Square> getSquares(Game game) ;
	public List<Square> all(); 
	public List<Square> getSquares(Game game);
	
}
