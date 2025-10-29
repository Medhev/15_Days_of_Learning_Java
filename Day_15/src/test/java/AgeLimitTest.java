import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class AgeLimitTest {

    @Test
    void AgeBracketLogic() {
        Assertions.assertEquals("20-29", AgeBracketLogic.BracketLogic(24));
    }

    @Test
    void personCountOnMapping() {
        List<Person> listOfNames = List.of(
                new Person("", 23),
                new Person("", 12),
                new Person("", 13)
        );
        Map<String, Long> expected = Map.of(
                "20-29", 1L,
                "10-19", 2L
        );
        Map<String, Long> actual = PipeLining.personCountOnMapping(listOfNames);
        Assertions.assertEquals(expected,actual);
    }
}
