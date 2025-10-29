import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Person {
    private int age;

    public Person(String name, int age) {
        this.age = age;
    }

    public Person(){}

    public int getAge() {
        return age;
    }
}

class RandomNameGenerator {
    public String randomMethod (){
        String characters = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();
        Random rand = new Random();
        int length = rand.nextInt(characters.length());
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for (char c : text) {
            randomString.append(c);
        }
        return randomString.toString();
    }
}

class AgeBracketLogic {

    public static String BracketLogic(int age) {
        int limit = (age / 10) * 10;
        return limit + "-" + (limit + 9);
    }
}

class PipeLining extends AgeBracketLogic {

    public static Map<String, Long> personCountOnMapping (List<Person> person){
        return person.stream()
                .map(p -> BracketLogic(p.getAge()))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }
}

public class AgeLimit {
    public static void main(String[] args) {
        RandomNameGenerator randName = new RandomNameGenerator();
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the list of persons to be added: ");
        int persons = scan.nextInt();
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < persons;i++){
            String randomName = randName.randomMethod();
            int randAge = rand.nextInt(1,65);
            people.add( new Person(randomName ,randAge));
            System.out.println(randomName + ": " + randAge);
        }
        Map<String, Long> counts = PipeLining.personCountOnMapping(people);
        System.out.println("Age brackets → counts: " + counts);

    }
}