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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/specialist/app")
public class SpecialistAppController {
    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(AdjustClientResource.class);

    private static final String ENTITY_NAME = "adjustClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Value("${specialist.app.apiKey}")
    private String apiKey;

    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final UserJWTController userJWTController;


    private final SpecialistRepository specialistRepository;
    private final SpecialistService specialistService;
    private final SpecialistMapper specialistMapper;

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



    private final AdjustProgramService adjustProgramService;
    private final ProgramDevelopmentService programDevelopmentService;
    private final BodyCompositionService bodyCompositionService;
    private final FitnessProgramService fitnessProgramService;
    private final NutritionProgramService nutritionProgramService;
    private final AdjustNutritionService adjustNutritionService;
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
    private final NutritionService nutritionService;

    private final ConversationService conversationService;
    private final ChatMessageRepository chatMessageRepository;
    private final AdjustMoveRepository adjustMoveRepository;
    private final AdjustMoveService adjustMoveService;
    private final AdjustMoveMapper adjustMoveMapper;

    public SpecialistAppController(SpecialistRepository specialistRepository, UserService userService, UserJWTController userJWTController, TokenProvider tokenProvider, AdjustClientService adjustClientService,
                                   AdjustClientRepository adjustClientRepository, AdjustClientMapper adjustClientMapper,
                                   AdjustTokensResource adjustTokensResource, AdjustTutorialService adjustTutorialService,
                                   TutorialRepository tutorialRepository, AdjustTutorialVideoService adjustTutorialVideoService,
                                   TutorialService tutorialService, TutorialVideoService tutorialVideoService, TutorialMapper tutorialMapper,
                                   AdjustShopingItemService adjustShopingItemService, OrderService orderService,
                                   CartService cartService, ShopingItemService shopingItemService, AdjustTokensService adjustTokensService, AdjustNutritionService adjustNutritionService,
                                   SpecialistService specialistService, SpecialistMapper specialistMapper, AdjustProgramService adjustProgramService, ProgramDevelopmentService programDevelopmentService,
                                   BodyCompositionService bodyCompositionService, FitnessProgramService fitnessProgramService, NutritionProgramService nutritionProgramService,
                                   MealService mealService, AdjustNutritionRepository adjustNutritionRepository, WorkoutService workoutService, ExerciseService exerciseService, MoveService moveService,
                                   ProgramDevelopmentMapper programDevelopmentMapper, BodyCompositionMapper bodyCompositionMapper, NutritionProgramMapper nutritionProgramMapper, FitnessProgramMapper fitnessProgramMapper, MealMapper mealMapper,
                                   NutritionMapper nutritionMapper, AdjustNutritionMapper adjustNutritionMapper, AdjustFoodMapper adjustFoodMapper, WorkoutMapper workoutMapper, ExerciseMapper exerciseMapper, MoveMapper moveMapper,
                                   AdjustProgramRepository adjustProgramRepository, AdjustProgramMapper adjustProgramMapper, AdjustFoodRepository adjustFoodRepository, ProgramDevelopmentRepository programDevelopmentRepository,
                                   BodyCompositionRepository bodyCompositionRepository, ConversationService conversationService, ChatMessageRepository chatMessageRepository,
                                   NutritionService nutritionService, AdjustMoveRepository adjustMoveRepository, AdjustMoveService adjustMoveService, AdjustMoveMapper adjustMoveMapper)  {
        this.specialistRepository = specialistRepository;
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
        this.adjustNutritionService = adjustNutritionService;
        this.nutritionService = nutritionService;
        this.adjustMoveRepository = adjustMoveRepository;
        this.adjustMoveService = adjustMoveService;
        this.adjustMoveMapper = adjustMoveMapper;
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
    public ResponseEntity<UserJWTController.JWTToken> registerAccountBySpecialistApp(@Valid @RequestBody ManagedUserVM managedUserVM, @RequestHeader("Authorization") String authorization) throws Exception {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }

        String clientApiKeyEncoded = authorization.substring(6);
        log.info("specialist api key encoded: {}", clientApiKeyEncoded);

        String apiKeyEncoded = new String(Base64.getEncoder().encode(apiKey.getBytes()), "UTF-8");
        log.info("backend api key encoded: {}", apiKeyEncoded);
        if (!clientApiKeyEncoded.equals(apiKeyEncoded)) {
            throw new Exception("request does not come from specialist app");
        }

        managedUserVM.setLangKey("fa");
        com.adjust.api.domain.User user = userService.registerUser(managedUserVM, managedUserVM.getPassword(), false, true);


        SpecialistDTO specialistDTO = new SpecialistDTO();
        specialistDTO.setUsername(user.getLogin());
        specialistDTO.setBusy(false);
        specialistDTO.setStars(0.0);
        specialistService.save(specialistDTO);

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
    @GetMapping("/specialists")
    public ResponseEntity<SpecialistDTO> getSpecialistBySpecialistApp() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        SpecialistDTO specialistDTO = specialistRepository.findByUsername(userLogin).map(specialistMapper::toDto).get();
        return ResponseEntity.ok().header("charset", "UTF-8")
            .header("charset", "UTF-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialistDTO.getId().toString()))
            .body(specialistDTO);
    }

    /**
     * {@code PUT  /adjust-clients} : Updates an existing adjustClient from client app.
     *
     * @param specialistDTO the specialistDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjustClientDTO,
     * or with status {@code 400 (Bad Request)} if the adjustClientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjustClientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/specialists")
    public ResponseEntity<SpecialistDTO> updateAdjustClientByApp(@RequestBody SpecialistDTO specialistDTO) throws URISyntaxException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        SpecialistDTO specialistDTOUpdatee = specialistRepository.findByUsername(userLogin).map(specialistMapper::toDto).get();
        specialistDTO.setStars(0.0);
        SpecialistDTO specialistDTOUpdated = (SpecialistDTO) ClassUpdater.updateClass(specialistDTO, specialistDTOUpdatee);
        log.debug("REST request to update AdjustClient : {}", specialistDTOUpdated);
        if (specialistDTOUpdated.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecialistDTO result = specialistService.save(specialistDTOUpdated);
        return ResponseEntity.ok().header("charset", "UTF-8")
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialistDTOUpdated.getId().toString()))
            .body(result);
    }


    /**
     * {@code GET  /adjust-programs} : get all the adjustPrograms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjustPrograms in body.
     */
    @GetMapping("/adjust-programs")
    public List<DummyAdjustProgramDTO> getAllAdjustProgramsForSpecialistApp() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        SpecialistDTO specialistDTO = specialistRepository.findByUsername(userLogin).map(specialistMapper::toDto).get();
        List<AdjustProgram> adjustPrograms = adjustProgramRepository.findAllBySpecialist(specialistMapper.toEntity(specialistDTO));
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

    @GetMapping("/adjust-nutritions")
    public ResponseEntity<List<DummyAdjustNutritionDTO>> getAdjustNutritionsForSpecialistApp() {
        List<AdjustNutrition> adjustNutritions = adjustNutritionRepository.findAll();
        List<DummyAdjustNutritionDTO> dummyAdjustNutritionDTOList = adjustNutritions.stream().map((adjustNutrition) -> {
            List<DummyAdjustFoodDTO> dummyAdjustFoodDTOList = adjustNutrition.getFoods().stream().map(adjustFood -> {
                DummyAdjustFoodDTO dummyAdjustFoodDTO = new DummyAdjustFoodDTO(adjustFoodMapper.toDto(adjustFood));
                return dummyAdjustFoodDTO;
            }).collect(Collectors.toList());
            DummyAdjustNutritionDTO dummyAdjustNutritionDTO = new DummyAdjustNutritionDTO(adjustNutritionMapper.toDto(adjustNutrition));
            dummyAdjustNutritionDTO.setFoods(dummyAdjustFoodDTOList);
            return dummyAdjustNutritionDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dummyAdjustNutritionDTOList);
    }

    @GetMapping("/adjust-moves")
    public ResponseEntity<List<AdjustMoveDTO>> getAdjustMovesForSpecialistApp() {
        List<AdjustMoveDTO> adjustMoveDTOList = adjustMoveService.findAll();
        return ResponseEntity.ok(adjustMoveDTOList);
    }

    @PostMapping("/design-nutrition-program")
    public void designNutritionProgramBySpecialist(@RequestBody DummyAdjustProgramDTO dummyAdjustProgramDTO) {
        AdjustProgramDTO adjustProgramDTO = adjustProgramService.findOne(dummyAdjustProgramDTO.getId()).get();
        NutritionProgramDTO nutritionProgramDTO = nutritionProgramService.save(dummyAdjustProgramDTO.getNutritionProgram());
        dummyAdjustProgramDTO.getNutritionProgram().getMeals().forEach((mealDTO) -> {
            mealDTO.setNutritionProgramId(nutritionProgramDTO.getId());
            MealDTO mealDTOSaved = mealService.save(mealDTO);
            mealDTO.getNutritions().forEach((nutritionDTO) -> {
                nutritionDTO.setMealId(mealDTOSaved.getId());
                nutritionService.save(nutritionDTO);
            });
        });
        adjustProgramDTO.setNutritionProgramId(nutritionProgramDTO.getId());
        adjustProgramDTO.setNutritionDone(true);
        adjustProgramService.save(adjustProgramDTO);
    }

    @PostMapping("/design-fitness-program")
    public void designFitnessProgramBySpecialist(@RequestBody DummyAdjustProgramDTO dummyAdjustProgramDTO) {
        AdjustProgramDTO adjustProgramDTO = adjustProgramService.findOne(dummyAdjustProgramDTO.getId()).get();
        FitnessProgramDTO fitnessProgramDTO = fitnessProgramService.save(dummyAdjustProgramDTO.getFitnessProgram());
        dummyAdjustProgramDTO.getFitnessProgram().getWorkouts().forEach((dummyWorkoutDTO) -> {
            dummyWorkoutDTO.setProgramId(fitnessProgramDTO.getId());
            WorkoutDTO workoutDTO = workoutService.save(dummyWorkoutDTO);
            dummyWorkoutDTO.getExercises().forEach((dummyExerciseDTO) -> {
                dummyExerciseDTO.setWorkoutId(workoutDTO.getId());
                DummyMoveDTO dummyMoveDTO = dummyExerciseDTO.getMove();
                dummyMoveDTO.setAdjustMoveId(dummyMoveDTO.getId());
                MoveDTO moveDTO = moveService.save(dummyMoveDTO);
                dummyExerciseDTO.setMoveId(moveDTO.getId());
                log.info("--------->" + dummyExerciseDTO.toString());
                ExerciseDTO exerciseDTO = exerciseService.save(dummyExerciseDTO);
                exerciseDTO.setMoveId(moveDTO.getId());
                exerciseService.save(exerciseDTO);
            });
        });
        adjustProgramDTO.setFitnessProgramId(fitnessProgramDTO.getId());
        adjustProgramDTO.setFitnessDone(true);
        adjustProgramService.save(adjustProgramDTO);
    }


    @GetMapping("/messages")
    public ResponseEntity<List<MessageDTO>> getSpecialistMessages(@RequestParam("client-id") Long clientId, @RequestParam("specialist-id") Long specialistId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findAllByClientIdAndSpecialistId(clientId, specialistId);
        List<MessageDTO> messageDTOList = chatMessages.stream().map((chatMessage) -> {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setClientId(chatMessage.getClientId());
            messageDTO.setMessage(chatMessage.getText());
            messageDTO.setSpecialistId(chatMessage.getSpecialistId());
            messageDTO.setSender(chatMessage.getSender());
            messageDTO.setReceiver(chatMessage.getReceiver());
            return messageDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(messageDTOList);
    }
}
