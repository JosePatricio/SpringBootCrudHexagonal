package com.crud.main;

import com.crud.domain.data.AnimalDto;
import com.crud.jpa.model.Animal;
import com.crud.jpa.repositories.AnimalRepository;
import com.crud.service.AnimalServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CrudOperationApplicationTests {

	@Autowired
	private AnimalServiceImpl animalService;

	@MockBean
	private AnimalRepository repository;



	@Test
	public void getAnimalTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Animal(Long.parseLong("1"), "Gato",  "Felino"), new Animal(Long.parseLong("2"), "Pollo", "Ave")).collect(Collectors.toList()));
		assertEquals(2, animalService.getAllAnimals().size());
	}
	@Test
	public void addAnimalTest() {
		Animal animal = new Animal(Long.parseLong("1"), "Gato",  "Felino");
		when(repository.save(animal)).thenReturn(animal);

		AnimalDto ExpectedObject=new AnimalDto(Long.parseLong("1"), "Gato", "Felino");
		AnimalDto animalDto=new AnimalDto(Long.parseLong("1"), "Gato", "Felino");
		assertEquals(ExpectedObject, animalService.addAnimal(animalDto));
	}
	@Test
	public void removeAnimalTest() {
		Animal animal = new Animal(Long.parseLong("1"), "Gato",  "Felino");
		repository.delete(animal);
		verify(repository, times(1)).delete(animal);
	}
	@Test
	public void updateAnimalTest() {

		when(repository.findById(Long.parseLong("1"))).thenReturn(java.util.Optional.ofNullable(Stream
				.of(new Animal(Long.parseLong("1"), "Gato", "Felino")).collect(Collectors.toList()).get(0)));

		AnimalDto animalDto=new AnimalDto(Long.parseLong("1"), "Gato", "Felino");
		AnimalDto expectedObject=new AnimalDto(Long.parseLong("1"), "Gato", "Felino");
		assertEquals(expectedObject, animalService.updateAnimal(animalDto));

	}
	@Test
	public void getAnimalsByIdTest() {
		when(repository.findById(Long.parseLong("1"))).thenReturn(java.util.Optional.ofNullable(Stream
				.of(new Animal(Long.parseLong("1"), "Gato", "Felino")).collect(Collectors.toList()).get(0)));

		AnimalDto ExpectedObject=new AnimalDto(Long.parseLong("1"), "Gato", "Felino");
		assertEquals(ExpectedObject, animalService.getAnimalsById(Long.parseLong("1")));
	}

	}




