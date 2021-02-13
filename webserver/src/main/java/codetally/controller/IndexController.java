package codetally.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class IndexController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        if (null != principal) {
            return "redirect:/dashboard/" + principal.getName();
        }
        model.addAttribute("pageTitle", "Dashboard");
        return "index";
    }
}
