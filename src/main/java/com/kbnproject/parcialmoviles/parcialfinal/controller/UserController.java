package com.kbnproject.parcialmoviles.parcialfinal.controller;

import com.kbnproject.parcialmoviles.parcialfinal.model.FcmToken;
import com.kbnproject.parcialmoviles.parcialfinal.model.User;
import com.kbnproject.parcialmoviles.parcialfinal.service.UserService;
import com.kbnproject.parcialmoviles.parcialfinal.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public User createUser(
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("name") String name,
            @RequestPart("position") String position,
            @RequestPart("fcmToken") String fcmToken,
            @RequestPart("phoneNumber") String phoneNumber,
            @RequestPart("photo") MultipartFile photo) throws IOException {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setPosition(position);
        user.setPhoneNumber(phoneNumber);
        user.setRole(Role.USER);

        //Irregular code
        FcmToken token = new FcmToken();
        token.setUser(user);
        token.setToken(fcmToken);
/*        user.getFcmTokens().add(token);*/

        String photoUrl = saveFile(photo);
        user.setPhoto(photoUrl);

        return userService.createUser(user);
    }

    private String saveFile(MultipartFile photo) throws IOException {
        if (photo.isEmpty()) {
            throw new IOException("File is empty");
        }
        String fileName = photo.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, photo.getBytes());
        return fileName;
    }

    @PostMapping("/login")
    public String login(@RequestPart("email") String email, @RequestPart("password") String password) {
        return userService.login(email, password);
    }
}
