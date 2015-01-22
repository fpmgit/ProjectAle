package presentation;

import java.util.List;

import javax.swing.JOptionPane;

import domain.CUPlayGame;
import domain.DataFactoriaController;
import domain.Level;

public class PresentationController {
	
	private final LoginView login;
	private  LevelsView levels;
	//private CUJugarPartida cujp; //controlador de caso de uso
	private final  CUPlayGame game;
	//private GameView partida;
	private final DataFactoriaController dataController;
	private String user;
	private List<String> levelNames;
	//private final InfoLevel nivell = new InfoLevel();
		
	public PresentationController() {
		dataController = DataFactoriaController.getInstance();
		login = new LoginView(this);
		levels = new LevelsView(this,"");
		//cujp = new CUJugarPartida(this);
		game = new CUPlayGame(this);
	}

	// Lo hacemos gral (muestra/oculta) si le agregamos un parametro
	public void showLogin(boolean value) {
		login.setUsuariField("");
		login.setPasswordField("");
		login.setVisible(value);
	}
	
	public void iniciarPartida(String nomNivell){
		//FALTA LLAMAR A CREAR GAME!
		Level level = dataController.getLevelController().getLevel(nomNivell);
		/*
		nivell.setNomNivell(nomNivell);
		nivell.setNumFiles(niveles.getTextFieldValueNumFiles());
		nivell.setNumColumnes(niveles.getTextFieldValueNumCol());
		nivell.setNumMines(Integer.parseInt((niveles.getTexttextFieldValueNomMines())));
		partida = new GameView(this,nivell.getNumFiles(),nivell.getNumColumnes(), nivell.getNomNivell(), nivell.getNumMines().toString());
		partida.setVisible(true);
		cujp.crearPartida(nomNivell);
		*/
	}
/*
	public void showNivells() {
		partida.setVisible(false);
		niveles.setVisible(true);
	}
	*/
	public void showDialog( String missatge ){
		Object[] options = { "Ok" };
		JOptionPane.showOptionDialog(login, missatge, "Warning!",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
	}

	
	public void okLogin(String user, String pwd) {
		this.user = user ;
		try {
			game.authentication(user, pwd);
			//login.setVisible(false);
			// mejor esto:
			showLogin(false);
			
			levels = new LevelsView(this, user);
			levels.setVisible(true);
			
			levels.initLevels();

			
		} catch (Exception e) {
			String missatge = "Dades incorrectes";
			if(e.getMessage().equals("UsuariIncorrecte")){
				missatge = "Les dades introduides no són correctes";
			}
			
			else if(e.getMessage().equals("UsuariNoJugador")){
				missatge = "Els adminstradors no poden jugar";
			}
			else if(e.getMessage().equals("NoExisteixUsuariRegistrat")){
				missatge = "No hi ha cap usuari registrat amb aquest identificador";
			}
			
			showDialog(missatge); //substituye a las funciones mostraEtsAdmin, mostraDadesIncorrectes
		}
		
	}
	public String getUser(){
		return user;
	}
	public String[] getLevelNames(){
		String[]names = new String[3];
		List<Level> levels = dataController.getLevelController().all();
		for(int i = 0; i < levels.size(); ++i){
			names[i] = levels.get(i).getName();
		}
		return names;
	}
/*
	public ArrayList<ArrayList<Integer>> descobrirCasella(ButtonXY b) throws Exception{
		System.out.println(b.getX()+ " "+ b.getY());
		ArrayList<ArrayList<Integer>> ret = cujp.descobrirCasella(b.getX(), b.getY());
		ret.remove(ret.size()-1);
		for (int v=0; v<ret.size();++v){
			System.out.println(ret.get(v).get(0)+" "+ret.get(v).get(1)+" "+ret.get(v).get(2));
		}
		return ret;
	}
	
	public Integer getTemps() {
		Integer s = partida.getSegons();
		Integer m = partida.getMinuts();
		return s+m*60;
	}
	
	public String[] obtenirNomNivells() throws Exception{
		ArrayList<ArrayList<String>> res = cujp.obtenirNivells();
		String r[] = new String [res.size()];
		for(int i=0; i<res.size();++i){
			r[i]=res.get(i).get(0);
		}
		return r;
	}
	public String[] obtenirFilesNivells() throws Exception{
		ArrayList<ArrayList<String>> res = cujp.obtenirNivells();
		String r[] = new String [res.size()];
		for(int i=0; i<res.size();++i){
			r[i]=res.get(i).get(1);
		}
		return r;
	}public String[] obtenirColumnesNivells() throws Exception{
		ArrayList<ArrayList<String>> res = cujp.obtenirNivells();
		String r[] = new String [res.size()];
		for(int i=0; i<res.size();++i){
			r[i]=res.get(i).get(2);
		}
		return r;
	}public String[] obtenirMinesNivells() throws Exception{
		ArrayList<ArrayList<String>> res = cujp.obtenirNivells();
		String r[] = new String [res.size()];
		for(int i=0; i<res.size();++i){
			r[i]=res.get(i).get(3);
		}
		return r;
	}
	public void mostrarMina(Integer nf, Integer nc){
		partida.mostrarMina(nf,nc);
	}

	public void marcarDesmarcar(int nf, int nc) throws Exception {
		cujp.marcarDesmarcar(nf, nc);
	}
	public void marcarBoton(int nf, int nc) {
		partida.marcarBoton(nf,nc);
	}
	public void desmarcarBoton(int nf, int nc) {
		partida.desmarcarBoton(nf,nc);
	}*/

	public InfoLevel getDadesNivell(String nivelEscollit) {
		Level level = dataController.getLevelController().getLevel(nivelEscollit);
		InfoLevel infoLevel = new InfoLevel();
		infoLevel.setNameLevel(level.getName());
		infoLevel.setRows(level.getSquaresXRow());
		infoLevel.setColumns(level.getSquaresXColumn());
		infoLevel.setMine(level.getMinesNumber());
		return infoLevel;
		
		
	}
}

