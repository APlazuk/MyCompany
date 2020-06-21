package pl.aplazuk.companyonline.domain.converters;

import pl.aplazuk.companyonline.domain.dtos.AccountPayableDTO;
import pl.aplazuk.companyonline.domain.entities.document.Document;
import pl.aplazuk.companyonline.domain.entities.document.DocumentDetails;


public class AccountPayableConverter {

    public static AccountPayableDTO toAccountPayableDTO(Document document) {
        AccountPayableDTO payableDTO = new AccountPayableDTO();
        payableDTO.setId(document.getId());
        payableDTO.setCompanyName(document.getDocumentDetails().getCompanyName());
        payableDTO.setClientNumber(document.getDocumentDetails().getClientNumber());
        payableDTO.setAmount(document.getDocumentDetails().getAmount());
        payableDTO.setDocumentNumber(document.getDocumentDetails().getDocumentNumber());
        payableDTO.setDate(document.getDocumentDetails().getDate());
        payableDTO.setIBAN(document.getDocumentDetails().getIBAN());
        payableDTO.setStatus(document.getStatus());
        return payableDTO;
    }

    public static DocumentDetails from (AccountPayableDTO accountPayableDTO) {

        DocumentDetails documentDetails = new DocumentDetails();

        documentDetails.setCompanyName(accountPayableDTO.getCompanyName());
        documentDetails.setClientNumber(accountPayableDTO.getClientNumber());
        documentDetails.setDate(accountPayableDTO.getDate());
        documentDetails.setDocumentNumber(accountPayableDTO.getDocumentNumber());
        documentDetails.setAmount(accountPayableDTO.getAmount());
        documentDetails.setIBAN(accountPayableDTO.getIBAN());


        return documentDetails;
    }

}
