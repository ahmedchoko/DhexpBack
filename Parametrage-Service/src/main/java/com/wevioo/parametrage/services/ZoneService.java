package com.wevioo.parametrage.services;

import java.util.List;

import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.dto.ZoneDto;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Zone;
import org.springframework.data.domain.Page;

public interface ZoneService {
	Page<Zone> getAllZones(int page, int size );

	Zone createZone(ZoneDto zoneRequest);

	Zone getZoneById(Long id);

	Zone updateZone(Long id, ZoneDto zoneRequest);

	Zone deleteZone(Long id);

}
