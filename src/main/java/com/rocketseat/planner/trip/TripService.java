package com.rocketseat.planner.trip;

import com.rocketseat.planner.activity.ActivityData;
import com.rocketseat.planner.activity.ActivityRequestPayload;
import com.rocketseat.planner.activity.ActivityResponse;
import com.rocketseat.planner.activity.ActivityService;
import com.rocketseat.planner.link.LinkService;
import com.rocketseat.planner.participant.ParticipantService;
import com.rocketseat.planner.util.LocalDateTimeUtils;
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
            rawTrip.setStartsAt(LocalDateTimeUtils.stringToLocalDateTime(payload.starts_at()));
            rawTrip.setEndsAt(LocalDateTimeUtils.stringToLocalDateTime(payload.ends_at()));
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

}
