package org.npu.etms.dao;

import org.npu.etms.domain.EmployeeTask;

public interface EmployeeTaskDAO {
	public EmployeeTask findPendingTaskByProjectId(int projectid);

	public int insertEmployeeTask(EmployeeTask empTask);
}