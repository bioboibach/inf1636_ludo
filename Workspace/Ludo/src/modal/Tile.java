package modal;

/*
 * =======  atributos  =======
 * id -> pk, toda casa tem um id diferente
 * p1 e p2 ->  objeto piece se existir uma piece no tile
 * num_pieces -> quantas pieces tem no tile
 * 
 * 
 * ==============================================
 * type -> indica o tipo e a cor do tile
 * 		-> 0 = basic tile
 * 		-> [1..5] = tiles home, safe, entrance, colored e finish do player1
 * 		-> [6..10] = tiles home, safe, entrance, colored e finish do player2
 * 		-> [11..15] = tiles home, safe, entrance, colored e finish do player3
 * 		-> [16..20] = tiles home, safe, entrance, colored e finish do player4
 * ===============================================
 *
 * esse tipo provavelmente n vai ser assim. isso eh so uma ideia inicial
 * outra opcao:
 * 
 * type -> indica tipo do tile:
 * 		-> 0 = basic
 * 		-> 1 = home 
 * 		-> 2 = safe
 * 		-> 3 = entrance
 * 		-> 4 = colored
 * 		-> 5 = finish
 * 
 * color -> indica a cor do tile [0..3] = player[1..4]
 * 
 * =======    =======
 * 
 * 
 * 
 * 
 * */

public class Tile {
	int id;
	int type;
	Piece p1 = null;
	Piece p2 = null;
	int color;
	int num_pieces = 0;
	
	public Tile(int input_id, int input_type, int input_color, Piece input_p1, Piece input_p2, int input_num_pieces) {
		id = input_id;
		type = input_type;
		color = input_color;
		p1 = input_p1;
		p2 = input_p2;
		num_pieces = input_num_pieces;
	}

//	construtor pra todos os tiles q n sao home
//	no inicio do jogo so os tiles de home sao ocupados com pecas
	public Tile(int input_id, int input_type, int input_color) {
		id = input_id;
		type = input_type;
		color = input_color;
	}

	public boolean is_vacant(/*Piece moving_piece*/) {
		if (num_pieces > 1)
			return false;
/*
		else if (moving_piece.get_color() != color)
			return false;
*/
		return true;
	}

	public boolean is_passable() {
		return !p1.is_stacked();
	}

//	funcoes que apenas retornam atributos do objeto

	public int pieces_in_tile() {
		return num_pieces;
	}
	
	public int get_id() {
		return id;
	}
	
	public boolean is_basic() {
		return type == 0;
	}
	public boolean is_home() {
		return type == 1;
	}
	public boolean is_safe() {
		return type == 2;
	}
	public boolean is_entrance() {
		return type == 3;
	}
	public boolean is_colored() {
		return type == 4;
	}
	public boolean is_finish() {
		return type == 5;
	}

}
