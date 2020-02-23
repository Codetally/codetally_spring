package com.codetally.controller;

import com.codetally.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private com.codetally.service.UserService UserService;

    @Autowired
    private com.codetally.service.SecurityService SecurityService;

    @Autowired
    private com.codetally.validator.UserValidator UserValidator;

    @Autowired
    private com.codetally.validator.UserprofileValidator UserprofileValidator;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("pageTitle", "Create a new registration.");

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        UserValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Create a new registration.");

            return "registration";
        }

        //This is tough. Store off the username and password prior to encryption
        //in order to autologin later.

        String username = userForm.getUsername();
        String password = userForm.getPassword();

        User encrypteduser = UserService.encryptuser(userForm);
        if (encrypteduser.getBannerurl() == null) {
            encrypteduser.setBannerurl("/banners/default/default.jpg");
        }
        if (encrypteduser.getPhotourl() == null) {
            encrypteduser.setPhotourl("/images/default/default.jpg");
        }
        UserService.save(encrypteduser);

        SecurityService.autologin(username, password);
        return "redirect:/members/" + username;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        model.addAttribute("userForm", new User());
        model.addAttribute("pageTitle", "Log in with your account.");

        return "login";
    }

    @RequestMapping(value = {"/members/{username}"}, method = RequestMethod.GET)
    public String getMember(Model model, @PathVariable String username, Principal principal) {

        boolean isEditable = false;

        User loggedUser = UserService.findByUsername(username);

        if (principal != null && principal.getName().equalsIgnoreCase(username)) {
            isEditable = true;
        }
        model.addAttribute("user", loggedUser);
        model.addAttribute("editable", isEditable);
        model.addAttribute("pageTitle", "Welcome to your profile, " + username + ".");
        return "member";
    }

    @RequestMapping(value = {"/members/{username}/edit"}, method = RequestMethod.GET)
    public String getEditpage(Model model, @PathVariable String username, Principal principal) {
        if (principal != null && principal.getName().equalsIgnoreCase(username)) {
            User loggedUser = UserService.findByUsername(username);
            model.addAttribute("user", loggedUser);
            model.addAttribute("pageTitle", "Edit your profile, " + username + ".");
            return "editmember";
        }
        return "redirect:/members/" + username;
    }

    @RequestMapping(value = "/members/{username}/edit", method = RequestMethod.POST)
    public String editMember(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, Principal principal, @RequestParam(value = "photofile", required = false) MultipartFile photofile, @RequestParam(value = "bannerfile", required = false) MultipartFile bannerfile) {
        if (principal != null && principal.getName().equalsIgnoreCase(user.getUsername())) {
            User loggedUser = UserService.findByUsername(principal.getName());
            loggedUser.setDescription(user.getDescription());
            loggedUser.setWebsiteurl(user.getWebsiteurl());
            loggedUser.setBlogurl(user.getBlogurl());
            loggedUser.setLinkedinurl(user.getLinkedinurl());
            loggedUser.setTwitterurl(user.getTwitterurl());
            loggedUser.setFacebookurl(user.getFacebookurl());
            loggedUser.setGithuburl(user.getGithuburl());
            loggedUser.setPhotourl(user.getPhotourl());

            UserprofileValidator.validate(loggedUser, bindingResult);
            if (bindingResult.hasErrors()) {
                model.addAttribute("pageTitle", "Edit your profile - but please fix these errors!");
                return "editmember";
            }
            if (photofile != null) {
                String filePath = context.getRealPath("/images/") + photofile.getOriginalFilename();
                try {
                    photofile.transferTo(new File(filePath));
                    loggedUser.setPhotourl("/images/" + photofile.getOriginalFilename());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
            if (bannerfile != null) {
                String filePath = context.getRealPath("/banners/") + bannerfile.getOriginalFilename();
                try {
                    bannerfile.transferTo(new File(filePath));
                    loggedUser.setBannerurl("/banners/" + bannerfile.getOriginalFilename());
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
            UserService.save(loggedUser);
            return "redirect:/members/" + principal.getName();
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.GET)
    public String searchforusers(Model model,
                                 @RequestParam("keywords") String keywords,
                                 @RequestParam(value = "category_id", required = false) Integer categoryId) {

        System.out.println("Keywords: " + keywords + " Category: " + categoryId);
        User user = new User();
        user.setUsername(keywords);

        List<User> searchresults = UserService.findUsers(user);

        model.addAttribute("searchresults", searchresults);

        return "searchresults";
    }
}
