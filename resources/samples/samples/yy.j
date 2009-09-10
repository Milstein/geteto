.class public y
.super b

;
; *** Fields ***
;

.field static x J
.field static y J
.field g Lb;
.field l [[Lb;

;
; *** Methods ***
;

; public void <init>()
.method public <init>()V

.limit stack 1
.limit locals 1

	aload_0

	invokespecial b/<init>()V

	return

.end method


; public void r()
.method public r()V

.limit stack 4
.limit locals 4

;Exception handlers
.catch all from Label1 to Label2 using Label2

	bipush 10

	bipush 7

	multianewarray [[I 2

	astore_1

Label1:	; 9

	aload_0

	bipush 11

	iconst_1

	multianewarray [[Lb; 2

	putfield y/l [[Lb;

	aload_1

	iconst_0

	aaload

	iconst_0

	sipush 1963

	iastore

	aload_1

	iconst_1

	iconst_2

	newarray int

	aastore

	getstatic java/lang/System/out Ljava/io/PrintStream;

	new java/lang/StringBuffer

	dup

	invokespecial java/lang/StringBuffer/<init>()V

	aload_1

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/Object;)Ljava/lang/StringBuffer;

	ldc " "	; #65

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;

	aload_1

	iconst_1

	aaload

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/Object;)Ljava/lang/StringBuffer;

	ldc " "	; #65

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;

	aload_1

	iconst_0

	aaload

	iconst_0

	iaload

	invokevirtual java/lang/StringBuffer/append(I)Ljava/lang/StringBuffer;

	invokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;

	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	jsr Label3	; 90 (+12)

	goto Label4	; 103 (+22)

Label2:	; 84

	astore_2

	jsr Label3	; 90 (+5)

	aload_2

	athrow

Label3:	; 90

	astore_3

	aload_0

	bipush 11

	iconst_1

	multianewarray [[Lb; 2

	putfield y/l [[Lb;

	return

Label4:	; 103

	return

.end method


; public static void main(java.lang.String[])
.method public static main([Ljava/lang/String;)V

.limit stack 4
.limit locals 3

	getstatic y/x J

	getstatic y/y J

	lcmp

	ifle Label1	; 18 (+11)

	new y

	dup

	invokespecial y/<init>()V

	astore_1

Label1:	; 18

	new b

	dup

	invokespecial b/<init>()V

	astore_1

	aload_1

	invokevirtual b/p()V

	new b

	dup

	invokespecial b/<init>()V

	astore_2

	aload_2

	invokevirtual b/u()V

	aload_2

	invokevirtual C/u()V

	return

.end method


; void p()
.method p()V

.limit stack 3
.limit locals 1

	getstatic java/lang/System/out Ljava/io/PrintStream;

	new java/lang/StringBuffer

	dup

	invokespecial java/lang/StringBuffer/<init>()V

	aload_0

	invokevirtual java/lang/Object/getClass()Ljava/lang/Class;

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/Object;)Ljava/lang/StringBuffer;

	ldc " y"	; #66

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;

	invokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;

	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	return

.end method


; static void <clinit>()
.method static <clinit>()V

.limit stack 2
.limit locals 0

	lconst_0

	putstatic y/x J

	ldc2_w 10	; #43

	putstatic y/y J

	aconst_null

	dup

	ifnull L1

	pop

	ldc "uuuuu"
L1:
	nop	

	return

.end method


