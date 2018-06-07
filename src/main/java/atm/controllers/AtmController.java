package atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import atm.entity.BankNoteQty;
import atm.repository.BankNoteQtyRepository;
import atm.repository.BankNoteRepository;

@RestController
@RequestMapping("/atm")
public class AtmController {

	@Autowired
	private BankNoteRepository bankNoteRepository;

	@Autowired
	private BankNoteQtyRepository bankNoteQtyRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<BankNoteQty> getAllBooks() {
		return bankNoteQtyRepository.findAll();
	}
}
