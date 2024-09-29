package com.api5.controller;

import com.api5.entity.Registration;
import com.api5.payload.RegistrationDto;
import com.api5.service.RegistrationService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api5/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
 @GetMapping
 public ResponseEntity<List<Registration>>getAllRegistration(){
     List<Registration> registrations = registrationService.getRegistration();
     return new ResponseEntity<>(registrations, HttpStatus.OK);
 }

    @PostMapping
    public ResponseEntity<?>createRegistration(
            @Valid @RequestBody
            RegistrationDto registrationDto,
            BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
        }
        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto,HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<String>deleteRegistration(
            @RequestParam long id
    ){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Record is deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration>updateRegistration(
            @PathVariable long id,
            @RequestBody Registration registration
    ){
        Registration updateReg = registrationService.updateRegistration(id, registration);
        return new ResponseEntity<>(updateReg,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto>getRegistrationById(
            @PathVariable() long id
    ){
      RegistrationDto dto=  registrationService.getRegistrationById(id);
      return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
