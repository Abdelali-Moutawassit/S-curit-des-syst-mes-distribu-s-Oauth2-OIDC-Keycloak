package abdelali.projet.orderservice.repositories;

import abdelali.projet.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
