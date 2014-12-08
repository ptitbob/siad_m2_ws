package fr.univ.blois.jee.restful.service;

import fr.univ.blois.jee.restful.domain.*;
import fr.univ.blois.jee.restful.service.layer.BookStorePersistenceLayer;
import fr.univ.blois.jee.restful.service.layer.DeleteItemNotExist;

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
  public List<User> getAuthorList() {
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

  public Author getAuthor(int authorId) throws AuthorNotExistException {
    BookStorePersistenceLayer bookStorePersistenceLayer = BookStorePersistenceLayer.getInstance();
    Author author = bookStorePersistenceLayer.getAuthorById(authorId);
    if (author == null) {
      throw new AuthorNotExistException(authorId);
    }
    return author;
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

  /**
   * Méthode métier pour la mise à jour d'un auteur
   * @param id identifiant de l'auteur
   * @param authorName nom de l'auteur
   * @return l'auteur modifié
   * @throws fr.univ.blois.jee.restful.service.AuthorUpdatingProcessAborted en cas d'erreur de mise à jour
   */
  public Author updateAuthor(int id, String authorName) throws AuthorUpdatingProcessAborted {
    try {
      Author author = getAuthor(id);
      author.setName(authorName);
      return author;
    } catch (AuthorNotExistException e) {
      throw new AuthorUpdatingProcessAborted(id);
    }
  }


  public void deleteAuthor(int id) throws DeleteItemNotExist {
    BookStorePersistenceLayer bookStorePersistenceLayer = BookStorePersistenceLayer.getInstance();
    bookStorePersistenceLayer.deleteAuthor(id);
  }
}
