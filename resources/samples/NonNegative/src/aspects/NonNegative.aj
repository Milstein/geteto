package aspects;

import components.*;

aspect NonNegative {
  before(Stack stack) : call(* Stack.*(..))
    && target(stack) && !within(NonNegative) {
    Iterator it = stack.iterator();
    while (it.hasNext()) {
      int i = it.next();
      if (i < 0) throw new RuntimeException("negative");
    }
  }
}