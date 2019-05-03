package com.waracle.projects.service;

import com.waracle.projects.domain.Cake;
import com.waracle.projects.repository.ICakeRepository;
import com.waracle.projects.service.api.ICakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CakeServiceImpl implements ICakeService {

    @Autowired
    private ICakeRepository cakeRepository;


    public Set<Cake> getCakes() {
        return cakeRepository.getCakes();
    }

    @Override
    public Cake saveCake(Cake cake) {
        cakeRepository.save(cake);
        return cake;
    }

    public boolean doesCakeExist(Cake cake) {
        Optional<Cake> existingCake = cakeRepository.findById(cake.getId());
        if (existingCake.isPresent() && existingCake.get() != null) {
            return true;
        }
        return false;
    }

}