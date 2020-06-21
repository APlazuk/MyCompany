package pl.aplazuk.companyonline.domain.converters;

import pl.aplazuk.companyonline.domain.dtos.AccountPayableDTO;
import pl.aplazuk.companyonline.domain.dtos.AccountReceivableDTO;
import pl.aplazuk.companyonline.domain.entities.document.Document;
import pl.aplazuk.companyonline.domain.entities.document.DocumentDetails;

public class AccountReceivableConverter {

    public static AccountReceivableDTO toAccountReceivableDTO(Document document){
        AccountReceivableDTO dto = new AccountReceivableDTO();

        dto.setId(document.getId());
        dto.setCompanyName(document.getDocumentDetails().getCompanyName());
        dto.setClientNumber(document.getDocumentDetails().getClientNumber());
        dto.setDocumentNumber(document.getDocumentDetails().getDocumentNumber());
        dto.setDate(document.getDocumentDetails().getDate());
        dto.setAmount(document.getDocumentDetails().getAmount());
        dto.setIBAN(document.getDocumentDetails().getIBAN());
        dto.setStatus(document.getStatus());

        return dto;
    }

    public static DocumentDetails from (AccountReceivableDTO accountReceivableDTO) {

        DocumentDetails documentDetails = new DocumentDetails();

        documentDetails.setCompanyName(accountReceivableDTO.getCompanyName());
        documentDetails.setClientNumber(accountReceivableDTO.getClientNumber());
        documentDetails.setDate(accountReceivableDTO.getDate());
        documentDetails.setDocumentNumber(accountReceivableDTO.getDocumentNumber());
        documentDetails.setAmount(accountReceivableDTO.getAmount());
        documentDetails.setIBAN(accountReceivableDTO.getIBAN());


        return documentDetails;
    }
}
