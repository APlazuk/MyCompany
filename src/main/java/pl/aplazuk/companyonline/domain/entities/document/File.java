package pl.aplazuk.companyonline.domain.entities.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import pl.aplazuk.companyonline.domain.entities.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "files")
@Getter @Setter @ToString(exclude = "content")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Column(nullable = false)
    private String contentType;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte [] content;

    @CreatedDate
    private LocalDateTime createdOn;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedOn;

    @LastModifiedBy
    private String updatedBy;

    @ManyToOne
    private User user;


    @PrePersist
    public void PrePersist(){
        createdOn = LocalDateTime.now();
    }

}
