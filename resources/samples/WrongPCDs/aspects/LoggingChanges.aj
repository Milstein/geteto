package aspects;
import base.*;

public aspect LoggingChanges {
	
	// case A
	pointcut settingMethodsA() : execution(* *.set*(*));
	
	// case B
	pointcut settingMethodsB() : execution(* *.get*(*));
	
	// case C
	pointcut settingMethodsC() : 
	  execution(* *.set*(*)) && execution(* *.change*(*));
	
	// case D
	pointcut settingMethodsD() : 
	  execution(* *.set*(*)) && !execution(* *.settings());
	
	after() : settingMethodsA() { 
	  //call(* *.set*(*)) || 
	  //call(* *.change*(*)) && !call(* *.settings()) {
		System.out.println("Changing object state");
	}
	
}
