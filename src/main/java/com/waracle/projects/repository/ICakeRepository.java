package com.waracle.projects.repository;

import com.waracle.projects.domain.Cake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICakeRepository extends CrudRepository<Cake, Long>, ICustomCakeRepository {


}
