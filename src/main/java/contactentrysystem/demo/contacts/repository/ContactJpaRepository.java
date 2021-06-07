package contactentrysystem.demo.contacts.repository;

import contactentrysystem.demo.contacts.model.Contact;
import contactentrysystem.demo.contacts.model.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ContactJpaRepository extends JpaRepository<Contact,Long> {

    List<Contact> findContactsByPhone_type(Type type, Sort sort);

}
