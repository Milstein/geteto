package aspects;

import components.*;

aspect PushCount {
  int Stack.count = 0;
  int allStackCount = 0;
  public void Stack.increaseCount() {
    count++;
  }
  boolean around(Stack stack):
    execution(* Stack.push(int)) && target(stack) {
    boolean ret = proceed(stack);
    stack.increaseCount();
    allStackCount++;
    return ret;
  }
  public int getAllStackCount() {
    return allStackCount;
  }
}