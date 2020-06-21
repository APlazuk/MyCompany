package pl.aplazuk.companyonline.domain.entities.document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.aplazuk.companyonline.domain.entities.user.User;

import javax.persistence.*;


@Entity
@Table(name = "documents")
@Getter @Setter @ToString @EqualsAndHashCode(of="id")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "varchar(50) default 'niezredagowany'")
    private String status;

    @ManyToOne
    private User user;

    @OneToOne
    @JoinColumn(name = "document_details_id")
    private DocumentDetails documentDetails;

    @OneToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;



}
