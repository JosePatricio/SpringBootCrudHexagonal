package com.crud.jpa.repositories;

import com.crud.jpa.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long>, PagingAndSortingRepository<Animal, Long> {

}
