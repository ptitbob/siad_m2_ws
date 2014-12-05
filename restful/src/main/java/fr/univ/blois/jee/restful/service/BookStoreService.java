package fr.univ.blois.jee.restful.service;

import fr.univ.blois.jee.restful.domain.*;
import fr.univ.blois.jee.restful.service.layer.BookStorePersistenceLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frobert on 05/12/2014.
 */
public class BookStoreService {

  /**
   * Renvoi la liste des auteur (sans la liste des livres)
   * @return
   */
  public List<User> getAuthorLisr() {
    BookStorePersistenceLayer bookStorePersistenceLayer = BookStorePersistenceLayer.getInstance();
    List<User> authorList = new ArrayList<>();
    for (Author author : bookStorePersistenceLayer.getAllAuthor()) {
      authorList.add(new User(author.getId(), author.getName()));
    }
    return authorList;
  }

  public List<BookInformation> getBookList() {
    BookStorePersistenceLayer bookStorePersistenceLayer = BookStorePersistenceLayer.getInstance();
    List<BookInformation> bookList = new ArrayList<>();
    for (Book book : bookStorePersistenceLayer.getAllBook()) {
      bookList.add(new BookInformation(book.getId(), book.getTitle(), book.getGenre()));
    }
    return bookList;
  }

  public Author addAuthor(String authorName) {
    BookStorePersistenceLayer bookStorePersistenceLayer = BookStorePersistenceLayer.getInstance();
    return bookStorePersistenceLayer.saveAuthor(authorName);
  }

  public Author getAuthor(int authorId) {
    BookStorePersistenceLayer bookStorePersistenceLayer = BookStorePersistenceLayer.getInstance();
    return bookStorePersistenceLayer.getAuthorById(authorId);
  }

  public Book addBook(String title, Genre genre, int authorId) throws AuthorNotExistException {
    BookStorePersistenceLayer bookStorePersistenceLayer = BookStorePersistenceLayer.getInstance();
    Author author = getAuthor(authorId);
    if (author == null) {
      throw new AuthorNotExistException("L'auteur demandé n'existe pas");
    }
    Book book = bookStorePersistenceLayer.saveBook(title, genre);
    author.addBook(book);
    book.addAuthor(author);
    return book;
  }
}
