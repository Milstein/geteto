.class x
.super java/lang/Object
.implements u

;
; *** Fields ***
;

.field h I
.field d D
.field l J

;
; *** Methods ***
;

; void <init>()
.method <init>()V

.limit stack 1
.limit locals 1

.var 0 is this Lx; from Label1 to Label2

Label1:	; 0

	aload_0

	invokespecial java/lang/Object/<init>()V

	return

Label2:	; 5
.end method


; synchronized void m(int)
.method synchronized m(I)V

.limit stack 3
.limit locals 5

;Exception handlers
.catch all from Label8 to Label13 using Label13

.var 0 is this Lx; from Label1 to Label15
.var 1 is y I from Label1 to Label15
.var 2 is x I from Label2 to Label14

Label1:	; 0

	bipush 10

	iload_1

	iadd

	istore_2

Label2:	; 5

	iload_2

	tableswitch 0
	Label3	; 32 (+26)
	Label5	; 51 (+45)
	Label6	; 61 (+55)
	default: Label7		; 62 (+56)


Label3:	; 32

	iinc 2 1

	iload_2

	bipush 15

	if_icmple Label4	; 44 (+6)

	goto Label7	; 62 (+21)

Label4:	; 44

	iinc 2 -1

	iload_2

	iload_1

	iadd

	istore_2

Label5:	; 51

	iconst_0

	aload_0

	getfield x/h I

	iadd

	istore_1

	goto Label7	; 62 (+4)

Label6:	; 61

	return

Label7:	; 62

	aload_0

	astore_3

	aload_3

	monitorenter

Label8:	; 66

	iload_2

	lookupswitch
	1: Label10		; 113 (+46)
	2: Label11		; 118 (+51)
	10: Label9		; 100 (+33)
	default: Label12	; 121 (+54)

Label9:	; 100

	iinc 2 1

	aload_0

	dup

	getfield x/h I

	iconst_1

	iadd

	putfield x/h I

Label10:	; 113

	iconst_0

	istore_1

	goto Label12	; 121 (+6)

Label11:	; 118

	aload_3

	monitorexit

	return

Label12:	; 121

	aload_3

	monitorexit

	goto Label14	; 133 (+10)

Label13:	; 126

	astore 4

	aload_3

	monitorexit

	aload 4

	athrow

Label14:	; 133

	return

Label15:	; 134
.end method


; void tryCatchFinally()
.method tryCatchFinally()V

.limit locals 5

;Exception handlers
.catch TestExc from Label1 to Label2 using Label3
.catch java/lang/Exception from Label1 to Label2 using Label4
.catch all from Label1 to Label5 using Label5

.var 0 is this Lx; from Label1 to Label8
.var 1 is e LTestExc; from Label3 to Label7
.var 2 is e Ljava/lang/Exception; from Label4 to Label7

Label1:	; 0

	aload_0

	invokevirtual x/tryItOut()V
	
	

Label2:	; 4

	jsr Label6	; 35 (+31)

	goto Label7	; 43 (+36)

Label3:	; 10

	astore_1

	aload_0

	aload_1

	invokevirtual x/handleExc(LTestExc;)V

	jsr Label6	; 35 (+19)

	goto Label7	; 43 (+24)

Label4:	; 22

	astore_2

	jsr Label6	; 35 (+12)

	goto Label7	; 43 (+17)

Label5:	; 29

	astore_3

	jsr Label6	; 35 (+5)

	aload_3

	athrow

Label6:	; 35

	astore 4

	aload_0

	invokevirtual x/wrapItUp()V
	

	ret 4
	

Label7:	; 43


	return

Label8:	; 44

.limit stack 2


.end method


; void tryItOut()
.method tryItOut()V

.limit stack 2
.limit locals 1

.var 0 is this Lx; from Label1 to Label2

Label1:	; 0

	new TestExc

	dup

	invokespecial TestExc/<init>()V

	athrow

Label2:	; 8
.end method


; void handleExc(TestExc)
.method handleExc(LTestExc;)V

.limit stack 0
.limit locals 2

.var 0 is this Lx; from Label1 to Label2
.var 1 is e LTestExc; from Label1 to Label2

Label1:	; 0

	return

Label2:	; 1
.end method


; void wrapItUp()
.method wrapItUp()V

.limit stack 0
.limit locals 1

.var 0 is this Lx; from Label1 to Label2

Label1:	; 0

	return

Label2:	; 1
.end method


