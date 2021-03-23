package com.crud.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="animals")
public class Animal {
    @Id
    private Long id;
    @Column
    private String animal;
    @Column
    private String type;
}
