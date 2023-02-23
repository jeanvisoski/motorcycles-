package com.br.motorcycles.service;

import com.br.motorcycles.entity.Motorcycle;
import com.br.motorcycles.entity.User;
import com.br.motorcycles.exception.UserNotFoundException;
import com.br.motorcycles.repository.MotorcycleRepository;
import com.br.motorcycles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Motorcycle> getMotorcyclesByUserId(Long userId) {
        return motorcycleRepository.findByUserId(userId);
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    public Motorcycle createMotorcycle(Long userId, Motorcycle motorcycle) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            motorcycle.setUser(user);
            return motorcycleRepository.save(motorcycle);
        } else {
            throw new UserNotFoundException("User with id " + userId + " not found.");
        }
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

