package com.erison.devsondeck3.repositories;

import com.erison.devsondeck3.models.Organization;
import com.erison.devsondeck3.models.Position;
import com.erison.devsondeck3.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepo extends CrudRepository<Position,Long> {
    List<Position> findBySkillIn(List<Skill> skills);
    List<Position> findByOrganization(Organization organization);

    Position findByIdAndOrganization(Long id, Organization organization);

}
