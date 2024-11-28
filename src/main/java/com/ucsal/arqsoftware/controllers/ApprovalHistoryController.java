package com.ucsal.arqsoftware.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ucsal.arqsoftware.proxy.ApprovalHistoryProxy;
import com.ucsal.arqsoftware.response.ApprovalHistory;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/approvalhistories")
public class ApprovalHistoryController {

	@Autowired
	private ApprovalHistoryProxy proxy;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_MANAGER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ApprovalHistory> findById(@PathVariable Long id) {
		ApprovalHistory approvalHistory = proxy.getApprovalHistoryById(id);
		return ResponseEntity.ok(approvalHistory);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_MANAGER')")
	@GetMapping
	public ResponseEntity<Page<ApprovalHistory>> findByAll(Pageable pageable) {
		Page<ApprovalHistory> approvalHistory = proxy.getApprovalHistory(pageable);
		return ResponseEntity.ok(approvalHistory);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<ApprovalHistory> insert(@Valid @RequestBody ApprovalHistory approvalHistory) {
		approvalHistory = proxy.postApprovalHistory(approvalHistory);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(approvalHistory.getId()).toUri();
		return ResponseEntity.created(uri).body(approvalHistory);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ApprovalHistory> update(@PathVariable Long id, @Valid @RequestBody ApprovalHistory approvalHistory) {
		approvalHistory = proxy.putApprovalHistory(id, approvalHistory);
		return ResponseEntity.ok(approvalHistory);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		proxy.deleteApprovalHistory(id);
		return ResponseEntity.noContent().build();
	}
	
}
