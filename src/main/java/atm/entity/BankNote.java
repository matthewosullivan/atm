package atm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BankNote implements Comparable<BankNote> {
	
	@Id
	@GeneratedValue
	private Long id;
	private int denomination;
	
	public BankNote() {}
	
	public BankNote(int denomination) {
		this.denomination = denomination;
	}
	
	public Long getId() {
		return id;
	}
	
	public int getDenomination() {
		return denomination;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}

	@Override
	public int compareTo(BankNote o) {
		if (denomination > o.denomination) {
			return -1;
		} else if (denomination > o.denomination) {
			return 1;
		}
		return 0;
	}
	
	
}
