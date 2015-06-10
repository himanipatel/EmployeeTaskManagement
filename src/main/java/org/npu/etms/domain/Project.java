package org.npu.etms.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "project")
public class Project {
	private int projectId;
	private String name;
	private Employee manager;
	private int teamMember;
	private int managerId;
	private String description;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	/**
	 * @return the teamMember
	 */
	public int getTeamMember() {
		return teamMember;
	}

	/**
	 * @param teamMember
	 *            the teamMember to set
	 */
	public void setTeamMember(int teamMember) {
		this.teamMember = teamMember;
	}

	public String toString() {
		String str = "\tProject Id: " + projectId + "\n\tName:" + name
				+ "\n\tManager Id:" + managerId + "\n\tTeam Member Count: "
				+ teamMember + "\n";
		return str;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}