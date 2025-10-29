import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class DataFormatException extends Exception {
    public DataFormatException(String msg) {
        super(msg);
    }
}

class StudentScore {
    private String name;
    private int score;

    public StudentScore() {
    }

    public StudentScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

class StudentScoreProcessor {
    public static StudentScore parseStudentLine(String name) throws DataFormatException{
        String[] parts = name.split(",");

        if(parts.length != 2){
            throw new DataFormatException("Line must have exactly two values: name and score");
        }
        try{
            String name2 = parts[0].trim();
            int score = Integer.parseInt(parts[1].trim());
            return new StudentScore(name, score);
        }catch (NumberFormatException e){throw new DataFormatException("Invalid score format: "+parts[1]);}
    }

    public static float computeClassAverage(Path csvFile) throws DataFormatException, IOException {
        float avg;
        try (BufferedReader reader = Files.newBufferedReader(csvFile)) {
            String line;
            reader.readLine();
            avg = 0;
            int sum = 0, count = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new DataFormatException("Missing fields in line: " + line);
                }
                int score;
                try {
                    score = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new DataFormatException("Invalid score for " + parts[0].trim() + ": " + parts[1]);
                }
                sum += score;
                count++;
            }
            if (count > 0) {
                avg = (float) sum / count;
            }
        }
        return avg;
    }

    public static void editCsvFile(Path targetCsvFile, List<StudentScore> students) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(targetCsvFile)) {
            writer.write("Name,Score");
            writer.newLine();

            for (StudentScore s : students) {
                String name = s.getName();
                int score = s.getScore();
                String line = String.join(",", name, String.valueOf(score));
                writer.write(line);
                writer.newLine();
            }
        }
        System.out.println("Cleaned CSV written to " + targetCsvFile);
    }
}

public class FileHandleStorage {
    public static void main(String[] args) throws IOException, DataFormatException {
        List<StudentScore> scores = List.of(
                new StudentScore("Alice", 89),
                new StudentScore("Bob", 72),
                new StudentScore("Carol", 95),
                new StudentScore("Dave", 58),
                new StudentScore("Eve", 76),
                new StudentScore("Frank", 62),
                new StudentScore("Grace", 83),
                new StudentScore("Heidi", 91),
                new StudentScore("Ivan", 47),
                new StudentScore("Judy", 68),
                new StudentScore("Karl", 54),
                new StudentScore("Lena", 88),
                new StudentScore("Mallory", 77),
                new StudentScore("Nira", 64),
                new StudentScore("Olivia", 99),
                new StudentScore("Peggy", 73),
                new StudentScore("Quentin", 55),
                new StudentScore("Rupert", 82),
                new StudentScore("Sybil", 60),
                new StudentScore("Trent", 93),
                new StudentScore("Uma", 70),
                new StudentScore("Victor", 49),
                new StudentScore("Walter", 80),
                new StudentScore("Xena", 66),
                new StudentScore("Yuri", 57),
                new StudentScore("Zara", 92),
                new StudentScore("Adrian", 61),
                new StudentScore("Becky", 85),
                new StudentScore("Calvin", 75),
                new StudentScore("Diana", 69)

//              More Names and Scores:
//                Ethan, 90
//                Fiona, 78
//                George, 53
//                Hannah, 96
//                Irene, 84
//                Jason, 59
//                Kara, 67
//                Leon, 81
//                Monica, 56
//                Nelson, 74
//                Opal, 63
//                Paula, 94
//                Quincy, 50
//                Rita, 71
//                Stan, 86
//                Tina, 52
//                Ulysses, 79
//                Vera, 65
//                Wendy, 97
//                Xander, 51
//                Yasmine, 98
//                Zack,48

        );
        StudentScoreProcessor.editCsvFile(Path.of("C:\\meing\\thtsme\\15_Days_of_Java_Learning\\Day_3\\src\\main\\students.csv"), scores);
        float average = StudentScoreProcessor.computeClassAverage(Path.of("C:\\meing\\thtsme\\15_Days_of_Java_Learning\\Day_3\\src\\main\\students.csv"));
        System.out.printf("%.1f", average);
    }
}
