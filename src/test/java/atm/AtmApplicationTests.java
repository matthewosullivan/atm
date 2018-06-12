package atm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import atm.entity.AtmDevice;
import atm.entity.BankNote;
import atm.entity.BankNoteQty;
import atm.entity.Withdraw;
import atm.repository.AtmDeviceRepository;
import atm.repository.BankNoteQtyRepository;
import atm.repository.BankNoteRepository;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtmApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	private BankNoteQtyRepository bankNoteQtyRepository;

	@Autowired
	private BankNoteRepository bankNoteRepository;

	@Autowired
	private AtmDeviceRepository atmDeviceRepository;

	@Before
	public void setupMockMvc() {
		mockMvc = webAppContextSetup(context).build();

		bankNoteQtyRepository.deleteAll();
		bankNoteRepository.deleteAll();

		BankNote $20 = new BankNote(20);
		BankNote $50 = new BankNote(50);
		bankNoteRepository.save(Arrays.asList($20, $50));

		AtmDevice atmDevice = new AtmDevice();
		atmDeviceRepository.save(atmDevice);

		bankNoteQtyRepository.save(new BankNoteQty($20, 8, atmDevice));
		bankNoteQtyRepository.save(new BankNoteQty($50, 3, atmDevice));
	}

	@Test
	public void withdraw$50() throws Exception {
		String withdraw = "{\"amount\" : \"50\"}";
		mockMvc.perform(post("/atm/withdraw").content(withdraw).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.bankNotesDispensed.[0].denomination").value("50"));

		mockMvc.perform(get("/atm/")).andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].bankNote.denomination").value("20"))
		.andExpect(jsonPath("$.[0].quantity").value("8"))
		.andExpect(jsonPath("$.[1].bankNote.denomination").value("50"))
		.andExpect(jsonPath("$.[1].quantity").value("2"));
	}
	
	@Test
	public void withdraw$110() throws Exception {
		String withdraw = "{\"amount\" : \"110\"}";
		mockMvc.perform(post("/atm/withdraw").content(withdraw).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.bankNotesDispensed.[0].denomination").value("20"))
		.andExpect(jsonPath("$.bankNotesDispensed.[1].denomination").value("20"))
		.andExpect(jsonPath("$.bankNotesDispensed.[2].denomination").value("20"))
		.andExpect(jsonPath("$.bankNotesDispensed.[3].denomination").value("50"));

		mockMvc.perform(get("/atm/")).andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].bankNote.denomination").value("20"))
		.andExpect(jsonPath("$.[0].quantity").value("5"))
		.andExpect(jsonPath("$.[1].bankNote.denomination").value("50"))
		.andExpect(jsonPath("$.[1].quantity").value("2"));
	}
	
	// TODO implement tests, use a data set of withdrawal amounts and expected bank note combinations 
	// and iterate over 
}
