package com.wevioo.parametrage.controllers;

import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.repository.DelegationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parametrage/api/v1/delegations")
public class DelegationController {
	@Autowired
	DelegationRepository delegationRepository;

	/**
	 * Get the list of all Delegations.
	 *
	 * @return List of Delegations.
	 */
	@GetMapping
	public List<Delegation> getAllDelegations(){
	    return delegationRepository.findAll();
	}


}
