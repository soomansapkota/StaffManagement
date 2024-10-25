package com.example.staffmanagement.repository;

import com.example.staffmanagement.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface managerRepository extends JpaRepository<Manager,Integer> {
    Manager findByManagerId(String managerId);
}