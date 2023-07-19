package controller;

import java.util.Arrays;

public class Moment {
	private static 		Moment  			instance;
	private int[] 		casasIniciais 		= new int[4];
	private int[][] 	path 				= new int[52][2];
	private int[] 		retaFinalVermelho 	= new int[6];
	private int[] 		retaFinalVerde 		= new int[6];
	private int[] 		retaFinalAmarelo 	= new int[6];
	private int[] 		retaFinalAzul 		= new int[6];
	private int[][]		podio 				= new int[4][2];
	private int			player				= 0;
	private int 		diceVal				;
	
// ____________________________________________________________________________________________________________________________
//
		
	protected Moment() {
			this.initializeBoardInfo();
	}
	
	//	Operacoes
	public void print() {
		System.out.println("casasIniciais: " 		+ Arrays.toString(casasIniciais));
	    System.out.println("path: " 				+ Arrays.deepToString(path));
	    System.out.println("retaFinalVermelho: " 	+ Arrays.toString(retaFinalVermelho));
	    System.out.println("retaFinalVerde: " 		+ Arrays.toString(retaFinalVerde));
	    System.out.println("retaFinalAmarelo: " 	+ Arrays.toString(retaFinalAmarelo));
	    System.out.println("retaFinalAzul: " 		+ Arrays.toString(retaFinalAzul));
	    System.out.println("podio: " 				+ Arrays.deepToString(podio));
	    System.out.println("player: " 				+ player);
	    System.out.println("diceVal: " 				+ diceVal);
	}
	
	// Inicializacao ------------------

	private void initializeBoardInfo() {
		int[] 	casas_iniciais 			= this.get_casasIniciais();
		int[][]	path					= this.getPath();
		int[] 	reta_final_vermelho 	= this.getRetaFinalVermelho();
		int[] 	reta_final_verde 		= this.getRetaFinalVerde();
		int[] 	reta_final_amarelo 		= this.getRetaFinalAmarelo();
		int[] 	reta_final_azul 		= this.getRetaFinalAzul();
		int[][]	podio					= this.getPodio();
		int		player					= 0;
		int		diceVal					= 0;
		
		// As casas_iniciais comecam cheias
		for (int i = 0; i < casas_iniciais.length; i++) {
		    casas_iniciais[i] = 4;
		}
		
		// Casas de path comecam vazias
		for (int i = 0; i < path.length; i++) {
		    for (int j = 0; j < path[i].length; j++) {
		        path[i][j] = -1;
		    }
		}
		
		// Casas das retas finais comecao vazias
		for (int i = 0; i < reta_final_vermelho.length; i++) {
		    reta_final_vermelho[i] 	= 0;
		    reta_final_verde[i] 	= 0;
		    reta_final_amarelo[i] 	= 0;
		    reta_final_azul[i] 		= 0;
		}
		
		//	Valor default do podio para indicar que ele esta vazio
		podio[0][0] = -1;
		
		this.setAll(
				casas_iniciais,
				path,
				reta_final_vermelho,
				reta_final_verde,
				reta_final_amarelo,
				reta_final_azul,
				podio,
				player,
				diceVal
				);
		
	}
	
	// SET ----------------------------
	public void setAll					(
			int[] 		casas_iniciais,
			int[][] 	path,
			int[] 		reta_final_vermelho,
			int[] 		reta_final_verde,
			int[] 		reta_final_amarelo,
			int[] 		reta_final_azul,
			int[][]		podio,
			int			turno,
			int			diceVal
			) {
		this.set_casasIniciais(casas_iniciais);
		this.set_path(path);
		this.set_retaFinalVermelho(reta_final_vermelho);
		this.set_retaFinalVerde(reta_final_verde);
		this.set_retaFinalAmarelo(reta_final_amarelo);
		this.set_retaFinalAzul(reta_final_azul);
		this.set_podio(podio);
		this.set_player(turno);
		this.set_diceVal(diceVal);
	}

	public void set_casasIniciais		(int[] casas_iniciais) {
	    this.casasIniciais = casas_iniciais;
	}
	public void set_path				(int[][] path) {
	    this.path = path;
	}
	public void set_retaFinalVermelho	(int[] reta_final_vermelho) {
	    this.retaFinalVermelho = reta_final_vermelho;
	}
	public void set_retaFinalVerde		(int[] reta_final_verde) {
	    this.retaFinalVerde = reta_final_verde;
	}
	public void set_retaFinalAmarelo	(int[] reta_final_amarelo) {
	    this.retaFinalAmarelo = reta_final_amarelo;
	}
	public void set_retaFinalAzul		(int[] reta_final_azul) {
	    this.retaFinalAzul = reta_final_azul;
	}
	public void set_podio				(int[][] podio) {
	    this.podio = podio;
	}
	public void set_player				(int turno) {
	    this.player = turno;
	}
	public void set_diceVal				(int diceVal) {
		this.diceVal = diceVal;
	}
	
	// GET ----------------------------
	public int[] 	get_casasIniciais		() {
	    return casasIniciais;
	}
	public int[][] 	getPath					() {
	    return path;
	}
	public int[] 	getRetaFinalVermelho	() {
	    return retaFinalVermelho;
	}
	public int[] 	getRetaFinalVerde		() {
	    return retaFinalVerde;
	}
	public int[] 	getRetaFinalAmarelo		() {
	    return retaFinalAmarelo;
	}
	public int[] 	getRetaFinalAzul		() {
	    return retaFinalAzul;
	}
	public int[][] 	getPodio				() {
	    return podio;
	}
	public int 		getPlayer				() {
	    return player;
	}
	public int 		getDiceVal				() {
		return diceVal;
	}
	
	//	Singleton ------------------------------------------
	public static Moment getInstance() {
		if (instance == null) {
			instance = new Moment();
		}
		return instance;
	}

	
}
