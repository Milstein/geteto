class x implements u {

    int h;
    double d;
    long l;

    synchronized void  m(int y) {
        int x;

        x tt;

        x = new x().h;
        x = 10 + y;
        switch (x) {
        case 0:
            x++;
            if (x > 15) {
                break;
            } else {
                x--;
            }
            x += y;

        case 1:
            y = 0 + h;
            break;

        case 2:
            return;
        }
        synchronized (this) {
            switch (x) {
            case 10:
                x++;
                h++;

            case 1:
                y = 0;
                break;

            case 2:
                return;
            }
        }

    }

    void tryCatchFinally() {
        try {
            tryItOut();
        } catch (TestExc e) {
            e = e;
            handleExc(e);
        } catch (Exception e) {;
        } finally {
            wrapItUp();
        }
    }

    void tryItOut() throws TestExc {
        throw new TestExc();
    }

    void handleExc(TestExc e) {}

    void wrapItUp() {} 

}


class TestExc extends Exception {}


interface u extends r, s, java.io.Serializable {}


interface r {}


interface s {}

