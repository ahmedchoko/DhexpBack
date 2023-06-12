package com.wevioo.parametrage.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.ParametrageEvent;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.entities.StoplossPartenaire;

/**
 * Service implementation for consuming Kafka messages related to parameterization.
 */
@Service
public class ParametrageConsume {

    private final FondServiceImpl fondServiceImpl;
    private final PartenaireServiceImpl partenaireServiceImpl;
    private final ParametrageProduce parametrageProducer;
    private final StopLossServiceImpl stopLossServiceimpl;

    @Autowired
    public ParametrageConsume(StopLossServiceImpl stopLossServiceimpl, PartenaireServiceImpl partenaireServiceImpl,
            FondServiceImpl fondServiceImpl, ParametrageProduce parametrageProducer) {
        this.fondServiceImpl = fondServiceImpl;
        this.partenaireServiceImpl = partenaireServiceImpl;
        this.parametrageProducer = parametrageProducer;
        this.stopLossServiceimpl = stopLossServiceimpl;
    }

    /**
     * Kafka message consumer method.
     *
     * @param event The ParametrageEvent received from Kafka.
     */
    @KafkaListener(topics = "topic1", groupId = "fond")
    public void consumeMessage(ParametrageEvent event) {
        // Fetch the list of Fond objects
        List<Fond> fonds = fondServiceImpl.listFond();
        List<Partenaire> partenaires = partenaireServiceImpl.getAllPartenaire();
        List<StoplossPartenaire> stpparteanire = stopLossServiceimpl.getSLPartenaire();

        // Prepare the ParametrageEvent with the list of Fond , Partenaire , Stopparteanire objects
        ParametrageEvent parametrageEvent = new ParametrageEvent();
        parametrageEvent.setStatus("PENDING");
        parametrageEvent.setMessage("parametrage status is in pending");
        parametrageEvent.setFond(fonds);
        parametrageEvent.setPartenaire(partenaires);
        parametrageEvent.setStpparteanire(stpparteanire);

        // Send the ParametrageEvent to the other microservice
        parametrageProducer.sendMessage(parametrageEvent);
    }
}
