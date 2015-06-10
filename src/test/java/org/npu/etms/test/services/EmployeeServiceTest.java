package org.npu.etms.test.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.npu.etms.dao.EmployeeDAO;
import org.npu.etms.domain.Employee;
import org.npu.etms.exceptions.EmployeeUniqueEmailException;
import org.npu.etms.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("classpath:emsapp-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
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
		setupEmployee.setEmail("mani098@mail.npu.edu");
		setupEmployee.setContactNo("123-456-7890");
		setupEmployee.setCurrentAddress("789 test drive");
		setupEmployee.setDepartment("software");
		setupEmployee.setDesignation("software engineer");
		setupEmployee.setCreatedDate(new Date());
	}

	@Test(expected = EmployeeUniqueEmailException.class)
	public void testUniqueEmailException() throws Exception {
		setupEmployee.setEmail("himani@mail.npu.edu");
		employeeService.insertEmployee(setupEmployee);
	}
}