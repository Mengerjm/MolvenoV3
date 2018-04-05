package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Special;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SpecialRepository {
    private static long counter = 0;
    private Map<Long, Special> specials = new HashMap<>();

    public Iterable<Special> findAll() {
        Iterable<Special> result = this.specials.values();
        return result;
    }

    public Special findById(long id) {
        return this.specials.get(id);
    }
}
