package com.wevioo.parametrage.controllers;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.enums.TypeDemande;
import com.wevioo.parametrage.enums.TypeModalite;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.ModaliteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("parametrage/api/v1/modalites")
public class ModaliteController {

	@Autowired
	private ModaliteService modaliteService;

	@Autowired
	private FondService fondService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Get the list of all Modalites.
	 *
	 * @param page The page number for pagination (default value: 0).
	 * @param size The page size for pagination (default value: 10).
	 * @return ResponseEntity containing the paginated list of Modalites.
	 */
	@GetMapping
	public ResponseEntity<Page<Modalite>> getAllModalite(@RequestParam(defaultValue = "0") int page,
	                                                     @RequestParam(defaultValue = "10") int size) {
	    try {
	        Page<Modalite> modalites = modaliteService.getAllModalite(page, size);
	        return ResponseEntity.ok().body(modalites);
	    } catch (NotFoundException exception) {
	        return ResponseEntity.notFound().build();
	    }
	}

	/**
	 * Create a new Modalite.
	 *
	 * @param modaliteRequest The ModaliteDto object containing the details of the Modalite to be created.
	 * @return ResponseEntity containing the created Modalite.
	 */
	@PostMapping
	public ResponseEntity<Modalite> createModalite(@RequestBody ModaliteDto modaliteRequest) {
	    try {
	        Fond fond = fondService.getFondById(modaliteRequest.getFond().getIdFond());
	        List<TypeDemande> listeMod = new ArrayList<>();
	        fond.getModalites().forEach((Modalite mod) -> listeMod.add(mod.getNatureDemande()));
	        if (listeMod.contains(modaliteRequest.getNatureDemande())) {
	            return ResponseEntity.badRequest().build();
	        }
	        Modalite modalite = modaliteService.createModalite(modaliteRequest);
	        return ResponseEntity.ok().body(modalite);
	    } catch (NotFoundException exception) {
	        return ResponseEntity.badRequest().build();
	    }
	}

	/**
	 * Get a Modalite by its ID.
	 *
	 * @param id The ID of the Modalite.
	 * @return ResponseEntity containing the retrieved Modalite.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Modalite> getModaliteById(@PathVariable(name = "id") Long id) {
	    try {
	        Modalite modalite = modaliteService.getModaliteById(id);
	        return ResponseEntity.ok().body(modalite);
	    } catch (NotFoundException exception) {
	        return ResponseEntity.notFound().build();
	    }
	}

	/**
	 * Update a Modalite by its ID.
	 *
	 * @param id              The ID of the Modalite to be updated.
	 * @param modaliteRequest The ModaliteDto object containing the updated details of the Modalite.
	 * @return ResponseEntity containing the updated Modalite.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Modalite> updateModalite(@PathVariable(name = "id") Long id,
	                                                @RequestBody ModaliteDto modaliteRequest) {
	    try {
	        Fond fond = fondService.getFondById(modaliteRequest.getFond().getIdFond());
	        List<TypeModalite> listeMod = new ArrayList<>();
	        fond.getModalites().forEach((Modalite mod) -> listeMod.add(mod.getTypeModalite()));
	        if (modaliteRequest.getTypeModalite() == modaliteService.getModaliteById(id).getTypeModalite()) {
	            Modalite modalite = modaliteService.updateModalite(id, modaliteRequest);
	            return ResponseEntity.ok().body(modalite);
	        }
	        if (listeMod.contains(modaliteRequest.getTypeModalite())) {
	            return ResponseEntity.badRequest().build();
	        }
	        Modalite modalite = modaliteService.updateModalite(id, modaliteRequest);
	        return ResponseEntity.ok().body(modalite);
	    } catch (NotFoundException exception) {
	        return ResponseEntity.badRequest().build();
	    }
	}

	/**
	 * Delete a Modalite by its ID.
	 *
	 * @param id The ID of the Modalite to be deleted.
	 * @return ResponseEntity containing the deleted Modalite.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Modalite> deleteModalite(@PathVariable(name = "id") Long id) {
	    try {
	        Modalite modalite = modaliteService.deleteModalite(id);
	        return ResponseEntity.ok().body(modalite);
	    } catch (NotFoundException exception) {
	        return ResponseEntity.notFound().build();
	    }
	}

	/**
	 * Get the total count of Modalites by type.
	 *
	 * @return ResponseEntity containing the count of Modalites.
	 */
	@GetMapping("/nombreModaliteParType")
	public ResponseEntity nombreModaliteParType() {
	    try {
	        return ResponseEntity.ok().body(modaliteService.nobreModaliteParType());
	    } catch (NotFoundException exception) {
	        return ResponseEntity.notFound().build();
	    }
	}

}
