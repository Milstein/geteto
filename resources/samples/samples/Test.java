package samples;


public class Test extends Test2 {

    public static void main(String[] args) {
        String msg1 = "Print msg1!!!";
        String msg2 = "Print msg2!!!";
        String msg3 = "Print msg3!!!";
        String msg4 = "Print msg4!!!";

        if (msg1.equals(msg2)) {
            test2Print(msg1);
        } else if (msg1.equals(msg3)) {
            testPrint(msg4);
        }
        testPrint(msg4);
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                int x = i;
            }
        }
    }
	
    public static void testPrint(String s) {
        System.out.println("Test print... " + s);
    }
}
