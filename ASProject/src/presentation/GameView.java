package presentation;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class GameView extends JFrame implements ActionListener, Runnable{

	private JPanel contentPane;
	private JPanel panelTablero;
	private JPanel panelInfo;
	private JPanel panelHelp;
	private JLabel lblUsername;
	private JLabel lblNivell;
	private JLabel lblValueNivell;
	private JLabel lblMinesTotals;
	private JLabel lblValueMines;
	private JLabel tiempo;
	private JLabel lblValuetemps;
	private JLabel lblTirades;
	private JLabel lblValuetirades;
	private JButton btnEtRendeixes;
	private JLabel labelHelp;
	
	Thread hilo;
	String min, seg;
	private boolean cronometroActivo = false;
	private PresentationController ctrl ;
	
	private JButton buttons[][];
	
	private int rows;
	private int cols;
	private Integer tirades = 0;
	private Integer minutos = 0;
	private Integer segundos = 0;
	boolean ganada = false;

	JButton boton;
	
	public void run(){
        minutos = 0;
        segundos = 0;
        //min es minutos, seg es segundos y mil es milesimas de segundo
        min="";
        seg="";
        try {
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
            while( cronometroActivo ){
                Thread.sleep(1000);
                segundos ++;
                //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                //y los segundos vuelven a 0
                if( segundos == 60 ){
                	segundos = 0;
                	minutos++;
                }
                //Esto solamente es estetica para que siempre este en formato
                //00:00
                if( minutos < 10 ) min = "0" + minutos;
                else min = minutos.toString();
                if( segundos < 10 ) seg = "0" + segundos;
                else seg = segundos.toString();
                //Modificamos la label haciendo invokeLater para que se haga en el thread principal
                EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					lblValuetemps.setText( min + ":" + seg );
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
                
                
            }
        }catch(Exception e){}
    }
	
	private void iniciarCronometro() {
        cronometroActivo = true;
        hilo = new Thread( this );
        hilo.start();
        
    }
	
    private void pararCronometro(){
        cronometroActivo = false;
    }    

    private void closeCurrentView() {
    	ctrl.showNivells();
    }

    public GameView(final PresentationController cp, int rows,int cols, String nomNivell, String numMines) {
		
		ctrl = cp;
		this.rows = rows;
		this.cols = cols;
		
		setTitle("Buscamines I Partida");
		GridLayout layoutTablero = new GridLayout(1, 0, 0, 0);
		layoutTablero.setColumns(cols);
		layoutTablero.setRows(rows);
		//Controlamos  lo que pasa cuando se marca la X para salir
		//como x defecto es hide lo pones a do nothing on close y lo tratamos nosotros
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				Object[] options = { "Si", "No" };
				int ret= JOptionPane.showOptionDialog(null, "Segur que vols sortir?", "Ets a punt d'abandonar la partida..",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
				if(ret == JOptionPane.YES_OPTION){
					System.exit(0);
				}

			}
		});
		setBounds(100, 100, 543, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//panelTablero = new JPanel();
		panelTablero = new JPanel(new GridLayout(rows, cols));
		panelTablero.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		contentPane.add(panelTablero, BorderLayout.CENTER);

		buttons = new JButton[rows][cols];
		for(int i = 0; i < rows; ++i){
			for(int j = 0; j < cols; ++j){
				
				JButton boton= new JButton();
				boton.setIcon(new ImageIcon(LevelsView.class.getResource("/Presentation/pictures/covered-45x27.png")));
				boton.setBorder(new EmptyBorder(0,0,0,0)); // para que la imagen del boton ocupe todo
				boton.setContentAreaFilled(false);
				boton.addActionListener(this);

				// HE DECLARADO EL BOTON FUERA PARA PODER INSTANCIARLO DENTRO DE LA NUEVA CLASE
				boton.addMouseListener(new MouseAdapter(){
				    public void mouseClicked(MouseEvent e){
				    	JButton buttonClicked = new JButton();
				    	if (e.getButton() == MouseEvent.BUTTON3) {
				    		try {
				    			buttonClicked = (JButton) e.getComponent();
								cp.marcarDesmarcar(buttonClicked.getX(),buttonClicked.getY());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						 }
				    	else if (e.getButton() == MouseEvent.BUTTON1) {
				    		try {
								
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						 }
				    }});
				buttons[i][j] = boton;
				panelTablero.add(boton);
				
			}
		}
		
		panelInfo = new JPanel();
		contentPane.add(panelInfo, BorderLayout.EAST);
		GridBagLayout gbl_panelInfo = new GridBagLayout();
		gbl_panelInfo.columnWidths = new int[]{30, 0, 59, 0, 0};
		gbl_panelInfo.rowHeights = new int[]{0, 0, 0, 51, 0, 0, 0, 56, 0, 0};
		gbl_panelInfo.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInfo.setLayout(gbl_panelInfo);
		
		lblUsername = new JLabel();
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.gridwidth = 2;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 0;
		panelInfo.add(lblUsername, gbc_lblUsername);
		lblUsername.setText(ctrl.getUser());
		
		lblNivell = new JLabel("Nivell:");
		GridBagConstraints gbc_lblNivell = new GridBagConstraints();
		gbc_lblNivell.anchor = GridBagConstraints.WEST;
		gbc_lblNivell.insets = new Insets(0, 0, 5, 5);
		gbc_lblNivell.gridx = 1;
		gbc_lblNivell.gridy = 1;
		panelInfo.add(lblNivell, gbc_lblNivell);
		
		lblValueNivell = new JLabel("");
		GridBagConstraints gbc_lblValueNivell = new GridBagConstraints();
		gbc_lblValueNivell.anchor = GridBagConstraints.WEST;
		gbc_lblValueNivell.gridwidth = 2;
		gbc_lblValueNivell.insets = new Insets(0, 0, 5, 0);
		gbc_lblValueNivell.gridx = 2;
		gbc_lblValueNivell.gridy = 1;
		panelInfo.add(lblValueNivell, gbc_lblValueNivell);
		lblValueNivell.setText(nomNivell);
		
		lblMinesTotals = new JLabel("Mines Totals: ");
		GridBagConstraints gbc_lblMinesTotals = new GridBagConstraints();
		gbc_lblMinesTotals.anchor = GridBagConstraints.WEST;
		gbc_lblMinesTotals.gridwidth = 2;
		gbc_lblMinesTotals.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinesTotals.gridx = 1;
		gbc_lblMinesTotals.gridy = 2;
		panelInfo.add(lblMinesTotals, gbc_lblMinesTotals);
		
		lblValueMines = new JLabel("");
		GridBagConstraints gbc_lblValueMines = new GridBagConstraints();
		gbc_lblValueMines.anchor = GridBagConstraints.WEST;
		gbc_lblValueMines.insets = new Insets(0, 0, 5, 0);
		gbc_lblValueMines.gridx = 3;
		gbc_lblValueMines.gridy = 2;
		panelInfo.add(lblValueMines, gbc_lblValueMines);
		lblValueMines.setText(numMines);
		
		tiempo = new JLabel("Temps");
		GridBagConstraints gbc_lblTemps = new GridBagConstraints();
		gbc_lblTemps.anchor = GridBagConstraints.WEST;
		gbc_lblTemps.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemps.gridx = 1;
		gbc_lblTemps.gridy = 4;
		panelInfo.add(tiempo, gbc_lblTemps);
		
		lblValuetemps = new JLabel("00:00");
		GridBagConstraints gbc_lblValuetemps = new GridBagConstraints();
		gbc_lblValuetemps.anchor = GridBagConstraints.WEST;
		gbc_lblValuetemps.insets = new Insets(0, 0, 5, 5);
		gbc_lblValuetemps.gridx = 2;
		gbc_lblValuetemps.gridy = 4;
		panelInfo.add(lblValuetemps, gbc_lblValuetemps);
		
		lblTirades = new JLabel("Tirades");
		GridBagConstraints gbc_lblTirades = new GridBagConstraints();
		gbc_lblTirades.anchor = GridBagConstraints.WEST;
		gbc_lblTirades.insets = new Insets(0, 0, 5, 5);
		gbc_lblTirades.gridx = 1;
		gbc_lblTirades.gridy = 5;
		panelInfo.add(lblTirades, gbc_lblTirades);
		
		lblValuetirades = new JLabel("0");
		GridBagConstraints gbc_lblValuetirades = new GridBagConstraints();
		gbc_lblValuetirades.anchor = GridBagConstraints.WEST;
		gbc_lblValuetirades.insets = new Insets(0, 0, 5, 5);
		gbc_lblValuetirades.gridx = 2;
		gbc_lblValuetirades.gridy = 5;
		panelInfo.add(lblValuetirades, gbc_lblValuetirades);
		
		btnEtRendeixes = new JButton("Et rendeixes?");
		GridBagConstraints gbc_btnEtRendeixes = new GridBagConstraints();
		gbc_btnEtRendeixes.gridwidth = 3;
		gbc_btnEtRendeixes.gridx = 1;
		gbc_btnEtRendeixes.gridy = 8;
		panelInfo.add(btnEtRendeixes, gbc_btnEtRendeixes);
		btnEtRendeixes.addActionListener(this);
		
		panelHelp = new JPanel();
		panelHelp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelHelp, BorderLayout.SOUTH);
		
		labelHelp = new JLabel("Botó esquerre: descobreix casella | Botó dret: marca/desmarca casella");
		panelHelp.add(labelHelp);
		pack();
		iniciarCronometro();

	}
	
	private void mostraPerdut(){
		pararCronometro();
		JOptionPane.showMessageDialog(this, "Ho sentim, has perdut la partida!",
			    "Partida perduda",
			    JOptionPane.PLAIN_MESSAGE);
		
		closeCurrentView();
	}

	private ButtonXY obtenerCoordenadasButton(JButton boton) {
		
		ButtonXY ret = new ButtonXY();
		
		for(int i = 0; i < rows; ++i){
			for(int j = 0; j < cols; ++j){
				if (boton == buttons[i][j]) {
					ret.setX(i);
					ret.setY(j);
				}	
			}
		}
		
		return ret;
	}
	
	private void actualizarBoton(Integer f, Integer c, Integer v){
		String val = "empty";
		if (v!=null) val = v.toString();
		System.out.println(val);
		buttons[f][c].setIcon(new ImageIcon(LevelsView.class.getResource("/Presentation/pictures/"+val+"-45x27.png")));
	}
	
	public void mostrarMina(Integer f, Integer c){
		buttons[f][c].setIcon(new ImageIcon(LevelsView.class.getResource("/Presentation/pictures/losingmine-45x27.png")));
	}
	
	public void mostraGuanyada( Integer punts ){
		Object[] options = { "Accepta" };
		String missatge = "Felicitats, has guanyat la partida amb "+ punts + " punts.";
		JOptionPane.showOptionDialog(this, missatge, "Partida completada",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		closeCurrentView();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton botonPressed = (JButton)e.getSource();
		if ( botonPressed == btnEtRendeixes) {
			mostraPerdut();
		} else { // Aqui se tratan los otros botones, los del array
			System.out.println("Fila: " + obtenerCoordenadasButton(botonPressed).x + "  Col: "+ obtenerCoordenadasButton(botonPressed).y);
			++tirades;
			ArrayList<ArrayList<Integer>> visibles;
			try {
				visibles = ctrl.descobrirCasella(obtenerCoordenadasButton(botonPressed));
				System.out.println("num de casillas Visibles: "+ visibles.size()) ;
				for (int v=0; v<visibles.size();++v){
					System.out.println("Casillas a descubrir: F:" +visibles.get(v).get(0)+" C:"+visibles.get(v).get(1)+" V:"+visibles.get(v).get(2));
				}
				for (int v=0; v<visibles.size();++v){
					Integer fila = visibles.get(v).get(0);
					Integer col = visibles.get(v).get(1);
					Integer valor = visibles.get(v).get(2);
					actualizarBoton(fila,col,valor);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			lblValuetirades.setText(tirades.toString());
		}

	}
	
	public Integer getSegons(){
		return segundos;
	}
	public Integer getMinuts(){
		return minutos;
	}
	
	public void marcarBoton(int nf, int nc) {
		buttons[nf][nc].setIcon(new ImageIcon(LevelsView.class.getResource("/Presentation/pictures/mark-45x27.png")));
	}
	public void desmarcarBoton(int nf, int nc) {
		buttons[nf][nc].setIcon(new ImageIcon(LevelsView.class.getResource("/Presentation/pictures/covered-45x27.png")));
	}
}
