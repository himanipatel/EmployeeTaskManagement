package org.npu.etms.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Employee {
	private int employeeId;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	private String middleName;
	@NotEmpty @Email
	private String email;

	//@NotEmpty
	//@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date birthDate;

	//@NotEmpty
	//@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date joiningDate;

	@NotEmpty
	private String contactNo;

	@NotEmpty
	private String currentAddress;

	@NotEmpty
	private String department;
	
	@NotEmpty
	private String designation;
	private Date createdDate;
	private Date modifiedDate;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String toString() {
		String str = "\tEmployee Id: " + employeeId + "\n\tFirst Name: "
				+ firstName + "\n\tLast Name: " + lastName
				+ "\n\tMiddle Name: " + middleName + "\n\tEmail: " + email
				+ "\n\tBirth date: " + birthDate + "\n\tJoining date: "
				+ joiningDate + "\n\tContact No: " + contactNo
				+ "\n\tAddress: " + currentAddress + "\n\tDepartment: "
				+ department + "\n\tDesignation: " + designation + "\n";
		return str;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
