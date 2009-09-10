/*
 * Copyrights (c) 2005 Ramnivas Laddad and New Aspects of Security. All rights reserved.
 */
package billing;

/**
 * @author Ramnivas Laddad (ramnivas@aspectivity.com)
 */
public class User {
	private String name;
    private Account account;
	
	public User(String name) {
		this.name = name;
        this.account = new Account();
	}
	
	public String toString() {
		return "User: " + this.name;
	}
    
    public Account getAccount() {
        return account;
    }
}
