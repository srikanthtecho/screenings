package com.techolution.interview;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tdelesio on 5/24/17.
 */
@Service
public class InterviewService {

    private InterviewRepository interviewRepository;

    public InterviewService(InterviewRepository interviewRepository)
    {
        this.interviewRepository = interviewRepository;
    }

    public List<Interview> getAllInterviews()
    {
        List<Interview> interviews = new ArrayList<>();
        interviewRepository.findAll().forEach(interviews::add);
        return interviews;
    }

    public Interview getInterviewById(String id)
    {
        Interview interview = interviewRepository.findOne(id);
        return interview;
    }

    public Interview createInterview(Interview interview)
    {
        String id = UUID.randomUUID().toString();
        interview.setId(id);
        return interviewRepository.save(interview);
    }
}
