package com.kbnproject.parcialmoviles.parcialfinal.repository;

import com.kbnproject.parcialmoviles.parcialfinal.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String>{
}
