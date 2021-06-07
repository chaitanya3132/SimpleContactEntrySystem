package contactentrysystem.demo.contacts.service;

import contactentrysystem.demo.contacts.model.Contact;
import contactentrysystem.demo.contacts.model.Phone;

import java.util.List;

public interface ContactService {
    List<Contact> getContacts();
    Contact createContact(Contact contact);
    Contact getContactById(Long id);
    void updateContactById(Long id, Contact contact);
    void deleteContactById(Long id);

    List<Contact> getCallList();
}
