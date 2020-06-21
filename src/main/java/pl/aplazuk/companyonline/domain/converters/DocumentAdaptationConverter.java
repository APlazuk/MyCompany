package pl.aplazuk.companyonline.domain.converters;

import pl.aplazuk.companyonline.domain.dtos.DocumentAdaptationDTO;
import pl.aplazuk.companyonline.domain.entities.document.DocumentDetails;

public class DocumentAdaptationConverter {

    public static DocumentDetails from (DocumentAdaptationDTO documentAdaptationDTO) {

        DocumentDetails documentDetails = new DocumentDetails();

        documentDetails.setCompanyName(documentAdaptationDTO.getCompanyName());
        documentDetails.setClientNumber(documentAdaptationDTO.getClientNumber());
        documentDetails.setDate(documentAdaptationDTO.getDate());
        documentDetails.setDocumentNumber(documentAdaptationDTO.getDocumentNumber());
        documentDetails.setAmount(documentAdaptationDTO.getAmount());
        documentDetails.setIBAN(documentAdaptationDTO.getIBAN());


        return documentDetails;
    }
}
