package com.marcos.punchclock.respositories;

import org.springframework.stereotype.Repository;

import com.marcos.punchclock.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
