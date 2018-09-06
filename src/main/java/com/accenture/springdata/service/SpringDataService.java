package com.accenture.springdata.service;

import com.accenture.springdata.model.Animal;
import com.accenture.springdata.model.Zoo;
import com.accenture.springdata.repository.AnimalRepository;
import com.accenture.springdata.repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpringDataService {

    @Autowired
    ZooRepository zooRepository;

    @Autowired
    AnimalRepository animalRepository;


    public List<Zoo> getAll() {
        return zooRepository.findAll();
    }


    public Zoo addZoo(Zoo zoo) {
        if (zooRepository.findByNombre(zoo.getNombre()) == null)
            zooRepository.save(zoo);
        return zooRepository.findByNombre(zoo.getNombre());
    }


    public Zoo findZooById(Long id) {
        return zooRepository.findById(id).orElse(null);
    }


    public void addAnimal(Animal animal, Long id) {
        animal.setId(animalRepository.findAll().size() + 1L);
        Zoo zoo = zooRepository.getById(id);
        zoo.addAnimal(animal);
        animalRepository.save(animal);
    }


    public List<Animal> getAllAnimals(String zooName) {

        return zooRepository.findByNombre(zooName).getAnimals();

    }

    public Map<String, Object> makeZoosDto(List<Zoo> zoos) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("titulo", "Spring Data Project");
        dto.put("subtitulo", "Zoológico");
        dto.put("zoos", makeZoosList(zoos));
        return dto;
    }

    public List<Object> makeZoosList(List<Zoo> zoos) {
        return zoos.stream().map(z -> makeZooDto(z.getId())).collect(Collectors.toList());
    }


    public Map<String, Object> makeZooDto(Long zooId) {
        Zoo zoo = zooRepository.findById(zooId).get();
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", zoo.getId());
        dto.put("zooNombre", zoo.getNombre());
        dto.put("telefono", zoo.getTelefono());
        dto.put("direccion", zoo.getDireccion());
        dto.put("animales", makeAnimalList(zoo.getAnimals()));
        return dto;
    }

    public List<Object> makeAnimalList(List<Animal> animals) {
        return animals.stream().map(a -> makeAnimalDto(a)).collect(Collectors.toList());
    }

    public Map<String, Object> makeAnimalDto(Animal animal) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("especie", animal.getEspecie());
        dto.put("cantidad", animal.getCantidad());
        return dto;
    }

}
