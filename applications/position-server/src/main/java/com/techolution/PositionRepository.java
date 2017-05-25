package com.techolution;

import com.techolution.position.Position;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tdelesio on 5/23/17.
 */
public interface PositionRepository extends CrudRepository<Position, String> {

}
