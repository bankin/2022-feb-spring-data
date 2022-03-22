package com.example.workshop1.controller;


import com.example.workshop1.service.CompanyService;
import com.example.workshop1.service.EmployeeService;
import com.example.workshop1.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    @Autowired
    public HomeController(CompanyService companyService, EmployeeService employeeService, ProjectService projectService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/home")
    public ModelAndView index() {
        boolean areImported = companyService.areImported() &&
                employeeService.areImported() &&
                projectService.areImported();

        ModelAndView modelAndView = new ModelAndView("home");

        modelAndView.addObject("title", "Some new page title");
        modelAndView.addObject("areImported", areImported);

        return modelAndView;
    }
}
