package main.com.xray.services;

import main.com.xray.model.Statistic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StatisticsService {
    @PersistenceContext
    EntityManager entityManager;
    public void store(String uri, String referrer){
        entityManager.persist(new Statistic(uri,referrer));
    }

}
