package abdelali.projet.orderservice.entities;

import abdelali.projet.orderservice.entities.enums.OrderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public Order() {
    }

    // Constructeur avec paramètres
    public Order(LocalDate date, OrderState state) {
        this.date = date;
        this.state = state;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }



    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    // Méthode pour ajouter un ProductItem à la liste
    public void addProductItem(ProductItem productItem) {
        this.productItems.add(productItem);
        productItem.setOrder(this);
    }

}
