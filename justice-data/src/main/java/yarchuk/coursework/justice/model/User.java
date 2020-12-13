package yarchuk.coursework.justice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "user_table")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String first_name, String last_name, String username, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                first_name.equals(user.first_name) &&
                last_name.equals(user.last_name) &&
                username.equals(user.username) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, username, password);
    }


    /*
    @NotBlank(message = "First name cannot be empty")
    @NotBlank(message = "Last name cannot be empty")
    @NotBlank(message = "Speciality cannot be empty")
    @NotBlank(message = "Email cannot be empty")
    @Email
    @NotBlank(message = "Password cannot be empty")
    @Length(min = 6, max = 32)
    * */

    ////    @NotBlank(message = "Enter your email")
////    @Email(message = "Enter a valid email example")
//    private String email;
//
////    @NotBlank(message = "password")
////    @Length(min = 6, message = "Password must be at least 6 characters")
//    private String password;
////    @NotBlank(message = "reenter password")
//    private String repassword;
////    @NotBlank(message = "first name")
//    private String first_name;
////    @NotBlank(message = "last name")
//    private String last_name;
//
//    public User() {
//
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRepassword() {
//        return repassword;
//    }
//
//    public void setRepassword(String repassword) {
//        this.repassword = repassword;
//    }
//
//    public String getFirstname() {
//        return first_name;
//    }
//
//    public void setFirstname(String firstname) {
//        this.first_name = firstname;
//    }
//
//    public String getLastname() {
//        return last_name;
//    }
//
//    public void setLastname(String lastname) {
//        this.last_name = lastname;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", repassword='" + repassword + '\'' +
//                ", firstname='" + first_name + '\'' +
//                ", lastname='" + last_name + '\'' +
//                '}';
//    }
}
