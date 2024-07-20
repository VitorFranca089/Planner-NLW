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
    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody @Valid TripRequestPayload payload){
        TripCreateResponse tripCreateResponse = this.tripService.createTrip(payload);
        return ResponseEntity.ok(tripCreateResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripData> getTripDetails(@PathVariable UUID id){
        TripData tripData = this.tripService.getTripDetails(id);
        if(tripData != null){
            return ResponseEntity.ok(tripData);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<TripData> updateTrip(@PathVariable UUID id, @RequestBody @Valid TripRequestPayload payload){
        TripData tripData = this.tripService.updateTrip(id, payload);
        if(tripData != null){
            return ResponseEntity.ok(tripData);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/confirm")
    public ResponseEntity<TripData> confirmTrip(@PathVariable UUID id){
        TripData tripData = this.tripService.confirmTrip(id);
        if(tripData != null){
            return ResponseEntity.ok(tripData);
        }
        return ResponseEntity.notFound().build();
    }

    // Activities
    @PostMapping("/{id}/activities")
    public ResponseEntity<ActivityResponse> registerActivity(@PathVariable UUID id, @RequestBody ActivityRequestPayload payload){
        ActivityResponse activityResponse = this.tripService.registerActivity(id, payload);
        if(activityResponse != null){
            return ResponseEntity.ok(activityResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityData>> getAllActivities(@PathVariable UUID id){
        List<ActivityData> activityDataList = this.tripService.getAllActivities(id);
        return ResponseEntity.ok(activityDataList);
    }

    // Participants
    @PostMapping("/{id}")
    public ResponseEntity<ParticipantCreateResponse> registerParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload){
        ParticipantCreateResponse participantCreateResponse = this.tripService.registerParticipant(id, payload);
        if(participantCreateResponse != null){
            return ResponseEntity.ok(participantCreateResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload){
        ParticipantCreateResponse participantCreateResponse = this.tripService.inviteParticipant(id, payload);
        if(participantCreateResponse != null){
            return ResponseEntity.ok(participantCreateResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantData>> getAllParticipants(@PathVariable UUID id){
        List<ParticipantData> participantDataList = this.tripService.getAllParticipants(id);
        return ResponseEntity.ok(participantDataList);
    }

    // Links
    @PostMapping("/{id}/links")
    public ResponseEntity<LinkResponse> registerLink(@PathVariable UUID id, @RequestBody LinkRequestPayload payload){
        LinkResponse linkResponse = this.tripService.registerLink(id, payload);
        if(linkResponse != null){
            return ResponseEntity.ok(linkResponse);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/links")
    public ResponseEntity<List<LinkData>> getAllLinks(@PathVariable UUID id){
            List<LinkData> linkData = this.tripService.getAllLinks(id);
            return ResponseEntity.ok(linkData);
    }

}
