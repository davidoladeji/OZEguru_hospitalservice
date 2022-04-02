package com.davidoladeji.ozeguru.controller;

import com.davidoladeji.ozeguru.model.Staff;
import com.davidoladeji.ozeguru.security.AuthenticationProvider;
import com.davidoladeji.ozeguru.service.StaffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//
//@AutoConfigureMockMvc(addFilters = false)
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@WebMvcTest(value = PublicController.class)
@EnableJpaRepositories
public class PublicControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private StaffService staffService;

    @MockBean
    AuthenticationProvider provider;

    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String timenow = sdf.format(timestamp);
    final String uuidx = UUID.randomUUID().toString().replace("-", "");

    String sampleStaffJson = "{\"name\" : \"Fadahunsi Kolawolek\", \"username\" : \"kolawolek\",\"password\" : \"password\"}";
    Staff mockStaff = new Staff(1l, "Fadahunsi Kolawolek", "kolawolek", "password", uuidx, timenow);



    @Test
    public void createStaff() throws Exception {
        Staff staff = Staff.builder()
                .id(1L)
                .name("David Oladeji")
                .username("daveola")
                .password("password")
                .uuid(uuidx)
                .registration_date(timenow)
                .build();

        Mockito.when(staffService.addStaff(Mockito
                .any(Staff.class))).thenReturn(staff);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/public/staff/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(staff));

        mockMvc.perform(mockRequest).andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("David Oladeji")))
                .andExpect(jsonPath("$.username", is("daveola")))
                .andExpect(jsonPath("$.password", is("password")))
                .andExpect(jsonPath("$.uuid", is(uuidx)))
                .andExpect(jsonPath("$.registration_date", is(timenow)));
    }

    @Test
    public void addStaff() throws Exception {
        Mockito.when(
                staffService.addStaff(Mockito
                        .any(Staff.class))).thenReturn(mockStaff);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                        "/api/v1/public/staff/add")
                .accept(MediaType.APPLICATION_JSON).content(sampleStaffJson)
                .contentType(MediaType.APPLICATION_JSON);

        // mockMvc.perform(requestBuilder).andDo(print());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        String expectedresponse = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Fadahunsi Kolawolek\",\n" +
                "    \"username\": \"kolawolek\",\n" +
                "    \"password\": \"password\",\n" +
                "    \"uuid\": \"" + uuidx + "\",\n" +
                "    \"registration_date\": \"" + timenow + "\"\n" +
                "}";

       // System.out.println("expected string: " + expectedresponse);
       // System.out.println("response :" + response.getContentAsString());
        JSONAssert.assertEquals(expectedresponse, response.getContentAsString(), false);

    }




}