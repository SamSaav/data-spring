package com.accenture.springdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Zoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    private String telefono;

    @OneToMany(mappedBy = "zoo")
    private List<Animal> animals;

    public Zoo() {
    }

    public Zoo(String nombre, List<Animal> animals, String direccion, String telefono) {
        this.nombre = nombre;
        this.animals = animals;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonIgnore
    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animal.setZoo(this);
        animals.add(animal);

    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
