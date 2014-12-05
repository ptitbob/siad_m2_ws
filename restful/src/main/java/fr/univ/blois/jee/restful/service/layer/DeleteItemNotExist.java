package fr.univ.blois.jee.restful.service.layer;

/**
 * Created by frobert on 05/12/2014.
 */
public class DeleteItemNotExist extends Exception {
  public DeleteItemNotExist(String message) {
    super(message);
  }
}
