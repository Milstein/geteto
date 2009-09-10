.class public samples/y
.super samples/b

;
; *** Fields ***
;

.field static x J
.field static y J
.field g Lsamples/b;
.field l [[Lsamples/b;
.field private __prober__ Lprobe/DefaultProber;

;
; *** Methods ***
;

; public void <init>()
.method public <init>()V

.limit stack 2
.limit locals 1

.var 0 is this Lsamples/y; from Label1 to Label2

Label1:	; 0

	aload_0

	invokespecial samples/b/<init>()V

Label2:	; 4

	aload_0

	invokestatic probe/DefaultProber/proberFactory()Lprobe/DefaultProber;

	putfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	return

.end method


; public void r()
.method public r()V

.limit stack 5
.limit locals 4

;Exception handlers
.catch all from Label2 to Label3 using Label3

.var 0 is this Lsamples/y; from Label1 to Label5

Label1:	; 0

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "0"	; #98

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	bipush 10

	bipush 7

	multianewarray [[I 1

	astore_1

Label2:	; 19

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "1"	; #104

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_0

	bipush 11

	iconst_1

	multianewarray [[Lsamples/b; 1

	putfield samples/y/l [[Lsamples/b;

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

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "2"	; #106

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_1

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/Object;)Ljava/lang/StringBuffer;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "3"	; #108

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	ldc " "	; #9

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "4"	; #110

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_1

	iconst_1

	aaload

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/Object;)Ljava/lang/StringBuffer;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "5"	; #112

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	ldc " "	; #9

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "6"	; #114

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_1

	iconst_0

	aaload

	iconst_0

	iaload

	invokevirtual java/lang/StringBuffer/append(I)Ljava/lang/StringBuffer;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "7"	; #116

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	invokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "8"	; #118

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	jsr Label4	; 190 (+22)

	goto Label5	; 223 (+52)

Label3:	; 174

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "9"	; #120

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	astore_2

	jsr Label4	; 190 (+5)

	aload_2

	athrow

Label4:	; 190

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "10"	; #122

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "11"	; #124

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	astore_3

	aload_0

	bipush 11

	iconst_1

	multianewarray [[Lsamples/b; 1

	putfield samples/y/l [[Lsamples/b;

	return

Label5:	; 223

	return

.end method


; public static void main(java.lang.String[])
.method public static main([Ljava/lang/String;)V

.limit stack 4
.limit locals 3

.var 0 is arg0 [Ljava/lang/String; from Label1 to Label5

Label1:	; 0

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "0"	; #98

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	getstatic samples/y/x J

	getstatic samples/y/y J

	lcmp

	ifle Label2	; 48 (+31)

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "1"	; #104

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	new samples/y

	dup

	invokespecial samples/y/<init>()V

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "2"	; #106

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	astore_1

Label2:	; 48

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "3"	; #108

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	new samples/b

	dup

	invokespecial samples/b/<init>()V

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "4"	; #110

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	astore_1

	aload_1

	invokevirtual samples/b/p()V

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "5"	; #112

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	new samples/b

	dup

	invokespecial samples/b/<init>()V

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "6"	; #114

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	astore_2

	aload_1

	ifnonnull Label3	; 126 (+17)

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "7"	; #116

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_2

	goto Label4	; 137 (+14)

Label3:	; 126

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "8"	; #118

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_1

Label4:	; 137

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "9"	; #120

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	invokevirtual samples/b/u()V

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "10"	; #122

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_2

	invokevirtual samples/b/u()V

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "11"	; #124

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_2

	invokevirtual samples/C/u()V

Label5:	; 178

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aconst_null

	ldc "12"	; #128

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	return

.end method


; void p()
.method p()V

.limit stack 6
.limit locals 1

.var 0 is this Lsamples/y; from Label1 to Label2

Label1:	; 0

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "0"	; #98

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	getstatic java/lang/System/out Ljava/io/PrintStream;

	new java/lang/StringBuffer

	dup

	invokespecial java/lang/StringBuffer/<init>()V

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "1"	; #104

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	aload_0

	invokevirtual java/lang/Object/getClass()Ljava/lang/Class;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "2"	; #106

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/Object;)Ljava/lang/StringBuffer;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "3"	; #108

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	ldc " y"	; #23

	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "4"	; #110

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	invokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "5"	; #112

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

Label2:	; 88

	aload_0

	getfield probe/DefaultProber/__prober__ Lprobe/DefaultProber;

	aload_0

	ldc "6"	; #114

	invokevirtual probe/DefaultProber/probe(Ljava/lang/Object;Ljava/lang/Object;)V

	return

.end method


; static void <clinit>()
.method static <clinit>()V

.limit stack 2
.limit locals 0

	lconst_0

	putstatic samples/y/x J

	ldc2_w 10	; #24

	putstatic samples/y/y J

	return

.end method


