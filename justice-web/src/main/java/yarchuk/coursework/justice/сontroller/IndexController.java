package yarchuk.coursework.justice.сontroller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController implements ErrorController {

    private static final String PATH = "/error";


    @RequestMapping({"", "index", "/", "index.html", "home"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/error")
    public String errorHandler(HttpServletRequest request, Model model) {
        String errorPage = "error";
        String pageTitle = "Error";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                pageTitle = "Page not found";
                errorPage = "error/404";
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                pageTitle = "Internal server error";
                errorPage = "error/500";
            } else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                pageTitle = "Forbidden error";
                errorPage = "error/403";
            }
        }
        model.addAttribute("pageTitle", pageTitle);

        return errorPage;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
