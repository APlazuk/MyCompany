package pl.aplazuk.companyonline.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.aplazuk.companyonline.domain.entities.document.Document;


import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findAllByUserUsername(String username);
    Document findDocumentById(Long id);

    @Query(value = "SELECT COUNT(*) FROM documents\n" +
            "LEFT JOIN users u on documents.user_id = u.id\n" +
            "WHERE document_type_id = 1 and username=?", nativeQuery = true)
    Long countByUserUsernameAndDocumentTypeAP(String username);


    @Query(value = "SELECT COUNT(*) FROM documents\n" +
            "LEFT JOIN users u on documents.user_id = u.id\n" +
            "WHERE document_type_id = 2 and username=?", nativeQuery = true)
    Long countByUserUsernameAndDocumentTypeAR(String username);

    @Query(value = "SELECT * FROM documents\n" +
            "LEFT JOIN users u on documents.user_id = u.id\n" +
            "WHERE document_type_id = 2 and username=?", nativeQuery = true)
    List<Document> findAllByUserUsernameAndDocumentTypeAR(String username);

    @Query(value = "SELECT * FROM documents\n" +
            "LEFT JOIN users u on documents.user_id = u.id\n" +
            "WHERE document_type_id = 1 and username=?", nativeQuery = true)
    List<Document> findAllByUserUsernameAndDocumentTypeAP(String username);

    Document deleteDocumentById(Long id);

}
