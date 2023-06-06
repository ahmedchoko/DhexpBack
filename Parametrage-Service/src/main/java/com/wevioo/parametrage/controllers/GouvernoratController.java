package com.wevioo.parametrage.controllers;

import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Gouvernorat;
import com.wevioo.parametrage.repository.GouvernoratRepository;
import com.wevioo.parametrage.services.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parametrage/api/v1/gouvernorats")
public class GouvernoratController {
	@Autowired
	GouvernoratRepository gouvernoratRepository;

	/**
	 * Get the list of all Gouvernorats.
	 *
	 * @return List of Gouvernorats.
	 */
	@GetMapping
	public List<Gouvernorat> getAllGouvernorats(){
	    return gouvernoratRepository.findAll();
	}

}