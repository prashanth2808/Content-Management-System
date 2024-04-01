package com.example.cms.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ContributionPanelResponse {

	private int panelid;
	private List<UserResponse> responses;
	
}
