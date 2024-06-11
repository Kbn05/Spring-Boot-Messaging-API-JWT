package com.kbnproject.parcialmoviles.parcialfinal.controller;

import com.kbnproject.parcialmoviles.parcialfinal.dto.MessageDto;
import com.kbnproject.parcialmoviles.parcialfinal.service.FirebaseMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @PostMapping("/{notificationId}")
    public String sendNotification(@RequestBody MessageDto messageDto) {
        return firebaseMessagingService.sendNotification(messageDto);
    }
}
