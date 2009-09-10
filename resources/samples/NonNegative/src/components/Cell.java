package components;

class Cell {
  int data; Cell next;
  Cell(Cell n, int i) {
    next = n;
    data = i;
  }
}