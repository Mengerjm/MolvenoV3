package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudIngredientRepository extends CrudRepository<Ingredient,Long> {
}
