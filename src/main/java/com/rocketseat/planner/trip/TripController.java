package com.rocketseat.planner.trip;

import com.rocketseat.planner.activity.ActivityData;
import com.rocketseat.planner.activity.ActivityRequestPayload;
import com.rocketseat.planner.activity.ActivityResponse;
import com.rocketseat.planner.link.LinkData;
import com.rocketseat.planner.link.LinkRequestPayload;
import com.rocketseat.planner.link.LinkResponse;
import com.rocketseat.planner.link.LinkService;
import com.rocketseat.planner.participant.ParticipantCreateResponse;
import com.rocketseat.planner.participant.ParticipantData;
import com.rocketseat.planner.participant.ParticipantRequestPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private TripRepository repository;

    // Criação do TripService para separar o core das trips

    // Trips
    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripRequestPayload payload){
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
    public ResponseEntity<TripData> updateTrip(@PathVariable UUID id, @RequestBody TripRequestPayload payload){
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
        Optional<Trip> trip = this.repository.findById(id);

        if(trip.isPresent()){
            Trip rawTrip = trip.get();

            LinkResponse linkResponse = this.linkService.registerLink(payload, rawTrip);

            return ResponseEntity.ok(linkResponse);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/links")
    public ResponseEntity<List<LinkData>> getAllLinks(@PathVariable UUID id){
            List<LinkData> linkData = this.linkService.getAllLinksFromId(id);
            return ResponseEntity.ok(linkData);
    }

}
