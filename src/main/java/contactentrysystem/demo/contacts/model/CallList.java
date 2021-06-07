package contactentrysystem.demo.contacts.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CallList {
    private Name name;
    private String number;
}
