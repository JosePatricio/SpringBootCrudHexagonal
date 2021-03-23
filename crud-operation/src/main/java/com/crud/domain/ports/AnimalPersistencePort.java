package com.crud.domain.ports;

import com.crud.domain.data.AnimalDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnimalPersistencePort {
    AnimalDto addAnimal(AnimalDto animal);

    void removeAnimal(Long id);

    AnimalDto updateAnimal(AnimalDto animal);

    List<AnimalDto> getAllAnimals();

    AnimalDto getAnimalsById(Long id);

    List<AnimalDto> getAllAnimalByPage(int page, int size);
}
