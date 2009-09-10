package aspects;

import supporting.MyInteger;

aspect NonNegativeArg {
  before() : call(* *.*(..)) && !within(NonNegativeArg){
    Object args[] = thisJoinPoint.getArgs();
    for(int i=0; i<args.length; i++) {
      if ((args[i] instanceof MyInteger) &&
      (((MyInteger)args[i]).intValue() < 0))
        throw new RuntimeException("Negative arg of " +
        thisJoinPoint.getSignature().toShortString());
    }
  }
}