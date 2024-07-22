package com.rocketseat.planner.participant;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @Operation(summary = "Confirma participação na viagem", description = "Metodo que o participante confirma sua presença na viagem", tags = "Participante")
    @PostMapping("/{id}/confirm")
    public ResponseEntity<ParticipantResponseConfirm> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload){
        ParticipantResponseConfirm participantResponseConfirm = this.participantService.confirmParticipant(id, payload);
        if(participantResponseConfirm != null){
            return ResponseEntity.ok(participantResponseConfirm);
        }
        return ResponseEntity.notFound().build();
    }

}
