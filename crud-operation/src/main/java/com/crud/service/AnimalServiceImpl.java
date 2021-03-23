package com.crud.service;

import com.crud.domain.data.AnimalDto;
import com.crud.domain.ports.AnimalPersistencePort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope("singleton")
public class AnimalServiceImpl implements AnimalServiceI{

    private final AnimalPersistencePort animalPersistencePort;
    public AnimalServiceImpl(AnimalPersistencePort animalPersistencePort) {
        this.animalPersistencePort = animalPersistencePort;
    }

    @Override
    public AnimalDto addAnimal(AnimalDto animal) {
        return animalPersistencePort.addAnimal(animal);
    }

    @Override
    @Transactional
    public void removeAnimal(Long id) {
         animalPersistencePort.removeAnimal(id);
    }

    @Override
    public AnimalDto updateAnimal(AnimalDto animal) {
        return animalPersistencePort.updateAnimal(animal);
    }

    @Override
    public List<AnimalDto> getAllAnimals() {
        return animalPersistencePort.getAllAnimals();
    }

    @Override
    public AnimalDto getAnimalsById(Long id) {
        return animalPersistencePort.getAnimalsById(id);
    }

    @Override
    public List<AnimalDto> getAllAnimalByPage(int page, int size) {
        return animalPersistencePort.getAllAnimalByPage(page,size);
    }

}
