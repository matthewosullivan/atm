package atm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import atm.entity.BankNoteQty;

@Repository
public interface BankNoteQtyRepository extends CrudRepository<BankNoteQty, Long>{

}
