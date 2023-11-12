package com.example.demo.service;

import com.example.demo.model.Feedback;
import com.example.demo.model.Patient;
import com.example.demo.repository.FeedBackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public  class FeedbackService {
    @Autowired
    private FeedBackRepository feedBackRepository;

    public List<Feedback> getAllFeedBack() {
        return feedBackRepository.findAll();
    }

    public Feedback getFeedBackById(Integer id) {
        return feedBackRepository.findById(id).orElse(null);
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedBackRepository.save(feedback);
    }

    public Feedback updateFeedBack(Integer id, Feedback feedback) {
        if (feedBackRepository.existsById(id)) {
            feedback.setId(id);
            return feedBackRepository.save(feedback);
        }
        return null;
    }

    public boolean deleteFeedBack(Integer id) {
        feedBackRepository.deleteById(id);
        return false;
    }
}