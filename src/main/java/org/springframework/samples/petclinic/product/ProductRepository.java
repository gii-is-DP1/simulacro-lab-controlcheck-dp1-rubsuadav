package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();
    
	@Query("SELECT p FROM ProductType p")
    List<ProductType> findAllProductTypes();
	
    Product findByName(String name);
    
    @Query("SELECT p FROM ProductType p WHERE p.name = ?1")
    ProductType finByName(String name);
    
    @Query("SELECT p FROM Product p WHERE p.price < :coste")
    List<Product> findByPriceLessThan(double coste);
}
