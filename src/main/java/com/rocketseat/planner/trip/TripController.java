package com.rocketseat.planner.trip;

import com.rocketseat.planner.activity.ActivityData;
import com.rocketseat.planner.activity.ActivityRequestPayload;
import com.rocketseat.planner.activity.ActivityResponse;
import com.rocketseat.planner.link.LinkData;
import com.rocketseat.planner.link.LinkRequestPayload;
import com.rocketseat.planner.link.LinkResponse;
import com.rocketseat.planner.participant.ParticipantCreateResponse;
import com.rocketseat.planner.participant.ParticipantData;
import com.rocketseat.planner.participant.ParticipantRequestPayload;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    // Criação do TripService para separar o core das trips - OK

    // Trips
    @Operation(summary = "Cria uma viagem", description = "Método que cria uma nova viagem", tags = "Viagem")
    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody @Valid TripRequestPayload payload){
        TripCreateResponse tripCreateResponse = this.tripService.createTrip(payload);
        return ResponseEntity.ok(tripCreateResponse);
    }

    @Operation(summary = "Obtém detalhes de uma viagem", description = "Método que obtém detalhes de uma viagem a partir do id da viagem", tags = "Viagem")
    @GetMapping("/{id}")
    public ResponseEntity<TripData> getTripDetails(@PathVariable UUID id){
        TripData tripData = this.tripService.getTripDetails(id);
        if(tripData != null){
            return ResponseEntity.ok(tripData);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Altera dados de uma viagem", description = "Método que altera os dados de destino, início e fim de uma viagem", tags = "Viagem")
    @PutMapping("/{id}")
    public ResponseEntity<TripData> updateTrip(@PathVariable UUID id, @RequestBody @Valid TripRequestPayload payload){
        TripData tripData = this.tripService.updateTrip(id, payload);
        if(tripData != null){
            return ResponseEntity.ok(tripData);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Confirma viagem", description = "Método que confirma que a viagem será realizada", tags = "Viagem")
    @GetMapping("/{id}/confirm")
    public ResponseEntity<TripData> confirmTrip(@PathVariable UUID id){
        TripData tripData = this.tripService.confirmTrip(id);
        if(tripData != null){
            return ResponseEntity.ok(tripData);
        }
        return ResponseEntity.notFound().build();
    }

    // Activities
    @Operation(summary = "Cria uma atividade", description = "Método que cria uma atividade para a viagem", tags = "Atividade")
    @PostMapping("/{id}/activities")
    public ResponseEntity<ActivityResponse> registerActivity(@PathVariable UUID id, @RequestBody ActivityRequestPayload payload){
        ActivityResponse activityResponse = this.tripService.registerActivity(id, payload);
        if(activityResponse != null){
            return ResponseEntity.ok(activityResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtém todas as atividades da viagem", description = "Método que obtém todas as atividaes da viagem a partir do id da viagem", tags = "Atividade")
    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityData>> getAllActivities(@PathVariable UUID id){
        List<ActivityData> activityDataList = this.tripService.getAllActivities(id);
        return ResponseEntity.ok(activityDataList);
    }

    // Participants
    @Operation(summary = "Registra um participante na viagem", description = "Método que registra um participante na viagem", tags = "Participante")
    @PostMapping("/{id}")
    public ResponseEntity<ParticipantCreateResponse> registerParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload){
        ParticipantCreateResponse participantCreateResponse = this.tripService.registerParticipant(id, payload);
        if(participantCreateResponse != null){
            return ResponseEntity.ok(participantCreateResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Convida um participante para viagem", description = "Método que convida um participante para a viagem", tags = "Participante")
    @PostMapping("/{id}/invite")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload){
        ParticipantCreateResponse participantCreateResponse = this.tripService.inviteParticipant(id, payload);
        if(participantCreateResponse != null){
            return ResponseEntity.ok(participantCreateResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtém todos os participantes da viagem", description = "Método que obtém todos os participantes da viagem a partir do id da viagem", tags = "Participante")
    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantData>> getAllParticipants(@PathVariable UUID id){
        List<ParticipantData> participantDataList = this.tripService.getAllParticipants(id);
        return ResponseEntity.ok(participantDataList);
    }

    // Links
    @Operation(summary = "Registra um link para viagem", description = "Método que registra um link para viagem", tags = "Link")
    @PostMapping("/{id}/links")
    public ResponseEntity<LinkResponse> registerLink(@PathVariable UUID id, @RequestBody LinkRequestPayload payload){
        LinkResponse linkResponse = this.tripService.registerLink(id, payload);
        if(linkResponse != null){
            return ResponseEntity.ok(linkResponse);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtém os links da viagem", description = "Método que obtém todos os links da viagem a partir do id da viagem", tags = "Link")
    @GetMapping("/{id}/links")
    public ResponseEntity<List<LinkData>> getAllLinks(@PathVariable UUID id){
            List<LinkData> linkData = this.tripService.getAllLinks(id);
            return ResponseEntity.ok(linkData);
    }

}
