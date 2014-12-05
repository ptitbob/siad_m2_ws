package fr.univ.blois.jee.restful.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by francois on 02/12/14.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

  /**
   * Identifiant
   */
  @XmlAttribute
  private int id;

  /**
   * Nom
   */
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;

    User user = (User) o;

    if (id != user.id) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

