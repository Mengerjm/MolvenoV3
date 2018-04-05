package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DishRepository {
    private static long counter = 0;
    private Map<Long, Dish> dishes = new HashMap<>();

    public Iterable<Dish> findAll() {
        Iterable<Dish> result = this.dishes.values();
        return result;
    }

    public Dish findById(long id) {
        return this.dishes.get(id);
    }
}
