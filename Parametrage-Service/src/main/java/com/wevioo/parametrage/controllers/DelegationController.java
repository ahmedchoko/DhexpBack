package com.wevioo.parametrage.controllers;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.repository.DelegationRepository;
import com.wevioo.parametrage.services.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parametrage/api/v1/delegation")
public class DelegationController {
    @Autowired
    DelegationRepository delegationRepository;
    @GetMapping
    public List<Delegation> getAllDelegations(){

         return delegationRepository.findAll();   }

}
