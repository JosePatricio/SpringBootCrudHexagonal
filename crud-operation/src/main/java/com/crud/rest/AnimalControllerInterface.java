package com.crud.rest;

import com.crud.domain.data.AnimalDto;
import com.crud.rest.authPayload.request.LoginRequest;
import com.crud.rest.authPayload.request.SignupRequest;
import com.crud.rest.responseModel.ResponseModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface AnimalControllerInterface {
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/animals")
    ResponseEntity<ResponseModel> addAnimal(@RequestBody AnimalDto animalDto);

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/animals/{id}")
    ResponseEntity<ResponseModel> removeAnimal(@PathVariable Long id);

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/animals")
    ResponseEntity<ResponseModel> updateAnimal(@RequestBody AnimalDto animalDto);

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/animals/{id}")
    ResponseEntity<ResponseModel> getAnimalId(@PathVariable Long id);

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/animals")
    ResponseEntity<ResponseModel> getAnimals();

    @PreAuthorize("hasRole('USER')")
    @PatchMapping(value = "/updateAnimalType/{id}",  consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseModel> updateAnimalType(@RequestBody Map<String, Object> updates,
                                                     @PathVariable("id") Long id);

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/animalsByPageBySize/{page}/{size}")
    ResponseEntity<ResponseModel> getAllAnimalByPage(@PathVariable int page,@PathVariable int size);


}
