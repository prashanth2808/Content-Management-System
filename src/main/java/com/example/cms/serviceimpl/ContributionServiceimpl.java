package com.example.cms.serviceimpl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepo;
import com.example.cms.repository.UserRepository;
import com.example.cms.response.ContributionPanelResponse;
import com.example.cms.service.ContributionService;
import com.example.cms.util.IllegalAccessRequestException;
import com.example.cms.util.PanelNotFoundByIdException;
import com.example.cms.util.ResponseStructure;
import com.example.cms.util.UnauthorizedException;
import com.example.cms.util.UserNotFoundByIdException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContributionServiceimpl implements ContributionService {

	private UserRepository urepo;
	private ContributionPanelRepo crepo;
	private BlogRepository brepo;
	private  ResponseStructure<ContributionPanelResponse> responseStructure; 

	
	public ContributionPanelResponse maptoresponse(ContributionPanel panel )
	{
		return ContributionPanelResponse.builder().panelid(panel.getPanelid()).build();
	}


	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> addContributor(int userId, int panelId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return urepo.findByEmail(email).map(owner-> {

			return crepo.findById(panelId).map( panel ->{  
				if(! brepo.existsByUserAndPanel(owner, panel))
					throw new UnauthorizedException("failed to add contributer");
				return urepo.findById(userId).map(contributer ->{
					
					if(panel.getList().contains(contributer)==true)					
					{
						throw new IllegalAccessRequestException("contributer already present");

					}
					panel.getList().add(contributer);
					crepo.save(panel);
					return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value()).setMessage("contributore is added ").setData(maptoresponse(panel )));

				}).orElseThrow(() -> new UserNotFoundByIdException("Check the user id" ));

			}).orElseThrow(() -> new PanelNotFoundByIdException("Check the panelid "));

		}).get();

	}


	@Override
	public ResponseEntity<ResponseStructure<ContributionPanelResponse>> deleteablog(int panelId, int userId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();


		return urepo.findByEmail(email).map(owner-> {

			return crepo.findById(panelId).map( panel ->{  
							
				if(! brepo.existsByUserAndPanel(owner, panel))
					throw new UnauthorizedException("failed to remove contributer");
			
				
				return urepo.findById(userId).map(contributer ->{
					panel.getList().remove( owner );
					//crepo.save(panel);
					return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value()).setMessage("Contributer is removed ").setData(maptoresponse(panel)));

				}).orElseThrow(() -> new UserNotFoundByIdException("Check the used id" ));
			}).orElseThrow(() -> new PanelNotFoundByIdException("Check the panelid "));
		}).get();
	}
}