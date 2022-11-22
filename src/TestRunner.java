import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

  /**
  * This is the test runner used to run
  * the tests.
  * 
  */
  public static void main(String[] args) {
    Result r = JUnitCore.runClasses(CardGameTestSuite.class);

    for (Failure x : r.getFailures()) {
      System.out.println(x.toString());
    }

    System.out.println("The outcomes of the tests: " + r.wasSuccessful());
  }
}