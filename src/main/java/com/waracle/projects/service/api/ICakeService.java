package com.waracle.projects.service.api;

import com.waracle.projects.domain.Cake;

import java.util.Set;

public interface ICakeService {

    Set<Cake> getCakes();

    Cake saveCake(Cake cake);

    boolean doesCakeExist(Cake cake);
}
