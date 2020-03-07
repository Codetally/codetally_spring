package codetally.controller;

import codetally.model.Project;
import codetally.model.User;
import codetally.service.ProjectService;
import codetally.service.UserService;
import com.codetally.service.RepositoryServiceImpl;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
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
    private UserService userService;

    @RequestMapping(value = "/projects/{projectid}", method = RequestMethod.GET)
    public String getSingleProject(Model model, @PathVariable Long projectid) {

        Project singleproject = projectService.getById(projectid);
        User singleUser = userService.getOne(singleproject.getCreatedby());

        model.addAttribute("singleproject", singleproject);
        model.addAttribute("singleuser", singleUser);
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
        String name = principal.getName();
        User loggedUser = userService.findByUsername(name);
        projectForm.setCreatedby(loggedUser.getId());
        projectForm.setDatecreated(new Date());

        projectValidator.validate(projectForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Create a new project - but please fix these errors!");
            return "projectcreate";
        }

        projectService.save(projectForm);
        return "redirect:/members/"+name;
    }
    @RequestMapping(value = "/projects/search", method = RequestMethod.GET)
    public String searchforprojects(Model model, Pageable pageable,
                                 @RequestParam("keywords") String keywords,
                                 @RequestParam(value="location", required=false) String location,
                                 @RequestParam(value="latitude", required=false) Double flatitude,
                                 @RequestParam(value="longitude", required=false) Double flongitude,
                                 @RequestParam(value="category_id", required=false) Integer categoryId) {

        double latitude = flatitude;
        double longitude = flongitude;

        System.out.println("Keywords: " + keywords
                + "Location: " + location
                + "Latitude: " + latitude
                + "Longitude: " + longitude
                + " Category: " + categoryId);
        if (latitude < 1 && longitude < 1) {

            JSONArray joa = null;
            try {
                joa = (JSONArray) new JSONTokener(IOUtils.toString(new URL("https://nominatim.openstreetmap.org/search?q="+ URLEncoder.encode(location, "UTF-8")+"&format=json&addressdetails=1").openStream(), "UTF-8")).nextValue();
                JSONObject jo = joa.getJSONObject(0);
                latitude = jo.getDouble("lat");
                longitude = jo.getDouble("lon");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        List<project> searchresults = projectService.findprojectsByTitleDescriptionAndLocation(keywords, keywords, latitude, longitude, 100, pageable);
        model.addAttribute("searchresults", searchresults);
        model.addAttribute("keywords", keywords);
        model.addAttribute("location", location);

        return "projectlist";
    }


}
