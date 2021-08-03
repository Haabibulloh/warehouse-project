package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
   // boolean existsByNameAndCategoryId(String name, Integer category_id);
}
