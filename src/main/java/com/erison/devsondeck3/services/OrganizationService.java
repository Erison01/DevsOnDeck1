package com.erison.devsondeck3.services;

import com.erison.devsondeck3.models.Developer;
import com.erison.devsondeck3.models.Organization;
import com.erison.devsondeck3.models.devLogin;
import com.erison.devsondeck3.models.orgLogin;
import com.erison.devsondeck3.repositories.OrganizationRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrganizationService {

    @Autowired
    OrganizationRepo organizationRepo;


    public Organization register(Organization newOrg, BindingResult result) {

        Optional<Organization> potentialOrg = organizationRepo.findByEmail(newOrg.getEmail());

        // Reject if email is taken (present in database)
        if(potentialOrg.isPresent()) {
            result.rejectValue("email", "Matches", "An account with that email already exists!");
        }

        // Reject if password doesn't match confirmation
        if(!newOrg.getPassword().equals(newOrg.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }

        // Return null if result has errors
        if(result.hasErrors()) {
            return null;
        }

        // Hash and set password, save user to database
        String hashed = BCrypt.hashpw(newOrg.getPassword(), BCrypt.gensalt());
        newOrg.setPassword(hashed);
        return organizationRepo.save(newOrg);

    }

    public Organization login(orgLogin logOrg, BindingResult result) {

        Optional<Organization> potentialOrg= organizationRepo.findByEmail(logOrg.getEmail());

        // Find user in the DB by email
        // Reject if NOT present
        if(!potentialOrg.isPresent()) {
            result.rejectValue("email", "Matches", "User not found!");
            return null;
        }

        // User exists, retrieve user from DB
        Organization org = potentialOrg.get();

        // Reject if BCrypt password match fails
        if(!BCrypt.checkpw(logOrg.getPassword(), org.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }

        // Return null if result has errors
        if(result.hasErrors()) {
            return null;
        }

        // Otherwise, return the user object
        return org;

    }

    public List<Organization> allUsers(){
        return organizationRepo.findAll();
    }

    public Organization saveOrg(Organization organization) {
        return organizationRepo.save(organization);
    }

    public Organization findById(Long id){
        return organizationRepo.findById(id).orElse(null);
    }
}

