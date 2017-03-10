import org.junit.Test;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.After;

/**
 * ScattTest.java
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 */

/**
 * Class to test Scatt class.
 *
 * @author Kara Beason
 * @author Cydney Caldwell
 * @author Michelle Melton
 * @version Feb 2017
 */
public class ScattTest
{
      private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

      @Before
      public void setUpStreams()
      {
          System.setOut(new PrintStream(outContent));
          System.setErr(new PrintStream(errContent));
      }

      @After
      public void cleanUpStreams()
      {
          System.setOut(null);
          System.setErr(null);
      }

      @Test
      public void out()
      {
        System.out.print("Report Pass");
        assertEquals("Report Pass", outContent.toString());
      }
      @Test
      public void err()
      {
          System.err.print("Report Fail");
          assertEquals("Report Fail", errContent.toString());
      }
}
