package com.ucsal.arqsoftware.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ucsal.arqsoftware.response.ApprovalHistory;

@FeignClient(name = "approvalhistory-service")
public interface ApprovalHistoryProxy {

	@GetMapping(value = "/approvalhistories-service")
	public Page<ApprovalHistory> getApprovalHistory(Pageable pageable);
	
	@GetMapping(value = "/approvalhistories-service/{id}")
	public ApprovalHistory getApprovalHistoryById(@PathVariable Long id);
	
	@PostMapping(value = "approvalhistories-service")
	public ApprovalHistory postApprovalHistory(@RequestBody ApprovalHistory approvalHistory);
	
	@PutMapping(value = "/approvalhistories-service/{id}")
	public ApprovalHistory putApprovalHistory(@PathVariable Long id, @RequestBody ApprovalHistory approvalHistory);
	
	@DeleteMapping(value = "/approvalhistories-service/{id}")
	public Void deleteApprovalHistory(@PathVariable Long id);
}
