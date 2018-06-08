package atm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BankNoteQty implements Comparable<BankNoteQty> {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private BankNote bankNote;
	private int quantity;
	
	@ManyToOne
	private AtmDevice atmDevice;
	
	public BankNoteQty() {}
	
	public BankNoteQty(BankNote bankNote, int quantity, AtmDevice atmDevice) {
		this.bankNote = bankNote;
		this.quantity = quantity;
		this.atmDevice = atmDevice;
	}

	public Long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BankNote getBankNote() {
		return bankNote;
	}

	@Override
	public int compareTo(BankNoteQty o) {
		return bankNote.compareTo(o.bankNote);
	}
	
	public void increment() {
		this.quantity++;
	}
	
	public void decrement() {
		this.quantity--;
	}

	
}
