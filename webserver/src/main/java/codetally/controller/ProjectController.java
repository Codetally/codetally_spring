package codetally.controller;

import codetally.model.Project;
import codetally.model.User;
import codetally.service.ProjectService;
import codetally.service.UserService;
import codetally.validator.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by greg on 29/08/17.
 */

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectValidator projectValidator;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/projects/{projectid}", method = RequestMethod.GET)
    public String getSingleProject(Model model, @PathVariable Long projectid) {

        Project singleproject = projectService.getById(projectid);

        model.addAttribute("singleproject", singleproject);
        model.addAttribute("pageTitle", singleproject.getProjectname());

        return "projectview";
    }

    @RequestMapping(value = "/projects/create", method = RequestMethod.GET)
    public String projectCreate(Model model) {

        model.addAttribute("projectForm", new Project());
        model.addAttribute("pageTitle", "Create a new project.");

        return "projectcreate";
    }
    @RequestMapping(value = "/projects/create", method = RequestMethod.POST)
    public String projectCreate(@ModelAttribute("projectForm") Project projectForm, BindingResult bindingResult, Model model, Principal principal) {
        projectValidator.validate(projectForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Create a new project - but please fix these errors!");
            return "projectcreate";
        }

        Project newProject = projectService.save(projectForm);
        return "redirect:/projects/"+newProject.getId();
    }
    @RequestMapping(value = "/projects/search", method = RequestMethod.GET)
    public String searchforprojects(Model model, Pageable pageable,
                                 @RequestParam("keywords") String keywords) {


        List<Project> searchresults = projectService.search(keywords, pageable);
        model.addAttribute("searchresults", searchresults);
        model.addAttribute("keywords", keywords);

        return "projectlist";
    }


}
