package exam.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private Gender gender;
    private List<User> friends;

    public User() {
        friends = new ArrayList<>();
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return this.username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return this.password;
    }

    @Enumerated(value = EnumType.STRING)
    public Gender getGender() {
        return this.gender;
    }

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "friends_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    public List<User> getFriends() {
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

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

}
