package pl.aplazuk.companyonline.domain.entities.document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "document_details")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class DocumentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String companyName;
    private Integer clientNumber;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String documentNumber;
    @Column(nullable = false)
    private Double amount;
    private String IBAN;



}
