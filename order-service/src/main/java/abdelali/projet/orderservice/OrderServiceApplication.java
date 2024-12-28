package abdelali.projet.orderservice;

import abdelali.projet.orderservice.entities.Order;
import abdelali.projet.orderservice.entities.ProductItem;
import abdelali.projet.orderservice.entities.enums.OrderState;
import abdelali.projet.orderservice.repositories.OrderRepository;
import abdelali.projet.orderservice.repositories.ProductItemRepository;
import abdelali.projet.orderservice.restClients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            InventoryRestClient inventoryRestClient,
            OrderRepository orderRepository,
            ProductItemRepository productItemRepository
    ) {
        return args -> {
            List<String> allProducts = List.of("ComputerID", "PrinterID", "SmartphoneID", "TabletID");

            for (int i = 0; i < 10; i++) {
                // Créer un nouvel Order
                Order savedOrder = new Order();
                savedOrder.setDate(LocalDate.now());
                savedOrder.setState(Math.random() > 0.5 ? OrderState.CONFIRMED : OrderState.CANCELLED);

                // Enregistrer l'Order
                savedOrder = orderRepository.save(savedOrder);

                // Ajouter des ProductItems à cet Order
                for (String product : allProducts) {
                    ProductItem productItem = new ProductItem();
                    productItem.setProductId(product);
                    productItem.setPrice(Math.random() * 500);
                    productItem.setQuantity((int) (1 + Math.random() * 100));
                    productItem.setOrder(savedOrder);

                    // Enregistrer dans le repository
                    productItemRepository.save(productItem);
                }
            }
        };
    }
}
