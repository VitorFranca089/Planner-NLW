package com.rocketseat.planner.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void sendConfirmMail(EmailConfirmParticipant emailConfirmParticipant){
        var message = new SimpleMailMessage();
        message.setFrom("noreply@email.com");
        message.setTo(emailConfirmParticipant.email());
        message.setSubject("Plann.er");
        message.setText(confirmMessage(emailConfirmParticipant));
        javaMailSender.send(message);
    }

    private String confirmMessage(EmailConfirmParticipant infoMail){
        String linkConfirmation = "http://localhost:8080/participants/" + infoMail.participantId() + "/confirm";
        String message = "Você foi convidado para uma viagem para " + infoMail.destination() + " por " + infoMail.owner_name() +
                " entre as datas " + infoMail.starts_at().format(formatter) + " e " + infoMail.ends_at().format(formatter) +
                ".\nConfirme sua participação no seguinte link: " + linkConfirmation;
        return message;
    }

}
