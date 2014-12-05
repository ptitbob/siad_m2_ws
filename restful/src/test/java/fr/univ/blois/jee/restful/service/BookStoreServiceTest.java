package fr.univ.blois.jee.restful.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by frobert on 05/12/2014.
 */
public class BookStoreServiceTest {

  @Test
  public void testInitialisation() {
    BookStoreService bookStoreService = new BookStoreService();
    Assert.assertNotNull(bookStoreService);
  }

  @Test
  public void testInitialisationList() {

  }
}
