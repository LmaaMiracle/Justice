package yarchuk.coursework.justice.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import yarchuk.coursework.justice.model.User;
import yarchuk.coursework.justice.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "register/register";
    }

    @PostMapping("/register")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register/register";
        }

        System.err.println(checkUser(user.getUsername()) + "out of it");
        if (checkUser(user.getUsername())) {
            System.err.println(checkUser(user.getUsername()) + "IN IF");
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setId(user.getId());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setFirst_name(user.getFirst_name());
            user.setLast_name(user.getLast_name());
            user.setUsername(user.getUsername());

            userRepository.save(user);
        } else {
            bindingResult.rejectValue("username", "duplicate", "this email is already in use");
            return "register/register";
        }

        return "register/login";

//        return "redirect:/login";
    }

    @GetMapping("/account")
    public String accountPage(Principal principal, Model model) {
        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "lawyers/lawyerAccount";
    }

    private boolean checkUser(String username) {
        User user = userRepository.findUserByUsername(username);
        return user == null;
    }
}
