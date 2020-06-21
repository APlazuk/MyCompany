package pl.aplazuk.companyonline.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.aplazuk.companyonline.domain.dtos.AccountPayableDTO;
import pl.aplazuk.companyonline.domain.dtos.DocumentAdaptationDTO;
import pl.aplazuk.companyonline.domain.entities.document.DocumentStatus;
import pl.aplazuk.companyonline.domain.entities.document.DocumentType;
import pl.aplazuk.companyonline.domain.repositories.DocumentTypeRepository;
import pl.aplazuk.companyonline.domain.services.interfaces.AccountPayableService;
import pl.aplazuk.companyonline.domain.services.interfaces.DocumentAdaptationService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ap")
public class AccountPayableController {

    private final AccountPayableService accountPayableService;
    private final DocumentAdaptationService documentAdaptationService;
    private final DocumentTypeRepository documentTypeRepository;


    public AccountPayableController(AccountPayableService accountPayableService, DocumentAdaptationService documentAdaptationService, DocumentTypeRepository documentTypeRepository) {
        this.accountPayableService = accountPayableService;
        this.documentAdaptationService = documentAdaptationService;
        this.documentTypeRepository = documentTypeRepository;
    }

    @ModelAttribute("creditInvoices")
    public List<AccountPayableDTO> creditInvoices() {
        return accountPayableService.yourCreditInvoices();
    }

    @GetMapping
    public String prepareMainPage() {
        return "invoices/AP/mainAP";
    }

    @GetMapping("/addDocument")
    public String addNewDocument(Model model) {
        model.addAttribute("document", new DocumentAdaptationDTO());

        return "invoices/AP/formAddInvoiceAP";
    }

    @PostMapping("/addDocument")
    public String distributeNewDocument(@Valid @ModelAttribute("document") DocumentAdaptationDTO document, BindingResult result) {
        if (result.hasErrors()) {
            return "invoices/AP/formAddInvoiceAP";
        }
        documentAdaptationService.addDocument(document);

        return "redirect:/ap";
    }

    @GetMapping("/edit/{id}")
    public String editDocument(Model model, @PathVariable Long id) {
        AccountPayableDTO editedDocument = accountPayableService.getDocumentById(id);

        model.addAttribute("editedDocument", editedDocument);
        return "invoices/AP/formEditInvoiceAP";
    }

    @PostMapping("/edit/{id}")
    public String distributeEditedDocument(@Valid @ModelAttribute("editedDocument") AccountPayableDTO editedDocument, BindingResult result) {
        if (result.hasErrors()) {
            return "invoices/AP/formEditInvoiceAP";
        }
        accountPayableService.editCreditInvoice(editedDocument);

        return "redirect:/ap";
    }

    @ModelAttribute("statuses")
    public List<DocumentStatus> statuses() {
        return Arrays.asList(DocumentStatus.values());
    }

    @ModelAttribute("types")
    public List<DocumentType> getType() {
        return documentTypeRepository.findAll();
    }
}
