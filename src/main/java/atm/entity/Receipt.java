package atm.entity;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
	private RequestStatus status;
	
	private List<BankNote> bankNotesDispensed = new ArrayList<>();
	
	public Receipt(RequestStatus status) {
		super();
		this.status = status;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public List<BankNote> getBankNotesDispensed() {
		return bankNotesDispensed;
	}

	public void setBankNotesDispensed(List<BankNote> bankNotesDispensed) {
		this.bankNotesDispensed = bankNotesDispensed;
	}
	
}
