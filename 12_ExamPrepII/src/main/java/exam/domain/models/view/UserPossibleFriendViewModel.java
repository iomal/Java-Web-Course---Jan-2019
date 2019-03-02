package exam.domain.models.view;

import exam.domain.entities.Gender;

public class UserPossibleFriendViewModel {
    private String id;
    private String username;
    private String gender;

    public UserPossibleFriendViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
