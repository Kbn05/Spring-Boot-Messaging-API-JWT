package com.kbnproject.parcialmoviles.parcialfinal.controller;

import com.kbnproject.parcialmoviles.parcialfinal.model.Message;
import com.kbnproject.parcialmoviles.parcialfinal.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @GetMapping("/{messageId}")
    public Message getMessage(String messageId) {
        return messageService.getMessage(messageId);
    }

    @PostMapping
    public Message createMessage(
            @RequestPart("senderId") String senderId, 
            @RequestPart("receiverId") String receiverId,
            @RequestPart("message") String messagebody,
            @RequestPart("imageUrl") MultipartFile image) throws IOException {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setMessage(messagebody);
        String imageUrl = saveFile(image);
        message.setImageUrl(imageUrl);
        message.setDate(LocalDateTime.now());
        message.setStatus("sent");
        return messageService.createMessage(message);
    }

    private String saveFile(MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IOException("File is empty");
        }
        String fileName = image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());
        return fileName;
    }

}
