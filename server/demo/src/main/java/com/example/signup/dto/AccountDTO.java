//package com.example.signup.dto;
//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//
//public class AccountDTO {
//    @NotBlank
//    @Size(max = 50)
//    private String name;
//
//    @NotBlank
//    @Email
//    private String email;
//
//    @NotBlank
//    @Pattern(regexp = "^[a-zA-Z0-9]{8,}$", message = "Password must be at least 8 characters and contain only alphabets and numbers.")
//    private String password;
//
//    // Getters and setters
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
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
//}

package com.example.signup.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccountDTO {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Email
    private String email;

    // Constructors
    public AccountDTO() {
    }

    public AccountDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
