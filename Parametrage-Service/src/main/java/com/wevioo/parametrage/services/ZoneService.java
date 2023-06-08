package com.wevioo.parametrage.services;

import java.util.List;


import com.wevioo.parametrage.dto.ZoneDTO;
import com.wevioo.parametrage.entities.Zone;
import org.springframework.data.domain.Page;

public interface ZoneService {
	Page<Zone> getAllZones(int page, int size ) throws Exception;

	Zone createZone(ZoneDTO zoneRequest) throws Exception;

	Zone getZoneById(Long id) throws Exception;

	Zone updateZone(Long id, ZoneDTO zoneRequest) throws Exception;

	Zone deleteZone(Long id) throws Exception;
	 public List<Zone> listZone();
	 public List<Zone> listZoneWithoutAffectation();
}
