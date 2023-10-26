package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pathologycal;
import com.example.demo.repository.PathologycalRepository;

@Service
public class PathologycalService {

    @Autowired
    private PathologycalRepository pathologycalRepository;

    public List<Pathologycal> getAllPathologycals() {
        return pathologycalRepository.findAll();
    }

    public Pathologycal getPathologycalById(Integer id) {
        return pathologycalRepository.findById(id).orElse(null);
    }

    public Pathologycal createPathologycal(Pathologycal pathologycal) {
        return pathologycalRepository.save(pathologycal);
    }

    public Pathologycal updatePathologycal(Integer id, Pathologycal updatedPathologycal) {
        Pathologycal pathologycal = getPathologycalById(id);
        if (pathologycal != null) {
            updatedPathologycal.setId(pathologycal.getId());
            return pathologycalRepository.save(updatedPathologycal);
        }
        return null;
    }

    public void deletePathologycal(Integer id) {
        pathologycalRepository.deleteById(id);
    }
}

