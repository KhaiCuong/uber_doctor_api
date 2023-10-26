package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pathologycal;
import com.example.demo.response.CustomStatusResponse;
import com.example.demo.service.PathologycalService;

@RestController
@RequestMapping("/api/pathologycals")
public class PathologycalController {

    @Autowired
    private PathologycalService pathologycalService;
    @Autowired
    private CustomStatusResponse customStatusResponse;

    @GetMapping
    public ResponseEntity<CustomStatusResponse<List<Pathologycal>>> getAllPathologycals() {
        try {
            List<Pathologycal> pathologycals = pathologycalService.getAllPathologycals();
            if (pathologycals.isEmpty()) {
                return customStatusResponse.NOTFOUND404("No pathologycals found.");
            }
            return customStatusResponse.OK200("Pathologycals retrieved successfully.", pathologycals);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomStatusResponse<Pathologycal>> getPathologycalById(@PathVariable Integer id) {
        try {
            Pathologycal pathologycal = pathologycalService.getPathologycalById(id);
            if (pathologycal == null) {
                return customStatusResponse.NOTFOUND404("Pathologycal not found.");
            }
            return customStatusResponse.OK200("Pathologycal retrieved successfully.", pathologycal);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<CustomStatusResponse<Pathologycal>> createPathologycal(@RequestBody Pathologycal pathologycal) {
        try {
            Pathologycal createdPathologycal = pathologycalService.createPathologycal(pathologycal);
            return customStatusResponse.CREATED201("Pathologycal created successfully.", createdPathologycal);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomStatusResponse<Pathologycal>> updatePathologycal(@PathVariable Integer id, @RequestBody Pathologycal pathologycal) {
        try {
            Pathologycal updatedPathologycal = pathologycalService.updatePathologycal(id, pathologycal);
            if (updatedPathologycal == null) {
                return customStatusResponse.NOTFOUND404("Pathologycal not found.");
            }
            return customStatusResponse.OK200("Pathologycal updated successfully.", updatedPathologycal);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomStatusResponse<?>> deletePathologycal(@PathVariable Integer id) {
        try {
            pathologycalService.deletePathologycal(id);
            return customStatusResponse.OK200("Pathologycal deleted successfully.");
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }
}
