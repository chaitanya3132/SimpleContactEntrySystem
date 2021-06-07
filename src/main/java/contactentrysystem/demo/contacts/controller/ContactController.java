package contactentrysystem.demo.contacts.controller;

import contactentrysystem.demo.contacts.model.*;
import contactentrysystem.demo.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class ContactController {

    @Autowired
    private ContactService contactService;

    public ContactController() {}

    @RequestMapping(value = "/contacts",method = RequestMethod.POST)
    public Contact createContact(@RequestBody Contact contact) {
        contact.getPhone().forEach(phone -> {phone.setContact(contact);});
        return contactService.createContact(contact);
    }

    @RequestMapping(value = "/contacts/{id}",method = RequestMethod.DELETE)
    public String deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContactById(id);
        return "Contact Deleted Successfully";
    }

    @RequestMapping(value = "/contacts/{id}",method = RequestMethod.PUT)
    public String updateContact(@PathVariable("id") Long id,@RequestBody Contact contact) {
        contact.getPhone().forEach(phone -> {phone.setContact(contact);});
        contactService.updateContactById(id, contact);
        return "Contact Updated Successfully";
    }

    @RequestMapping(value = "/contacts/{id}",method = RequestMethod.GET)
    public Contact getContact(@PathVariable("id") Long id) {
        return contactService.getContactById(id);

    }

    @RequestMapping(value = "/contacts",method = RequestMethod.GET)
    public List<Contact> getAllContacts() {
        return contactService.getContacts();
    }

    @RequestMapping(value = "/contacts/call-list",method = RequestMethod.GET)
    public List<CallList> getCallList() {

        return contactService.getCallList().stream()
                .map(c -> {
                    Optional<Phone> phone = c.getPhone().stream()
                            .filter(p -> p.getType() == Type.home).findFirst();

                    return CallList.builder()
                            .name(c.getName())
                            .number(phone.isPresent()? phone.get().getNumber():"")
                            .build();
                })
                .collect(Collectors.toList());
    }
}
