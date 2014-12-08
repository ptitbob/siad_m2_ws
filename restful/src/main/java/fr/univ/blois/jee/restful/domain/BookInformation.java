package fr.univ.blois.jee.restful.domain;

import javax.xml.bind.annotation.*;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * Information de base des livres
 */
@XmlRootElement(name = "livre")
@XmlAccessorType(value = FIELD)
public class BookInformation {

  /**
   * Identifiant du livre
   */
  @XmlAttribute
  private int id;

  /**
   * Nom du livre
   */
  @XmlElement(name = "titre")
  private String title;

  /**
   * Genre du livre
   */
  @XmlAttribute(name = "genre")
  private Genre genre;

  public BookInformation() {
  }

  public BookInformation(int id, String title, Genre genre) {
    this.id = id;
    this.title = title;
    this.genre = genre;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BookInformation)) return false;

    BookInformation that = (BookInformation) o;

    if (id != that.id) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookInformation{");
    sb.append("id=").append(id);
    sb.append(", title='").append(title).append('\'');
    sb.append(", genre=").append(genre);
    sb.append('}');
    return sb.toString();
  }
}
