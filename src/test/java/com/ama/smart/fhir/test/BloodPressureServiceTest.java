//package com.ama.smart.fhir.test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.time.LocalDate;
//import java.time.ZoneOffset;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Properties;
//
//import org.apache.http.HttpResponse;
//import org.hl7.fhir.dstu3.model.CodeableConcept;
//import org.hl7.fhir.dstu3.model.Coding;
//import org.hl7.fhir.dstu3.model.Dosage;
//import org.hl7.fhir.dstu3.model.MedicationStatement;
//import org.hl7.fhir.dstu3.model.MedicationStatement.MedicationStatementStatus;
//import org.hl7.fhir.dstu3.model.Patient;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import com.ama.smart.fhir.ErrorResponse;
//import com.ama.smart.fhir.context.ServerContext;
//import com.ama.smart.fhir.dto.BPAverageObservations;
//import com.ama.smart.fhir.dto.BPObservationsResult;
//import com.ama.smart.fhir.dto.CPTBillingAverageResult;
//import com.ama.smart.fhir.dto.ObservationsReviewHistory;
//import com.ama.smart.fhir.dto.RequestContext;
//import com.ama.smart.fhir.exceptions.UnAuthorizedRequestException;
//import com.ama.smart.fhir.rest.manager.RestClientManager;
//import com.ama.smart.fhir.service.BloodPressureService;
//import com.ama.smart.fhir.service.impl.BloodPressureServiceImpl;
//
//
///**
// * The Class BloodPressureServiceTest.
// */
//@TestPropertySource(locations = "classpath:config.properties")
//@RunWith(MockitoJUnitRunner.Silent.class)
////public class BloodPressureServiceTest {
////	
////	@Autowired
////	MockMvc mvc;
////	
////	@Mock
////	RestClientManager restClient;
////
////	/** The context. */
////	@Mock
////	private ServerContext context;
////
////	@Mock
////	private HttpResponse response;
////
////	/** The blood pressure service. */
////	@InjectMocks
////	private static BloodPressureService bloodPressureService = new BloodPressureServiceImpl();
////
////	@Mock
////	private  BloodPressureService bloodPressureService2 = new BloodPressureServiceImpl();
////
////	/** The smart is URL. */
////	private static String smartIsURL;
////	
////	/** The token. */
////	private static String token = null;
////
////	/**
////	 * Setup class data.
////	 */
////	@BeforeClass
////	public static void setupClassData() {
////
////		try {
////			InputStream inputStream = new BloodPressureServiceTest().getClass().getClassLoader()
////					.getResourceAsStream("config.properties");
////			Properties p = new Properties();
////			p.load(inputStream);
////
////			smartIsURL = p.getProperty("is.url");
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////
////		ReflectionTestUtils.setField(bloodPressureService, "ihmiBpBaseUrl",
////				"http://localhost:8080/ihm-fhir-bp-war/ihm");
////		ReflectionTestUtils.setField(bloodPressureService, "ihmiPatientBaseUrl",
////				"http://localhost:8080/ihm-fhir-bp-war/ihm");
////
////		ReflectionTestUtils.setField(bloodPressureService, "serverTimezone", "Asia/Kolkata");
////		// ReflectionTestUtils.setField(bloodPressureService, "fixedStartDate",
////		// "2019-06-01");
////		//
////		// ReflectionTestUtils.setField(bloodPressureService, "fixedEndDate",
////		// "2019-06-30");
////		ReflectionTestUtils.setField(bloodPressureService, "currentDate", "2019-06-30");
////
////		token = getToken();
////	}
////
////	/**
////	 * Test get patient observation.
////	 */
////	@Test
////	public void testGetPatientObservation() {
////
////		RequestContext requestContext = new RequestContext();
////
////		requestContext.setToken(token);
////
////		when(context.getRequestContext()).thenReturn(requestContext);
////		Patient patient = bloodPressureService.getPatient("ATC-3");
////		requestContext.setPatient(patient);
////
////		BPObservationsResult result = bloodPressureService.getPatientObservations("ATC-03", "127.0.0.0", "2019-06-02",
////				"2019-07-01");
////
////		Assert.assertNotNull(result);
////		assertThat(result.getObservations().size() > 0);
////	}
////
////	/**
////	 * Test get cpt billing details.
////	 */
////	@Test
////	public void testGetCptBillingDetails() {
////
////		RequestContext requestContext = new RequestContext();
////
////		requestContext.setToken(token);
////
////		when(context.getRequestContext()).thenReturn(requestContext);
////		Patient patient = bloodPressureService.getPatient("ATC-03");
////		requestContext.setPatient(patient);
////
////		CPTBillingAverageResult result = bloodPressureService.getCptBillingDetails("ATC-3", "2019-06-02", "2019-07-01");
////
////		Assert.assertNotNull(result);
////
////		assertThat(result.getCountOfObservationReviewed() > 0);
////	}
////
////	/**
////	 * Test Get RxEvents for a patient MRN
////	 */
////
////	 @Test
////	 public void testGetRxEvents() {
////	 List<MedicationStatement> medRx = new ArrayList<MedicationStatement>();
//////	 List<RxEvent> rxEvents = new ArrayList<RxEvent>();
//////	 RxEvent rxEvent3 = new RxEvent("lisinopril 10 mg tablet","10mg daily",
//////	 "2019-04-11",1554921000000l,"active");
////	 
////	 MedicationStatement rx = new MedicationStatement();
////	 rx.setStatus(MedicationStatementStatus.ACTIVE);
////	 
////	 List<Dosage> dosages = new ArrayList<Dosage>();
////	 Dosage dosage = new Dosage();	
////	 dosage.setText("10mg daily");
////	 dosages.add(dosage);
////	 rx.setDosage(dosages);
////	 
////	 CodeableConcept codeableConcept = new CodeableConcept();
////	 List<Coding> codings = new ArrayList<Coding>();
////	 Coding coding =  new Coding();
////	 coding.setCode("314076");
////	 coding.setDisplay("lisinopril 10 mg tablet");
////	 codings.add(coding);
////	 
////	 codeableConcept.setCoding(codings);
////	 
////	 rx.setMedication(codeableConcept);
////	 medRx.add(rx);
////	
////	 Mockito.when( bloodPressureService2.getRxEventsFromEHR(Mockito.anyString(), Mockito.anyString()))
////				.thenReturn(medRx);
////	
////	 assertThat( bloodPressureService2.getRxEvents(Mockito.anyString(), Mockito.anyString()).size() > 0);
////	 }
////	 
////	 
////	 @Test
////	 public void testGetActiveRx() {
////	 List<MedicationStatement> medRx = new ArrayList<MedicationStatement>();
////	 
////	 MedicationStatement rx = new MedicationStatement();
////	 rx.setStatus(MedicationStatementStatus.ACTIVE);
////	 
////	 List<Dosage> dosages = new ArrayList<Dosage>();
////	 Dosage dosage = new Dosage();	
////	 dosage.setText("10mg daily");
////	 dosages.add(dosage);
////	 rx.setDosage(dosages);
////	 
////	 CodeableConcept codeableConcept = new CodeableConcept();
////	 List<Coding> codings = new ArrayList<Coding>();
////	 Coding coding =  new Coding();
////	 coding.setCode("314076");
////	 coding.setDisplay("lisinopril 10 mg tablet");
////	 codings.add(coding);
////	 
////	 codeableConcept.setCoding(codings);
////	 
////	 rx.setMedication(codeableConcept);
////	 medRx.add(rx);
////	
////	 Mockito.when( bloodPressureService2.getRxEventsFromEHR(Mockito.anyString(), Mockito.anyString()))
////				.thenReturn(medRx);
////	
////	 assertThat( bloodPressureService2.getRxEvents(Mockito.anyString(), Mockito.anyString()).size() > 0);
////	 }
////	 
////	 
////	/**
////	 * Gets the token.
////	 *
////	 * @return the token
////	 */
////	private static String getToken() {
////		ResponseEntity<TokenModel> response = null;
////
////		String strUrl = smartIsURL;
////		// String strUrl = "http://localhost:8080/smart-is/oauth/token";
////		RestTemplate restClient = new RestTemplate();
////		HttpHeaders headers = new HttpHeaders();
////		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
////		headers.add("Authorization", "Basic bW9iaWxlOnBpbg==");
////
////		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
////
////		multiValueMap.add("grant_type", "password");
////		multiValueMap.add("username", "himanshu.dixit@rsystems.com");
////		multiValueMap.add("password", "smbp@123");
////
////		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
////				multiValueMap, headers);
////		try {
////			response = restClient.postForEntity(strUrl, requestEntity, TokenModel.class);
////		} catch (Exception e) {
////			if (response == null) {
////				throw new UnAuthorizedRequestException(ErrorResponse.UNAUTHORIZED.getHttpStatus(),
////						ErrorResponse.UNAUTHORIZED.getCode(), "unauthorized.user", null);
////			}
////			throw new UnAuthorizedRequestException(ErrorResponse.UNAUTHORIZED.getHttpStatus(),
////					ErrorResponse.UNAUTHORIZED.getCode(), "unauthorized.user", null);
////		}
////		return response.getBody().getAccess_token();
////	}
////
////	@Test
////	public void testMetProceduralCriteriaForAvgObservation() {
////		BPAverageObservations avgObs = new BPAverageObservations();
////		avgObs.setIhmiAverage("Some value");
////		avgObs.setSimpleAverage("Some value");
////		avgObs.setValidDevice("Y");
////		bloodPressureService.setCriteriaForAvgObservation(avgObs);
////		
////		assertTrue(avgObs.getCriteriaMet().equals("Met Procedural Criteria"));
////		
////	}
////	
////	@Test
////	public void testMetTimingCriteriaForAvgObservation() {
////		BPAverageObservations avgObs = new BPAverageObservations();
////		avgObs.setIhmiAverage("N/A");
////		avgObs.setSimpleAverage("Some Value");
////		avgObs.setValidDevice("N");
////		bloodPressureService.setCriteriaForAvgObservation(avgObs);
////		
////		assertTrue(avgObs.getCriteriaMet().equals("Met Timing Criteria"));
////		
////	}
////	
////	@Test
////	public void testMetClinicalCriteriaForAvgObservation() {
////		BPAverageObservations avgObs = new BPAverageObservations();
////		avgObs.setIhmiAverage("Some Value");
////		avgObs.setSimpleAverage("Some Value");
////		avgObs.setValidDevice("N");
////		
////		bloodPressureService.setCriteriaForAvgObservation(avgObs);
////		
////		assertTrue(avgObs.getCriteriaMet().equals("Met Clinical Profile"));
////		
////	}
////	
////	@Test
////	public void testInadequateCriteriaForAvgObservation() {
////		BPAverageObservations avgObs = new BPAverageObservations();
////		avgObs.setIhmiAverage("N/A");
////		avgObs.setSimpleAverage("N/A");
////		avgObs.setValidDevice("N");
////		bloodPressureService.setCriteriaForAvgObservation(avgObs);
////		assertTrue(avgObs.getCriteriaMet().equals("Inadequate data"));
////		
////	}
////	
////	@Test
////	 public void testSaveObservationReviewStatus() {
////	ObservationsReviewHistory reviewedStatus = new ObservationsReviewHistory("2019-06-01", "2019-06-30", 0, "Y", "801", null, null);
////	
////	
////	 Mockito.when( bloodPressureService2.saveObservationsReviewStatus(reviewedStatus))
////				.thenReturn(reviewedStatus);
////	 
////	reviewedStatus.setLastReviewDate("2019-06-30");
////	
////	 assertEquals(reviewedStatus.getLastReviewDate(), "2019-06-30");
////	 }
////
//}
