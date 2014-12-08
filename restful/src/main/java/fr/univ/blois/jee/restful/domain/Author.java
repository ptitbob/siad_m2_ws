package fr.univ.blois.jee.restful.domain;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * Information sur l'auteur contenant la liste des livres
 */
@XmlRootElement(name = "auteur")
@XmlAccessorType(FIELD)
public class Author extends User {

  /**
   * Liste des livres
   */
  @XmlElementWrapper(name = "livres")
  @XmlElement(name = "livre")
  private Set<BookInformation> bookList = new HashSet<>();

  public Author() {
  }

  public Author(int id, String name) {
    super(id, name);
  }

  public Set<BookInformation> getBookList() {
    return bookList;
  }

  public void setBookList(Set<BookInformation> bookList) {
    this.bookList = bookList;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Author{");
    sb.append("id=").append(getId());
    sb.append(", name='").append(getName()).append('\'');
    sb.append(", bookList=").append(bookList);
    sb.append('}');
    return sb.toString();
  }

  public void addBook(Book book) {
    bookList.add(new BookInformation(book.getId(), book.getTitle(), book.getGenre()));
  }
}
