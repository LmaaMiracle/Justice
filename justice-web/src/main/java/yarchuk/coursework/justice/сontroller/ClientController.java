package yarchuk.coursework.justice.сontroller;

import org.apache.commons.lang3.RandomStringUtils;
import yarchuk.coursework.justice.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import yarchuk.coursework.justice.service.ClientService;

import java.security.SecureRandom;
import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private static SecureRandom random = new SecureRandom();

    private static final String VIEWS_CLIENT_CREATE_OR_UPDATE_FORM = "/clients/createOrUpdateClientForm";

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findClients(Model model) {
        model.addAttribute("client", Client.builder().build());
//        return "/clients/findClients";
        return "clients/applyForConsultation";
    }

    @GetMapping
    public String findClientByAuthCode(Client client, BindingResult bindingResult, Model model) {
        if (client.getAuthCode() == null) {
            client.setAuthCode("");
        }

        List<Client> results = clientService.findAllByAuthCodeLike("%" + client.getAuthCode() + "%");

        if (results.isEmpty()) {
            bindingResult.rejectValue("authCode", "notFound", "not found");
            return "clients/findClients";
        } else if (results.size() == 1) {
            client = results.get(0);
            return "redirect:/clients/" + client.getId();
        } else {
            model.addAttribute("selections", results);
            return "clients/clientsList";
        }
    }

//    @GetMapping()
//    public String processFindForm(Client client, BindingResult result, Model model) {
//        if (client.getLastName() == null) {
//            client.setLastName("");
//        }
//
//        List<Client> results = clientService.findAllByLastNameLike("%" + client.getLastName() + "%");
//
//        if (results.isEmpty()) {
//            result.rejectValue("lastName", "notFound", "not found");
//            return "clients/findClients";
//        } else if (results.size() == 1) {
//            client = results.get(0);
//            return "redirect:/clients/" + client.getId();
//        } else {
//            model.addAttribute("selections", results);
//            return "clients/clientsList";
//        }
//    }

    @GetMapping("/{clientId}")
    public ModelAndView showClient(@PathVariable Long clientId) {
        ModelAndView mav = new ModelAndView("clients/clientDetails");
        mav.addObject(clientService.findById(clientId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("client", Client.builder().build());
        return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            client.setAuthCode(generateToken());
            Client savedClient = clientService.save(client);
            return "redirect:/clients/" + savedClient.getId();
        }
    }

    @GetMapping("/{clientId}/edit")
    public String initUpdateClientForm(@PathVariable Long clientId, Model model) {
        model.addAttribute(clientService.findById(clientId));
        return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{clientId}/edit")
    public String processUpdateClientForm(@Valid Client client, BindingResult result, @PathVariable Long clientId) {
        if (result.hasErrors()) {
            return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            client.setId(clientId);
            Client savedClient = clientService.save(client);
            return "redirect:/clients/" + savedClient.getId();
        }
    }

    public synchronized String generateToken() {
        return RandomStringUtils.randomAlphanumeric(10).toUpperCase();
    }
}
