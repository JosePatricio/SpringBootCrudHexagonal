package com.crud.service;

import com.crud.domain.data.AnimalDto;

import java.util.List;

public interface AnimalServiceI {

    AnimalDto addAnimal(AnimalDto animal);

    void removeAnimal(Long id);

    AnimalDto updateAnimal(AnimalDto animal);

    List<AnimalDto> getAllAnimals();

    AnimalDto getAnimalsById(Long id);

    List<AnimalDto> getAllAnimalByPage(int page, int size);

}

