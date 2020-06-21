package pl.aplazuk.companyonline.domain.entities.document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "booked_documents")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
public class BookDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Dt", nullable = false)
    private String debitAccount;
    @Column(name = "Ct",nullable = false)
    private String creditAccount;
    private String costCenter;

}
