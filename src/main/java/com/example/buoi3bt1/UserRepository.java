package com.example.buoi3bt1;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByTitle(String title);
}
