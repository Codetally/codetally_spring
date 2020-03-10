package codetally.controller;

import codetally.model.Charge;
import codetally.model.Project;
import codetally.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ChargeController {
    @Autowired
    private ChargeService chargeService;

    @RequestMapping(value = "/charges/{chargeid}", method = RequestMethod.GET)
    public String getSingleCharge(Model model, @PathVariable Long chargeid) {

        Charge singlecharge = chargeService.get(chargeid);

        model.addAttribute("singlecharge", singlecharge);
        model.addAttribute("pageTitle", singlecharge.getDescription());

        return "chargeview";
    }
    @RequestMapping(value = "/charges/create", method = RequestMethod.GET)
    public String chargeCreate(Model model) {

        model.addAttribute("chargeForm", new Charge());
        model.addAttribute("pageTitle", "Create a new charge.");

        return "chargecreate";
    }

    @RequestMapping(value = "/charges/create", method = RequestMethod.POST)
    public String chargeCreate(@ModelAttribute("chargeForm") Charge chargeForm, BindingResult bindingResult, Model model, Principal principal) {
        chargeValidator.validate(chargeForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Create a new charge - but please fix these errors!");
            return "chargecreate";
        }

        Charge newCharge = chargeService.save(chargeForm);
        return "redirect:/charges/"+newCharge.getId();
    }

}
