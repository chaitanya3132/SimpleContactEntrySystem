package contactentrysystem.demo.contacts.repository;

import contactentrysystem.demo.contacts.model.Contact;
import contactentrysystem.demo.contacts.model.Type;

import java.util.List;

public interface ContactRepository {

    Contact create(Contact contact);
    Contact get(Long id);
    void update(Long id, Contact contact);
    void delete(Long id);
    List<Contact> getAll();
    List<Contact> getContactList(Type type);
}
