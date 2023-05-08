import org.junit.*;
import static org.junit.Assert.*;

import board.Board;


public class teste
{
    @Test
    public void testeTile()
    {
        Tile t = new Tile();

        t.qtPieces = 5;

        //Teste 1 -> Verificar se a função retorna true quando há Tile definida
        assertEquals(true, is_vacant());
        //Teste 2 -> Verfifica se a função retorna false quando é atribuida um valor de nas Tiles
        assertEquals(true, is_penetrable());

        
        //Teste 3 -> Verifica se o numero de Peças na Tile é atribuido corretamente
        assertEquals(5, pieces_in_tile());

        //Teste 4-> Testa se a tile é indentificada como ocupada
        assertEquals(false, is_stacked());

        SafeTile j = new SafeTile();
        t.type = 's';
        j.type = SafeTile(s);
        //Teste 5 -> Garante que a SafeTile é descrita corretamente
        AssertEqual(t.type, j.type);
    }

    @Test
    public void testeTeam()
    {
        Team a = new Team();
        //Teste 5 -> Verifica se a cor do time é atribuida corretamente
        assertThat(a.team(1, -1).isEqualTo("yellow"));

    }

    
}