package com.br.motorcycles.controller;

import com.br.motorcycles.dto.MotorcycleDTO;
import com.br.motorcycles.entity.Motorcycle;
import com.br.motorcycles.entity.User;
import com.br.motorcycles.service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motorcycles")
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Motorcycle>> getMotorcyclesByUserId(@PathVariable Long userId) {
        List<Motorcycle> motorcycles = motorcycleService.getMotorcyclesByUserId(userId);
        return ResponseEntity.ok(motorcycles);
    }

    @PostMapping
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody MotorcycleDTO motorcycleDTO) {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setBrand(motorcycleDTO.getBrand());
        motorcycle.setModel(motorcycleDTO.getModel());
        motorcycle.setYear(motorcycleDTO.getYear());

        User optionalUser = motorcycleService.getUserById(motorcycleDTO.getUserId());

        motorcycle.setUser(optionalUser);

        Motorcycle createdMotorcycle = motorcycleService.createMotorcycle(optionalUser.getId(), motorcycle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMotorcycle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable Long id) {
        Optional<Motorcycle> optionalMotorcycle = motorcycleService.getMotorcycleById(id);

        return ResponseEntity.ok(optionalMotorcycle.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motorcycle> updateMotorcycle(@PathVariable Long id, @RequestBody Motorcycle motorcycle) {
            Motorcycle updatedMotorcycle = motorcycleService.updateMotorcycle(id, motorcycle);
            return ResponseEntity.ok(updatedMotorcycle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotorcycle(@PathVariable Long id) {
        motorcycleService.deleteMotorcycleById(id);
        return ResponseEntity.noContent().build();
    }
}
