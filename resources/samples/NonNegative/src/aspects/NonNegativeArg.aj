package aspects;

import components.*;

aspect NonNegativeArg {
	before() : execution(* *.*(..)) {
    Object args[] = thisJoinPoint.getArgs();
    for(int i=0; i<args.length; i++) {
      if ((args[i] instanceof Integer) &&
      (((Integer)args[i]).intValue() < 0))
        throw new RuntimeException("negative arg of " +
        thisJoinPoint.getSignature().toShortString());
    }
  }
}