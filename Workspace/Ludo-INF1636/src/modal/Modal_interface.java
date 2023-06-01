package modal;


public class Modal_interface {
	
	private static Modal_interface instance;

	private Modal_interface() {}
	
	public int roll() {
		return Dado.getInstance().roll();
	}
	
	public Casa get_path_index(int index){
		return Tabuleiro.getInstance().get_path_index(index);
	}
	public Casa get_casas_iniciais_index(int index){
		return Tabuleiro.getInstance().get_casas_iniciais_index(index);
	}
	public Casa get_reta_final_vermelho_index(int index){
		return Tabuleiro.getInstance().get_reta_final_vermelho_index(index);
	}
	public Casa get_reta_final_verde_index(int index){
		return Tabuleiro.getInstance().get_reta_final_verde_index(index);
	}
	public Casa get_reta_final_amarelo_index(int index){
		return Tabuleiro.getInstance().get_reta_final_amarelo_index(index);
	}
	public Casa get_reta_final_azul_index(int index){
		return Tabuleiro.getInstance().get_reta_final_azul_index(index);
	}
	
	public static Modal_interface getInstance() {
		if (instance == null) {
			instance = new Modal_interface();
		}
		return instance;
	}
}
