package components;

public class Stack {
  Cell head;
  public Stack() {
    head = null;
  }
  public boolean push(int i) {
    if (i < 0) return false;
      head = new Cell(head, i);
    return true;
  }
  public int pop() {
    if (head == null)
      throw new RuntimeException("empty");
    int result = head.data;
    head = head.next;
    return result;
  }
  public Iterator iterator() {
    return new StackItr(head);
  }
}