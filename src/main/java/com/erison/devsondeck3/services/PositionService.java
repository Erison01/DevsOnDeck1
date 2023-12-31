package com.erison.devsondeck3.services;

import com.erison.devsondeck3.models.Organization;
import com.erison.devsondeck3.models.Position;
import com.erison.devsondeck3.models.Skill;
import com.erison.devsondeck3.repositories.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepo positionRepo;

    public void savePosition(Position position) {
        positionRepo.save(position);
    }

    public List<Position> findPositionsBySkills(List<Skill> skills) {
        return positionRepo.findBySkillIn(skills);
    }

    public List<Position> getPositionsByOrganization(Organization organization) {
        return positionRepo.findByOrganization(organization);
    }

    public Position findPositionByIdAndOrganization(Long id, Organization organization) {
        return positionRepo.findByIdAndOrganization(id, organization);
    }


    public void deletePosition(Position position) {
        positionRepo.delete(position);
    }
}
