import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;


public class ContactListApp {

    private static final String MENU_BAR = "______________________________";

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome to My Contacts App");
        System.out.println();

        // Use a dedicated contact book to manage contacts
        ContactBook contactBook = new ContactBook();
        boolean isRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (isRunning) {
                printMenu();
                int choice = readInt(scanner, "Enter your choice:");

                switch (choice) {
                    case 1:
                        handleAddContact(scanner, contactBook);
                        break;
                    case 2:
                        contactBook.viewAllContacts();
                        break;
                    case 3:
                        handleSearchContact(scanner, contactBook);
                        break;
                    case 4:
                        handleRemoveContact(scanner, contactBook);
                        break;
                    case 5:
                        System.out.println("Bye Bye!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        System.out.println("Try Again!");
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred:");
            System.out.println(e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println(MENU_BAR);
        System.out.println();
        System.out.println("1. Add Contact");
        System.out.println("2. View All Contacts");
        System.out.println("3. Search Contact");
        System.out.println("4. Remove Contact");
        System.out.println("5. Exit");
        System.out.println(MENU_BAR);
        System.out.println();
    }

    private static void handleAddContact(Scanner scanner, ContactBook contactBook) {
        String name = readString(scanner, "Enter the name:");
        long phone = readLong(scanner, "Enter the phone number:");
        contactBook.addContact(new Contact(name, phone));
    }

    private static void handleSearchContact(Scanner scanner, ContactBook contactBook) {
        String name = readString(scanner, "Enter the name to be searched:");
        String result = contactBook.searchContact(name);
        System.out.println(result);
    }

    private static void handleRemoveContact(Scanner scanner, ContactBook contactBook) {
        String name = readString(scanner, "Enter the name to be removed:");
        contactBook.removeContact(new Contact(name, 0L));
    }

    // Robust readers that operate line-by-line to avoid Scanner token/newline issues
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static long readLong(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String line = scanner.nextLine().trim();
            try {
                return Long.parseLong(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }
}

/*
 Simple contact entry.
 Equality and hash code are based on case-insensitive name matching,
 so removing/searching by name behaves as users expect.
*/
class Contact {
    private final String name;
    private long phone;

    public Contact(String name, long phone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Contact)) return false;
        Contact other = (Contact) o;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return name + " - " + phone;
    }
}

/*
 ContactBook holds and manages multiple contacts.
*/
class ContactBook {
    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact c) {
        Optional<Contact> existing = findByName(c.getName());
        if (existing.isPresent()) {
            existing.get().setPhone(c.getPhone());
            System.out.println("Contact updated.");
        } else {
            contacts.add(c);
            System.out.println("Contact added.");
        }
    }

    public void viewAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        System.out.println("All Contacts:");
        for (Contact c : contacts) {
            System.out.println("- " + c);
        }
    }

    public String searchContact(String name) {
        return findByName(name)
                .map(c -> "Found: " + c.getName() + " - " + c.getPhone())
                .orElse("Contact not found.");
    }

    public void removeContact(Contact c) {
        boolean removed = contacts.removeIf(x -> x.getName().equalsIgnoreCase(c.getName()));
        if (removed) {
            System.out.println("Contact removed.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private Optional<Contact> findByName(String name) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
}