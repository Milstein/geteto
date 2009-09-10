package binarytreesearch;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ItemNotFoundException extends RuntimeException {
  public ItemNotFoundException()
  {
    super();
  }

  public ItemNotFoundException(String message)
  {
    super( message );
  }
}