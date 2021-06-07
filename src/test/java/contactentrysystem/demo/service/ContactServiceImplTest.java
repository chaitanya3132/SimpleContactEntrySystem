package contactentrysystem.demo.service;

import contactentrysystem.demo.contacts.model.Address;
import contactentrysystem.demo.contacts.model.Contact;
import contactentrysystem.demo.contacts.model.Name;
import contactentrysystem.demo.contacts.model.Phone;
import contactentrysystem.demo.contacts.model.Type;
import contactentrysystem.demo.contacts.repository.ContactRepositoryImpl;
import contactentrysystem.demo.contacts.service.ContactServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    @Mock
    private ContactRepositoryImpl contactRepositoryImpl;

    @InjectMocks
    private ContactServiceImpl contactServiceImpl;

    private Contact TEST_CONTACT_1;
    private Contact TEST_CONTACT_2;
    private Contact UPDATE_CONTACT;
    private Name TEST_NAME_1;
    private Name TEST_NAME_2;
    private Name UPDATE_NAME;
    private final List<Contact> TEST_CONTACT_LIST = new ArrayList<>();

    @Before
    public void setup() {

        Address TEST_ADDRESS = new Address();
        TEST_ADDRESS.setCity("TEST_CITY");
        TEST_ADDRESS.setState("TEST_STATE");
        TEST_ADDRESS.setStreet("TEST_STREET");
        TEST_ADDRESS.setZip("TEST_ZIP");

        // Test date set 1
        TEST_NAME_1 = Name.builder()
                .first("TEST_FIRST_NAME_1")
                .last("TEST_LAST_NAME_1")
                .middle("TEST_MIDDLE_NAME_1")
                .build();

        TEST_CONTACT_1 = Contact.builder()
                .address(TEST_ADDRESS)
                .name(TEST_NAME_1)
                .id(1234L)
                .email("TEST_EMAIL_1")
                .build();

        List<Phone> TEST_PHONE_CONTACT_1 = new ArrayList<>();
        Phone TEST_PHONE_1 = new Phone();
        TEST_PHONE_1.setContact(TEST_CONTACT_1);
        TEST_PHONE_1.setId(1234);
        TEST_PHONE_1.setNumber("TEST_NUMBER_1");
        TEST_PHONE_1.setType(Type.home);

        TEST_PHONE_CONTACT_1.add(TEST_PHONE_1);
        TEST_CONTACT_1.setPhone(TEST_PHONE_CONTACT_1);

        // Added test data set 1 into the list
        TEST_CONTACT_LIST.add(TEST_CONTACT_1);

        // Test data set 2
        TEST_NAME_2 = Name.builder()
                .first("TEST_FIRST_NAME_2")
                .last("TEST_LAST_NAME_2")
                .middle("TEST_MIDDLE_NAME_2")
                .build();

        TEST_CONTACT_2 = Contact.builder()
                .address(TEST_ADDRESS)
                .name(TEST_NAME_2)
                .id(5678L)
                .email("TEST_EMAIL_2")
                .build();

        List<Phone> TEST_PHONE_CONTACT_2 = new ArrayList<>();
        Phone TEST_PHONE_2 = new Phone();
        TEST_PHONE_2.setContact(TEST_CONTACT_2);
        TEST_PHONE_2.setId(1234);
        TEST_PHONE_2.setNumber("TEST_NUMBER_2");
        TEST_PHONE_2.setType(Type.work);

        TEST_PHONE_CONTACT_2.add(TEST_PHONE_2);
        TEST_CONTACT_2.setPhone(TEST_PHONE_CONTACT_2);

        // Added test data set 2 into the list
        TEST_CONTACT_LIST.add(TEST_CONTACT_2);

        UPDATE_NAME = Name.builder()
                .first("UPDATE_FIRST_NAME_2")
                .last("UPDATE_LAST_NAME_2")
                .middle("UPDATE_MIDDLE_NAME_2")
                .build();

        UPDATE_CONTACT = Contact.builder()
                .address(TEST_ADDRESS)
                .name(UPDATE_NAME)
                .id(5678L)
                .email("TEST_EMAIL_2")
                .build();
    }

    @Test
    public void createContactSuccess() {
        when(contactRepositoryImpl.create(TEST_CONTACT_1)).thenReturn(TEST_CONTACT_1);
        Contact response = contactServiceImpl.createContact(TEST_CONTACT_1);
        assertEquals(response.getName(), TEST_NAME_1);
    }

    @Test
    public void getContactsSuccess() {
        when(contactRepositoryImpl.getAll()).thenReturn(TEST_CONTACT_LIST);
        List<Contact> response = contactServiceImpl.getContacts();
        assertEquals(response, TEST_CONTACT_LIST);
    }

    @Test
    public void getContactByIdSuccess() {
        when(contactRepositoryImpl.get(1234L)).thenReturn(TEST_CONTACT_1);
        Contact response = contactServiceImpl.getContactById(1234L);
        assertEquals(response.getId().longValue(), 1234L);
    }

    @Test
    public void updateContactByIdSuccess() {
        when(contactRepositoryImpl.get(5678L)).thenReturn(UPDATE_CONTACT);
        contactServiceImpl.updateContactById(5678L, UPDATE_CONTACT);
        assertEquals(contactRepositoryImpl.get(5678L).getName().getFirst(), UPDATE_CONTACT.getName().getFirst());
    }

    @Test
    public void getCallListSuccess() {
        when(contactRepositoryImpl.getContactList(Type.home)).thenReturn(TEST_CONTACT_LIST);
        List<Contact> response = contactServiceImpl.getCallList();
        assertEquals(response.get(0).getName().getFirst(), "TEST_FIRST_NAME_1");
    }

    @Test
    public void deleteContactSuccess() {
        contactServiceImpl.deleteContactById(5678L);
        assertNull(contactRepositoryImpl.get(5678L));
    }
}
