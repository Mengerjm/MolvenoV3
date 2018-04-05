package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CrudTableRepository extends CrudRepository<Table,Long> {

}
