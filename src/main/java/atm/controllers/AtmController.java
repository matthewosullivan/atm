package atm.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import atm.entity.BankNote;
import atm.entity.BankNoteQty;
import atm.entity.Receipt;
import atm.entity.RequestStatus;
import atm.entity.Withdraw;
import atm.repository.BankNoteQtyRepository;
import atm.repository.BankNoteRepository;

@RestController
@RequestMapping("/atm")
public class AtmController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private BankNoteRepository bankNoteRepository;

	@Autowired
	private BankNoteQtyRepository bankNoteQtyRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<BankNoteQty> getAllBooks() {
		return bankNoteQtyRepository.findAll();
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public Receipt withdraw() {
		int runningAmt = 110;
		
		List<BankNoteQty> bankNoteQtyList = StreamSupport.stream(bankNoteQtyRepository.findAll().spliterator(), false)
				.filter(bnq -> bnq.getQuantity() != 0).collect(Collectors.toList());
		
		// get list of bank notes
		bankNoteQtyList.stream().map(bnq -> {
			int[] notes = new int[bnq.getQuantity()];
			Arrays.setAll(notes, () -> bnq.getBankNote().getDenomination());
			return notes;
		});
		
		
		return null;
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public Receipt withdraw(@RequestBody Withdraw withdraw) {
		
		int runningAmt = withdraw.getAmount();
		
		List<BankNoteQty> bankNoteQtyList = StreamSupport.stream(bankNoteQtyRepository.findAll().spliterator(), false)
		.filter(bnq -> bnq.getQuantity() != 0).collect(Collectors.toList());
		
		List<Integer> denoms = bankNoteQtyList.stream().map(BankNoteQty::getBankNote).map(BankNote::getDenomination).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		
		
		
		// get array of banknotes
		
		
		
		List<BankNote> dispense = new ArrayList<>();
		
		
		
		
		for (BankNoteQty bnq : bankNoteQtyList) {
			
			// is remainder divisble by any of the available quantities???
			while (bnq.getQuantity() > 0 && runningAmt > 0) {
				int quotient = runningAmt / bnq.getBankNote().getDenomination(); //2
				int remainder = runningAmt % bnq.getBankNote().getDenomination(); //2
				
				System.out.println("=====");
				System.out.println(quotient);
				System.out.println(remainder);
				
				if (remainder == 0 || (quotient > 0 && !aMethod(denoms, bnq, remainder) && !aMethod(denoms, bnq, runningAmt - bnq.getBankNote().getDenomination()))) {
					dispense.add(bnq.getBankNote());
					bnq.decrement();
					runningAmt -= bnq.getBankNote().getDenomination();
				} else {
					break;
				}
			}
			
			
			
			/*
			while (runningAmt > 0 && runningAmt - bnq.getBankNote().getDenomination() >= 0 && bnq.getQuantity() > 0 && aMethod(denoms, bnq, runningAmt)) {
				
				dispense.add(bnq.getBankNote());
				bnq.decrement();
			}
			*/
		}
		
		if (runningAmt == 0 && dispense.stream().mapToInt(bn -> bn.getDenomination()).sum() == withdraw.getAmount()) {
			Receipt receipt = new Receipt(RequestStatus.ACCEPTED);
			receipt.setBankNotesDispensed(dispense);
			System.out.println("return receipt");
			// TODO save to the database update the notes qty
			return receipt;
		}
			
		return new Receipt(RequestStatus.REJECTED);
	}
	
	
	
	private boolean aMethod(List<Integer> denoms, BankNoteQty bnq, int remainder) {
		//System.out.println("Running Amt: " + remainder);
		if (remainder == 0) return false;
		
		List<Integer> denomsToCheck = denoms.stream().filter(d -> d <= bnq.getBankNote().getDenomination()).collect(Collectors.toList());
		for (int denom : denomsToCheck) {
			System.out.println(remainder + " % " + denom);
			if (remainder % denom == 0) return true;
		}
		
		//if (remainder / bnq.getBankNote().getDenomination() > 0) {
		//	return true;
		//}
		
		return false;
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public Withdraw getWithdraw() {
		return new Withdraw(20);
	}
}
