package com.mohammadalsalkini.accountManager.resource;

import com.mohammadalsalkini.accountManager.AccountManagerApplication;
import com.mohammadalsalkini.accountManager.domain.Account;
import com.mohammadalsalkini.accountManager.domain.Event;
import com.mohammadalsalkini.accountManager.repository.AccountRepository;
import com.mohammadalsalkini.accountManager.repository.EventRepository;
import com.mohammadalsalkini.accountManager.service.EventStatisticsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 16.07.2020 - 21:10
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {AccountManagerApplication.class})
@Transactional
public class EventResourceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    EventRepository eventRepository;

    @Mock
    EventStatisticsService eventStatisticsService;

    EventResource eventResource;


    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventResource = new EventResource(accountRepository, eventRepository, eventStatisticsService);
        mockMvc = MockMvcBuilders.standaloneSetup(eventResource).build();

    }

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @SuppressWarnings("unchecked")
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    @Test
    public void retrieveAllEvents() {


    }

//    @Test
//    public void should_create_an_event_and_return_created_status() throws Exception {
//        Event event = new Event(1, "My new account");
//
//        mockMvc.perform(post("/api/accounts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json(account)))
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", is("http://localhost/api/accounts/1")))
//                .andExpect(content().string(""))
//                .andDo(MockMvcResultHandlers.print());
//    }
}