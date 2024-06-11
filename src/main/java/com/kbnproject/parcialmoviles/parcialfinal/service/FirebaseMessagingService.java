package com.kbnproject.parcialmoviles.parcialfinal.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.kbnproject.parcialmoviles.parcialfinal.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public String sendNotification(MessageDto messageDto) {
        Notification notification = Notification.builder()
                .setTitle(messageDto.getTitle())
                .setBody(messageDto.getBody())
                .setImage(messageDto.getImageUrl())
                .build();
        Message message = Message.builder()
                .setToken(messageDto.getToken())
                .setNotification(notification)
                .putData("data", messageDto.getData())
                .build();
        try {
            return firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            return e.getMessage();
        }
    }
}
