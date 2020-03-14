package codetally.controller;

import codetally.model.Charge;
import codetally.model.Project;
import codetally.service.ChargeService;
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
import java.util.List;

/**
 * Created by greg on 29/08/17.
 */

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ChargeService chargeService;

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

    @RequestMapping(value = "/projects/", method = RequestMethod.GET)
    public String getAllProject(Model model, @PathVariable Long projectid) {

        List<Project> projectList = projectService.getAll();

        model.addAttribute("projectlist", projectList);
        model.addAttribute("pageTitle", "Project list");

        return "projectlist";
    }

    @RequestMapping(value = "/projects/{projectid}/charges", method = RequestMethod.GET)
    public String getProjectCharges(Model model, @PathVariable Long projectid) {

        Project singleproject = projectService.getById(projectid);

        model.addAttribute("singleproject", singleproject);
        model.addAttribute("pageTitle", singleproject.getProjectname());

        return "projectchargeview";
    }
    @RequestMapping(value = "/projects/{projectid}/charges/add", method = RequestMethod.GET)
    public String addProjectCharge(Model model, @PathVariable Long projectid) {

        Project singleproject = projectService.getById(projectid);
        List<Charge> chargeList = chargeService.getAll();

        model.addAttribute("singleproject", singleproject);
        model.addAttribute("pageTitle", singleproject.getProjectname());
        model.addAttribute("chargelist", chargeList);


        return "projectchargeadd";
    }

    @RequestMapping(value = "/projects/import/{projectSourceName}", method = RequestMethod.GET)
    public String projectImport(Model model, @PathVariable String projectSourceName) {
        List<Project> projectList = projectService.importProjects(projectSourceName);
// load source services and get them?
        model.addAttribute("projectList", projectList);
        model.addAttribute("pageTitle", "Imported Projects new project.");

        return "projectcreate";
    }

    @RequestMapping(value = "/projects/sources", method = RequestMethod.GET)
    public String projectSources(Model model) {
// load source services and get them?
        model.addAttribute("projectSources", projectService.getProjectSources());
        model.addAttribute("pageTitle", "Create a new project.");

        return "projectcreate";
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
