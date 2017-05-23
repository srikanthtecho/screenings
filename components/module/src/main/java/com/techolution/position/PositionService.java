package com.techolution.position;

import javafx.geometry.Pos;
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

