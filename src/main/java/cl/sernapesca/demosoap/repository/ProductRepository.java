package cl.sernapesca.demosoap.repository;

import cl.sernapesca.demosoap.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    ProductModel findByName(String name);
}