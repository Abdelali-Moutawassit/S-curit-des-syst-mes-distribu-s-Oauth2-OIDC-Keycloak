package abdelali.projet.inentoryservice.repository;

import abdelali.projet.inentoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
