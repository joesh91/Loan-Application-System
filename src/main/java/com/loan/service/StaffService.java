package com.loan.service;

import java.util.List;
import com.loan.dao.StaffDAO;
import com.loan.entity.Staff;

public class StaffService {

	StaffDAO staffDAO = new StaffDAO();

	// REGISTER A Staff

	public void registerStaff(Staff staff) {

		staffDAO.save(staff);
	}

	// UPDATE A Staff

	public void updateStaff(Staff staff) {

		staffDAO.update(staff);

	}

	// DELETE A Staff

	public void deleteStaff(Staff staff) {

		staffDAO.delete(staff);
	}

	// SEARCH A Staff

	public Staff findStaff(int userID) {

		return staffDAO.findById(userID);
	}

	// GET ALL Staff

	public List<Staff> getAllStaff() {

		return staffDAO.findAll();
	}

}
