package fr.univ.blois.jee.restful.service.layer;

import fr.univ.blois.jee.restful.domain.Author;
import fr.univ.blois.jee.restful.domain.Book;
import fr.univ.blois.jee.restful.domain.Genre;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BookStorePersistenceLayer {

  private static BookStorePersistenceLayer instance = new BookStorePersistenceLayer();

  synchronized public static BookStorePersistenceLayer getInstance() {
    return instance;
  }
  private Map<Integer, Author> authorMap = new HashMap<>();

  private Map<Integer, Book> bookMap = new HashMap<>();

  private AtomicInteger authorIndex = new AtomicInteger();

  private AtomicInteger bookIndex = new AtomicInteger();

  private BookStorePersistenceLayer() {
    initialization();
  }

  private void initialization() {
    Author author = saveAuthor("David Eddings");
    Book book = saveBook("Pawn of Prophecy", Genre.FANTASY);
    linkAuthorToBook(author, book);
    book = saveBook("Queen of Sorcery", Genre.FANTASY);
    linkAuthorToBook(author, book);
    book = saveBook("Magician's Gambit", Genre.FANTASY);
    linkAuthorToBook(author, book);
    book = saveBook("Castle of Wizardry", Genre.FANTASY);
    linkAuthorToBook(author, book);
    book = saveBook("Enchanters' End Game", Genre.FANTASY);
    linkAuthorToBook(author, book);
    author = saveAuthor("Arthur C. Clarke");
    book = saveBook("2001: A Space Odyssey", Genre.SCIFI);
    linkAuthorToBook(author, book);
  }

  private void linkAuthorToBook(Author author, Book book) {
    book.addAuthor(author);
    author.addBook(book);
  }

  public Book saveBook(String bookTitle, Genre genre) {
    Book book = new Book(bookIndex.getAndIncrement(), bookTitle, genre);
    bookMap.put(book.getId(), book);
    return book;
  }

  public Author saveAuthor(String authorName) {
    Author author = new Author(authorIndex.getAndIncrement(), authorName);
    authorMap.put(author.getId(), author);
    return author;
  }

  public List<Book> getAllBook() {
    return new ArrayList<>(bookMap.values());
  }

  public List<Author> getAllAuthor() {
    return new ArrayList<>(authorMap.values());
  }

  public Author getAuthorById(int id) {
    return authorMap.get(id);
  }

  public Book getBookById(int id) {
    return bookMap.get(id);
  }

  public void deleteAuthor(int id) throws DeleteItemNotExist {
    Author author = authorMap.get(id);
    if (author != null) {
      authorMap.remove(author.getId());
    } else {
      throw new DeleteItemNotExist("L'auteur n'existe pas");
    }
  }

  public void deleteBook(int id) throws DeleteItemNotExist {
    Book book = bookMap.get(id);
    if (book == null) {
      throw new DeleteItemNotExist("Le livre n'existe pas");
    } else {
      bookMap.remove(book.getId());
    }
  }

}
