package com.loan.service;

import java.util.ArrayList;
import java.util.List;
import com.loan.dao.StaffDAO;
import com.loan.dto.StaffDto;
import com.loan.entity.Staff;
import com.loan.exception.StaffNotFoundException;

public class StaffService {

	StaffDAO staffDAO = new StaffDAO();

	// REGISTER A Staff

	public void registerStaff(StaffDto staffDto) {

		if(staffDto == null) {
			throw new StaffNotFoundException("STAFF DETAILS CANNT BE EMPTY.");
		}
		
		Staff staff = new Staff();
		staff.setEmail(staffDto.getEmail());
		staff.setName(staffDto.getName());
		staff.setPosition(staffDto.getPosition());
		staff.setStaffId(staffDto.getStaffId());
		
		staffDAO.save(staff);
		
	}

	// UPDATE A Staff

	public void updateStaff(StaffDto staffDto) {

		if(staffDto == null) {
			throw new StaffNotFoundException("STAFF DETAILS CANNOT BE EMPTY.");
		}
		
		Staff staff = staffDAO.findById(staffDto.getStaffId());
		
		if(staff == null) {
			throw new StaffNotFoundException("STAFF DETAILS ARE NOT FOUND.");
		}
		
		staff.setEmail(staffDto.getEmail());
		staff.setName(staffDto.getName());
		staff.setPosition(staffDto.getPosition());
		staff.setStaffId(staffDto.getStaffId());
		
		staffDAO.update(staff);
	}

	// DELETE A Staff

	public void deleteStaff(StaffDto staffDto) {

		if(staffDto == null) {
			throw new StaffNotFoundException("STAFF DETAILS CANNOT BE EMPTY.");
		}
		
		Staff staff = staffDAO.findById(staffDto.getStaffId());
		
		if(staff == null) {
			throw new StaffNotFoundException("STAFF DETAILS ARE NOT FOUND.");
		}
		
		staffDAO.delete(staff);
	}

	// SEARCH A Staff

	public StaffDto findStaff(Long userID) {

		Staff staff = staffDAO.findById(userID);
		
		if(staff == null) {
			throw new StaffNotFoundException("STAFF DETAILS ARE NOT FOUND.");
		}
		
		StaffDto staffDto = new StaffDto();
		
		staffDto.setEmail(staff.getEmail());
		staffDto.setName(staff.getName());
		staffDto.setPosition(staff.getPosition());
		staffDto.setStaffId(staff.getStaffId());
		
		return staffDto;
	}

	// GET ALL Staff

	public List<StaffDto> getAllStaff() {

		List<Staff> staffs = staffDAO.findAll();
		
		List<StaffDto> staffDtos = new ArrayList<>();
		
		for(Staff s : staffs) {
			
			StaffDto staffDto = new StaffDto();
			
			staffDto.setEmail(s.getEmail());
			staffDto.setName(s.getName());
			staffDto.setPosition(s.getPosition());
			staffDto.setStaffId(s.getStaffId());
			
			staffDtos.add(staffDto);
		}
		
		return staffDtos;
	}

}
