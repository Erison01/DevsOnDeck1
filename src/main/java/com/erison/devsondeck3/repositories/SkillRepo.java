package com.erison.devsondeck3.repositories;


import com.erison.devsondeck3.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SkillRepo extends CrudRepository<Skill,Long> {
    List<Skill>findAll();

    List<Skill> findByIdIn(List<Long> skillIds);
}
