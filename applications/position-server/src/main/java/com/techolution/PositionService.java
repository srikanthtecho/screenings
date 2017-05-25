package com.techolution;

import com.techolution.position.Position;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdelesio on 5/23/17.
 */
@Service
public class PositionService {

    private PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository)
    {
        this. positionRepository = positionRepository;
    }

    public Position createPosition(Position position)
    {
        return positionRepository.save(position);
    }

    public Position linkSkillsToPosition(Position positionWithSkill)
    {
        Position positionFromDB = positionRepository.findOne(positionWithSkill.getId());
        positionFromDB.setSkills(positionWithSkill.getSkills());

        return positionRepository.save(positionFromDB);
    }

    public List<Position> getAllPositions()
    {
        List<Position> positions = new ArrayList<>();

        positionRepository.findAll().forEach(positions::add);
        return positions;
    }

    public Position getPositionById(String id)
    {
        return positionRepository.findOne(id);
    }
}

