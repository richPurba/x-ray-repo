package main.com.xray.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Statistic {
    @Id
    @GeneratedValue

    private long id;
    private String uri;
    private String referrer;

    public Statistic(){ /* for JPA*/}

    public Statistic(String uri, String referrer){
        this.uri = uri;
        this.referrer = referrer;
    }
}
