package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudMenuRepository extends CrudRepository<Menu,Long> {
}
