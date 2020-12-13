package yarchuk.coursework.justice.сontroller;

import yarchuk.coursework.justice.model.Consultation;
import yarchuk.coursework.justice.model.Issue;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import yarchuk.coursework.justice.service.ConsultationService;
import yarchuk.coursework.justice.service.IssueService;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class ConsultationController {

    private final ConsultationService consultationService;
    private final IssueService issueService;

    public ConsultationController(ConsultationService consultationService, IssueService issueService) {
        this.consultationService = consultationService;
        this.issueService = issueService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("consultation")
    public Consultation loadIssuesWithConsultation(@PathVariable("issueId") Long issueId, Map<String, Object> model) {
        Issue issue = issueService.findById(issueId);
        model.put("issue", issue);
        Consultation consultation = new Consultation();
        issue.getConsultations().add(consultation);
        consultation.setIssue(issue);
        return consultation;
    }

    @GetMapping("/clients/*/issues/{issueId}/consultations/new")
    public String initNewConsultationForm(@PathVariable("issueId") Long issueId, Map<String, Object> model) {
        return "issues/createOrUpdateConsultationForm";
    }

    @PostMapping("/clients/{clientId}/issues/{issueId}/consultations/new")
    public String processNewVisitForm(@Valid Consultation consultation, BindingResult result) {
        if (result.hasErrors()) {
            return "issues/createOrUpdateConsultationForm";
        } else {
            consultationService.save(consultation);
            return "redirect:/clients/{clientId}";
        }
    }

}
