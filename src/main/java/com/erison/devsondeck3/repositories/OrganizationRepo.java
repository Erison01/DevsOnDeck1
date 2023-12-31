package com.erison.devsondeck3.repositories;


import com.erison.devsondeck3.models.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepo extends CrudRepository<Organization,Long> {

    List<Organization>findAll();
    Optional<Organization> findByEmail(String email);


}
