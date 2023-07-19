package modal;

import static org.junit.Assert.assertTrue;

import org.junit.*;

public class Teste_inicializacao {
	private static Dado dado;
	private static Peca p1;
	private static Peca p2;
	private static Casa c1;
	private static Casa c2;
	
	private static Object jogo;
	
	@BeforeClass
	public static void setUp() throws Exception{
		// 	Dado
		dado = Dado.getInstance();
		
		//	Peca
		p1  = new Peca(0);
		c1 = new Casa(1);
		
		// 	Casa
		p2 = new Peca(0);
		c2 = new Casa(0);
		
	}
	
	//	Dado
	@Test 
	public void dado_roll() {
		int res = dado.roll();
		assert(res >= 0 && res <= 6);
	}
	
	@Test 
	public void dado_setRoll() {
		int res = dado.set_roll_value(3);
		assert(res == 3);
	}
	
	
	//	Peca
	@Test
	public void peca_changeCasa() {
		p1.change_casa(c1);
		assert(p1.get_currentCasa() == c1);
	}
	
	//	Casa
	@Test
	public void casa_addPeca() {
		c2.add_peca(p1);
		c2.add_peca(p2);
		
		assert(c2.get_num_pecas() == 2);
	}
	@Test
	public void casa_removePeca() {
		c2.remove_peca(p2);
		
		assert(c2.get_num_pecas() == 1);
	}
	
	
	//	Singleton
	  @Test
	    public void jogo_getInstance() {
	        jogo = Jogo.getInstance();

	        assertTrue(jogo instanceof Jogo);
	    }
	
//	@Test
//	public void initialization() {
//		Casa c1, c2;
//		c1 = jg.get_player(0).get_peca(0).get_currentCasa();
//		c2 = tb.get_pathIndex(0);
//
//		assertEquals(c1, c2);
//	}
//	
//	@Test
//	public void end_game_condition() {
//		tb.get_casasIniciaisIndex(0).remove_peca(jg.get_player(0).get_peca(1));
//		tb.get_casasIniciaisIndex(0).remove_peca(jg.get_player(0).get_peca(2));
//		tb.get_casasIniciaisIndex(0).remove_peca(jg.get_player(0).get_peca(3));
//		for (int i = 0; i < 4; i++) {
////			tb.get_retaFinalIndex(5, 0).add_peca(jg.get_player(0).get_peca(i));
//		}
//		assertSame(jg.check_EndGameCondition(), 0);
//	}	
	
}
