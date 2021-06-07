package contactentrysystem.demo.contacts.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="contacts")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "first", column = @Column(name = "first")),
            @AttributeOverride( name = "middle", column = @Column(name = "middle")),
            @AttributeOverride( name = "last", column = @Column(name = "last"))
    })
    private Name name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "street", column = @Column(name = "street")),
            @AttributeOverride( name = "city", column = @Column(name = "city")),
            @AttributeOverride( name = "state", column = @Column(name = "state")),
            @AttributeOverride( name = "zip", column = @Column(name = "zip")),
    })
    private Address address;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Phone> phone;

    @Column
    private String email;
}
