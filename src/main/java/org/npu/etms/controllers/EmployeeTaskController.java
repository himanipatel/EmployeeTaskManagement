package org.npu.etms.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.npu.etms.domain.Employee;
import org.npu.etms.domain.EmployeeTask;
import org.npu.etms.domain.Project;
import org.npu.etms.services.EmployeeService;
import org.npu.etms.services.EmployeeTaskService;
import org.npu.etms.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeTaskController {

	@Autowired
	private EmployeeTaskService taskService;

	@Resource
	private List<String> statusList;

	@Resource
	private List<String> categoryList;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ProjectService projectService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat(
				"MM/dd/yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping(value = "/newEmployeeTaskForm", method = RequestMethod.GET)
	public ModelAndView newEmployeeDeleteForm() {
		ModelAndView modelView;
		modelView = new ModelAndView("EmployeeTaskForm");
		List<Employee> employeeList = getEmployeeList();
		List<Project> projectList = getProjectList();
		modelView.addObject("employeeList", employeeList);
		modelView.addObject("projectList", projectList);
		modelView.addObject("statusList", statusList);
		modelView.addObject("categoryList", categoryList);
		modelView.addObject("employeeTask", new EmployeeTask());
		return modelView;
	}

	@RequestMapping(value = "/assignProject", method = RequestMethod.POST)
	public ModelAndView updateEmployeeProfile(@Valid EmployeeTask employeeTask,
			BindingResult result, HttpSession session) {
		List<Employee> employeeList = null;
		List<Project> projectList = null;
		ModelAndView modelView;
		modelView = new ModelAndView("EmployeeTaskForm", "employeeTask",
				employeeTask);
		employeeList = getEmployeeList();
		projectList = getProjectList();
		modelView.addObject("employeeList", employeeList);
		modelView.addObject("projectList", projectList);
		modelView.addObject("statusList", statusList);
		modelView.addObject("categoryList", categoryList);

		if (result.hasErrors()) {
			return modelView;
		}

		try {
			employeeTask.setCreatedDate(new Date());
			taskService.assignProjectToEmployee(employeeTask);
			modelView = new ModelAndView("ProjectAssignSuccess");
		} catch (Exception ex) {
			modelView.addObject("errorMessage", ex.getMessage());
		}

		modelView.addObject("employeeTask", employeeTask);
		return modelView;
	}

	private List<Employee> getEmployeeList() {
		List<Employee> employeeList = null;
		employeeList = employeeService.findAllEmployees();
		return employeeList;
	}

	private List<Project> getProjectList() {
		List<Project> projectList = null;
		projectList = projectService.findAllProjects();
		return projectList;
	}
}