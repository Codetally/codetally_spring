package codetally.validator;


import codetally.model.Project;
import codetally.model.User;
import codetally.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProjectValidator implements Validator {
    @Autowired
    private ProjectService projectService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Project project = (Project) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectname", "NotEmpty");
        if (project.getProjectname().length() < 6 || project.getProjectname().length() > 32) {
            errors.rejectValue("projectname", "Size.projectForm.projectname");
        }
        if (projectService.findByProjectname(project.getProjectname()) != null) {
            errors.rejectValue("projectname", "Duplicate.projectForm.projectname");
        }

    }
}
