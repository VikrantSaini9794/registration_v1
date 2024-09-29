package com.api5.service;

import com.api5.entity.Registration;
import com.api5.exception.ResourceNotFoundException;
import com.api5.payload.RegistrationDto;
import com.api5.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;//dependency injection creation

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public List<Registration>getRegistration(){
        List<Registration> registrations = registrationRepository.findAll();
        return registrations;

    }


    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        //copy dto to entity
        Registration registration = mapTOEntity(registrationDto);
        Registration saveEntity = registrationRepository.save(registration);

        //copy entity ot dto
        RegistrationDto dto = mapTODto(saveEntity);
        return dto;

    }


    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
        Registration r = registrationRepository.findById(id).get();
        r.setId(id);
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());

        Registration savedEntity = registrationRepository.save(r);
        return savedEntity;

    }
    Registration mapTOEntity(RegistrationDto registrationDto){
        Registration  registration = modelMapper.map(registrationDto, Registration.class);
//        Registration registration=new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
        return registration;
    }

    RegistrationDto mapTODto(Registration registration){
        RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
//        RegistrationDto dto=new RegistrationDto();
//        dto.setName(registration.getName());
//        dto.setEmail(registration.getMobile());
//        dto.setMobile(registration.getEmail());
      return dto;
    }

    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()-> new  ResourceNotFoundException("Record Not Found")
        );
        return mapTODto(registration);
    }
}
