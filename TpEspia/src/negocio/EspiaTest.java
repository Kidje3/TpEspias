package negocio;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EspiaTest {

    private Espia espia;

    @Before
    public void iniciar() {
        
        espia = new Espia("Espia", 51.5074, -0.1278);
    }

    @Test
    public void testDarNombre() {
        
        assertEquals("Espia", espia.darNombre());
    }

    @Test
    public void testDarLatitud() {
       
        assertEquals(51.5074, espia.darLatitud(), 0.0001); 
    }

    @Test
    public void testDarLongitud() {
        
        assertEquals(-0.1278, espia.darLongitud(), 0.0001);
    }

}