import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class StudentProcessorTest {

    @Test
    void computeClassAverage() throws DataFormatException, IOException {
        Assertions.assertEquals(69.5, StudentScoreProcessor.computeClassAverage(Path.of("C:\\meing\\thtsme\\15_Days_of_Java_Learning\\Day_15\\src\\main\\students.csv")));
    }

    @BeforeEach
    void editCsvFile() throws IOException {
        List<StudentScore> listings = List.of(
                new StudentScore("Alice", 90),
                new StudentScore("Bob", 49)
        );
        StudentScoreProcessor.editCsvFile(Path.of("C:\\meing\\thtsme\\15_Days_of_Java_Learning\\Day_15\\src\\main\\students.csv"), listings);
    }

    @Test
    void parseLine() {
        String badLine = "Dana,notANumber";

        Assertions.assertThrows(DataFormatException.class, () -> {
            StudentScore stringLine = StudentScoreProcessor.parseStudentLine(badLine);
            Assertions.assertEquals(new StudentScore("Dana",23),stringLine);
        });
    }
}
