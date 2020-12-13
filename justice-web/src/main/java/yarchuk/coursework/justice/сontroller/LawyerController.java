package yarchuk.coursework.justice.сontroller;

import yarchuk.coursework.justice.model.Lawyer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yarchuk.coursework.justice.service.LawyerService;

import java.util.Set;

@Controller
public class LawyerController {

    private final LawyerService lawyerService;

    public LawyerController(LawyerService lawyerService) {
        this.lawyerService = lawyerService;
    }

    @RequestMapping({"/lawyers", "/lawyers/index", "/lawyers/index.html", "/lawyers.html"})
    public String listOfLawyers(Model model) {
        model.addAttribute("lawyers", lawyerService.findAll());
        return "lawyers/index";
    }

    @GetMapping("/api/lawyers")
    public @ResponseBody
    Set<Lawyer> getLawyersJson() {
        return lawyerService.findAll();
    }

}
