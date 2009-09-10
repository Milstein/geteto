package components;

public class StackItr implements Iterator {
  private Cell cell;
  public StackItr(Cell head) {
    this.cell = head;
  }
  public boolean hasNext() {
    return cell != null;
  }
  public int next() {
    int result = cell.data;
    cell = cell.next;
    return result;
  }
}