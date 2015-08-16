package customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CustomerControllerTest {

    private MockMvc mockMvc;

    private List<Customer> customerList = new ArrayList<>();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.customerList.add(customerRepository.save(new Customer("Kevin C1", "Add1", "0221111111")));
    }

    @Test
    public void readSingleCustomer() throws Exception {
        mockMvc.perform(get("/customer/" + this.customerList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Kevin C1")))
        ;
    }

    @Test
    public void createCustomer() throws Exception {
        String customerJson = "{\"name\" : \"Kevin\",  \"address\" : \"Browns Bay\", \"phone\" : \"0222222222\" }";
        this.mockMvc.perform(post("/customer")
                .content(customerJson))
                .andExpect(status().isCreated());
    }
}