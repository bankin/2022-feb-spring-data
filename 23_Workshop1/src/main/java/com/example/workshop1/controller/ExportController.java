package com.example.workshop1.controller;

import com.example.workshop1.service.EmployeeService;
import com.example.workshop1.service.ExportEmployeeDTO;
import com.example.workshop1.service.ProjectService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExportController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final Gson gson;

    @Autowired
    public ExportController(ProjectService projectService, EmployeeService employeeService, Gson gson) {
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.gson = gson;
    }

    @GetMapping("/export/employees-above")
    public ModelAndView showEmployeesAbove() {
        ModelAndView modelAndView = new ModelAndView("export/export-employees-with-age");

        List<ExportEmployeeDTO> employees = this.employeeService.getEmployeesAbove();

        StringBuilder sb = new StringBuilder();
        this.gson.toJson(employees, sb);

        modelAndView.addObject("employeesAbove", sb.toString());

        return modelAndView;
    }

    @GetMapping("/export/project-if-finished")
    public ModelAndView showFinishedProjects() {
        ModelAndView modelAndView = new ModelAndView("export/export-project-if-finished");

        String result = this.projectService.getFinishedProjects();

        modelAndView.addObject("projectsIfFinished", result);

        return modelAndView;
    }

}
