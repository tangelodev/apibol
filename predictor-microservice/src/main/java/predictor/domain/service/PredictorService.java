package predictor.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import predictor.domain.Event;
import predictor.domain.Participant;
import predictor.domain.Predictor;
import predictor.domain.repository.PredictorRepository;
import predictor.domain.resource.PredictorDTO;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
@Service
public class PredictorService {

    private final ParticipantService participantService;
            
    private final EventService eventService;

    private final PredictorRepository predictorRepository;
    
    @Autowired
    public PredictorService(ParticipantService participantService, EventService eventService,PredictorRepository predictorRepository) {
        this.participantService = participantService;
        this.eventService = eventService;
        this.predictorRepository = predictorRepository;
    }

    public Predictor create(PredictorDTO predictorDTO) {
        Participant newParticipant = this.participantService.getUserInfo(predictorDTO.getUserId());
        Event event = this.eventService.getEventInfo(predictorDTO.getEventId());
        Predictor predictor = Predictor.createPredictor(event.getEventId(), newParticipant);
        predictor = this.predictorRepository.save(predictor);
        return predictor;
    }

}