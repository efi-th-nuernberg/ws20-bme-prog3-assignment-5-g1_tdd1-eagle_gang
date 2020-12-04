import org.junit.Test;
import static org.junit.Assert.*;

public class LifeTest {
    
    @Test
    public void createNewCell() {  
        // Arrange: drei lebende Zellen
        Life l = new Life(5, 5);
        l.setAlive(0, 0);
        l.setAlive(0, 1);
        l.setAlive(0, 2);

        // Act: Berechnung der Folgegeneration
        l.nextGeneration();

        // Assert: Rasterpunkt mit drei Nachbarn sollte jetzt leben
        assertTrue(l.isAlive(1,1));
    }


    @Test
    public void destroyLonelyCell() {
      // Arrange: Einsame Zellen
      Life l = new Life(5,5);
      l.setAlive(3,2);
      // Act: Berechnung der Folgegeneration
      l.nextGeneration();
      // Assert: Einsamer Rasterpunkt sollte nun tot sein
      assertFalse("Error: lonely cell still alive", l.isAlive(3,2));
    }


    @Test
    public void keepAliveCell() {
      // Test Case for 2 living neighbors
      Life l = new Life(5,5);
      l.setAlive(2,2);
      l.setAlive(3,1);
      l.setAlive(3,2);
      l.nextGeneration();
      assertTrue("Error: cell with two neighbors died",l.isAlive(3,2));
       // Test Case for 3 living neighbors
      Life f = new Life(5,5);
      f.setAlive(2,2);
      f.setAlive(3,1);
      f.setAlive(2,1);
      f.setAlive(3,2);
      f.nextGeneration();
      assertTrue("Error: cell with three neighbors died", f.isAlive(3,2));
    }


    @Test
    public void destroyCrowdedCell() {
      Life l = new Life(5,5);
      l.setAlive(2,2);
      l.setAlive(3,1);
      l.setAlive(2,1);
      l.setAlive(4,2);
      l.setAlive(3,2);
      l.nextGeneration();
      assertFalse("Error: cell with more then three neighbors survived", l.isAlive(3,2));
    }


}
