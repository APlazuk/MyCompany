package pl.aplazuk.companyonline.domain.entities.others;


import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private Double exchangeRate;
}
