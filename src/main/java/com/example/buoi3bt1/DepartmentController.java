package com.example.buoi3bt1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        try {
            Department _department = departmentRepository.save(department);
            return new ResponseEntity<>(_department, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        try {
            List<Department> departments = new ArrayList<>();
            departmentRepository.findAll().forEach(departments::add);

            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") String id){
        try {
            Optional<Department> department = departmentRepository.findById(id);
            if (department.isPresent()) {
                return new ResponseEntity<>(department.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> getUsersByDepartmentId(@PathVariable("id") String id){
        try {
            Optional<Department> department = departmentRepository.findById(id);
            if (department.isPresent()) {
                List<User> users = department.get().getUsers();
                if (users.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Department> getDepartmentByUserId(@PathVariable("userId") String userId){
        try {
            Optional<Department> department = departmentRepository.findByUsersId(userId);
            if (department.isPresent()) {
                return new ResponseEntity<>(department.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/users/{userId}")
    public ResponseEntity<Department> updateUserInDepartment(@PathVariable("id") String id, @PathVariable("userId") String userId, @RequestBody User updatedUser){
        try {
            Optional<Department> departmentOpt = departmentRepository.findById(id);
            if (departmentOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Department department = departmentOpt.get();
            List<User> users = department.getUsers();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId().equals(userId)) {
                    users.set(i, updatedUser);
                    department.setUsers(users);
                    departmentRepository.save(department);
                    return new ResponseEntity<>(department, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/users/name/{userName}")
    public ResponseEntity<Department> updateUserByNameInDepartment(@PathVariable("id") String id, @PathVariable("userName") String userName, @RequestBody User updatedUser){
        try {
            Optional<Department> departmentOpt = departmentRepository.findById(id);
            if (departmentOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Department department = departmentOpt.get();
            List<User> users = department.getUsers();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getName().equals(userName)) {
                    users.set(i, updatedUser);
                    department.setUsers(users);
                    departmentRepository.save(department);
                    return new ResponseEntity<>(department, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
