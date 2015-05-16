package org.vizun.auth;

/**
 * Created by River on 5/16/2015.
 */
public class Token {
    
    private String token;
    
    public Token(String token) {
        if(token.length() != 15) {
            throw new RuntimeException("The Authentication token must be 15 characters");
        }
        
        this.token = token;
    }

    /**
     * Refresh a new auth token from the master server, and purge the current token.* 
     */
    public void refresh() {
        this.purge();
        //TODO request a new auth token from the master server
    }

    /**
     * Purge the current authentication token and revoke the token from the master server* 
     */
    public void purge() {
        //TODO purge the existing auth token from the master server
    }

    /**
     * Protected method to return the current authentication token* 
     * @return Auth Token
     */
    protected String getToken() {
        return this.token;
    }

    /**
     * Private method to set a new Token for Authentication* 
     * @param newToken
     */
    private void setToken(String newToken) {
        this.token = newToken;
    }
}
