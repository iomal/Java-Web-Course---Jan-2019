package exam.domain.models.service;

import exam.domain.entities.Gender;

import java.util.ArrayList;
import java.util.List;

public class UserServiceNotFriendsModel {
    private String id;
    private String username;
    private Gender gender;

    public UserServiceNotFriendsModel() {

    }

    public UserServiceNotFriendsModel(String id, String username, Gender gender) {
        this.id = id;
        this.username = username;
        this.gender = gender;
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public Gender getGender() {
        return this.gender;
    }


    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
