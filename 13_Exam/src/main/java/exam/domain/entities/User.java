package exam.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;

    public User() {
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return this.username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return this.password;
    }

    @Column(nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
