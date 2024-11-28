package com.ucsal.arqsoftware.response;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApprovalHistory {

	@Setter
	private Long id;
	
	@Setter
	private Date dateTime;
	
	@Setter
	private boolean decision;
	
	@Setter
	private String observation;
	
	@Setter
	private Long userId;
	
	@Setter
	private Long requestId;
	
	public ApprovalHistory() {
	}
	
}
