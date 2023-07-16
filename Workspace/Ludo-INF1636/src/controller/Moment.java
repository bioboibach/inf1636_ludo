package controller;

public class Moment {
	private static 		Moment  			instance;
	private int[] 		casasIniciais 		= new int[4];
	private int[][] 	path 				= new int[52][2];
	private int[] 		retaFinalVermelho 	= new int[6];
	private int[] 		retaFinalVerde 		= new int[6];
	private int[] 		retaFinalAmarelo 	= new int[6];
	private int[] 		retaFinalAzul 		= new int[6];
	private int[][]		podio 				= new int[4][2];
	private int			turno				= 0;
	
// ____________________________________________________________________________________________________________________________
//
		
	protected Moment() {
			this.initializeBoardInfo();
	}
	
	// Inicializacao ------------------
	private void initializeBoardInfo() {
		int[] 	casas_iniciais 			= this.getCasasIniciais();
		int[][]	path					= this.getPath();
		int[] 	reta_final_vermelho 	= this.getRetaFinalVermelho();
		int[] 	reta_final_verde 		= this.getRetaFinalVerde();
		int[] 	reta_final_amarelo 		= this.getRetaFinalAmarelo();
		int[] 	reta_final_azul 		= this.getRetaFinalAzul();
		
		
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
		
		this.setAllCasas(
				casas_iniciais,
				path,
				reta_final_vermelho,
				reta_final_verde,
				reta_final_amarelo,
				reta_final_azul
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
			int			turno
			) {
		this.setCasasIniciais(casas_iniciais);
		this.setPath(path);
		this.setRetaFinalVermelho(reta_final_vermelho);
		this.setRetaFinalVerde(reta_final_verde);
		this.setRetaFinalAmarelo(reta_final_amarelo);
		this.setRetaFinalAzul(reta_final_azul);
		this.setPodio(podio);
		this.setTurno(turno);
	}
	public void setAllCasas(
			int[] casas_iniciais,
			int[][] path,
			int[] reta_final_vermelho,
			int[] reta_final_verde,
			int[] reta_final_amarelo,
			int[] reta_final_azul) {
		this.setCasasIniciais(casas_iniciais);
		this.setPath(path);
		this.setRetaFinalVermelho(reta_final_vermelho);
		this.setRetaFinalVerde(reta_final_verde);
		this.setRetaFinalAmarelo(reta_final_amarelo);
		this.setRetaFinalAzul(reta_final_azul);
		
	}

	public void setCasasIniciais		(int[] casas_iniciais) {
	    this.casasIniciais = casas_iniciais;
	}
	public void setPath					(int[][] path) {
	    this.path = path;
	}
	public void setRetaFinalVermelho	(int[] reta_final_vermelho) {
	    this.retaFinalVermelho = reta_final_vermelho;
	}
	public void setRetaFinalVerde		(int[] reta_final_verde) {
	    this.retaFinalVerde = reta_final_verde;
	}
	public void setRetaFinalAmarelo		(int[] reta_final_amarelo) {
	    this.retaFinalAmarelo = reta_final_amarelo;
	}
	public void setRetaFinalAzul		(int[] reta_final_azul) {
	    this.retaFinalAzul = reta_final_azul;
	}
	public void setPodio				(int[][] podio) {
	    this.podio = podio;
	}
	public void setTurno				(int turno) {
	    this.turno = turno;
	}
	
	// GET ----------------------------
	public int[] 	getCasasIniciais		() {
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
	public int 		getTurno				() {
	    return turno;
	}

	//	Singleton ------------------------------------------
	public static Moment getInstance() {
		if (instance == null) {
			instance = new Moment();
		}
		return instance;
	}

	
}