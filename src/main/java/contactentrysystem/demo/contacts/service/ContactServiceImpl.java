package contactentrysystem.demo.contacts.service;

import contactentrysystem.demo.contacts.model.Contact;
import contactentrysystem.demo.contacts.model.Phone;
import contactentrysystem.demo.contacts.model.Type;
import contactentrysystem.demo.contacts.repository.ContactRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepositoryImpl contactRepositoryImpl;

    @Override
    public List<Contact> getContacts() {
        return contactRepositoryImpl.getAll();
    }

    @Override
    public Contact createContact(Contact contact) {
      contactRepositoryImpl.create(contact);
      return contact;

    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepositoryImpl.get(id);
    }

    @Override
    public void updateContactById(Long id, Contact contact) {
        contactRepositoryImpl.update(id,contact);
    }

    @Override
    public  void deleteContactById(Long id) {
        contactRepositoryImpl.delete(id);
    }

    public List<Contact> getCallList() {
        return contactRepositoryImpl.getContactList(Type.home);
    }

    public List<Contact> getAllContacts() {
        return contactRepositoryImpl.getAll();
    }
}
