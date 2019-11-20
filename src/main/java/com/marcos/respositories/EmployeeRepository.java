package com.marcos.respositories;

import org.springframework.stereotype.Repository;

import com.marcos.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
