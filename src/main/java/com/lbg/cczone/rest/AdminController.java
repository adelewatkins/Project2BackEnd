package com.lbg.cczone.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbg.cczone.domain.Admin;
import com.lbg.cczone.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin

public class AdminController {

	private AdminService service;

	public AdminController(AdminService service) {
		super();
		this.service = service;
	}

	@GetMapping("/get")
	public List<Admin> getAdmin() {
		return this.service.getAdmin();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Admin> getAdmin(@PathVariable int id) {
		return this.service.getAdmin(id);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createAdmin(@RequestBody Admin Admin) {
		return this.service.createAdmin(Admin);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin Admin) {
		return this.service.updateAdmin(id, Admin);
	}

	@DeleteMapping("/delete/{id}")
	public boolean deleteAdmin(@PathVariable int id) {

		return this.service.deleteAdmin(id);
	}

}
