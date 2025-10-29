import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Student {
    final String name;
    final String rollNo;
    final int[] marks;

    Student(String n, String r, int[] m) throws Exception {
        name = n;
        rollNo = r;
        marks = m;
        try {
            for (int i : m) {
                if (marks.length > 6 || marks.length == 0 && i < 0 || i > 100) {
                    throw new IllegalArgumentException("Invalid marks \nMarks should range (0-100)");
                }
            }
        } catch (Exception e) {
            throw new Exception("Invalid input");
        }

    }

    Student() {
        name = rollNo = " ";
        marks = new int[6];
    }

    static int totalMarks(int[] marks) {
        return Arrays.stream(marks).sum();
    }

    static double avgMarks(int[] marks) {
        return Math.round((Arrays.stream(marks).sum() / (double) marks.length - 1)*100.0)/100.0;
    }

    @Override
    public String toString() {
        return "Name : " + name + "\nRoll No : " + rollNo + "\nTotal Marks : " + totalMarks(marks) + "\nAverage Marks : " + avgMarks(marks);
    }
}


public class StudentsDataApp {

    public static void main(String[] args) {
        System.out.println("Welcome to My Student's Data App");
        Scanner input = new Scanner(System.in);

        Student obj2 = new Student();
        String name = obj2.name;
        String rollNo = obj2.rollNo;
        int[] marks;
        int i;

        System.out.print("Enter the number fo students: ");
        int numberOfStudents = input.nextInt();
        /*  Use arraylist to store multiple student details and
         * for our convenience of easy access */
        List<String> students = new ArrayList<>();

        for (i = 0; i < numberOfStudents; i++) {
            try {
                System.out.printf("Enter the name of student %d : ", i + 1);
                name = input.next();
                System.out.printf("Enter the roll no of student %d : ", i + 1);
                rollNo = input.next();
                System.out.printf("Enter the marks of student %d : ", i + 1);
                marks = new int[obj2.marks.length];
                for (int j = 0; j < marks.length; j++) {
                    obj2.marks[j] = input.nextInt();
                }

                Student obj = new Student(name, rollNo, obj2.marks);
                students.add(obj.toString());



            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try Again!");
                i--;
            }
        }
        int bestStudent = -1;
        int highestAvg = -1;
        for(int student =0;student< numberOfStudents;student++){
            double avg = Student.avgMarks(obj2.marks);
            if(avg > highestAvg){
                highestAvg = (int) avg;
                bestStudent = student;
            }
        }
        if(bestStudent != -1){
            System.out.println("The best student is "+students.get(bestStudent));
        }
    }
}
