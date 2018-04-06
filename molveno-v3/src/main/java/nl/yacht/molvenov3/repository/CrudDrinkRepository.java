package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Drink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudDrinkRepository extends CrudRepository<Drink, Long> {
}
