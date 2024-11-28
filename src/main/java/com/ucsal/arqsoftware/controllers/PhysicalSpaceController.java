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

import com.ucsal.arqsoftware.proxy.PhysicalSpaceProxy;
import com.ucsal.arqsoftware.response.PhysicalSpace;
import com.ucsal.arqsoftware.response.PhysicalSpaceType;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/physicalspaces")
public class PhysicalSpaceController {
	
	@Autowired
	private PhysicalSpaceProxy proxy;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_MANAGER')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<PhysicalSpace> findById(@PathVariable Long id) {
		PhysicalSpace physicalSpace = proxy.getPhysicalSpaceById(id);
		return ResponseEntity.ok(physicalSpace);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_MANAGER')")
	@GetMapping
	public ResponseEntity<Page<PhysicalSpace>> findByAll(Pageable pageable) {
		Page<PhysicalSpace> physicalSpace = proxy.getPhysicalSpace(pageable);
		return ResponseEntity.ok(physicalSpace);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<PhysicalSpace> insert(@Valid @RequestBody PhysicalSpace physicalSpace) {
		physicalSpace = proxy.postPhysicalSpace(physicalSpace);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(physicalSpace.getId()).toUri();
		return ResponseEntity.created(uri).body(physicalSpace);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<PhysicalSpace> update(@PathVariable Long id, @Valid @RequestBody PhysicalSpace physicalSpace) {
		physicalSpace = proxy.putPhysicalSpace(id, physicalSpace);
		return ResponseEntity.ok(physicalSpace);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		proxy.deletePhysicalSpace(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_MANAGER')")
	@GetMapping("/type/{type}")
    public ResponseEntity<Page<PhysicalSpace>> getByType(
            @PathVariable PhysicalSpaceType type, Pageable pageable) {
        Page<PhysicalSpace> physicalSpace = proxy.getByType(type, pageable);
        return ResponseEntity.ok(physicalSpace);
    }
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_MANAGER')")
    @GetMapping("/capacity/{capacity}")
    public ResponseEntity<Page<PhysicalSpace>> getByCapacity(
            @PathVariable Integer capacity, Pageable pageable) {
        Page<PhysicalSpace> physicalSpace = proxy.getByCapacity(capacity, pageable);
        return ResponseEntity.ok(physicalSpace);
    }
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_MANAGER')")
    @GetMapping("/name/{name}")
    public ResponseEntity<Page<PhysicalSpace>> getByName(
            @PathVariable String name, Pageable pageable) {
        Page<PhysicalSpace> physicalSpace = proxy.getByName(name, pageable);
        return ResponseEntity.ok(physicalSpace);
    }
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_MANAGER')")
    @GetMapping("/availability/{availability}")
    public ResponseEntity<Page<PhysicalSpace>> getByAvailability(
            @PathVariable Boolean availability, Pageable pageable) {
        Page<PhysicalSpace> physicalSpace = proxy.getByAvailability(availability, pageable);
        return ResponseEntity.ok(physicalSpace);
    }
}
