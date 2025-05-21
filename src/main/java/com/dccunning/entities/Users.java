package com.dccunning.entities;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Example Users entity for application backend using CRUD.
 */
@Entity
@Table(name = "users")
public class Users {
    public Users() {}

    @Id
    private Long id;

}
