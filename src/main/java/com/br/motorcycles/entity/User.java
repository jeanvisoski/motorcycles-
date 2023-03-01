package com.br.motorcycles.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "motorcycle_id")
    private Motorcycle motorcycle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Motorcycle getMotorcycles() {
        return motorcycle;
    }

    public void setMotorcycles(Motorcycle motorcycles) {
        this.motorcycle = motorcycles;
    }
}
