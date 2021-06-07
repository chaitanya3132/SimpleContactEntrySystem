package contactentrysystem.demo.contacts.repository;

import contactentrysystem.demo.contacts.model.Phone;
import contactentrysystem.demo.contacts.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PhoneRepositoryImpl implements PhoneRepository{

    @Autowired
    private PhoneJpaRepository phoneJpaRepository;

    public void deleteByContactId(Long contact_id) {
         phoneJpaRepository.deleteByContactId(contact_id);
    }

}
