package com.wevioo.parametrage.servicesImpl;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;




@Service
public class ParametrageConsume {

    private final FondServiceImpl fondServiceImpl;
    private final PartenaireServiceImpl partenaireServiceImpl;
    private final ParametrageProduce parametrageProducer;
    private final StopLossServiceImpl stopLossServiceimpl ; 

    @Autowired
    public ParametrageConsume(StopLossServiceImpl stopLossServiceimpl  , PartenaireServiceImpl partenaireServiceImpl , FondServiceImpl fondServiceImpl, ParametrageProduce parametrageProducer) {
        this.fondServiceImpl = fondServiceImpl;
		this.partenaireServiceImpl =  partenaireServiceImpl;
        this.parametrageProducer = parametrageProducer;
        this.stopLossServiceimpl=stopLossServiceimpl;
    }
/*
    @KafkaListener(topics = "topic1", groupId = "fond")
    public void consumeMessage(ParametrageEvent event) {
        System.out.print("xxxxxx"+event.getMessage());

        // Fetch the list of Fond objects from your database or service
        List<Fond> fonds = fondServiceImpl.listFond();
        List<Partenaire> partenaires = partenaireServiceImpl.getAllPartenaire();
        List<StoplossPartenaire> stpparteanire = stopLossServiceimpl.getSLPartenaire();
        // Prepare the ParametrageEvent with the list of Fond objects
        ParametrageEvent parametrageEvent = new ParametrageEvent();
        parametrageEvent.setStatus("PENDING");
        parametrageEvent.setMessage("parametrage status is in pending");
        parametrageEvent.setFond(fonds);
        parametrageEvent.setPartenaire(partenaires);
        parametrageEvent.setStpparteanire(stpparteanire);
        // Send the ParametrageEvent to the other microservice
        parametrageProducer.sendMessage(parametrageEvent);
    }*/
}
