package modal;

import controller.Moment;

public class ModalAPI {
	private static ModalAPI instance;
	private Jogo jogoInst = Jogo.getInstance();
	private Tabuleiro tabuleiroInst = Tabuleiro.getInstance();
	private Moment	moment = Moment.getInstance();
	
	//	Jogador anterior (para saber quando os valores 5 e 6 do dado representam um movimento e por isso um peao deve ser selecionado)
	private int lastPlayer = 0;
	
// ____________________________________________________________________________________________________________________________
//
	private ModalAPI() {}
	
	//	Operacoes -------------------------------------------	
	public void run_turn(int pathIndex, int finalPathIndex, int diceVal) {
		System.out.println("index = " + diceVal);
        System.out.println("lastTurn = " + lastPlayer);
//        if((diceVal == 5 || diceVal == 6) && moment.getPlayer() == lastPlayer) {
//        	System.out.println("Regra do 5 e 6: 2a vez");
//		}
//        else {
//        	nextPlayer();
//        	jogoInst.initializeTurn(pathIndex, finalPathIndex, diceVal);
//        }
//        if(pathIndex == -2 || finalPathIndex == -2) {
//        	return;
//		}
    	jogoInst.initializeTurn(pathIndex, finalPathIndex, diceVal);
	}

	public void clear_tabuleiro() {
		Tabuleiro.getInstance().clear_tabuleiro();
	}
	public void new_game() {
		jogoInst.new_game();
	}

	public int roll() {
		return Dado.getInstance().roll();
	}

	//	SET ----------------------------------------

	public void set_turn(int t) {
		jogoInst.set_currentPlayer(t);
	}
	public void set_dice(int t) {
		jogoInst.set_dice(t);
	}

	private void nextPlayer() {
		lastPlayer = (lastPlayer + 1) % 4;
	}
	//	GET ----------------------------------------
	public Casa get_path_index(int index){
		return Tabuleiro.getInstance().get_pathIndex(index);
	}
	public Casa get_casas_iniciais_index(int index){
		return Tabuleiro.getInstance().get_casasIniciaisIndex(index);
	}
	public Casa get_reta_final_vermelho_index(int index){
		return Tabuleiro.getInstance().get_retaFinalIndex(index, 0);
	}
	public Casa get_reta_final_verde_index(int index){
		return Tabuleiro.getInstance().get_retaFinalIndex(index, 1);
	}
	public Casa get_reta_final_amarelo_index(int index){
		return Tabuleiro.getInstance().get_retaFinalIndex(index, 2);
	}
	public Casa get_reta_final_azul_index(int index){
		return Tabuleiro.getInstance().get_retaFinalIndex(index, 3);
	}
	
	public int get_player_turn() {
		return jogoInst.get_currentPlayer(); 
	}
	public int[] get_peca_indexes(int player_id, int peca_id) {
		Peca p = jogoInst.get_player(player_id).get_peca(peca_id);
		return Tabuleiro.getInstance().get_currentCasaIndex(p);
	}
	public int get_roll() {
		return jogoInst.get_currentDice();
	}
	
	protected int get_currentPlayer() {
		return lastPlayer;
	}
	
	//	Singleton ------------------------------------------
	public static ModalAPI getInstance() {
		if (instance == null) {
			instance = new ModalAPI();
		}
		return instance;
	}
}