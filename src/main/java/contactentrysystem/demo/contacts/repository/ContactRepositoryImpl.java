package contactentrysystem.demo.contacts.repository;

import contactentrysystem.demo.contacts.model.Contact;
import contactentrysystem.demo.contacts.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private ContactJpaRepository contactJpaRepository;
    @Autowired
    private PhoneJpaRepository phoneJpaRepository;
    @Override
    public Contact create(Contact contact) {
        Contact savedContact = contactJpaRepository.save(contact);
        return savedContact;
    }

    @Override
    public Contact get(Long id) {
        return contactJpaRepository.findById(id).get();
    }

    @Override
    public void update(Long id, Contact contact) {

        Contact savedContact = contactJpaRepository.findById(id).get();
        phoneJpaRepository.deleteByContactId(id);
        contact.setId(id);
        contactJpaRepository.save(contact);
    }

    @Override
    public void delete(Long id) {
        contactJpaRepository.deleteById(id);
    }

    @Override
    public List<Contact> getAll() {
        return contactJpaRepository.findAll();
    }

    public List<Contact> getContactList(Type type) {
        return contactJpaRepository.findContactsByPhone_type(type, Sort.by(Sort.Direction.ASC,
                "Name.last","Name.first"));
    }
}
