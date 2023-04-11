package com.wevioo.parametrage.servicesImpl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.repository.QuotiteRepository;
import com.wevioo.parametrage.services.QuotiteService;
import com.wevioo.parametrage.specification.FondSpecification;
import com.wevioo.parametrage.specification.QuotiteSpecification;

@Service
public class QuotiteServiceImpl implements QuotiteService{

	@Autowired
	private QuotiteRepository quotiteRepository;
	
	@Override
	public Page <Quotite> getAllQuotite(int page , int size , String fond ,String zone, String zonal , String ritic,String nouveauProm,String creditLeasing) throws ParseException {
          Pageable pageable = PageRequest.of(page, size);
		 QuotiteSpecification specif = new QuotiteSpecification();
		 Specification <Quotite> spec = specif.getSpec(fond,zone, zonal, ritic,nouveauProm, creditLeasing );
		 return quotiteRepository.findAll(spec,pageable);
	}
	@Override
	public Page<Quotite> getAllQuotite(int page, int size) throws ParseException {
		// TODO Auto-generated method stub
		  Pageable pageable = PageRequest.of(page, size);
		return quotiteRepository.findAll(pageable);
	}
}
