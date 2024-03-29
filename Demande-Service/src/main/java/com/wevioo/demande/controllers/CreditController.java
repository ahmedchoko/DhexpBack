package com.wevioo.demande.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.demande.entities.Demande;
import com.wevioo.demande.repository.DemandeRepository;
import com.wevioo.demande.servicesImpl.DemandeProducer;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.ParametrageEvent;


@RestController
@RequestMapping("demande/api/v1/credits")
public class CreditController {
}
