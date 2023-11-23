package com.example.demo.controller;

import com.example.demo.model.Feedback;
import com.example.demo.model.Patient;
import com.example.demo.response.CustomStatusResponse;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FeedBackCOntroller {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private CustomStatusResponse customStatusResponse;

    @CrossOrigin
    @GetMapping("/feedback/list")
    public ResponseEntity<CustomStatusResponse<List<Feedback>>> getAllFeedBacks() {
        try {
            List<Feedback> feedbacks = feedbackService.getAllFeedBack();
            if (feedbacks.isEmpty()) {
                return customStatusResponse.NOTFOUND404("No feedback found");
            }
            return customStatusResponse.OK200("Feedback found", feedbacks);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }
    @CrossOrigin
    @GetMapping("/feedback/{id}")
    public ResponseEntity<CustomStatusResponse<Feedback>> getFeedBackById(@PathVariable Integer id) {
        try {
            Feedback feedback = feedbackService.getFeedBackById(id);
            if (feedback == null) {
                return customStatusResponse.NOTFOUND404("Feedback not found");
            }
            return customStatusResponse.OK200("Feedback found", feedback);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }
    @CrossOrigin

    @PostMapping("/feedback/create")
    public ResponseEntity<CustomStatusResponse<Feedback>> createFeedback(@RequestBody Feedback feedback) {
        try {
            Feedback createdFeedBack = feedbackService.createFeedback(feedback);
            return customStatusResponse.CREATED201("FeedBack created", createdFeedBack);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @CrossOrigin
    @PutMapping("/update-feedback/{id}")
    public ResponseEntity<CustomStatusResponse<Feedback>> updateFeedBack(@PathVariable Integer id, @RequestBody Feedback feedback) {
        try {
            Feedback updatedFeedBack = feedbackService.updateFeedBack(id, feedback);
            if (updatedFeedBack == null) {
                return customStatusResponse.NOTFOUND404("FeedBack not found");
            }
            return customStatusResponse.OK200("FeedBack updated", updatedFeedBack);
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("/delete-feedback/{id}")
    public ResponseEntity<CustomStatusResponse<?>> deleteFeedBack(@PathVariable Integer id) {
        try {
            if (feedbackService.deleteFeedBack(id)) {
                return customStatusResponse.OK200("Feedback deleted");
            } else {
                return customStatusResponse.NOTFOUND404("FeedBack not found");
            }
        } catch (Exception e) {
            return customStatusResponse.INTERNALSERVERERROR500(e.getMessage());
        }
    }

}
