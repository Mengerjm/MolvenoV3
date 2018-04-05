package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudDishRepository extends CrudRepository<Dish, Long> {
}
