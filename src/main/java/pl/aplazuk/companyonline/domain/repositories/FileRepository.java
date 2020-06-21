package pl.aplazuk.companyonline.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.aplazuk.companyonline.domain.entities.document.File;

import java.util.List;


public interface FileRepository extends JpaRepository<File, Long> {

    @Query(value = "SELECT * FROM files LEFT JOIN users u on files.user_id = u.id where username = ?", nativeQuery = true)
    List<File> findQueryByUserName(String username);


    @Query(value = "SELECT COUNT(*) FROM files LEFT JOIN users u on files.user_id = u.id where username = ?", nativeQuery = true)
    Long countByUserUsername(String username);

    File findFileById(Long id);

    @Modifying
    @Query(value = "DELETE FROM files WHERE id = ?;", nativeQuery = true)
    void deleteFileById(Long id);

}
