package board;

public class Team {
	
	TeamColors color;
	//Armazena a quantidade de Pieces na base
	//obs: um time comeca com todos os seu Pieces (4 no total)
	int qtPieces= 4;
	
	public Team(int team) {
		//Define-se a cor da equipe baseado no argumento int
		color = TeamColors.values()[team];
	}
}
