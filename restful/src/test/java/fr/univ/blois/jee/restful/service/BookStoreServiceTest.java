package fr.univ.blois.jee.restful.service;

import fr.univ.blois.jee.restful.domain.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by frobert on 05/12/2014.
 */
public class BookStoreServiceTest {

  @Test
  public void testInitialisation() {
    BookStoreService bookStoreService = new BookStoreService();
    assertNotNull(bookStoreService);
  }

  @Test
  public void testInitialisationList() {
    BookStoreService bookStoreService = new BookStoreService();
    List<User> authorList = bookStoreService.getAuthorList();
    assertNotNull(authorList);
    assertTrue(authorList.size() > 0);
    List<BookInformation> bookInformationList = bookStoreService.getBookList();
    assertNotNull(bookInformationList);
    assertTrue(bookInformationList.size() > 0);
  }

  @Test
  public void testSaveAndGetAuthor() throws ItemNotExistException {
    BookStoreService bookStoreService = new BookStoreService();
    Author author = bookStoreService.addAuthor("J. K. Rowling");
    assertNotNull(author);
    assertTrue(author.getId() > 0);
    assertTrue(author.getBookList().size() == 0);
    Author author1 = bookStoreService.getAuthor(author.getId());
    assertEquals(author, author1);
  }

  @Test
  public void testAddBookToAuthor() {
    BookStoreService bookStoreService = new BookStoreService();
    Author author = bookStoreService.addAuthor("J. K. Rowling");
    assertNotNull(author);
    assertTrue(author.getId() > 0);
    assertTrue(author.getBookList().size() == 0);
    try {
      Book book = bookStoreService.addBook("Harry Potter and the Philosopher's Stone", Genre.CHILDREN, author.getId());
      assertNotNull(book);
      assertTrue(book.getAuthorList().size() == 1);
      assertTrue(author.getBookList().size() == 1);
    } catch (ItemNotExistException e) {
      fail("Exception non attendu");
    }
  }

  @Test(expected = ItemNotExistException.class)
  public void testAddBookUnexistantAuthor() throws ItemNotExistException {
    BookStoreService bookStoreService = new BookStoreService();
    Book book = bookStoreService.addBook("blah blah", Genre.MISC, Integer.MIN_VALUE);
  }

  @Test(expected = ItemNotExistException.class)
  public void testGetUnregisteredAuthor() throws ItemNotExistException {
    BookStoreService bookStoreService = new BookStoreService();
    Author author = bookStoreService.getAuthor(Integer.MAX_VALUE);
    fail("Nous attendions une exception precise ici");
  }
}
