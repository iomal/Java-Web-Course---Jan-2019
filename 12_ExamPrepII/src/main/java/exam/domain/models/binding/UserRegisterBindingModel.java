package exam.domain.models.binding;

import exam.domain.entities.Gender;

public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private Gender gender;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
