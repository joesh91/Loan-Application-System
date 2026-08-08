package com.loan.test;

import com.loan.service.StaffService;
import com.loan.entity.Staff;
import java.util.List;

public class StaffServiceTest {
	public static void main(String[] args) {

		StaffService staffService = new StaffService();

		Staff staff = staffService.findStaff(1);
		/*
		 * staff.setStaffId(1); staff.setEmail("TEST2@GMAIL.COM");
		 * staff.setName("TEST 2"); staff.setPosition("SOFTWARE ENGINEER");
		 * 
		 * staffService.registerStaff(staff);
		 * 
		 */

		staff.setEmail("TEST MERGED");

		staffService.updateStaff(staff);

		System.out.println(staffService.getAllStaff());

	}
}