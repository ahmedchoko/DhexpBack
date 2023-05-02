package com.wevioo.parametrage.servicesImpl;

import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.repository.DelegationRepository;
import com.wevioo.parametrage.services.DelegationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DelegationServiceImpl implements DelegationService {
    @Autowired
    DelegationRepository delegationRepository;
    @Override
    public List<Delegation> getAllDelegations() {
        return delegationRepository.findAll();
    }
}
