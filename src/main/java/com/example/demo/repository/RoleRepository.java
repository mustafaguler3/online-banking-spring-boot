package com.example.demo.repository;

import com.example.demo.domain.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole,Long> {
}
