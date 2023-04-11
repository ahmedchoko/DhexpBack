package com.wevioo.parametrage.controllers;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.dto.StopLossDto;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.StopLoss;
import com.wevioo.parametrage.services.StopLossService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/stoploss")
public class StopLossController {

    @Autowired
    private StopLossService stopLossService ;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
   // public Page<StopLoss> getAllStopLoss() { return stopLossService.getAllStopLoss(int page, int size);}
    public ResponseEntity <Page<StopLoss> > getAllStopLoss(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "20") int size) {

        try { Page<StopLoss> stopLosses = stopLossService.getAllStopLoss(page, size);
            return ResponseEntity.ok().body(stopLosses) ;   }
        catch(NotFoundException exception) { return ResponseEntity.notFound().build();  }    }

    @PostMapping
    public ResponseEntity<StopLoss> createStopLoss(@RequestBody StopLossDto stopLossRequest) {
        try {
            StopLoss stopLoss = stopLossService.createStopLoss(stopLossRequest);
            return ResponseEntity.ok().body(stopLoss) ;
        }
        catch(NotFoundException exception) {
            return ResponseEntity.badRequest().build();

        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<StopLoss> getStopLossById(@PathVariable(name= "id") Long id){
        try {
            StopLoss stopLoss = stopLossService.getStopLossById(id);
            return ResponseEntity.ok().body(stopLoss) ;
        }
        catch(NotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<StopLoss> updateStopLoss(@PathVariable(name= "id") Long id, @RequestBody StopLossDto stopLossRequest) {
        try {
            StopLoss stopLoss = stopLossService.updateStopLoss(id, stopLossRequest);
            return ResponseEntity.ok().body(stopLoss) ;
        }
        catch(NotFoundException exception){
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StopLoss> deleteStopLoss(@PathVariable(name= "id") Long id) {
        try {
            StopLoss stoploss = stopLossService.deleteStopLoss(id);
            return ResponseEntity.ok().body(stoploss);
        } catch (NotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

}
