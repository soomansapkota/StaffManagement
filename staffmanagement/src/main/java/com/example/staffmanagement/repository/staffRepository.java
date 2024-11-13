package com.example.staffmanagement.repository;


import com.example.staffmanagement.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface staffRepository extends JpaRepository<Staff, Integer> {
}
