package fr.univ.blois.jee.restful.domain;

import javax.xml.bind.annotation.*;

/**
 * Created by francois on 02/12/14.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlAttribute
    private int id;

    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

