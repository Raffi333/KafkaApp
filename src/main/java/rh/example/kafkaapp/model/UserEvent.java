package rh.example.kafkaapp.model;


public class UserEvent {

    private String name;
    private String surname;
    private String email;
    private String phone;

    public UserEvent() {
    }

    public UserEvent(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "name։'" + name + '\'' +
                ", surname։'" + surname + '\'' +
                ", email։'" + email + '\'' +
                ", phone։'" + phone + '\'' +
                '}';
    }
}
