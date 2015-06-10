package org.npu.etms.exceptions;

public class EmployeeUniqueEmailException extends RuntimeException {
	public EmployeeUniqueEmailException(String msg) {
		super(msg);
	}
}
