package atm.entity;

public class Withdraw {
	private int amount;
	private RequestStatus requestStatus = RequestStatus.PENDING;
	
	public Withdraw() {
		// TODO Auto-generated constructor stub
	}
	
	public Withdraw(int amount) {
		super();
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public RequestStatus getRequestStatus() {
		return requestStatus;
	}
	
	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}
	
}