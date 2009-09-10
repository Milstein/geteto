class b2 {
    int m() {
        return -1;
    }
			
    void p() {
        int k = m();

        k = ((k == 0) ? new b2() : new c2()).m();
    } 
	
}


class c2 extends b2 {
	
    int m() {
        return 0;
    }
}

