package com.api5.payload;

//import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationDto {
    @NotEmpty
    @Size(min = 5,message = "Name Should be 5 latter")
    private String name;
    @Email
    private String email;
    @Size(min = 10,max = 10,message = "Mobile Number Should be 10 Digit")
    private String mobile;

}