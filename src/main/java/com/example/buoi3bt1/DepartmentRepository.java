package com.example.buoi3bt1;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    Optional<Department> findByUsersId(String userId);
    List<Department> findByTitle(String title);
}
