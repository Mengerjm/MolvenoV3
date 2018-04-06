package nl.yacht.molvenov3.repository;

import nl.yacht.molvenov3.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudOrderRepository extends CrudRepository<Order,Long> {
}
