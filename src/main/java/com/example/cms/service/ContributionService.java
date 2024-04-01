package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.response.ContributionPanelResponse;
import com.example.cms.util.ResponseStructure;

public interface ContributionService {
	
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(int userId,int panelId);

public	ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteablog(int panelId, int userId);

}
