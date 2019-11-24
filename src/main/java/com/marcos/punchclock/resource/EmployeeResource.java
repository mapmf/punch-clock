package com.marcos.punchclock.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.punchclock.dto.EmployeeDTO;
import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody EmployeeDTO dto){
			
		Employee employee = employeeService.fromDTO(dto);
		
		employeeService.insert(employee);
		
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> list(){
			
		List<EmployeeDTO> dtos = employeeService
									.list()
									.stream()
									.map(e -> new EmployeeDTO(e)).collect(Collectors.toList());
		
		return ResponseEntity.ok(dtos);
	}
	
}
