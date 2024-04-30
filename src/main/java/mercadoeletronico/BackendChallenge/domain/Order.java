package mercadoeletronico.BackendChallenge.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalPrice(){
        double price = 0 ;
        for(OrderItem orderItem : items){
            price += orderItem.getItemTotalPrice();
        }
        return price;
    }

    public int getTotalItemsQuantity(){
        int quantity = 0 ;
        for(OrderItem orderItem : items){
            quantity += orderItem.getQuantity();
        }
        return quantity;
    }
}
