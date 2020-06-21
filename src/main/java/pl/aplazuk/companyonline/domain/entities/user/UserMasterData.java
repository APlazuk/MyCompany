package pl.aplazuk.companyonline.domain.entities.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
@Getter @Setter @ToString @EqualsAndHashCode(of="id")
public class UserMasterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String entityName;
    @Column(unique = true)
    private String nip;
    @Column(unique = true)
    private String regon;
    @Column(unique = true)
    private String pesel;
    private String telephone;


}
