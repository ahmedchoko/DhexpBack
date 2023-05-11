package com.wevioo.parametrage.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.wevioo.parametrage.dto.ZoneDTO;
import com.wevioo.parametrage.entities.StopLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.repository.ZoneRepository;
import com.wevioo.parametrage.services.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService{
	@Autowired
	private ZoneRepository zoneRepository;


	@Override
	public Page<Zone> getAllZones(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("idZone"));
		Page<Zone> myDataPage = zoneRepository.findAll(pageable);
		return myDataPage;
	}

	@Override
	public Zone createZone(ZoneDTO zoneRequest) {
		Zone zone = Zone.builder()
				.nomZone(zoneRequest.getNomZone())
				.nomArabeZone(zoneRequest.getNomArabeZone())
				.gouvernorats(zoneRequest.getGouvernorats())
				.delegations(zoneRequest.getDelegations())
				.build();
		zoneRepository.save(zone);
		return zone;
	}

	@Override
	public Zone getZoneById(Long id) {
		Zone zone = zoneRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
		return zone;
	}

	@Override
	public Zone updateZone(Long id, ZoneDTO zoneRequest) {
		Zone zone = zoneRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));

		zone.setNomZone(zoneRequest.getNomZone());
		zone.setNomArabeZone(zoneRequest.getNomArabeZone());
		zone.setDelegations(zoneRequest.getDelegations());
		zone.setGouvernorats(zoneRequest.getGouvernorats());
		zoneRepository.save(zone);
		return zone;
	}

	@Override
	public Zone deleteZone(Long id) {
		Zone zone = zoneRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
		zoneRepository.deleteById(id);
		return zone;
	}
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
