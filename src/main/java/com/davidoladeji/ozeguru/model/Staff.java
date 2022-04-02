package com.davidoladeji.ozeguru.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "staff")
public class Staff implements Serializable {


    public Staff(long id, String name, String username, String password) {
        id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_staff_sequence_name")
    private long id;


    @NotEmpty(message = "The name is required.")
    @Column(name = "name", unique = true)
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "The username is required.")
    @Column(name = "username", unique = true)
    @JsonProperty("username")
    private String username;

    @NotEmpty(message = "The password is required.")
    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    @Column(name = "uuid")
    @JsonProperty("uuid")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;

    @Column(name = "registration_date", updatable = false)
    @JsonProperty("registration_date")
    private String registration_date;

    @PrePersist
    protected void onCreate() {
        // set the uid
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        setUuid(uuid);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timenow = sdf.format(timestamp);
        setRegistration_date(timenow);

    }







}

/*

package com.davidoladeji.ozeguru.controller;

        import com.davidoladeji.ozeguru.model.Staff;
        import com.davidoladeji.ozeguru.repository.PatientRepository;
        import com.davidoladeji.ozeguru.repository.StaffRepository;
        import com.davidoladeji.ozeguru.security.AuthenticationProvider;
        import com.davidoladeji.ozeguru.service.StaffService;
        import com.davidoladeji.ozeguru.service.implementation.StaffServiceImpl;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.Mockito;
        import org.mockito.MockitoAnnotations;
        import org.mockito.junit.MockitoJUnitRunner;
        import org.skyscreamer.jsonassert.JSONAssert;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.boot.web.server.LocalServerPort;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
        import org.springframework.http.*;
        import org.springframework.mock.web.MockHttpServletResponse;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.MvcResult;
        import org.springframework.test.web.servlet.RequestBuilder;
        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
        import org.springframework.test.web.servlet.setup.MockMvcBuilders;

        import java.sql.Timestamp;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.Optional;

        import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.BDDMockito.given;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
        import static org.hamcrest.Matchers.*;
//
//@AutoConfigureMockMvc(addFilters = false)
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@WebMvcTest(value = PublicController.class)
@EnableJpaRepositories
public class PublicControllerTest  {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Mock
    private StaffRepository staffRepository;


    @MockBean
    private StaffService staffService;

    @MockBean
    AuthenticationProvider provider;

    // @MockBean
    //private StaffServiceImpl staffServiceImpl;

    private Staff staff;

    @Before
    public void setup(){
        // MockitoAnnotations.initMocks(this);
        staff = Staff.builder()
                .id(1L)
                .name("David Oladeji")
                .username("daveola")
                .password("password")
                .build();
        // mockMvc = MockMvcBuilders.standaloneSetup(new PublicController()).build();

    }


    // Staff RECORD_1 = new Staff(1l, "Raven Heights", "ravenh", "ravenh");
    // Staff RECORD_2 = new Staff(2l, "David Olanipekun", "davido", "davido");
    // Staff RECORD_3 = new Staff(3l, "Jane Doe", "janed", "janed");

    // List<Staff> staffList = new ArrayList<>();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @DisplayName("JUnit test for staff method")
    @Test
    public void createAndReturnStaff(){
        given(staffRepository.findByName(staff.getName()))
                .willReturn(Optional.empty());

        given(staffRepository.save(staff)).willReturn(staff);

        Staff savedEmployee = staffService.addStaff(staff);
        assertThat(savedEmployee).isNotNull();
    }

    Staff mockStaff = new Staff(1l, "Fadahunsi Kolawole", "kolawole", "password");
    String sampleStaffJson = "{\"id\": 11, \"name\" : \"Fadahunsi Kolawole\",\"username\" : \"kolawole\",\"password\" : \"password\",\"uuid\": \"a60bc0ae44614d82b08584e1d8590e50\",\"registration_date\": \"2022-04-01T13:55:42.569+00:00\"}";

    @Test
    public void addStaff() throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Staff mockStaff = new Staff(1l, "Fadahunsi Kolawole", "kolawole", "password", "a60bc0ae44614d82b08584e1d8590e50", (timestamp));

      */
/*  Mockito.when(
                staffService.addStaff(Mockito
                        .any(Staff.class))).thenReturn(mockStaff);*//*




        HttpEntity<Staff> entity = new HttpEntity<Staff>(mockStaff);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/public/staff/add"),
                HttpMethod.GET, entity, String.class);

        String expectedresponse = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Fadahunsi Kolawole\",\n" +
                "    \"username\": \"kolawole\",\n" +
                "    \"password\": \"password\",\n" +
                "    \"uuid\": \"a60bc0ae44614d82b08584e1d8590e50\",\n" +
                "    \"registration_date\": \"2022-04-01T13:55:42.569+00:00\"\n" +
                "}";
        JSONAssert.assertEquals(expectedresponse, response.getBody(), false);
*/
/*
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                        "/api/v1/public/staff/add")
                .accept(MediaType.APPLICATION_JSON).content(sampleStaffJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();*//*



        */
/*mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("Jane Doe")));*//*


        */
/*MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        System.out.println(response.getContentAsString());

        String expectedresponse = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Fadahunsi Kolawole\",\n" +
                "    \"username\": \"kolawole\",\n" +
                "    \"password\": \"password\",\n" +
                "    \"uuid\": \"a60bc0ae44614d82b08584e1d8590e50\",\n" +
                "    \"registration_date\": \"2022-04-01T13:55:42.569+00:00\"\n" +
                "}";
        JSONAssert.assertEquals(expectedresponse, response.getContentAsString(), false);*//*


    }

   */
/* @Test
    public void listStaff_success() throws Exception {

        List<Staff> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        Mockito.when(staffRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/public/staff/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("Jane Doe")));
    }

    @Test
    public void createStaff() throws Exception {
        Staff staff = Staff.builder()
                .name("David Oladeji")
                .username("daveola")
                .password("password")
                .build();

        Mockito.when(staffService.addStaff(staff)).thenReturn(staff);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/public/staff/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(staff));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("David Oladeji")));
    }
*//*


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}
*/