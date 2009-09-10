.class public y3
.super java/lang/Object

;
; *** Fields ***
;

.field inst Lk;

;
; *** Methods ***
;

; public void <init>()
.method public <init>()V

.limit stack 5
.limit locals 1

.var 0 is this Ly2; from Label1 to Label2

Label1:	; 0

	aload_0

	new k

	dup

	invokespecial k/<init>()V

	aload_0

	invokespecial java/lang/Object/<init>()V

	return

Label2:	; 13
.end method


; public static void main(java.lang.String[])
.method public static main([Ljava/lang/String;)V

.limit stack 2
.limit locals 2

.var 0 is x [Ljava/lang/String; from Label1 to Label4
.var 1 is g Ly2; from Label2 to Label3

Label1:	; 0

	new y2

	dup

	invokespecial y2/<init>()V

	astore_1

Label2:	; 8

	getstatic java/lang/System/out Ljava/io/PrintStream;

	aload_1

	getfield y2/inst Lk;

	invokevirtual java/io/PrintStream/println(Ljava/lang/Object;)V

Label3:	; 18

	return

Label4:	; 19
.end method


