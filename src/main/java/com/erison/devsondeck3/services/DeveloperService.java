package com.erison.devsondeck3.services;

import com.erison.devsondeck3.models.Developer;
import com.erison.devsondeck3.models.Skill;
import com.erison.devsondeck3.models.devLogin;
import com.erison.devsondeck3.repositories.DeveloperRepo;
import com.erison.devsondeck3.repositories.SkillRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {
    @Autowired
    private SkillRepo skillRepo;
    @Autowired
    DeveloperRepo developerRepo;




    public Developer register(Developer newDev, BindingResult result) {

        Optional<Developer> potentialDev = developerRepo.findByEmail(newDev.getEmail());

        // Reject if email is taken (present in database)
        if(potentialDev.isPresent()) {
            result.rejectValue("email", "Matches", "An account with that email already exists!");
        }

        // Reject if password doesn't match confirmation
        if(!newDev.getPassword().equals(newDev.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }

        // Return null if result has errors
        if(result.hasErrors()) {
            return null;
        }

        // Hash and set password, save user to database
        String hashed = BCrypt.hashpw(newDev.getPassword(), BCrypt.gensalt());
        newDev.setPassword(hashed);
        return developerRepo.save(newDev);

    }

    public Developer login(devLogin logDev, BindingResult result) {

        Optional<Developer> potentialDev= developerRepo.findByEmail(logDev.getEmail());

        // Find user in the DB by email
        // Reject if NOT present
        if(!potentialDev.isPresent()) {
            result.rejectValue("email", "Matches", "User not found!");
            return null;
        }

        // User exists, retrieve user from DB
        Developer dev = potentialDev.get();

        // Reject if BCrypt password match fails
        if(!BCrypt.checkpw(logDev.getPassword(), dev.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }

        // Return null if result has errors
        if(result.hasErrors()) {
            return null;
        }

        // Otherwise, return the user object
        return dev;

    }

    public List<Developer> allUsers(){
        return developerRepo.findAll();
    }

    @Transactional
    public Developer saveDev(Developer developer) {
        return developerRepo.save(developer);
    }


    public Developer findById(Long id){
        return developerRepo.findById(id).orElse(null);
    }

    public List<Skill> getAllSkills() {
        return skillRepo.findAll();
    }

    public List<Skill> getSkillsByIds(List<Long> skillIds) {
        return skillRepo.findByIdIn(skillIds);
    }
}
