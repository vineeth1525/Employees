package com.cgg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgg.data.model.EmployeeDto;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDto, Integer> {

}
