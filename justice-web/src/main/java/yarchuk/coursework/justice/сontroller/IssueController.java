package yarchuk.coursework.justice.сontroller;

import yarchuk.coursework.justice.model.Client;
import yarchuk.coursework.justice.model.Issue;
import yarchuk.coursework.justice.model.IssueType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import yarchuk.coursework.justice.service.ClientService;
import yarchuk.coursework.justice.service.IssueService;
import yarchuk.coursework.justice.service.IssueTypeService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/clients/{clientId}")
public class IssueController {
    private static final String VIEWS_ISSUES_CREATE_OR_UPDATE_FORM = "issues/createOrUpdateIssueForm";

    private final ClientService clientService;
    private final IssueService issueService;
    private final IssueTypeService issueTypeService;

    public IssueController(ClientService clientService, IssueService issueService, IssueTypeService issueTypeService) {
        this.clientService = clientService;
        this.issueService = issueService;
        this.issueTypeService = issueTypeService;
    }

    @ModelAttribute("types")
    public Collection<IssueType> populateIssueTypes() {
        return issueTypeService.findAll();
    }

    @ModelAttribute("client")
    public Client findClient(@PathVariable("clientId") Long clientId) {
        return clientService.findById(clientId);
    }

    @InitBinder("client")
    public void initClientBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/issues/new")
    public String initCreationForm(Client client, Model model) {
        Issue issue = new Issue();
        client.getIssues().add(issue);
        issue.setClient(client);
        model.addAttribute("issue", issue);
        return VIEWS_ISSUES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/issues/new")
    public String processCreationForm(Client client, @Valid Issue issue, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(issue.getDescription())
                && issue.isNew()
                && client.getIssue(issue.getDescription(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        client.getIssues().add(issue);
        if (result.hasErrors()) {
            model.put("issue", issue);
            return VIEWS_ISSUES_CREATE_OR_UPDATE_FORM;
        } else {
            issue.setIssueType(issue.getIssueType());
            issue.setClient(client);
            issue.setDescription(issue.getDescription());
            this.issueService.save(issue);
            return "redirect:/clients/" + client.getId();
        }
    }

    @GetMapping("/issues/{issueId}/edit")
    public String initUpdateForm(@PathVariable Long issueId, Model model) {
        model.addAttribute("issue", issueService.findById(issueId));
        return VIEWS_ISSUES_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/issues/{issueId}/edit")
    public String processUpdateForm(@Valid Issue issue, BindingResult result, Client client, Model model) {
        if (result.hasErrors()) {
            issue.setClient(client);

            model.addAttribute("issue", issue); //
            return VIEWS_ISSUES_CREATE_OR_UPDATE_FORM;
        } else {
            client.getIssues().add(issue);
            issueService.save(issue);
            return "redirect:/clients/" + client.getId();
        }
    }
}

