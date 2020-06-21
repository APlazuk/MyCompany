package pl.aplazuk.companyonline.domain.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.aplazuk.companyonline.domain.dtos.FileDTO;
import pl.aplazuk.companyonline.domain.repositories.DocumentRepository;
import pl.aplazuk.companyonline.domain.repositories.FileRepository;
import pl.aplazuk.companyonline.domain.services.interfaces.DocumentAdaptationService;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;


@Controller
@Slf4j
@RequestMapping("/")
public class HomePageController {

    private final DocumentRepository documentRepository;
    private final DocumentAdaptationService documentAdaptationService;
    private final FileRepository fileRepository;

    public HomePageController(DocumentRepository documentRepository, DocumentAdaptationService documentAdaptationService, FileRepository fileRepository) {
        this.documentRepository = documentRepository;
        this.documentAdaptationService = documentAdaptationService;
        this.fileRepository = fileRepository;
    }

    @GetMapping
    public String prepareHomePage(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);

        Long countCreditInvoices = documentRepository.countByUserUsernameAndDocumentTypeAP(username);
        model.addAttribute("countCreditInvoices",countCreditInvoices);

        Long countVendorInvoices = documentRepository.countByUserUsernameAndDocumentTypeAR(username);
        model.addAttribute("countVendorInvoices",countVendorInvoices);

        Long countUnspecifiedDocuments = fileRepository.countByUserUsername(username);
        model.addAttribute("countUnspecifiedDocuments",countUnspecifiedDocuments);

        model.addAttribute("file",new FileDTO());

        return "home/main";
    }

    @PostMapping("/addFile")
    public String distributeNewFile(@RequestParam MultipartFile documentFile, @Valid @ModelAttribute("file") @RequestBody FileDTO uploadedFile, BindingResult result){

        try {
            String filename = documentFile.getOriginalFilename();
            String contentType = documentFile.getContentType();
            byte[] bytes = documentFile.getBytes();

            uploadedFile.setName(filename);
            uploadedFile.setContentType(contentType);
            uploadedFile.setContent(bytes);

        }catch (IOException ioe){
            log.error("Błędne przetwarzanie pilków", ioe);
            result.rejectValue("documentFile", null, "Błąd przetwarzania pliku");
            return "home/main";
        }

        log.debug("Zapisujemy plik: {}",uploadedFile);
        documentAdaptationService.addFile(uploadedFile);

        return "redirect:/";
    }

}
