package com.george.orca.controller;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<EmployeeEntity> getEmployee(@RequestParam Long employeeId) {
        EmployeeEntity employee = employeeService.get(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/getVisitors", method = RequestMethod.POST)
    @CrossOrigin
    public List<EmployeeEntity> getVisitors() {
        List<EmployeeEntity> visitors = employeeService.getVisitors();
        return visitors;
    }

    @RequestMapping(value = "/getAgents", method = RequestMethod.POST)
    @CrossOrigin
    public List<EmployeeEntity> getAgents() {
        return employeeService.getAgents();
    }



    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public EmployeeEntity edit(@RequestBody EmployeeEntity employeeEntity) {
        return employeeService.edit(employeeEntity);
    }

    @GetMapping("list")
    @CrossOrigin
    public List<EmployeeEntity> employees() {
        return employeeService.list();
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    @CrossOrigin
    public List<EmployeeEntity> find(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String personalNumber,
            @RequestParam Integer offset,
            @RequestParam Integer limit) {
        EmployeeEntity searchParams = EmployeeEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .personalNumber(personalNumber)
                .build();
        return employeeService.find(searchParams, limit, offset);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long employeeId) {
        employeeService.delete(employeeId);
    }
}
