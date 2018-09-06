package com.accenture.springdata.controller;

import com.accenture.springdata.model.Animal;
import com.accenture.springdata.model.Zoo;
import com.accenture.springdata.service.SpringDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zoo")
public class SpringDataController {

    @Autowired
    SpringDataService springDataService;

    @GetMapping("/zoos")
    @ResponseBody
    public Map<String, Object> getZoos() {
        return springDataService.makeZoosDto(getAllZoo());
    }

    @GetMapping("/")
    @ResponseBody
    public List<Zoo> getAllZoo() {
        return springDataService.getAll();
    }

    @PostMapping("/")
    @ResponseBody
    public Zoo newZoo(@RequestBody Zoo zoo) {
        return springDataService.addZoo(zoo);

    }

    @PostMapping("/zoos")
    @ResponseBody
    public List<Zoo> newZoos(@RequestBody List<Zoo> zoos) {
        for (Zoo zoo : zoos) {
            newZoo(zoo);
        }
        return getAllZoo();
    }

    @GetMapping("/{zooId}")
    @ResponseBody
    public Zoo getZoo(@PathVariable("zooId") Long zooId) {
        return springDataService.findZooById(zooId);
    }

    @PostMapping("/animal")
    @ResponseBody
    public boolean addAnimal(@RequestBody Animal animal) {
        Long id = animal.getZoo().getId();
        springDataService.addAnimal(animal, id);
        return true;
    }


    @GetMapping("/{zooId}/animals")
    @ResponseBody
    public Map<String, Object> getAllAnimals(@PathVariable Long zooId) {
        return springDataService.makeZooDto(zooId);
    }


}
