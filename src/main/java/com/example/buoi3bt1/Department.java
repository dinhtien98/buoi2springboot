package com.example.buoi3bt1;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document("departments")
public class Department {
    @Id
    private String id;
    private String nameDepartment;
    private String title;
    private List<User> users;

    public Department() {}

    public Department(String nameDepartment, String title, List<User> users) {
        this.nameDepartment = nameDepartment;
        this.title = title;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
