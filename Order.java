import java.util.HashMap;

public class Order {
    protected long orderId;
    protected int customerId;
    protected String orderTime;
    
    protected HashMap<Integer,Integer> details =new HashMap<>();


    public Order() {
    }

    public Order(long orderId,int customerId, String orderTime, HashMap<Integer,Integer> details) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.details = details;
    }


    public long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

   

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public HashMap<Integer,Integer> getDetails() {
        return this.details;
    }

    public void setDetails(HashMap<Integer,Integer> details) {
        this.details = details;
    }

    

    @Override
    public String toString() {
        return "{" +
            " customerId='" + getCustomerId() + "'" +
            ", orderTime='" + getOrderTime() + "'" +
            ", details='" + getDetails() + "'" +
            "}";
    }


}
