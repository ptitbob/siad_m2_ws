package fr.univ.blois.jee.restful.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation des information de livre avec la liste des auteurs
 */
public class Book extends BookInformation {

  /**
   * List des auteurs
   */
  private List<User> authorList = new ArrayList<>();

  public Book() {
  }

  public Book(int id, String title, Genre genre) {
    super(id, title, genre);
  }

  /**
   * Ajout d'un auteur
   * @param author auteur (user)
   */
  public void addAuthor(User author) {
    getAuthorList().add(new User(author.getId(), author.getName()));
  }

  public List<User> getAuthorList() {
    return authorList;
  }

  public void setAuthorList(List<User> authorList) {

    this.authorList = authorList;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Book{");
    sb.append("id=").append(getId());
    sb.append(", title='").append(getTitle()).append('\'');
    sb.append(", authorList=").append(authorList);
    sb.append('}');
    return sb.toString();
  }
}
