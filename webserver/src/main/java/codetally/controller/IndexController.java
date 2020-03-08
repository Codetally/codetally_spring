package codetally.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Random;

@Controller
public class IndexController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        if (null != principal) {
            return "redirect:/members/" + principal.getName();
        }
        model.addAttribute("pageTitle", "#1 Inspection Marketplace for Inspector Talent | Openspection.com");
        return "index";
    }
}
