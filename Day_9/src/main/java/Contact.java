package Day_9.src.main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class InvalidPhoneNumberException extends Exception {
    public InvalidPhoneNumberException(String message) {
        super(message + "Invalid Phone Number");
    }
}

class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String message) {
        super(message + "Contact Not Found");
    }
}

class ContactAlreadyExistsException extends Exception {
    public ContactAlreadyExistsException(String message) {
        super(message + "Contact Already Exists");
    }
}


public class Contact {
    public static ArrayList<Contact> contacts = new ArrayList<>();
    private final String name;
    private final long phoneNumber;

    public Contact() {
        name = "";
        phoneNumber = 0;
    }

    public Contact(String name, long phoneNumber) throws Exception {
        if (phoneNumber < 0 || phoneNumber > 9999999999L) {
            throw new InvalidPhoneNumberException("INVALID :");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static void saveContents() throws Exception {
        try (FileWriter fw = new FileWriter("contacts.txt", true)) {
            for (Contact contact : contacts) {
                String loader = contact.getName() + " : " + contact.getPhoneNumber() + "\n";
                fw.write(loader);

            }
            System.out.println("Contact saved successfully");
        }
    }

    public String getName() {
        return name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    void addContact(Contact contact) throws AssertionError, Exception {
        if (contact == null) {
            System.out.println("Invalid contact: cannot add null");
            return;
        }
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        if (contacts.contains(contact)) {
            throw new ContactAlreadyExistsException("Warning! Contact already exists: " + contact.getName() + " : " + contact.getPhoneNumber() + "\n");
        }
        contacts.add(contact);
        saveContents();
        System.out.println("Contact added successfully");
    }

    void viewAllContacts() {
        if (contacts == null || contacts.isEmpty()) {
            System.out.println();
            System.out.println("Empty Contacts!");
            return;
        }

        for (Contact contact : contacts) {
            System.out.println("______________________________");
            System.out.println();
            System.out.println("Name : " + contact.getName());
            System.out.println("Phone Number : " + contact.getPhoneNumber());
            System.out.println("______________________________");
            System.out.println();
        }
    }

    public String searchContact(String name) {

        String lowerCaseName = name.toLowerCase();
//      List<String> matchedString = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().equals(lowerCaseName)) {
                return contact.getName() + " : " + contact.getPhoneNumber();
            }
        }
        return "";
    }

    void removeContact(Contact toRemove) throws AssertionError, Exception {
        if (contacts == null || toRemove == null) {
            System.out.println("Invalid input: contacts or contact is null");
            System.out.println();
            return;
        }

        boolean found = false;
        Iterator<Contact> it = contacts.iterator();
        while (it.hasNext()) {
            Contact c = it.next();
            if (c.getName().equals(toRemove.getName()) && c.getPhoneNumber() == toRemove.getPhoneNumber()) {
                it.remove();
                saveContents();
                System.out.println("Contact removed successfully");
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ContactNotFoundException("Warning! Contact not found: " + toRemove.getName() + " : " + toRemove.getPhoneNumber() + "\n");
        }
    }

    public void loadContents() throws Exception {
        contacts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s*:\\s*"); // or "," if separated by commas
                if (parts.length < 2) continue; // skip or handle invalid lines
                String name = parts[0];
                long phoneNumber = Long.parseLong(parts[1]);
                contacts.add(new Contact(name, phoneNumber));
            }
        }
    }


    public static class ContactAtScheduleProject_7 {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.println();
            System.out.println("Welcome to My Contacts App");
            boolean exit = true;
            Contact contact = new Contact();

            System.out.println();

            while (exit) {
                try {
                    System.out.println("______________________________");
                    System.out.println();
                    System.out.println("1. Add Contact");
                    System.out.println("2. View All Contacts");
                    System.out.println("3. Search Contact");
                    System.out.println("4. Remove Contact");
                    System.out.println("5. Save Contacts");
                    System.out.println("6. Load Contacts");
                    System.out.println("7. Exit");
                    System.out.println("______________________________");
                    System.out.println();
                    System.out.print("Enter your choice : ");
                    int choice = scan.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter the name : ");
                            String name = scan.next();
                            System.out.print("Enter the phone number : ");
                            long phoneNumber = scan.nextLong();
                            Contact contact1 = new Contact(name, phoneNumber);
                            contact.addContact(contact1);
                            break;
                        case 2:
                            contact.viewAllContacts();
                            break;
                        case 3:
                            System.out.print("Enter the name to be searched : ");
                            String searchingName = scan.next();
                            String matchedString = contact.searchContact(searchingName);
                            System.out.println(matchedString);
                            break;
                        case 4:
                            System.out.print("Enter the name to be removed : ");
                            scan.nextLine(); // clear buffer

                            String removingName = scan.nextLine();
                            // Remove contact based on name (assuming unique name for simplicity)
                            // We have to find and remove the object
                            Contact contactToRemove = null;
                            for (Contact c : contacts) {
                                if (c.getName().equals(removingName)) {
                                    contactToRemove = c;
                                    break;
                                }
                            }
                            if (contactToRemove != null) {
                                contact.removeContact(contactToRemove);
                                break;
                            }
                            System.out.println("Contact does not exist");
                            break;
                        case 5:
                            saveContents();
                            break;
                        case 6:
                            contact.loadContents();
                            break;
                        case 7:
                            System.out.println("Bye Bye!");
                            exit = false;
                            break;
                        default:
                            System.out.print("Invalid Choice\nTry Again!");
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    scan.nextLine();
                }
            }
        }
    }
}

