//package test.javidesoft.search.step;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.finsoft.petdirectory.application.petcard.api.dto.AddOwnersDTO;
//import com.finsoft.petdirectory.application.petcard.api.dto.CreatePetCardDTO;
//import com.finsoft.petdirectory.application.petcard.api.dto.OwnerDTO;
//import com.finsoft.petdirectory.application.petcard.api.dto.PetCardDTO;
//import com.finsoft.petdirectory.domain.petcard.external.PetCardPersistence;
//import com.finsoft.petdirectory.domain.user.domain.User;
//import com.finsoft.petdirectory.domain.user.external.UserPersistence;
//
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import io.cucumber.datatable.DataTable;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//public class PetCardSteps {
//
//    @Autowired
//    private PetCardPersistence persistence;
//
//    @Autowired
//    private UserPersistence userPersistence;
//
//    private ResponseEntity<PetCardDTO> response;
//
//    @LocalServerPort
//    private int port;
//    private CreatePetCardDTO createCardDTO;
//    private List<PetCardDTO> responseCards;
//    private List<ResponseEntity> responseEntities;
//    private List<ResponseEntity<PetCardDTO>> foundCards;
//    private List<PetCardDTO> toUpdateDtos;
//    private User userCreated;
//
//    @Before
//    public void setUp(Scenario scenario) {
//        persistence.clean();
//        System.out.println("------------------------------");
//        System.out.println("Starting - " + scenario.getName());
//        System.out.println("------------------------------");
//    }
//
//    @After
//    public void tearDown() {
//        persistence.clean();
//    }
//
//    @Given("^Tomas fill the required data to create a card$")
//    public void fillBasicDataToCreateACard() {
//        createCardDTO = CreatePetCardDTO.builder()
//                .name("some")
//                .type("cat")
//                .build();
//    }
//
//    @When("^Tomas keep the pet card$")
//    public void keepPetCard() {
//        createAPetCardRequest(createCardDTO);
//    }
//
//    @Then("^The kept data is returned with new id$")
//    public void answerCardCreated() {
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertNotNull(response.getBody().getId());
//        PetCardDTO expected = PetCardDTO.builder()
//                .name("some")
//                .type("cat")
//                .build();
//        assertTrue(new ReflectionEquals(expected, "id").matches(response.getBody()));
//    }
//
//    @Given("Tomas create cards")
//    public void createCards(DataTable dt) {
//        List<Map<String, String>> table = dt.asMaps(String.class, String.class);
//        List<CreatePetCardDTO> createDtos = extractCreateCardDtosFromTable(table);
//        responseEntities = createDtos.stream().map(dto -> createAPetCardRequest(dto)).collect(Collectors.toList());
//        responseCards = responseEntities.stream().map(ResponseEntity<PetCardDTO>::getBody).collect(Collectors.toList());
//    }
//
//    @Given("Tomas update cards")
//    public void updateCards(DataTable dt) {
//        List<Map<String, String>> table = dt.asMaps(String.class, String.class);
//        toUpdateDtos = extractPetCardDtosFromTable(table);
//        for(int index = 0; index < responseCards.size(); index++){
//            toUpdateDtos.get(index).setId(responseCards.get(index).getId());
//        }
//
//        responseEntities = toUpdateDtos.stream().map(dto -> updatePeRequest(dto)).collect(Collectors.toList());
//        responseCards = responseEntities.stream().map(ResponseEntity<PetCardDTO>::getBody).collect(Collectors.toList());
//
//    }
//
//
//    @And("Tomas update cards wich not exist")
//    public void tomasUpdateCardsWichNotExist(DataTable dt) {
//        List<Map<String, String>> table = dt.asMaps(String.class, String.class);
//        toUpdateDtos = extractPetCardDtosFromTable(table);
//        toUpdateDtos.stream().forEach(dto -> dto.setId("any"));
//        responseEntities = toUpdateDtos.stream().map(dto -> updatePeRequest(dto)).collect(Collectors.toList());
//        responseCards = responseEntities.stream().map(ResponseEntity<PetCardDTO>::getBody).collect(Collectors.toList());
//    }
//
//    @And("Tomas tries to find the cards created")
//    public void findCardsCreated() {
//        foundCards = responseCards.stream()
//                .map(dto -> getPetCardRequest(dto.getId()))
//                .collect(Collectors.toList());
//    }
//
//    @Then("^verify cards exists$")
//    public void verifyCardExists() {
//        responseCards.stream().forEach(createdCard -> {
//            Optional<ResponseEntity<PetCardDTO>> found = foundCards.stream().
//                    filter(response -> createdCard.getId().equals(response.getBody().getId()))
//                    .findAny();
//            assertTrue("Card created is not found", found.isPresent());
//            assertEquals("HttpStatus is not 200", HttpStatus.ACCEPTED, found.get().getStatusCode());
//            assertEquals("dto is not equals", createdCard, found.get().getBody());
//        });
//    }
//
//    @Then("^verify cards has been updated")
//    public void verifyUpdatedCards() {
//        responseCards.stream().forEach(updatedCard -> {
//            Optional<PetCardDTO> updatedDto = toUpdateDtos.stream().filter(dto -> dto.getId().equals(updatedCard.getId()))
//                    .findAny();
//            assertEquals("dto is not equals", updatedDto.get(), updatedCard);
//        });
//    }
//
//    private ResponseEntity<PetCardDTO> getPetCardRequest(String id) {
//        TestRestTemplate restCall = new TestRestTemplate();
//        return restCall.getForEntity("http://localhost:" + port + "/petcard/" + id, PetCardDTO.class);
//    }
//
//    private ResponseEntity<PetCardDTO> createAPetCardRequest(CreatePetCardDTO dto) {
//        TestRestTemplate restCall = new TestRestTemplate();
//        response = restCall.postForEntity("http://localhost:" + port + "/petcard", dto, PetCardDTO.class);
//        return response;
//    }
//
//    private ResponseEntity<PetCardDTO> updatePeRequest(PetCardDTO dto) {
//        TestRestTemplate restCall = new TestRestTemplate();
//        HttpEntity<PetCardDTO> httpEntity = new HttpEntity<>(dto);
//        response = restCall.exchange("http://localhost:" + port + "/petcard", HttpMethod.PUT, httpEntity, PetCardDTO.class);
//        return response;
//    }
//
//    private List<CreatePetCardDTO> extractCreateCardDtosFromTable(List<Map<String, String>> table) {
//        return table.stream()
//                .map(data -> CreatePetCardDTO.builder()
//                        .name(data.get("Name"))
//                        .type(data.get("type"))
//                        .build()
//                ).collect(Collectors.toList());
//    }
//
//
//    private List<PetCardDTO> extractPetCardDtosFromTable(List<Map<String, String>> table) {
//        return table.stream()
//                .map(data -> PetCardDTO.builder()
//                        .name(data.get("Name"))
//                        .type(data.get("type"))
//                        .build()
//                ).collect(Collectors.toList());
//    }
//
//    @And("Tomas find a id")
//    public void tomasFindAId(DataTable dt) {
//        List<Map<String, String>> maps = dt.asMaps(String.class, String.class);
//        String id = maps.get(0).get("id");
//        foundCards = Arrays.asList(getPetCardRequest(id));
//    }
//
//    @Then("verify cards does not exist")
//    public void verifyCardsDoesNotExist() {
//        foundCards.stream()
//                .forEach(foundResponse -> assertEquals("HttpStatus NOT_FOUND is not correct", HttpStatus.NOT_FOUND, foundResponse.getStatusCode()));
//    }
//
//    @Then("verify cards updated does not exist")
//    public void verifyCardsUpdatedDoesNotExist() {
//        responseEntities.stream()
//                .forEach(foundResponse -> assertEquals("HttpStatus NOT_FOUND is not correct", HttpStatus.NOT_FOUND, foundResponse.getStatusCode()));
//    }
//
//    @And("User is created")
//    public void userIsCreated() {
//        User user = new User("Juan");
//        userCreated = userPersistence.save(user);
//    }
//
//    @When("Owner is added")
//    public void ownerIsAdded() {
//        addOwnerToCard(responseCards.get(0), userCreated);
//    }
//
//    private void addOwnerToCard(PetCardDTO petCard, User user) {
//        AddOwnersDTO dto = AddOwnersDTO.builder()
//                .cardId(petCard.getId())
//                .usersId(Arrays.asList(user.getId()))
//                .build();
//        TestRestTemplate restCall = new TestRestTemplate();
//        HttpEntity<AddOwnersDTO> httpEntity = new HttpEntity<>(dto);
//        response = restCall.exchange("http://localhost:" + port + "/petcard/addOwner", HttpMethod.PUT, httpEntity, PetCardDTO.class);
//    }
//
//    @Then("card has the owner")
//    public void cardHasTheOwner() {
//        OwnerDTO owner = OwnerDTO.builder()
//                .id(userCreated.getId())
//                .name(userCreated.getUserName()).build();
//        assertEquals("Does not contains the same user", response.getBody().getOwners(), Arrays.asList(owner));
//    }
//}
