package org.npu.etms.test.dao;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.npu.etms.dao.EmployeeDAO;
import org.npu.etms.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:emsapp-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDAOTest {

	@Autowired
	@Qualifier("employeeDaoJdbc")
	private EmployeeDAO employeeDAO;
	private Employee setupEmployee;

	@Before
	public void setup() {
		setupEmployee = new Employee();
		setupEmployee.setFirstName("Himani");
		setupEmployee.setLastName("Desai");
		setupEmployee.setMiddleName("Dhaval");

		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date birthDate = null;
		Date joiningDate = null;

		try {
			birthDate = formatter.parse("12/29/1985");
			joiningDate = formatter.parse("08/01/2009");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		setupEmployee.setBirthDate(birthDate);
		setupEmployee.setJoiningDate(joiningDate);
		setupEmployee.setEmail("mani099@mail.npu.edu");
		setupEmployee.setContactNo("123-456-7890");
		setupEmployee.setCurrentAddress("789 test drive");
		setupEmployee.setDepartment("software");
		setupEmployee.setDesignation("software engineer");
		setupEmployee.setCreatedDate(new Date());
	}

	@Test
	public void testEmployeeCount() {
		int cnt = employeeDAO.getEmployeeCount();
		System.out.println("Total Employees:" + cnt);
	}

	@Test
	public void testFindAllEmployees() {
		List<Employee> employeeList = employeeDAO.findAllEmployees();
		int empCnt = employeeDAO.getEmployeeCount();
		assertTrue(employeeList.size() == empCnt);
		System.out.println(employeeList);
	}

	@Test
	public void testFinEmployeeById() {
		// Employee employee = employeeDAO.findEmployeeById(2);
		// System.out.println(employee);
	}

	@Test
	public void testInsertEmployee() {
		employeeDAO.insertEmployee(setupEmployee);
		System.out.println("New Employee added with id:"
				+ setupEmployee.getEmployeeId());
	}

	@Test
	public void testUpdateEmployee() {
		/*
		 * Employee employee = employeeDAO.findEmployeeById(2);
		 * 
		 * employee.setDepartment("Embedded");
		 * employee.setDesignation("senior engineer"); int rowAffected;
		 * rowAffected = employeeDAO.updateEmployeeById(employee);
		 * assertTrue(rowAffected == 1);
		 */
	}

	@Test
	public void testDeleteEmployee() {
		int rowAffected = employeeDAO.removeEmployeeById(8);
		assertTrue(rowAffected == 1);
	}

	@Test
	public void testFindEmployeeByEmail() {
		List<Employee> employee = employeeDAO
				.findEmployeeByEmail("hi14@mail.npu.edu",false,0);
		System.out.println(employee);
	}
}