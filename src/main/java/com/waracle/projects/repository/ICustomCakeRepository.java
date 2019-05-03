package com.waracle.projects.repository;

import com.waracle.projects.domain.Cake;
import java.util.Set;

public interface ICustomCakeRepository {

    Set<Cake> getCakes();
}
