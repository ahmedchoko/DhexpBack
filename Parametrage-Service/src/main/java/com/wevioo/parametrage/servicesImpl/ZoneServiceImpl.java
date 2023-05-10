package com.wevioo.parametrage.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.repository.ZoneRepository;
import com.wevioo.parametrage.services.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService{
	@Autowired
	private ZoneRepository zoneRepository;
	@Override
	public List<Zone> listZone() {
		// TODO Auto-generated method stub
		List <Zone> zonesNonQuotite = new ArrayList();
		for (Zone zone :zoneRepository.findAll()) {
			if (zone.getQuotitee()==null) {
				zonesNonQuotite.add(zone);
			}
		}
		return zonesNonQuotite ;
	}

}
