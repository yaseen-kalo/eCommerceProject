package Pojo.OrdersPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRoot {

    private List<String> orders = new ArrayList<String>();
    private List<String> productOrderId = new ArrayList<String>();
    private String message;

    public List<String> getOrders() {
        return orders;
    }
    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    public List<String> getProductOrderId() {
        return productOrderId;
    }
    public void setProductOrderId(List<String> productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
