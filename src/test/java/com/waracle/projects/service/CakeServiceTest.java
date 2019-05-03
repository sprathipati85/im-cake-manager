package com.waracle.projects.service;

import com.waracle.projects.Application;
import com.waracle.projects.domain.Cake;
import com.waracle.projects.service.api.ICakeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class })
public class CakeServiceTest {

    @Autowired
    private ICakeService cakeService;

    @Test
    public void invokeSaveCakeForCaramelCake() {
        Cake cake = new Cake();
        cake.setTitle("Caramel Cake");
        Cake caramelCake = cakeService.saveCake(cake);
        Assert.assertNotNull(caramelCake);
        Assert.assertNotNull(caramelCake.getId());
    }

    @Test
    public void invokeGetCakes() {
        Set<Cake> cakes = cakeService.getCakes();
        Assert.assertNotNull(cakes);
    }
}
