package atm;

import java.util.Arrays;

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
		
		bankNoteQtyRepository.save(new BankNoteQty($20, 5, atmDevice));
		bankNoteQtyRepository.save(new BankNoteQty($50, 5, atmDevice));
		
	}

}
