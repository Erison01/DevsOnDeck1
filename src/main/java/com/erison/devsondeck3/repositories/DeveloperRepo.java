package com.erison.devsondeck3.repositories;

import com.erison.devsondeck3.models.Developer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepo extends CrudRepository<Developer,Long> {

    List<Developer>findAll();
    Optional<Developer>findByEmail(String email);




}
