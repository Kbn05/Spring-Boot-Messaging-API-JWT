package com.kbnproject.parcialmoviles.parcialfinal;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@SpringBootApplication
public class ParcialfinalApplication {

	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials credentials = GoogleCredentials.fromStream(new ClassPathResource("spring-notipush-firebase-adminsdk-4ygj4-341d735efe.json").getInputStream());
		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(credentials)
				.build();
		FirebaseApp app = FirebaseApp.initializeApp(options, "spring-notipush");
		return FirebaseMessaging.getInstance(app);
	}

	public static void main(String[] args) {
		SpringApplication.run(ParcialfinalApplication.class, args);
	}

}
