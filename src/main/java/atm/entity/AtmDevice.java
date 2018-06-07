package atm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AtmDevice {
	
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "atmDevice")
	private List<BankNoteQty> bankNoteQuantities = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public List<BankNoteQty> getBankNoteQuantities() {
		return bankNoteQuantities;
	}

	public void setBankNoteQuantities(List<BankNoteQty> bankNoteQuantities) {
		this.bankNoteQuantities = bankNoteQuantities;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
