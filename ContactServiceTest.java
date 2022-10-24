package projectApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactServiceTest {

	
	@Test
	void testAddContactMethod() {
		// create a contact
		ContactService contactService = new ContactService();
		String testID = contactService.generateUniqueId();
		Contact contact = new Contact(testID, "Larry", "Joe", "7025554900", "6029 Elm Street");
		
		// add contact to the list
		contactService.addContact(contact);
		
		// confirm contact is in the list, count is not zero
		assertTrue(!contactService.getContactLIst().isEmpty());
		assertTrue(contactService
				.getContactLIst()
				.elementAt(0)
				.getContactID()
				.equals(testID));
		assertTrue(contactService.getNumContacts() > 0);
	}
	
	@Test
	void testAddContactWithVariables() {
		ContactService contactService = new ContactService();
		String testID = contactService.generateUniqueId();
		
		// add contact to the list
		contactService.addContact(testID, "Larry", "Joe", "7025554900", "6029 Elm Street");
		
		// confirm contact is in the list, count is not zero
		assertTrue(!contactService.getContactLIst().isEmpty());
		assertTrue(contactService
				.getContactLIst()
				.elementAt(0)
				.getContactID()
				.equals(testID));
		assertTrue(contactService.getNumContacts() > 0);
	}
	
	@Test
	void testRemoveContactMethod() {
		ContactService contactService = new ContactService();
		
		// create new contact
		Contact contact = new Contact("123456", "Larry", "Joe", "7025554900", "6029 Elm Street");
		
		// try to remove with null id
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.removeContact("");
		});
		
		// try to remove with id that's too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.removeContact("12345678901");
		});
		
		// try to remove from empty list
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.removeContact("1234567890");
		});
		
		// add the contact
		contactService.addContact(contact);
		
		// remove a contact that isn't there
		contactService.removeContact("123457");
		
		// contact list is not empty, count is not zero
		// contact not removed because contact doesn't exist
		assertTrue(!contactService.getContactLIst().isEmpty());
		assertTrue(contactService.getNumContacts() != 0);
		
		// remove correct contact
		contactService.removeContact("123456");
		
		// list is empty, count is zero, contact successfully removed
		assertTrue(contactService.getNumContacts() == 0);
		assertTrue(contactService.getContactLIst().isEmpty());
		
	}
	
	@Test
	void testUpdateContactMethodErrors() {
		ContactService contactService = new ContactService();
		
		// contact list is empty
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("123456", "Lawrence", 1);
		});
		
		// create new contact, add to list
		Contact contact = new Contact("123456", "Matt", "Haggard", "2565555555", "123 Anywhere St.");
		contactService.addContact(contact);
		
		// check that contact was added
		assertTrue(!contactService.getContactLIst().isEmpty());
		
		// id is too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("12345678901", "Haggard", 1);
		});
		
		// id is null
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact(null, "Haggard", 1);
		});
		
		// update value is null
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("123456", null, 1);
		});
		
		// selection value is less than zero
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("123456", "Haggard", -1);
		});
		
		// print "contact not found" to console
		contactService.updateContact("123457", "Haggard", 1);
		
		// print "contact not updated" to console
		contactService.updateContact("123456", "Haggard", 5);
		
	}
	
	@Test
	void testUpdateContactMethod() {
		ContactService contactService = new ContactService();
		Contact contact = new Contact("123456", "Matt", "Haggard", "2565555555", "123 Anywhere St.");
		contactService.addContact(contact);
		assertTrue(!contactService.getContactLIst().isEmpty());
		
		// update first name
		contactService.updateContact("123456", "Amber", 1);
		assertTrue(contactService
				.getContactLIst()
				.elementAt(0)
				.getName()
				.equals("Amber Haggard"));
		
		// update last name
		contactService.updateContact("123456", "Murdoc", 2);
		assertTrue(contactService
				.getContactLIst()
				.elementAt(0)
				.getName()
				.equals("Amber Murdoc"));
		
		// update phone number
		contactService.updateContact("123456", "2561112222", 3);
		assertTrue(contactService
				.getContactLIst()
				.elementAt(0)
				.getPhoneNumber()
				.equals("2561112222"));
		
		// update address
		contactService.updateContact("123456", "567 Smackdown Blvd.", 4);
		assertTrue(contactService
				.getContactLIst()
				.elementAt(0)
				.getContactAddress()
				.equals("567 Smackdown Blvd."));
		
		// update first name too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.updateContact("123456", "MatthewChad", 1);
		});
				
		// check that list has been updated
		// only one contact in list, check that it's updated by checking name
		assertTrue(contactService.getNumContacts() == 1);
		assertTrue(contactService.getContactLIst().elementAt(0)
				.getName().equals("Amber Murdoc"));
				
	}
}