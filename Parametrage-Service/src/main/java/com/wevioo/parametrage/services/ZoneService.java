package com.wevioo.parametrage.services;

import java.util.List;

import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.dto.ZoneDTO;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Zone;
import org.springframework.data.domain.Page;

public interface ZoneService {
	Page<Zone> getAllZones(int page, int size );

	Zone createZone(ZoneDTO zoneRequest);

	Zone getZoneById(Long id);

	Zone updateZone(Long id, ZoneDTO zoneRequest);

	Zone deleteZone(Long id);
	 public List<Zone> listZone();
	 public List<Zone> listZoneWithoutAffectation();
}
