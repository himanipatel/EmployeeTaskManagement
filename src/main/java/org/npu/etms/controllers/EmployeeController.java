package org.npu.etms.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.npu.etms.domain.Employee;
import org.npu.etms.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Resource
	private List<String> departmentList;

	@Resource
	private List<String> designationList;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat(
				"MM/dd/yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping(value = "/newEmployeeAddForm", method = RequestMethod.GET)
	public ModelAndView newEmployeeAddForm() {
		ModelAndView modelView;
		modelView = new ModelAndView("EmployeeAddForm");
		modelView.addObject("employee", new Employee());
		modelView.addObject("departmentList", departmentList);
		modelView.addObject("designationList", designationList);
		return modelView;
	}

	@RequestMapping(value = "/newViewEmployeeForm", method = RequestMethod.GET)
	public ModelAndView newViewEmployeeForm() {
		ModelAndView modelView;
		modelView = new ModelAndView("ViewEmployee");
		return modelView;
	}

	@RequestMapping(value = "/newEmployeeUpdateForm", method = RequestMethod.GET)
	public ModelAndView newEmployeeUpdateForm(
			@RequestParam("employeeId") String empId) {
		ModelAndView modelView;
		modelView = new ModelAndView("EmployeeUpdateForm");
		Employee emp = new Employee();
		try {
			int employeeId = Integer.parseInt(empId);
			emp = employeeService.findEmployeeById(employeeId);
		} catch (Exception ex) {
			modelView.addObject("errorMessage", ex.getMessage());
		}
		modelView.addObject("employee", emp);
		modelView.addObject("departmentList", departmentList);
		modelView.addObject("designationList", designationList);
		return modelView;
	}

	@RequestMapping(value = "/newEmployeeDeleteForm", method = RequestMethod.GET)
	public ModelAndView newEmployeeDeleteForm(
			@RequestParam("employeeId") String empId) {
		ModelAndView modelView;
		modelView = new ModelAndView("EmployeeDeleteForm");
		modelView.addObject("employeeId", empId);
		return modelView;
	}

	@RequestMapping(value = "/processViewEmployeeForm", method = RequestMethod.POST)
	public ModelAndView processViewEmployeeForm(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, Model model) {
		ModelAndView modelView;
		modelView = new ModelAndView("ViewEmployee");
		List<Employee> empList = null;
		try {
			empList = employeeService.findEmployeeByName(firstName, lastName);
		} catch (Exception ex) {
			modelView.addObject("errorMessage", ex.getMessage());
		}
		if (empList == null || empList.size() == 0) {
			modelView.addObject("errorMessage", "No data found.");
		}
		modelView.addObject("employeeList", empList);
		return modelView;
	}

	@RequestMapping(value = "/deleteEmployeeProfile", method = RequestMethod.POST)
	public ModelAndView deleteEmployeeProfile(
			@RequestParam("employeeId") String empId, Model model) {
		ModelAndView modelView;
		modelView = new ModelAndView("EmployeeDeleteForm");
		try {
			int employeeId = Integer.parseInt(empId);
			employeeService.removeEmployeeById(employeeId);
			modelView = new ModelAndView("EmployeeProcessSuccess");
			modelView.addObject("processType", "employeeDelete");
		} catch (Exception ex) {
			modelView.addObject("errorMessage", ex.getMessage());
		}
		return modelView;
	}

	@RequestMapping(value = "/updateEmployeeProfile", method = RequestMethod.POST)
	public ModelAndView updateEmployeeProfile(@Valid Employee employee,
			BindingResult result, HttpSession session) {
		ModelAndView modelView;
		modelView = new ModelAndView("EmployeeUpdateForm", "employee", employee);
		modelView.addObject("departmentList", departmentList);
		modelView.addObject("designationList", designationList);

		if (result.hasErrors()) {
			return modelView;
		}

		employee.setModifiedDate(new Date());
		try {
			employeeService.updateEmployeeById(employee);
			modelView = new ModelAndView("EmployeeProcessSuccess");
			modelView.addObject("processType", "employeeUpdate");
		} catch (Exception ex) {
			modelView.addObject("errorMessage", ex.getMessage());
		}

		modelView.addObject("employee", employee);
		return modelView;
	}

	@RequestMapping(value = "/processNewEmployeeProfile", method = RequestMethod.POST)
	public ModelAndView processNewEmployeeForm(@Valid Employee employee,
			BindingResult result, HttpSession session) {
		ModelAndView modelView;
		modelView = new ModelAndView("EmployeeAddForm", "employee", employee);
		modelView.addObject("departmentList", departmentList);
		modelView.addObject("designationList", designationList);

		if (result.hasErrors()) {
			return modelView;
		}

		employee.setCreatedDate(new Date());
		try {
			employeeService.insertEmployee(employee);
			modelView = new ModelAndView("EmployeeProcessSuccess");
			modelView.addObject("processType", "employeeAdd");
		} catch (Exception ex) {
			modelView.addObject("errorMessage", ex.getMessage());
		}
		modelView.addObject("employee", employee);
		return modelView;
	}
}