package exam.domain.models.service;

import exam.domain.entities.Gender;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {
    private String id;
    private String username;
    private String password;
    private Gender gender;
    private List<UserServiceModel> friends;

    public UserServiceModel() {
        friends = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Gender getGender() {
        return this.gender;
    }

    public List<UserServiceModel> getFriends() {
        return this.friends;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setFriends(List<UserServiceModel> friends) {
        this.friends = friends;
    }

    public void setId(String id) {
        this.id = id;
    }
}
