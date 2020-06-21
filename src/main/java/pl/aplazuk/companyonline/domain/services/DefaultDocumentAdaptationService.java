package pl.aplazuk.companyonline.domain.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aplazuk.companyonline.domain.converters.DocumentAdaptationConverter;
import pl.aplazuk.companyonline.domain.converters.FileConverter;
import pl.aplazuk.companyonline.domain.dtos.DocumentAdaptationDTO;
import pl.aplazuk.companyonline.domain.dtos.FileDTO;
import pl.aplazuk.companyonline.domain.entities.document.Document;
import pl.aplazuk.companyonline.domain.entities.document.DocumentDetails;
import pl.aplazuk.companyonline.domain.entities.document.DocumentType;
import pl.aplazuk.companyonline.domain.entities.document.File;
import pl.aplazuk.companyonline.domain.entities.user.User;
import pl.aplazuk.companyonline.domain.repositories.*;
import pl.aplazuk.companyonline.domain.services.interfaces.DocumentAdaptationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class DefaultDocumentAdaptationService implements DocumentAdaptationService {

    private final DocumentRepository documentRepository;
    private final DocumentDetailsRepository documentDetailsRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;


    public DefaultDocumentAdaptationService(DocumentRepository documentRepository, DocumentDetailsRepository documentDetailsRepository, DocumentTypeRepository documentTypeRepository, UserRepository userRepository, FileRepository fileRepository) {
        this.documentRepository = documentRepository;
        this.documentDetailsRepository = documentDetailsRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }


    public void addDocument(DocumentAdaptationDTO document) {
        log.debug("Dane do zmapowania dokumentu: {}", document);
        Document document1 = new Document();

        DocumentType documentType = documentTypeRepository.findOneByName(document.getDocumentTypeName());
        document1.setDocumentType(documentType);
        document1.setStatus(document.getStatus());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findOneByUsername(username);
        document1.setUser(user);
        documentRepository.save(document1);
        log.info("Zapisany dokument: {}", document1);

        DocumentDetails documentDetails = DocumentAdaptationConverter.from(document);
        documentDetailsRepository.save(documentDetails);
        log.info("Zapisano szczegóły dokumentu: {}", documentDetails);

        document1.setDocumentDetails(documentDetails);
        documentRepository.save(document1);
        log.info("Powiązano dokument o id {} ze szczegółami o id {}", document1.getId(), documentDetails.getId());
    }

    public void addFile(FileDTO uploadedFile) {
        log.debug("Dane do zmapowania pliku: {}", uploadedFile);
        File documentFile = new File();

        if (uploadedFile.getName() !=null && uploadedFile.getContent() != null){
            documentFile.setId(uploadedFile.getId());
            documentFile.setName(uploadedFile.getName());
            documentFile.setContentType(uploadedFile.getContentType());
            documentFile.setContent(uploadedFile.getContent());
            documentFile.setCreatedOn(uploadedFile.getCreatedOn());

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findOneByUsername(username);
            documentFile.setUser(user);
            fileRepository.save(documentFile);
            log.info("Zapisano dokument tekstowy: {}", documentFile);
        }

            log.error("Niewybrano pliku lub plik jest pusty: {}",uploadedFile);

    }


    public List<FileDTO> yourFiles(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<File> files = fileRepository.findQueryByUserName(username);
        List<FileDTO> fileDTOS = new ArrayList<>();
        for (File file : files) {
            fileDTOS.add(FileConverter.toFileDTO(file));
        }
        return fileDTOS;
    }

    @Override
    public Optional<FileDTO> getFileById(Long fileId) {
        File file = fileRepository.findFileById(fileId);

        if (file == null){
            return Optional.empty();
        }

        FileDTO fileDTO = new FileDTO();

        fileDTO.setId(file.getId());
        fileDTO.setName(file.getName());
        fileDTO.setContentType(file.getContentType());
        fileDTO.setContent(file.getContent());
        fileDTO.setCreatedOn(file.getCreatedOn());

        return Optional.of(fileDTO);
    }



    public void deleteFile(Long id) {
        fileRepository.deleteFileById(id);
    }
}
