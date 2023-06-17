package modal;

import org.junit.*;
import static org.junit.Assert.*;

public class Teste_inicializacao {
	private static Jogo jg;
	private static Tabuleiro tb;
	
	@BeforeClass
	public static void setUp() throws Exception{
		jg = Jogo.getInstance();
		jg.initialize_jogo();
		tb = Tabuleiro.getInstance();
	}
	
	@Test
	public void initialization() {
		Casa c1, c2;
		c1 = jg.get_player(0).get_peca(0).get_current_casa();
		c2 = tb.get_path_index(0);

		assertEquals(c1, c2);
	}
	
	@Test
	public void end_game_condition() {
		tb.get_casas_iniciais_index(0).remove_peca(jg.get_player(0).get_peca(1));
		tb.get_casas_iniciais_index(0).remove_peca(jg.get_player(0).get_peca(2));
		tb.get_casas_iniciais_index(0).remove_peca(jg.get_player(0).get_peca(3));
		for (int i = 0; i < 4; i++) {
			tb.get_reta_final_index(5, 0).add_peca(jg.get_player(0).get_peca(i));
		}
		assertSame(jg.check_end_game_condition(), 0);
	}	
	
}
