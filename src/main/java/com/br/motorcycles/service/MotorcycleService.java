package com.br.motorcycles.service;

import com.br.motorcycles.dto.MotorcycleDTO;
import com.br.motorcycles.entity.Motorcycle;
import com.br.motorcycles.entity.User;
import com.br.motorcycles.repository.MotorcycleRepository;
import com.br.motorcycles.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<MotorcycleDTO> getAllMotorcycles() {
        List<Motorcycle> motorcycles = motorcycleRepository.findAll();

        List<MotorcycleDTO> motorcycleDTO = new ArrayList<>();

        for (Motorcycle motorcycle : motorcycles) {
            MotorcycleDTO dto = new MotorcycleDTO();
            dto.setId(motorcycle.getId());
            dto.setBrand(motorcycle.getBrand());
            dto.setModel(motorcycle.getModel());
            dto.setYear(motorcycle.getYear());
            motorcycleDTO.add(dto);
        }

        return motorcycleDTO;
    }

    public List<MotorcycleDTO> getMotorcyclesByUserId(String userId) {
        List<Motorcycle> motorcycles = userRepository.findById(userId).map(User::getMotorcycles).stream().collect(Collectors.toList());

        List<MotorcycleDTO> motorcycleDTO = new ArrayList<>();

        for (Motorcycle motorcycle : motorcycles) {
            MotorcycleDTO dto = new MotorcycleDTO();
            dto.setId(motorcycle.getId());
            dto.setBrand(motorcycle.getBrand());
            dto.setModel(motorcycle.getModel());
            dto.setYear(motorcycle.getYear());
            dto.setImageUrl(motorcycle.getImageUrl());
            motorcycleDTO.add(dto);
        }

        return motorcycleDTO;
    }


    public Motorcycle createMotorcycle(Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle);
    }

    public Optional<Motorcycle> getMotorcycleById(Long id) {
        return motorcycleRepository.findById(id);
    }

    public void deleteMotorcycleById(Long id) {
        motorcycleRepository.deleteById(id);
    }

    public Motorcycle updateMotorcycle(Long id, Motorcycle motorcycle) {
        Optional<Motorcycle> optionalMotorcycle = motorcycleRepository.findById(id);
        if (optionalMotorcycle.isPresent()) {
            Motorcycle existingMotorcycle = optionalMotorcycle.get();
            existingMotorcycle.setBrand(motorcycle.getBrand());
            existingMotorcycle.setModel(motorcycle.getModel());
            existingMotorcycle.setYear(motorcycle.getYear());
            return motorcycleRepository.save(existingMotorcycle);
        }
        return null;
    }
}

