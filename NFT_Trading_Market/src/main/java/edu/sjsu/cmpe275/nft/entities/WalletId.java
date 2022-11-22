public class WalletId implements Serializable {
    
    private int user;
    private String cryptocurrency;

    public int getUser( ) {
        return user;
    }

    public void setUser( int userId ) {
        this.user = userId;
    }

    public String getSymbol( ) {
        return cryptocurrency;
    }

    public void setUser( String symbol ) {
        this.cryptocurrency = symbol;
    }

    // TO-DO equals() and hashCode()
}