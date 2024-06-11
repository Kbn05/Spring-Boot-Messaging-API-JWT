package com.kbnproject.parcialmoviles.parcialfinal.service;

import com.kbnproject.parcialmoviles.parcialfinal.model.Message;
import com.kbnproject.parcialmoviles.parcialfinal.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(String messageId) {
        return messageRepository.findById(messageId).
                orElse(null);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }
}
