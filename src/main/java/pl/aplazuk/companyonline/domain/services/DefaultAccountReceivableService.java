package pl.aplazuk.companyonline.domain.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aplazuk.companyonline.domain.converters.AccountReceivableConverter;
import pl.aplazuk.companyonline.domain.dtos.AccountReceivableDTO;
import pl.aplazuk.companyonline.domain.entities.document.Document;
import pl.aplazuk.companyonline.domain.entities.document.DocumentDetails;
import pl.aplazuk.companyonline.domain.entities.user.User;
import pl.aplazuk.companyonline.domain.repositories.DocumentDetailsRepository;
import pl.aplazuk.companyonline.domain.repositories.DocumentRepository;
import pl.aplazuk.companyonline.domain.repositories.UserRepository;
import pl.aplazuk.companyonline.domain.services.interfaces.AccountReceivableService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DefaultAccountReceivableService implements AccountReceivableService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final DocumentDetailsRepository documentDetailsRepository;

    public DefaultAccountReceivableService(DocumentRepository documentRepository, UserRepository userRepository, DocumentDetailsRepository documentDetailsRepository) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.documentDetailsRepository = documentDetailsRepository;
    }


    @Override
    public void editVendorInvoice(AccountReceivableDTO accountReceivableDTO) {
        log.debug("Dane do zmapowania dokumentu: {}", accountReceivableDTO);
        Document document = documentRepository.getOne(accountReceivableDTO.getId());

        document.setStatus(accountReceivableDTO.getStatus());

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findOneByUsername(userName);
        document.setUser(user);
        documentRepository.save(document);
        log.info("Zapisany zmodyfkowany dokument: {}", document);

        DocumentDetails documentDetails = AccountReceivableConverter.from(accountReceivableDTO);
        documentDetailsRepository.save(documentDetails);
        log.info("Zapisane szczegóły modyfikowanego dokumentu: {}", documentDetails);

        document.setDocumentDetails(documentDetails);
        documentRepository.save(document);
        log.info("Powiązano dokument o id {} ze szczegółami o id {}", document.getId(), documentDetails.getId());

    }

    @Override
    public List<AccountReceivableDTO> yourVendorInvoices() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Document> vendorInvoices = documentRepository.findAllByUserUsernameAndDocumentTypeAR(userName);
        List<AccountReceivableDTO> accountReceivableDTOS = new ArrayList<>();
        for (Document vendorInvoice : vendorInvoices) {
            accountReceivableDTOS.add(AccountReceivableConverter.toAccountReceivableDTO(vendorInvoice));
        }
        return accountReceivableDTOS;
    }

    @Override
    public AccountReceivableDTO getDocumentById(Long id) {
        Document document = documentRepository.findDocumentById(id);
        return AccountReceivableConverter.toAccountReceivableDTO(document);
    }
}
