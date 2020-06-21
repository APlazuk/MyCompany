package pl.aplazuk.companyonline.domain.entities.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.aplazuk.companyonline.domain.entities.document.Document;
import pl.aplazuk.companyonline.domain.entities.document.File;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @ToString(exclude = {"password", "documents"}) @EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean active = false;
    private String role;

    @OneToOne
    @JoinColumn(name = "user_master_data_id")
    private UserMasterData userMasterData;

    @OneToMany(mappedBy = "user")
    private List<Document> documents;


}
