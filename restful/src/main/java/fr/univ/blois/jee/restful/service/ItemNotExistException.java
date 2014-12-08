package fr.univ.blois.jee.restful.service;

/**
 * Created by frobert on 05/12/2014.
 */
public class ItemNotExistException extends Exception {

  public enum ItemMissingSelection {
      AUTHOR_MISSED(AUTHOR_NOT_EXIST)
      , BOOK_MISSED(BOOK_NOT_EXIST)
    ;
    private final String pattern;

    private ItemMissingSelection(String pattern) {
      this.pattern = pattern;
    }

  }

  private static final String AUTHOR_NOT_EXIST = "L'auteur identifié par le N° %d n'est pas référencé dans notre bibliothèque";
  private static final String BOOK_NOT_EXIST = "Le livre identifié par le N° %d n'est pas référencé dans notre bibliothèque";

  public ItemNotExistException(ItemMissingSelection itemMissingSelection, int id) {
    super(String.format(itemMissingSelection.pattern, id));
  }

}
