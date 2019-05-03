package com.waracle.projects.rest;

import com.waracle.projects.domain.Cake;
import com.waracle.projects.service.api.ICakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
public class CakeRestController {

    private static final Logger logger = LoggerFactory.getLogger(CakeRestController.class);

    private ICakeService cakeService;

    @Autowired
    public CakeRestController(ICakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<String>> listCakes(){
        Set<Cake> cakes = cakeService.getCakes();
        if(cakes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<String>>(cakes.stream().map(Cake::getTitle).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/cakes")
    public ResponseEntity<Set<Cake>> getCakes(){
        Set<Cake> cakes = cakeService.getCakes();
        if(cakes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cakes, HttpStatus.OK);
    }

    @PostMapping(value = "/cakes")
    public ResponseEntity<Void> addCake(@RequestBody Cake cake){
        if (cakeService.doesCakeExist(cake)) {
            logger.info("Cake already exists with that name {}", cake.getTitle());
            return new ResponseEntity<>(CONFLICT);
        }

        cakeService.saveCake(cake);
        logger.info("Cake {} saved", cake.getTitle());
        return new ResponseEntity<>(CREATED);
       }
}
