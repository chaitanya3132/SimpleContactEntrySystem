package contactentrysystem.demo.contacts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Name {

    private String first;
    private String middle;
    private String last;
}
