package com.wevioo.parametrage.controllers;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.dto.StopLossDto;
import com.wevioo.parametrage.dto.StoplossPartenaireDto;
import com.wevioo.parametrage.entities.StopLoss;
import com.wevioo.parametrage.entities.StoplossPartenaire;
import com.wevioo.parametrage.services.StopLossService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("parametrage/api/v1/stoploss")
public class StopLossController {

    @Autowired
    private StopLossService stopLossService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all stop losses.
     *
     * @param page the page number
     * @param size the number of items per page
     * @return the paginated list of stop losses
     */
    @GetMapping
    public ResponseEntity<?> getAllStopLoss (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        try {
            Page<StopLoss> stopLosses = stopLossService.getAllStopLoss(page, size);
            return ResponseEntity.ok().body(stopLosses);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create a new stop loss.
     *
     * @param stopLossRequest the stop loss data
     * @return the created stop loss
     */
    @PostMapping
    public ResponseEntity<?> createStopLoss(@RequestBody StopLossDto stopLossRequest) {
        try {
            StopLoss stopLoss = stopLossService.createStopLoss(stopLossRequest);
            return ResponseEntity.ok().body(stopLoss);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get a stop loss by its ID.
     *
     * @param id the stop loss ID
     * @return the stop loss with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getStopLossById(@PathVariable(name = "id") Long id) {
        try {
            StopLoss stopLoss = stopLossService.getStopLossById(id);
            return ResponseEntity.ok().body(stopLoss);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update a stop loss by its ID.
     *
     * @param id               the stop loss ID
     * @param stopLossRequest the updated stop loss data
     * @return the updated stop loss
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStopLoss(@PathVariable(name = "id") Long id, @RequestBody StopLossDto stopLossRequest) {
        try {
            StopLoss stopLoss = stopLossService.updateStopLoss(id, stopLossRequest);
            return ResponseEntity.ok().body(stopLoss);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a stop loss by its ID.
     *
     * @param id the stop loss ID
     * @return the deleted stop loss
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStopLoss(@PathVariable(name = "id") Long id) {
        try {
            StopLoss stopLoss = stopLossService.deleteStopLoss(id);
            return ResponseEntity.ok().body(stopLoss);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create a stop loss with a partner.
     *
     * @param slPartenaireRequest the stop loss partner data
     * @return the created stop loss partner
     */
    @PostMapping("/slPartenaire")
    public ResponseEntity<?> createStoplossPartenaire(@RequestBody StoplossPartenaireDto slPartenaireRequest) {
        try {
            StoplossPartenaire slPartenaire = stopLossService.createSLPartenaire(slPartenaireRequest);
            return ResponseEntity.ok().body(slPartenaire);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all stop loss partners.
     *
     * @param page the page number
     * @param size the number of items per page
     * @return the paginated list of stop loss partners
     */
    @GetMapping("/slPartenaire")
    public ResponseEntity<?> getAllslPartenaire(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        try {
            Page<StoplossPartenaire> stoplossPartenaire = stopLossService.getSLPartenaire(page, size);
            return ResponseEntity.ok().body(stoplossPartenaire);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a stop loss partner.
     *
     * @param slpartenaire the stop loss partner data
     * @return the deleted stop loss partner
     */
    @PutMapping("/slPartenaire")
    public ResponseEntity<?> supprimerSLPartenaire(@RequestBody StoplossPartenaireDto slpartenaire) {
        try {
            StoplossPartenaire stoplossPartenaire = stopLossService.supprimerSLPartenaire(slpartenaire);
            return ResponseEntity.ok().body(stoplossPartenaire);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update a stop loss partner.
     *
     * @param slpartenaire the updated stop loss partner data
     * @return the updated stop loss partner
     */
    @PostMapping("updateSlPartenaire")
    public ResponseEntity<?> updateSLPartenaire(@RequestBody StoplossPartenaireDto slpartenaire) {
        try {
            StoplossPartenaire stoplossPartenaire = stopLossService.updateSLPartenaire(slpartenaire);
            return ResponseEntity.ok().body(stoplossPartenaire);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
