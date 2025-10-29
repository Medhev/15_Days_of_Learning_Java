package Day_9.src.test.java;

import Day_9.src.main.java.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testGetMessage() {
        String expected = "Hello World!";
        String actual = App.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
