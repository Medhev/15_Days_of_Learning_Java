package Day_9.src.test.java;

import Day_9.src.main.java.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;


class ContactTest {

    Contact contact;

    @BeforeEach
    void setUp() throws Exception {
        contact = new Contact();
        contact.loadContents();
    }

    @AfterEach
    void tearDown() throws Exception {
        try (FileWriter fw = new FileWriter("contacts.txt")) {
            fw.write(""); // Clear file contents
        }
        Contact.contacts.clear();
    }

    @Test
    void getName() throws Exception {
        Contact testContact = new Contact("raja", 9444114586L);
        Contact.contacts.add(testContact);
        Contact found = null;
        for (Contact c : Contact.contacts) {
            if ("raja".equals(c.getName())) {
                found = c;
                break;
            }
        }
        Assertions.assertNotNull(found, "contact not found!");
        Assertions.assertEquals("raja", found.getName());
    }

    @Test
    void getPhoneNumber() throws Exception {
        Contact testContact = new Contact("test", 9042104903L);
        Contact.contacts.add(testContact);
        Contact found = null;
        for (Contact c : Contact.contacts) {
            if (9042104903L == (c.getPhoneNumber())) {
                found = c;
                break;
            }
        }
        Assertions.assertNotNull(found, "contact not found!");
        Assertions.assertEquals(9042104903L, found.getPhoneNumber());
    }

    @Test
    void searchContact() throws Exception {
        Contact testContact = new Contact("raja", 9444114586L);
        Contact.contacts.add(testContact);
        Assertions.assertEquals("raja : 9444114586", contact.searchContact("raja"));
    }
}
