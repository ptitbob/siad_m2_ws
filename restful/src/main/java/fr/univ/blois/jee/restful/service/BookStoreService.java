package fr.univ.blois.jee.restful.service;

import fr.univ.blois.jee.restful.domain.*;
import fr.univ.blois.jee.restful.service.layer.BookStorePersistenceLayer;
import fr.univ.blois.jee.restful.service.layer.DeleteItemNotExist;

import java.util.ArrayList;
import java.util.List;

import static fr.univ.blois.jee.restful.service.ItemNotExistException.ItemMissingSelection.AUTHOR_MISSED;
import static fr.univ.blois.jee.restful.service.ItemNotExistException.ItemMissingSelection.BOOK_MISSED;

/**
 * Created by frobert on 05/12/2014.
 */
public class BookStoreService {

  /**
   * Renvoi la liste des auteur (sans la liste des livres)
   * @return
   */
  public List<User> getAuthorList() {
    BookStorePersistenceLayer bookStorePersistenceLayer = getBookStorePersistenceLayer();
    List<User> authorList = new ArrayList<>();
    for (Author author : bookStorePersistenceLayer.getAllAuthor()) {
      authorList.add(new User(author.getId(), author.getName()));
    }
    return authorList;
  }

  public List<BookInformation> getBookList() {
    BookStorePersistenceLayer bookStorePersistenceLayer = getBookStorePersistenceLayer();
    List<BookInformation> bookList = new ArrayList<>();
    for (Book book : bookStorePersistenceLayer.getAllBook()) {
      bookList.add(new BookInformation(book.getId(), book.getTitle(), book.getGenre()));
    }
    return bookList;
  }

  public Author addAuthor(String authorName) {
    BookStorePersistenceLayer bookStorePersistenceLayer = getBookStorePersistenceLayer();
    return bookStorePersistenceLayer.saveAuthor(authorName);
  }

  public Author getAuthor(int authorId) throws ItemNotExistException {
    BookStorePersistenceLayer bookStorePersistenceLayer = getBookStorePersistenceLayer();
    Author author = bookStorePersistenceLayer.getAuthorById(authorId);
    if (author == null) {
      throw new ItemNotExistException(AUTHOR_MISSED, authorId);
    }
    return author;
  }

  /**
   *
   * @param title titre du livre
   * @param genre genre du livre
   * @param authorId id de l'auteur
   * @return Livre ajouté
   * @throws ItemNotExistException si l'auteur n'a pas pu être localisé
   */
  public Book addBook(String title, Genre genre, int authorId) throws ItemNotExistException {
    BookStorePersistenceLayer bookStorePersistenceLayer = getBookStorePersistenceLayer();
    Author author = getAuthor(authorId);
    if (author == null) {
      // throw new ItemNotExistException("L'auteur demandé n'existe pas");
      throw new ItemNotExistException(AUTHOR_MISSED, authorId);
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
   * @throws AuthorUpdatingProcessAbortedException en cas d'erreur de mise à jour
   */
  public Author updateAuthor(int id, String authorName) throws AuthorUpdatingProcessAbortedException {
    try {
      Author author = getAuthor(id);
      author.setName(authorName);
      return author;
    } catch (ItemNotExistException e) {
      throw new AuthorUpdatingProcessAbortedException(id);
    }
  }


  public void deleteAuthor(int id) throws DeleteItemNotExist {
    BookStorePersistenceLayer bookStorePersistenceLayer = getBookStorePersistenceLayer();
    bookStorePersistenceLayer.deleteAuthor(id);
  }

  public Book getBook(int id) {
    BookStorePersistenceLayer bookStorePersistenceLayer = getBookStorePersistenceLayer();
    return bookStorePersistenceLayer.getBookById(id);
  }

  private BookStorePersistenceLayer getBookStorePersistenceLayer() {
    return BookStorePersistenceLayer.getInstance();
  }

  public void updateBook(int id, String title, Genre genre) throws ItemNotExistException {
    BookStorePersistenceLayer bookStorePersistenceLayer = getBookStorePersistenceLayer();
    Book book = bookStorePersistenceLayer.getBookById(id);
    if (book == null) {
      throw new ItemNotExistException(BOOK_MISSED, id);
    }
    book.setTitle(title);
    book.setGenre(genre);
  }

  public void deleteBook(int id) throws DeleteItemNotExist {
    getBookStorePersistenceLayer().deleteBook(id);
  }
}
