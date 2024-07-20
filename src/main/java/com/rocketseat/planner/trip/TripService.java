package com.rocketseat.planner.trip;

import com.rocketseat.planner.activity.ActivityData;
import com.rocketseat.planner.activity.ActivityRequestPayload;
import com.rocketseat.planner.activity.ActivityResponse;
import com.rocketseat.planner.activity.ActivityService;
import com.rocketseat.planner.link.LinkData;
import com.rocketseat.planner.link.LinkRequestPayload;
import com.rocketseat.planner.link.LinkResponse;
import com.rocketseat.planner.link.LinkService;
import com.rocketseat.planner.participant.ParticipantCreateResponse;
import com.rocketseat.planner.participant.ParticipantData;
import com.rocketseat.planner.participant.ParticipantRequestPayload;
import com.rocketseat.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private TripRepository repository;

    // Trips
    public TripCreateResponse createTrip(TripRequestPayload payload){
        Trip trip = new Trip(payload);
        this.repository.save(trip);
        this.participantService.registerParticipantsToEvent(payload.emails_to_invite(), trip);
        return new TripCreateResponse(trip.getId());
    }

    public TripData getTripDetails(UUID tripId){
        Optional<Trip> trip = this.repository.findById(tripId);
        if(trip.isPresent()){
            Trip rawTrip = trip.get();
            return new TripData(rawTrip);
        }
        return null; // tratar com uma exceção
    }

    public TripData updateTrip(UUID tripId, TripRequestPayload payload){
        Optional<Trip> trip = this.repository.findById(tripId);
        if(trip.isPresent()){
            Trip rawTrip = trip.get();
            rawTrip.setStartsAt(payload.starts_at());
            rawTrip.setEndsAt(payload.ends_at());
            rawTrip.setDestination(payload.destination());
            this.repository.save(rawTrip);
            return new TripData(rawTrip);
        }
        return null;
    }

    public TripData confirmTrip(UUID tripId){
        Optional<Trip> trip = this.repository.findById(tripId);
        if(trip.isPresent()){
            Trip rawTrip = trip.get();
            rawTrip.setIsConfirmed(true);
            this.repository.save(rawTrip);
            this.participantService.triggerConfirmationEmailToParticipants(tripId);
            return new TripData(rawTrip);
        }
        return null;
    }

    // Activities
    public ActivityResponse registerActivity(UUID tripId, ActivityRequestPayload payload){
        Optional<Trip> trip = this.repository.findById(tripId);
        if(trip.isPresent()){
            Trip rawTrip = trip.get();
            return this.activityService.registerActivity(payload, rawTrip);
        }
        return null;
    }

    public List<ActivityData> getAllActivities(UUID tripId){
        return this.activityService.getAllActivitiesFromId(tripId);
    }

    // Participants
    public ParticipantCreateResponse registerParticipant(UUID tripId, ParticipantRequestPayload payload){
        Optional<Trip> trip = this.repository.findById(tripId);
        if(trip.isPresent()){
            Trip rawTrip = trip.get();
            ParticipantCreateResponse participantCreateResponse = this.participantService.registerParticipantToEvent(payload.email(), rawTrip);
            if(rawTrip.getIsConfirmed()) this.participantService.triggerConfirmationEmailToParticipant(payload.email());
            return participantCreateResponse;
        }
        return null;
    }

    public ParticipantCreateResponse inviteParticipant(UUID tripId, ParticipantRequestPayload payload){
        Optional<Trip> trip = this.repository.findById(tripId);
        if(trip.isPresent()){
            Trip rawTrip = trip.get();
            ParticipantCreateResponse participantCreateResponse = this.participantService.registerParticipantToEvent(payload.email(), rawTrip);
            if(rawTrip.getIsConfirmed()) this.participantService.triggerConfirmationEmailToParticipant(payload.email());
            return participantCreateResponse;
        }
        return null;
    }

    public List<ParticipantData> getAllParticipants(UUID tripId){
        Optional<Trip> trip = this.repository.findById(tripId);
        if(trip.isPresent()){
            Trip rawTrip = trip.get();
            return this.participantService.getAllParticipantsFromEvent(rawTrip.getId());
        }
        return null;
    }

    // Links
    public LinkResponse registerLink(UUID tripId, LinkRequestPayload payload){
        Optional<Trip> trip = this.repository.findById(tripId);
        if(trip.isPresent()){
            Trip rawTrip = trip.get();
            return this.linkService.registerLink(payload, rawTrip);
        }
        return null;
    }

    public List<LinkData> getAllLinks(UUID tripId){
        return this.linkService.getAllLinksFromId(tripId);
    }

}
