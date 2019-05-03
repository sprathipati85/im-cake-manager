package com.waracle.projects.rest;


import com.waracle.projects.domain.Cake;
import com.waracle.projects.service.api.ICakeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CakeRestController.class)
public class CakeRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ICakeService cakeService;

    private Cake cake;
    Set<Cake> cakes = new HashSet<>();

    @Before
    public void setup() {
        cake = generateCake();
        cakes.add(cake);
    }

    @Test
    public void invokeCakesControllerEndpointForGetMethod() throws Exception {
        Mockito.when(cakeService.getCakes()).thenReturn(cakes);

        mvc.perform(get("/cakes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private Cake generateCake() {
        cake = new Cake();

        cake.setDesc("Chocolate Mint cake");
        cake.setImage("http://patteserie.com/chocolate.jpg");
        cake.setTitle("Patteseirie cake");

        return cake;
    }
}