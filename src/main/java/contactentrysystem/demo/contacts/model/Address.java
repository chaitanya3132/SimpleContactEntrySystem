package contactentrysystem.demo.contacts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
}
