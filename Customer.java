/**
 * Customer
 */
public class Customer {

    protected int customerId;
    protected String username;
    protected String password;
    protected String mobileNo;



    public Customer() {
    }

    public Customer(int customerId, String username, String password, String mobileNo) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.mobileNo = mobileNo;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    
    @Override
    public String toString() {
        return "{" +
            " customerId='" + getCustomerId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            "}";
    }
    

}