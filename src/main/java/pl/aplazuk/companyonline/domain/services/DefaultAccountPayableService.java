package pl.aplazuk.companyonline.domain.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aplazuk.companyonline.domain.converters.AccountPayableConverter;
import pl.aplazuk.companyonline.domain.dtos.AccountPayableDTO;
import pl.aplazuk.companyonline.domain.entities.document.Document;
import pl.aplazuk.companyonline.domain.entities.document.DocumentDetails;
import pl.aplazuk.companyonline.domain.entities.user.User;
import pl.aplazuk.companyonline.domain.repositories.DocumentDetailsRepository;
import pl.aplazuk.companyonline.domain.repositories.DocumentRepository;
import pl.aplazuk.companyonline.domain.repositories.UserRepository;
import pl.aplazuk.companyonline.domain.services.interfaces.AccountPayableService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DefaultAccountPayableService implements AccountPayableService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final DocumentDetailsRepository documentDetailsRepository;

    public DefaultAccountPayableService(DocumentRepository documentRepository, UserRepository userRepository, DocumentDetailsRepository documentDetailsRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.documentDetailsRepository = documentDetailsRepository;
    }

    public AccountPayableDTO getDocumentById(Long id){

        Document document = documentRepository.findDocumentById(id);
        return AccountPayableConverter.toAccountPayableDTO(document);
    }


    public List<AccountPayableDTO> yourCreditInvoices(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Document> userCreditInvoices = documentRepository.findAllByUserUsernameAndDocumentTypeAP(username);
        List<AccountPayableDTO> accountPayableDTOS = new ArrayList<>();
        for (Document userCreditInvoice : userCreditInvoices) {
            accountPayableDTOS.add(AccountPayableConverter.toAccountPayableDTO(userCreditInvoice));
        }
        return accountPayableDTOS;
    }

     public void editCreditInvoice(AccountPayableDTO editedDocument){
        log.debug("Dane do zmapowania dokumentu: {}", editedDocument);
        Document document = documentRepository.getOne(editedDocument.getId());

        document.setStatus(editedDocument.getStatus());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findOneByUsername(username);
        document.setUser(user);
        documentRepository.save(document);
        log.info("Zapisany zmodyfkowany dokument: {}", document);


        DocumentDetails documentDetails = AccountPayableConverter.from(editedDocument);
        documentDetailsRepository.save(documentDetails);
        log.info("Zapisane szczegóły modyfikowanego dokumentu: {}", documentDetails);

        document.setDocumentDetails(documentDetails);
        documentRepository.save(document);
        log.info("Powiązano dokument o id {} ze szczegółami o id {}", document.getId(), documentDetails.getId());
    }
}
