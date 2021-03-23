package com.crud.jpa.adapter;

import com.crud.domain.exceptions.NotFoundException;
import com.crud.domain.data.AnimalDto;
import com.crud.domain.ports.AnimalPersistencePort;
import com.crud.jpa.model.Animal;
import com.crud.jpa.repositories.AnimalRepository;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class AnimalJpaAdapter implements AnimalPersistencePort {
    private AnimalRepository animalRepository;

    public AnimalJpaAdapter(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public AnimalDto addAnimal(AnimalDto animaldto) {
        final Animal animal = getAnimalEntity(animaldto);
        return getAnimal(animalRepository.save(animal));
    }

    @Override
    public void removeAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public AnimalDto updateAnimal(AnimalDto animalDto) {
        Animal animalEntity= getAnimalEntity(animalDto);
        final Optional<Animal>animal =animalRepository.findById(animalDto.getId());

        if (animal.isPresent()) {
            animalEntity=animal.get();
            animal.get().setAnimal(animalDto.getAnimal());
            animal.get().setType(animalDto.getType());
            animal.get().setId(animalDto.getId());
            animalRepository.save(animalEntity);
        }
        return getAnimal(animalEntity);
    }

    @Override
    public List<AnimalDto> getAllAnimals() {
        return animalRepository.findAll()
                .stream()
                .map(this::getAnimal)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public AnimalDto getAnimalsById(Long id) {

        return getAnimal(animalRepository.findById(id)
                .orElseThrow((Supplier<Throwable>) () -> new NotFoundException(id)));
    }

    @Override
    public List<AnimalDto> getAllAnimalByPage(int page, int size) {
        Pageable secondPageWithFiveElements = PageRequest.of(page, size);


        return animalRepository.findAll(secondPageWithFiveElements)
                .stream()
                .map(this::getAnimal)
                .collect(Collectors.toList());
    }


    private Animal getAnimalEntity(AnimalDto animalDto) {
        return Animal.builder()
                .animal(animalDto.getAnimal())
                .type(animalDto.getType())
                .id(animalDto.getId())
                .build();
    }

    private AnimalDto getAnimal(Animal animalEntity) {
        if(animalEntity==null){
            return null;
        }
        return AnimalDto.builder()
                .animal(animalEntity.getAnimal())
                .type(animalEntity.getType())
                .id(animalEntity.getId())
                .build();
    }

}
