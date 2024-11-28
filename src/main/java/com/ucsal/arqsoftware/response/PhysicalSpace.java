package com.ucsal.arqsoftware.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PhysicalSpace {

	private Long id;
	
	@Setter
	private String name;
	
	@Setter
	private String location;
	
	@Setter
	private PhysicalSpaceType type;
	
	@Setter
	private Integer capacity;
	
	@Setter
	private Boolean availability;
	
	@Setter
	private String resources;
	
	public PhysicalSpace() {
	}
	
}
