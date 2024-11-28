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

import com.ucsal.arqsoftware.response.PhysicalSpace;
import com.ucsal.arqsoftware.response.PhysicalSpaceType;

@FeignClient(name = "physicalspaces-service")
public interface PhysicalSpaceProxy {

	@GetMapping(value = "/physicalspaces-service")
	public Page<PhysicalSpace> getPhysicalSpace(Pageable pageable);
	
	@GetMapping(value = "/physicalspaces-service/{id}")
	public PhysicalSpace getPhysicalSpaceById(@PathVariable Long id);
	
	@PostMapping(value = "physicalspaces-service")
	public PhysicalSpace postPhysicalSpace(@RequestBody PhysicalSpace physicalSpace);
	
	@PutMapping(value = "/physicalspaces-service/{id}")
	public PhysicalSpace putPhysicalSpace(@PathVariable Long id, @RequestBody PhysicalSpace physicalSpace);
	
	@DeleteMapping(value = "/physicalspaces-service/{id}")
	public Void deletePhysicalSpace(@PathVariable Long id);
	
	@GetMapping(value = "/physicalspaces-service/type/{type}")
	public Page<PhysicalSpace> getByType(@PathVariable PhysicalSpaceType type, Pageable pageable);
	
	@GetMapping(value = "/physicalspaces-service/capacity/{capacity}")
	public Page<PhysicalSpace> getByCapacity(@PathVariable Integer capacity, Pageable pageable);
	
	@GetMapping(value = "/physicalspaces-service/name/{name}")
	public Page<PhysicalSpace> getByName(@PathVariable String name, Pageable pageable);
	
	@GetMapping(value = "/physicalspaces-service/availability/{availability}")
	public Page<PhysicalSpace> getByAvailability(@PathVariable Boolean availability, Pageable pageable);
}
