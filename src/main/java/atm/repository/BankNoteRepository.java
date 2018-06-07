package atm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import atm.entity.BankNote;

@Repository
//@RepositoryRestResource
public interface BankNoteRepository extends CrudRepository<BankNote, Long> {

}
