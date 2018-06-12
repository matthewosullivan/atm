package atm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import atm.entity.AtmDevice;
import atm.entity.BankNote;
import atm.entity.BankNoteQty;
import atm.repository.AtmDeviceRepository;
import atm.repository.BankNoteQtyRepository;
import atm.repository.BankNoteRepository;


public class StartupRunner implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired 
	private BankNoteRepository bankNoteRepository;
	
	@Autowired 
	private AtmDeviceRepository atmDeviceRepository;
	
	@Autowired 
	private BankNoteQtyRepository bankNoteQtyRepository;
	
	@Override
	public void run(String... args) throws Exception {
		BankNote $20 = new BankNote(20);
		BankNote $50 = new BankNote(50);
		bankNoteRepository.save(Arrays.asList($20, $50));
		logger.info("Number of Bank Note Types: " + bankNoteRepository.count()); 
		
		AtmDevice atmDevice = new AtmDevice();
		atmDeviceRepository.save(atmDevice);
		
		bankNoteQtyRepository.save(new BankNoteQty($20, 8, atmDevice));
		bankNoteQtyRepository.save(new BankNoteQty($50, 3, atmDevice));
		
		
		
		/*
		List<BankNoteQty> bankNoteQtyList = StreamSupport.stream(bankNoteQtyRepository.findAll().spliterator(), false)
				.filter(bnq -> bnq.getQuantity() != 0).collect(Collectors.toList());
		
		// get list of bank notes
		List<Integer> list = bankNoteQtyList.stream().map(bnq -> {
			Stream<Integer> stream = Stream.generate(() -> bnq.getBankNote().getDenomination()).limit(bnq.getQuantity());
			return stream.toArray(Integer[]::new);
		})
				.flatMap(x -> Arrays.stream(x))
				.collect(Collectors.toList());
		
		*/
		
		
		
	}
	
	

}
