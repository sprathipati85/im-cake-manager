package com.waracle.projects.repository;

import com.waracle.projects.Application;
import com.waracle.projects.domain.Cake;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class CakeRepositoryTest {

    @Autowired
    private ICakeRepository cakeRepository;

    @Test
    public void testToFetchAllTheCakes() {
        Set<Cake> cakes = cakeRepository.getCakes();
        Assert.assertNotNull(cakes);
        Assert.assertEquals(cakes.size(), 5);
    }
}