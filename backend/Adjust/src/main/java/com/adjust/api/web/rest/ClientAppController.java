package com.adjust.api.web.rest;

import com.adjust.api.domain.*;
import com.adjust.api.repository.*;
import com.adjust.api.security.SecurityUtils;
import com.adjust.api.security.jwt.TokenProvider;
import com.adjust.api.service.*;
import com.adjust.api.service.dto.*;
import com.adjust.api.service.mapper.*;
import com.adjust.api.web.rest.errors.BadRequestAlertException;
import com.adjust.api.web.rest.errors.InvalidPasswordException;
import com.adjust.api.web.rest.errors.LoginAlreadyUsedException;
import com.adjust.api.web.rest.vm.LoginVM;
import com.adjust.api.web.rest.vm.ManagedUserVM;
import com.adjust.api.web.websocket.dto.MessageDTO;
import io.github.jhipster.web.util.HeaderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/client/app")
public class ClientAppController {

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(AdjustClientResource.class);

    private static final String ENTITY_NAME = "adjustClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Value("${client.app.apiKey}")
    private String apiKey;

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final UserJWTController userJWTController;

    private final AdjustClientService adjustClientService;
    private final AdjustClientRepository adjustClientRepository;
    private final AdjustClientMapper adjustClientMapper;

    private final AdjustTutorialService adjustTutorialService;
    private final AdjustTutorialVideoService adjustTutorialVideoService;

    private final TutorialService tutorialService;
    private final TutorialVideoService tutorialVideoService;
    private final TutorialRepository tutorialRepository;
    private final TutorialMapper tutorialMapper;

    private final AdjustShopingItemService adjustShopingItemService;
    private final OrderService orderService;
    private final CartService cartService;
    private final ShopingItemService shopingItemService;

    private final AdjustTokensResource adjustTokensResource;
    private final AdjustTokensService adjustTokensService;

    private final SpecialistService specialistService;
    private final SpecialistMapper specialistMapper;

    private final AdjustProgramService adjustProgramService;
    private final ProgramDevelopmentService programDevelopmentService;
    private final BodyCompositionService bodyCompositionService;
    private final FitnessProgramService fitnessProgramService;
    private final NutritionProgramService nutritionProgramService;
    private final MealService mealService;
    private final AdjustNutritionRepository adjustNutritionRepository;
    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final MoveService moveService;

    private final AdjustProgramMapper adjustProgramMapper;
    private final ProgramDevelopmentMapper programDevelopmentMapper;
    private final BodyCompositionMapper bodyCompositionMapper;
    private final NutritionProgramMapper nutritionProgramMapper;
    private final FitnessProgramMapper fitnessProgramMapper;
    private final MealMapper mealMapper;
    private final NutritionMapper nutritionMapper;
    private final AdjustNutritionMapper adjustNutritionMapper;
    private final AdjustFoodMapper adjustFoodMapper;
    private final WorkoutMapper workoutMapper;
    private final ExerciseMapper exerciseMapper;
    private final MoveMapper moveMapper;

    private final AdjustProgramRepository adjustProgramRepository;
    private final AdjustFoodRepository adjustFoodRepository;
    private final ProgramDevelopmentRepository programDevelopmentRepository;
    private final BodyCompositionRepository bodyCompositionRepository;

    private final ConversationService conversationService;
    private final ChatMessageRepository chatMessageRepository;


    public ClientAppController(UserService userService, UserJWTController userJWTController, TokenProvider tokenProvider, AdjustClientService adjustClientService,
                               AdjustClientRepository adjustClientRepository, AdjustClientMapper adjustClientMapper,
                               AdjustTokensResource adjustTokensResource, AdjustTutorialService adjustTutorialService,
                               TutorialRepository tutorialRepository, AdjustTutorialVideoService adjustTutorialVideoService,
                               TutorialService tutorialService, TutorialVideoService tutorialVideoService, TutorialMapper tutorialMapper,
                               AdjustShopingItemService adjustShopingItemService, OrderService orderService,
                               CartService cartService, ShopingItemService shopingItemService, AdjustTokensService adjustTokensService,
                               SpecialistService specialistService, SpecialistMapper specialistMapper, AdjustProgramService adjustProgramService, ProgramDevelopmentService programDevelopmentService,
                               BodyCompositionService bodyCompositionService, FitnessProgramService fitnessProgramService, NutritionProgramService nutritionProgramService,
                               MealService mealService, AdjustNutritionRepository adjustNutritionRepository, WorkoutService workoutService, ExerciseService exerciseService, MoveService moveService,
                               ProgramDevelopmentMapper programDevelopmentMapper, BodyCompositionMapper bodyCompositionMapper, NutritionProgramMapper nutritionProgramMapper, FitnessProgramMapper fitnessProgramMapper, MealMapper mealMapper,
                               NutritionMapper nutritionMapper, AdjustNutritionMapper adjustNutritionMapper, AdjustFoodMapper adjustFoodMapper, WorkoutMapper workoutMapper, ExerciseMapper exerciseMapper, MoveMapper moveMapper,
                               AdjustProgramRepository adjustProgramRepository, AdjustProgramMapper adjustProgramMapper, AdjustFoodRepository adjustFoodRepository, ProgramDevelopmentRepository programDevelopmentRepository,
                               BodyCompositionRepository bodyCompositionRepository, ConversationService conversationService, ChatMessageRepository chatMessageRepository;) {
        this.userService = userService;
        this.userJWTController = userJWTController;
        this.tokenProvider = tokenProvider;
        this.adjustClientService = adjustClientService;
        this.adjustClientRepository = adjustClientRepository;
        this.adjustClientMapper = adjustClientMapper;
        this.adjustTutorialService = adjustTutorialService;
        this.adjustTutorialVideoService = adjustTutorialVideoService;
        this.tutorialRepository = tutorialRepository;
        this.tutorialService = tutorialService;
        this.tutorialVideoService = tutorialVideoService;
        this.tutorialMapper = tutorialMapper;
        this.adjustShopingItemService = adjustShopingItemService;
        this.orderService = orderService;
        this.cartService = cartService;
        this.shopingItemService = shopingItemService;
        this.adjustTokensResource = adjustTokensResource;
        this.adjustTokensService = adjustTokensService;
        this.specialistService = specialistService;
        this.specialistMapper = specialistMapper;
        this.adjustProgramService = adjustProgramService;
        this.programDevelopmentService = programDevelopmentService;
        this.bodyCompositionService = bodyCompositionService;
        this.fitnessProgramService = fitnessProgramService;
        this.nutritionProgramService = nutritionProgramService;
        this.mealService = mealService;
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.moveService = moveService;
        this.programDevelopmentMapper = programDevelopmentMapper;
        this.bodyCompositionMapper = bodyCompositionMapper;
        this.nutritionProgramMapper = nutritionProgramMapper;
        this.fitnessProgramMapper = fitnessProgramMapper;
        this.mealMapper = mealMapper;
        this.adjustNutritionRepository = adjustNutritionRepository;
        this.nutritionMapper = nutritionMapper;
        this.adjustNutritionMapper = adjustNutritionMapper;
        this.adjustFoodMapper = adjustFoodMapper;
        this.workoutMapper = workoutMapper;
        this.exerciseMapper = exerciseMapper;
        this.moveMapper = moveMapper;
        this.adjustProgramRepository = adjustProgramRepository;
        this.adjustProgramMapper = adjustProgramMapper;
        this.adjustFoodRepository = adjustFoodRepository;
        this.programDevelopmentRepository = programDevelopmentRepository;
        this.bodyCompositionRepository = bodyCompositionRepository;
        this.conversationService = conversationService;
        this.chatMessageRepository = chatMessageRepository;
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param managedUserVM the managed user View Model.
     * @throws com.adjust.api.web.rest.errors.InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
     * @throws com.adjust.api.web.rest.errors.EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException                                {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserJWTController.JWTToken> registerAccountByClientApp(@Valid @RequestBody ManagedUserVM managedUserVM, @RequestHeader("Authorization") String authorization) throws Exception {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }

        String clientApiKeyEncoded = authorization.substring(6);
        log.info("client api key encoded: {}", clientApiKeyEncoded);

        String apiKeyEncoded = new String(Base64.getEncoder().encode(apiKey.getBytes()), "UTF-8");
        log.info("backend api key encoded: {}", apiKeyEncoded);
        if (!clientApiKeyEncoded.equals(apiKeyEncoded)) {
            throw new Exception("request does not come from client app");
        }

        managedUserVM.setLangKey("fa");
        com.adjust.api.domain.User user = userService.registerUser(managedUserVM, managedUserVM.getPassword(), true, false);


        AdjustClientDTO adjustClientDTO = new AdjustClientDTO();
        adjustClientDTO.setUsername(user.getLogin());
        adjustClientDTO.setToken(0.0);
        adjustClientDTO.setScore(0.0);
        adjustClientService.save(adjustClientDTO);

        LoginVM loginVM = new LoginVM();
        loginVM.setUsername(user.getLogin());
        loginVM.setPassword(managedUserVM.getPassword());
        loginVM.setRememberMe(true);
        return userJWTController.authorize(loginVM);
    }


    /**
     * get adjust client for login
     *
     * @return
     */
    @GetMapping("/adjust-clients")
    public ResponseEntity<AdjustClientDTO> getAdjustClientByClientApp() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        return ResponseEntity.ok().header("charset", "UTF-8")
            .header("charset", "UTF-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustClientDTO.getId().toString()))
            .body(adjustClientDTO);
    }

    /**
     * {@code PUT  /adjust-clients} : Updates an existing adjustClient from client app.
     *
     * @param adjustClientDTO the adjustClientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustClientDTO,
     * or with status {@code 400 (Bad Request)} if the adjustClientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustClientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjust-clients")
    public ResponseEntity<AdjustClientDTO> updateAdjustClientByApp(@RequestBody AdjustClientDTO adjustClientDTO) throws URISyntaxException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTOUpdatee = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        adjustClientDTO.setScore(null);
        adjustClientDTO.setToken(null);
//        if (adjustClientDTO.getBirthDate() != null)
//            adjustClientDTO.setBirthDate(adjustClientDTO.getBirthDate().plusDays(1));
        AdjustClientDTO adjustClientDTOUpdated = (AdjustClientDTO) ClassUpdater.updateClass(adjustClientDTO, adjustClientDTOUpdatee);
        log.debug("REST request to update AdjustClient : {}", adjustClientDTOUpdated);
        if (adjustClientDTOUpdated.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjustClientDTO result = adjustClientService.save(adjustClientDTOUpdated);
        return ResponseEntity.ok().header("charset", "UTF-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustClientDTOUpdated.getId().toString()))
            .body(result);
    }

    /**
     * buy a video by client app
     *
     * @param videoId
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/buy-tutorial")
    public ResponseEntity<TutorialDTO> buyVideo(@RequestBody Long videoId, HttpServletResponse response) throws Exception {
        response.setHeader("charset", "utf-8");
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        AdjustTutorialDTO adjustTutorialDTO = adjustTutorialService.findOne(videoId).get();

        if (adjustClientDTO.getToken() < adjustTutorialDTO.getTokenPrice()) {
            ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("client does not have enough token");
        }

        List<TutorialDTO> tutorialDTOList = tutorialRepository.findTutorialsByClient(adjustClientRepository.findAdjustClientByUsername(userLogin).get()).stream().map(tutorialMapper::toDto).collect(Collectors.toList());

        boolean clientHasTutorial = tutorialDTOList.stream().filter((e) -> e.getVideoId() == adjustTutorialDTO.getVideoId()).collect(Collectors.toList()).iterator().hasNext();
        if (clientHasTutorial) {
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("client has the tutorial already");
        }

        AdjustTutorialVideoDTO adjustTutorialVideoDTO = adjustTutorialVideoService.findOne(adjustTutorialDTO.getVideoId()).get();

        TutorialVideoDTO tutorialVideoDTO = new TutorialVideoDTO();
        tutorialVideoDTO.setAdjustTutorialVideoId(adjustTutorialVideoDTO.getId());
        tutorialVideoDTO.setContent(adjustTutorialVideoDTO.getContent());
        tutorialVideoDTO.setContentContentType(adjustTutorialVideoDTO.getContentContentType());

        tutorialVideoDTO = tutorialVideoService.save(tutorialVideoDTO);

        TutorialDTO tutorialDTO = new TutorialDTO();
        tutorialDTO.setClientId(adjustClientDTO.getId());
        tutorialDTO.setDescription(adjustTutorialDTO.getDescription());
        tutorialDTO.setText(adjustTutorialDTO.getText());
        tutorialDTO.setThumbnail(adjustTutorialDTO.getThumbnail());
        tutorialDTO.setThumbnailContentType(adjustTutorialDTO.getThumbnailContentType());
        tutorialDTO.setTitle(adjustTutorialDTO.getTitle());
        tutorialDTO.setTokenPrice(adjustTutorialDTO.getTokenPrice());
        tutorialDTO.setAdjustTutorialId(adjustTutorialDTO.getId());
        tutorialDTO.setVideoId(tutorialVideoDTO.getId());
        tutorialDTO = tutorialService.save(tutorialDTO);


        adjustClientDTO.setToken(adjustClientDTO.getToken() - adjustTutorialDTO.getTokenPrice());
        adjustClientService.save(adjustClientDTO);

        return ResponseEntity.ok().header("charset", "utf-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjustClientDTO.getId().toString()))
            .body(tutorialDTO);
    }

    /**
     * get the list of tutorials owned by client
     *
     * @return
     */
    @GetMapping("/get-tutorials")
    public ResponseEntity<List<TutorialDTO>> getClientTutorials() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        List<TutorialDTO> tutorialDTOList = tutorialRepository.findTutorialsByClient(adjustClientRepository.findAdjustClientByUsername(userLogin).get()).stream().map(tutorialMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok().header("charset", "utf-8")
            .body(tutorialDTOList);
    }

    /**
     * broadcast tutorial video by videoId
     *
     * @param videoId
     * @param jwt
     * @return
     * @throws Exception
     */
    @GetMapping("/get-tutorial-video")
    public DeferredResult<ResponseEntity<ByteArrayResource>> getClientTutorialVideo(@RequestParam("video-id") Long videoId, @RequestParam("jwt") String jwt) throws Exception {
        String userLogin = ((User) tokenProvider.getAuthentication(jwt).getPrincipal()).getUsername();
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        AdjustClient adjustClient = adjustClientMapper.toEntity(adjustClientDTO);

        Tutorial tutorial = tutorialRepository.findTutorialByClientAndAdjustTutorialId(adjustClient, videoId).get();
        if (tutorial == null) {
            throw new Exception("client has not bought the tutorial");
        }
        TutorialVideo tutorialVideo = tutorial.getVideo();

        byte[] videoByte = tutorialVideo.getContent();
        ByteArrayResource resource = new ByteArrayResource(videoByte);
        DeferredResult<ResponseEntity<ByteArrayResource>> dr = new DeferredResult<>();
        dr.setResult(ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .contentType(MediaType.parseMediaType(tutorialVideo.getContentContentType()))
            .body(resource));
        return dr;
    }


    /**
     * {@code GET  /adjust-shoping-items} : get all the adjustShopingItems for client app.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustShopingItems in body.
     */
    @GetMapping("/adjust-shoping-items")
    public List<AdjustShopingItemDTO> getAllAdjustShopingItemsForClientApp() {
        log.debug("REST request to get all AdjustShopingItems");
        return adjustShopingItemService.findAll();
    }

    /**
     * order a product from client app using DummyOrderDTO class
     *
     * @param dummyOrderDTO
     * @return
     */
    @PostMapping("/adjust-shopings")
    public ResponseEntity<DummyOrderDTO> order(@RequestBody DummyOrderDTO dummyOrderDTO) {
        OrderDTO orderDTO = dummyOrderDTO;
        DummyCartDTO dummyCartDTO = dummyOrderDTO.getCart();
        List<DummyShopingItemDTO> dummyShopingItemDTOList = dummyCartDTO.getItems();
        CartDTO cartDTO = cartService.save(dummyCartDTO);
        Long cartId = cartDTO.getId();
        orderDTO.setCartId(cartId);
        orderService.save(orderDTO);
        dummyShopingItemDTOList.forEach((ShopingItemDTO i) -> {
            i.setCartId(cartId);
            shopingItemService.save(i);
        });
        return ResponseEntity.ok(dummyOrderDTO);
    }

    /**
     * buy a token for client by client app
     *
     * @param adjustTokenItemId
     * @return
     */
    @PostMapping("/buy-token")
    public ResponseEntity<Double> buyToken(@RequestBody String adjustTokenItemId) {
        AdjustTokensDTO adjustTokensDTO = adjustTokensResource.getAdjustTokens(Long.valueOf(adjustTokenItemId)).getBody();
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        adjustClientDTO.setToken(adjustClientDTO.getToken() + adjustTokensDTO.getToken());
        AdjustClientDTO result = adjustClientService.save(adjustClientDTO);
        return ResponseEntity.ok(result.getToken());
    }

    /**
     * get the amount of token client has
     *
     * @return
     */
    @GetMapping("/get-client-token")
    public ResponseEntity<Double> getClientTokens() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        return ResponseEntity.ok(adjustClientDTO.getToken());
    }

    /**
     * {@code GET  /adjust-tokens} : get all the adjustTokens for client app.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustTokens in body.
     */
    @GetMapping("/adjust-tokens")
    public List<AdjustTokensDTO> getAllAdjustTokensForClientApp() {
        log.debug("REST request to get all AdjustTokens");
        return adjustTokensService.findAll();
    }

    /**
     * {@code GET  /adjust-tutorials} : get all the adjustTutorials.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustTutorials in body.
     */
    @GetMapping("/adjust-tutorials")
    public List<AdjustTutorialDTO> getAllAdjustTutorialsForClientApp() {
        log.debug("REST request to get all AdjustTutorials");
        return adjustTutorialService.findAll();
    }

    /**
     * {@code GET  /specialists} : get all the specialists.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specialists in body.
     */
    @GetMapping("/specialists")
    public List<SpecialistDTO> getAllSpecialists(@RequestParam(required = false) String filter) {
        if ("conversation-is-null".equals(filter)) {
            log.debug("REST request to get all Specialists where conversation is null");
            return specialistService.findAllWhereConversationIsNull();
        }
        log.debug("REST request to get all Specialists");
        return specialistService.findAll();
    }

    //program request

    /**
     * reuquest program by client app
     *
     * @param dummyAdjustProgramDTO
     * @return
     */
    @PostMapping("/request-program")
    public ResponseEntity<AdjustProgramDTO> requestForProgramByClient(@RequestBody DummyAdjustProgramDTO dummyAdjustProgramDTO) {
        // set client id for program
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        dummyAdjustProgramDTO.setClientId(adjustClientDTO.getId());

        // set adjust program (nutrition program, fitness program and body composition)
        AdjustProgramDTO adjustProgramDTO = adjustProgramService.save(dummyAdjustProgramDTO);
        dummyAdjustProgramDTO.getBodyCompositions().forEach((bodyComposition) -> {
            bodyComposition.setProgramId(adjustProgramDTO.getId());
            bodyCompositionService.save(bodyComposition);
        });

        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setClientId(adjustClientDTO.getId());
        conversationDTO.setSpecialistId(dummyAdjustProgramDTO.getSpecialistId());
        conversationService.save(conversationDTO);

        return ResponseEntity.ok().header("charset", "utf-8").body(adjustProgramDTO);
    }

    /**
     * {@code GET  /adjust-programs} : get all the adjustPrograms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustPrograms in body.
     */
    @GetMapping("/adjust-programs")
    public List<DummyAdjustProgramDTO> getAllAdjustPrograms() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        List<AdjustProgram> adjustPrograms = adjustProgramRepository.findAllByClient(adjustClientMapper.toEntity(adjustClientDTO));
        List<DummyAdjustProgramDTO> adjustProgramDTOList = adjustPrograms.stream().map((program) -> {

            // set adjust program's client
            AdjustClient adjustClient = program.getClient();
            DummyAdjustClientDTO dummyAdjustClientDTO = new DummyAdjustClientDTO(adjustClientMapper.toDto(adjustClient));

            // set adjust program's specialist
            Specialist specialist = program.getSpecialist();
            DummySpecialistDTO dummySpecialistDTO = new DummySpecialistDTO(specialistMapper.toDto(specialist));

            // set adjust program's program development
            List<DummyProgramDevelopmentDTO> dummyProgramDevelopmentDTOList = programDevelopmentRepository.findAllByAdjustProgram(program).stream().map((programDevelopment) -> {
                DummyProgramDevelopmentDTO dummyProgramDevelopmentDTO = new DummyProgramDevelopmentDTO(programDevelopmentMapper.toDto(programDevelopment));
                return dummyProgramDevelopmentDTO;
            }).collect(Collectors.toList());

            // set adjust program's body composition
            List<DummyBodyCompositionDTO> dummyBodyCompositionDTOList = bodyCompositionRepository.findAllByProgram(program).stream().map((bodyComposition) -> {
                DummyBodyCompositionDTO dummyBodyCompositionDTO = new DummyBodyCompositionDTO(bodyCompositionMapper.toDto(bodyComposition));
                return dummyBodyCompositionDTO;
            }).collect(Collectors.toList());

            // set adjust program's nutrition program
            NutritionProgram nutritionProgram = program.getNutritionProgram();
            List<DummyMealDTO> dummyMealDTOList = new ArrayList<>();
            DummyNutritionProgramDTO dummyNutritionProgramDTO = new DummyNutritionProgramDTO();
            if (nutritionProgram != null) {
                dummyMealDTOList = nutritionProgram.getMeals().stream().map((meal) -> {

                    List<DummyNutritionDTO> nutritions = meal.getNutritions().stream().map((nutrition) -> {
                        AdjustNutrition adjustNutrition = adjustNutritionRepository.findById(nutrition.getAdjustNutritionId()).get();
                        List<DummyAdjustFoodDTO> dummyAdjustFoodDTOList = adjustNutrition.getFoods().stream().map((food) -> {
                            DummyAdjustFoodDTO dummyAdjustFoodDTO = new DummyAdjustFoodDTO(adjustFoodMapper.toDto(food));
                            return dummyAdjustFoodDTO;
                        }).collect(Collectors.toList());
                        DummyNutritionDTO dummyNutritionDTO = new DummyNutritionDTO(nutritionMapper.toDto(nutrition));
                        dummyNutritionDTO.setFoods(dummyAdjustFoodDTOList);
                        return dummyNutritionDTO;
                    }).collect(Collectors.toList());

                    DummyMealDTO dummyMealDTO = new DummyMealDTO(mealMapper.toDto(meal));
                    dummyMealDTO.setNutritions(nutritions);
                    return dummyMealDTO;
                }).collect(Collectors.toList());
                dummyNutritionProgramDTO = new DummyNutritionProgramDTO(nutritionProgramMapper.toDto(nutritionProgram));
                dummyNutritionProgramDTO.setMeals(dummyMealDTOList);
            }


            // set adjust programs's fitness program
            FitnessProgram fitnessProgram = program.getFitnessProgram();
            List<DummyWorkoutDTO> dummyWorkoutDTOList = new ArrayList<>();
            DummyFitnessProgramDTO dummyFitnessProgramDTO = new DummyFitnessProgramDTO();
            if (fitnessProgram != null) {
                dummyWorkoutDTOList = fitnessProgram.getWorkouts().stream().map((workout) -> {
                    List<DummyExerciseDTO> dummyExerciseDTOList = workout.getExercises().stream().map((exercise) -> {
                        DummyMoveDTO dummyMoveDTO = new DummyMoveDTO(moveMapper.toDto(exercise.getMove()));
                        DummyExerciseDTO dummyExerciseDTO = new DummyExerciseDTO(exerciseMapper.toDto(exercise));
                        dummyExerciseDTO.setMove(dummyMoveDTO);
                        return dummyExerciseDTO;
                    }).collect(Collectors.toList());
                    DummyWorkoutDTO dummyWorkoutDTO = new DummyWorkoutDTO(workoutMapper.toDto(workout));
                    dummyWorkoutDTO.setExercises(dummyExerciseDTOList);
                    return dummyWorkoutDTO;
                }).collect(Collectors.toList());
                dummyFitnessProgramDTO = new DummyFitnessProgramDTO(fitnessProgramMapper.toDto(fitnessProgram));
                dummyFitnessProgramDTO.setWorkouts(dummyWorkoutDTOList);
            }


            DummyAdjustProgramDTO dummyAdjustProgramDTO = new DummyAdjustProgramDTO(adjustProgramMapper.toDto(program));
            dummyAdjustProgramDTO.setClient(dummyAdjustClientDTO);
            dummyAdjustProgramDTO.setSpecialist(dummySpecialistDTO);
            dummyAdjustProgramDTO.setProgramDevelopments(dummyProgramDevelopmentDTOList);
            dummyAdjustProgramDTO.setBodyCompositions(dummyBodyCompositionDTOList);
            dummyAdjustProgramDTO.setNutritionProgram(dummyNutritionProgramDTO);
            dummyAdjustProgramDTO.setFitnessProgram(dummyFitnessProgramDTO);

            return dummyAdjustProgramDTO;
        }).collect(Collectors.toList());
        log.debug("REST request to get all AdjustPrograms");
        return adjustProgramDTOList;
    }

    @PostMapping("/program-developments")
    public List<ProgramDevelopmentDTO> rate(@RequestBody DummyProgramDevelopmentDTO programDevelopmentDTO) throws Exception {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        LocalDate localDate = LocalDate.now();
        AdjustProgram adjustProgram = adjustProgramRepository.findById(programDevelopmentDTO.getAdjustProgramId()).get();
        for (ProgramDevelopment pd : adjustProgram.getProgramDevelopments()) {
            if (pd.getDate().getYear() == localDate.getYear() && pd.getDate().getMonth() == localDate.getMonth() && pd.getDate().getDayOfMonth() == localDate.getDayOfMonth()) {
                throw new Exception("client has scored his/her performance already today!");
            }
        }
        programDevelopmentDTO.setDate(localDate);
        if (programDevelopmentDTO.getFitnessScore() <= 5 && programDevelopmentDTO.getNutritionScore() <= 5) {
            adjustClientDTO.setScore(adjustClientDTO.getScore() + programDevelopmentDTO.getNutritionScore() + programDevelopmentDTO.getFitnessScore());
            adjustClientService.save(adjustClientDTO);
            programDevelopmentService.save(programDevelopmentDTO);
            List<ProgramDevelopmentDTO> programDevelopmentDTOList =
                programDevelopmentRepository.findAllByAdjustProgram(adjustProgram).stream().map((programDevelopment -> programDevelopmentMapper.toDto(programDevelopment))).collect(Collectors.toList());
            return programDevelopmentDTOList;
        } else {
            throw new Exception("wrong score!");
        }
    }


    @GetMapping("/client-score")
    public ResponseEntity<Double> getClientScore() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        AdjustClientDTO adjustClientDTO = adjustClientRepository.findAdjustClientByUsername(userLogin).map(adjustClientMapper::toDto).get();
        return ResponseEntity.ok(adjustClientDTO.getScore());
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDTO>> getClientMessages(@RequestParam("client-id") Long clientId, @RequestParam("specialist-id") Long specialistId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findAllByClientIdAndSpecialistId(clientId, specialistId);
        List<MessageDTO> messageDTOList = chatMessages.stream().map((chatMessage) -> {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setClientId(chatMessage.getClientId());
            messageDTO.setMessage(chatMessage.getText());
            messageDTO.setSpecialistId(chatMessage.getSpecialistId());
            messageDTO.setSender(chatMessage.);
        }).collect(Collectors.toList());
    }
}
