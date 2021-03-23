package com.crud.rest;
import com.crud.domain.data.AnimalDto;
import com.crud.rest.responseModel.ResponseModel;
import com.crud.service.AnimalServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/zoologico")
public class AnimalControllerImpl implements AnimalControllerInterface {

    private final AnimalServiceI animalServiceI;

    public AnimalControllerImpl(AnimalServiceI animalServiceI) {
        this.animalServiceI = animalServiceI;
    }

    @Override
    public ResponseEntity<ResponseModel> addAnimal(AnimalDto animalDto) {
        ResponseModel response= new ResponseModel();
        try {
            response.setStatus(true);
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("Successfully created");
            animalServiceI.addAnimal(animalDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.setStatus(false);
            response.setCode(HttpStatus.METHOD_FAILURE.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.METHOD_FAILURE);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> removeAnimal(Long id) {
        ResponseModel response= new ResponseModel();
        try {
            response.setStatus(true);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Successfully deleted");
            animalServiceI.removeAnimal(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setStatus(false);
            response.setCode(HttpStatus.METHOD_FAILURE.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.METHOD_FAILURE);
        }
     }


    @Override
    public ResponseEntity<ResponseModel> updateAnimal(AnimalDto animalDto) {

        ResponseModel response= new ResponseModel();
        try {
            response.setStatus(true);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Successfully updated");
            animalServiceI.updateAnimal(animalDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setStatus(false);
            response.setCode(HttpStatus.METHOD_FAILURE.value());
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.METHOD_FAILURE);
        }

    }

    @Override
    public ResponseEntity<ResponseModel> getAnimalId(Long id) {
        ResponseModel response= new ResponseModel();
        try {
            AnimalDto animal=animalServiceI.getAnimalsById(id);
            response.setStatus(true);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Successfully found");
            response.setResponseData(animal);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> getAnimals() {
        ResponseModel response= new ResponseModel();
        try {
            List<AnimalDto> animals=animalServiceI.getAllAnimals();
            response.setStatus(true);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Successfully found");
            response.setResponseData(animals);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.METHOD_FAILURE);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> updateAnimalType(Map<String, Object> type, Long id) {
        ResponseModel response= new ResponseModel();
        try {
//https://dev.to/scottshipp/docker-compose-a-spring-boot-app-backed-by-mysql-89m
            //https://stackoverflow.com/questions/60961757/spring-boot-mysql-docker-compose-cannot-make-spring-boot-connect-to-mysql
            AnimalDto animal=animalServiceI.getAnimalsById(id);
            animal.setType(type.get("type").toString());
            animalServiceI.updateAnimal(animal);
            response.setStatus(true);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Successfully type update");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseModel> getAllAnimalByPage(int page, int size) {
        ResponseModel response= new ResponseModel();
        try {
            response.setStatus(true);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Successfully Found");
            response.setResponseData(animalServiceI.getAllAnimalByPage(page,size));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.METHOD_FAILURE);
        }
    }


}
