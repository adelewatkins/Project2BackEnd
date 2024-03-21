package com.lbg.cczone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.lbg.cczone.Repos.AdminRepo;
import com.lbg.cczone.domain.Admin;

@Service
public class AdminService {
	private AdminRepo repo;

	public AdminService(AdminRepo repo) {
		super();
		this.repo = repo;
	}

	public List<Admin> getAdmin() {
		return this.repo.findAll();
	}

	public ResponseEntity<Admin> getAdmin(int id) {
		Optional<Admin> found = this.repo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
		}
		Admin body = found.get();
		return ResponseEntity.ok(body);

	}

	public ResponseEntity<Object> createAdmin(Admin Admin) {

		Admin created = this.repo.save(Admin);

		return new ResponseEntity<Object>(created, HttpStatus.CREATED);
	}

	public ResponseEntity<Admin> updateAdmin(int id, Admin Admin) {
		Optional<Admin> found = this.repo.findById(id);
		if (found.isEmpty()) {
			return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
		}
		Admin existing = found.get();
		if (Admin.getAdminName() != null) {
			existing.setAdminName(Admin.getAdminName());
		}

		if (Admin.getAdminId() != null) {
			existing.setAdminId(Admin.getAdminId());
		}
		if (Admin.getPassword() != null) {
			existing.setPassword(Admin.getPassword());
		}

		Admin updated = this.repo.save(existing);
		return ResponseEntity.ok(updated);

	}

	public boolean deleteAdmin(@PathVariable int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
