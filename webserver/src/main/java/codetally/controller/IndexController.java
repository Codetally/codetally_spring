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
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        if (null != principal) {
            return "redirect:/members/" + principal.getName();
        }
        model.addAttribute("pageTitle", "#1 Inspection Marketplace for Inspector Talent | Openspection.com");
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/randomimage", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] testphoto() throws IOException {
        Random random = new Random();
        int iback = random.nextInt(11);
        InputStream in = servletContext.getResourceAsStream("/resources/img/back" + iback + ".jpg");
        return IOUtils.toByteArray(in);
    }
}
