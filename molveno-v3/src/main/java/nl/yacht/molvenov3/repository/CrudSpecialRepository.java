package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Special;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudSpecialRepository extends CrudRepository<Special, Long> {
}
