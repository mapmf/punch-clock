package com.marcos.punchclock.repositories;

import org.springframework.stereotype.Repository;

import com.marcos.punchclock.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
