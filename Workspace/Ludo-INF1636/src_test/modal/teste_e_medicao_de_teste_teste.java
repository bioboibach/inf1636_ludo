package modal;

import org.junit.*;
import static org.junit.Assert.*;


//	TODO
//	referencia, dps tem q apagar
public class teste_e_medicao_de_teste_teste {
	private static Jogo servico;
	
	@Before
	public void setUp() throws Exception{
		servico = Jogo.getInstance();
		servico.initialize_jogo();
	}
	
	@Test
	public void test_unit() {
		Casa c1, c2;
		c1 = servico.get_player(0).get_peca(0).get_current_tile();
		c2 = Tabuleiro.getInstance().get_path_index(0);

		assertEquals(c1, c2);
	}
	
}
