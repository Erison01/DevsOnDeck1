package com.erison.devsondeck3.services;

import com.erison.devsondeck3.models.Skill;
import com.erison.devsondeck3.repositories.SkillRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {

    @Autowired
    SkillRepo skillRepo;


    public void saveSkill(Skill skill) {
        skillRepo.save(skill);
    }


}
