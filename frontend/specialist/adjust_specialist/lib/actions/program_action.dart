import 'dart:convert';
import 'dart:io';

import 'package:adjust_specialist/actions/jwt.dart';
import 'package:adjust_specialist/constants/urls.dart';
import 'package:adjust_specialist/dto/body_composition_dto.dart';
import 'package:adjust_specialist/dto/client_dto.dart';
import 'package:adjust_specialist/dto/fitness_program_dto.dart';
import 'package:adjust_specialist/dto/food_dto.dart';
import 'package:adjust_specialist/dto/nutrition_dto.dart';
import 'package:adjust_specialist/dto/nutrition_program_dto.dart';
import 'package:adjust_specialist/dto/program_development_dto.dart';
import 'package:adjust_specialist/dto/program_dto.dart';
import 'package:adjust_specialist/dto/specialist_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/model/food.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/body_composition_state.dart';
import 'package:adjust_specialist/states/client_state.dart';
import 'package:adjust_specialist/states/exercise_state.dart';
import 'package:adjust_specialist/states/fitness_program_state.dart';
import 'package:adjust_specialist/states/food_state.dart';
import 'package:adjust_specialist/states/meal_state.dart';
import 'package:adjust_specialist/states/nutrition_program_state.dart';
import 'package:adjust_specialist/states/nutrition_state.dart';
import 'package:adjust_specialist/states/program_development_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:adjust_specialist/states/workout_state.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;

class GetProgramListAction {
  ProgramListState payload;

  GetProgramListAction({this.payload});
}

class SetProgramListAction {
  ProgramListState payload;

  SetProgramListAction({this.payload});
}

class SetNutritionProgramAction {
  NutritionProgramState payload;

  SetNutritionProgramAction({this.payload});
}

class SetFitnessProgramAction {
  FitnessProgramState payload;

  SetFitnessProgramAction({this.payload});
}

class GetAdjustNutritions {
  NutritionStateList payload;
  GetAdjustNutritions({this.payload});
}

Future<int> getSpecialistPrograms(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(PROGRAM_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<ProgramState> programList = l.map((e) {
      ProgramDTO programDTO = ProgramDTO.fromJson(e);

      List<ProgramDevelopmentState> programDevelopmentStateList =
      programDTO.programDevelopments
          .map((programDevelopmentDTO) {
        ProgramDevelopmentState programDevelopmentState =
        ProgramDevelopmentState(
            programDevelopmentDTO.id,
            programDevelopmentDTO.date,
            programDevelopmentDTO.nutritionScore,
            programDevelopmentDTO.fitnessScore,
            programDevelopmentDTO.adjustProgramId);
        return programDevelopmentState;
      }).toList();

      List<BodyCompositionState> bodyCompositionStateList =
      programDTO.bodyCompositions.map((bodyCompositionDTO) {
        BodyCompositionState bodyCompositionState = BodyCompositionState(
            bodyCompositionDTO.id,
            bodyCompositionDTO.createdAt,
            bodyCompositionDTO.height,
            bodyCompositionDTO.weight,
            bodyCompositionDTO.bmi,
            bodyCompositionDTO.wrist,
            bodyCompositionDTO.waist,
            bodyCompositionDTO.lbm,
            bodyCompositionDTO.muscleMass,
            bodyCompositionDTO.muscleMassPercentage,
            bodyCompositionDTO.fatMass,
            bodyCompositionDTO.fatMassPercentage,
            bodyCompositionDTO.gender,
            bodyCompositionDTO.age,
            bodyCompositionDTO.bodyImage,
            bodyCompositionDTO.bodyImageContentType,
            bodyCompositionDTO.bodyCompositionFile,
            bodyCompositionDTO.bodyCompositionFileContentType,
            bodyCompositionDTO.bloodTestFile,
            bodyCompositionDTO.bloodTestFileContentType,
            bodyCompositionDTO.programId);
        return bodyCompositionState;
      }).toList();

      NutritionProgramDTO nutritionProgramDTO = programDTO.nutritionProgram;
      NutritionProgramState nutritionProgramState = null;
      if (nutritionProgramDTO != null && nutritionProgramDTO.meals != null) {
        List<MealState> meals = nutritionProgramDTO.meals.map((meal) {
          List<NutritionState> nutritions = meal.nutritions.map((nutrition) {
            List<FoodState> foods = nutrition.foods.map((food) {
              FoodState foodState = FoodState(
                  food.id, food.name, food.description, food.nutritionId);
              return foodState;
            }).toList();
            NutritionState nutritionState = NutritionState(
                nutrition.id,
                nutrition.name,
                nutrition.description,
                nutrition.unit,
                nutrition.adjustNutritionId,
                nutrition.caloriesPerUnit,
                nutrition.proteinPerUnit,
                nutrition.carbsPerUnit,
                nutrition.fatInUnit,
                nutrition.mealId,
                foods);
            return nutritionState;
          }).toList();
          MealState mealState = MealState(meal.id, meal.name, meal.number,
              meal.nutritionProgramId, nutritions);
          return mealState;
        }).toList();
        nutritionProgramState = NutritionProgramState(
            nutritionProgramDTO.id,
            nutritionProgramDTO.dailyCalories,
            nutritionProgramDTO.proteinPercentage,
            nutritionProgramDTO.carbsPercentage,
            nutritionProgramDTO.fatPercentage,
            nutritionProgramDTO.description,
            meals);
      }

      FitnessProgramDTO fitnessProgramDTO = programDTO.fitnessProgram;
      FitnessProgramState fitnessProgramState = null;
      if (fitnessProgramDTO != null && fitnessProgramDTO.workouts != null) {
        List<WorkoutState> workoutStateList =
        fitnessProgramDTO.workouts.map((e) {
          List<ExerciseState> exerciseStateList = e.exercises.map((e) {
            return ExerciseState(
                e.id,
                e.number,
                e.sets,
                e.repsMin,
                e.repsMax,
                e.moveId,
                e.workoutId,
                e.move);
          }).toList();
          WorkoutState workoutState =
          WorkoutState(e.id, e.dayNumber, e.programId, exerciseStateList);
          return workoutState;
        }).toList();
        fitnessProgramState = FitnessProgramState(
            fitnessProgramDTO.id,
            fitnessProgramDTO.type,
            fitnessProgramDTO.description,
            workoutStateList);
      }

      ClientDTO clientDTO = programDTO.client;
      ClientState clientState = ClientState(
          clientDTO.id,
          clientDTO.username,
          clientDTO.firstName,
          clientDTO.lastName,
          clientDTO.birthDate,
          clientDTO.age,
          clientDTO.gender,
          clientDTO.token,
          clientDTO.score,
          clientDTO.image,
          clientDTO.imageContentType);

      SpecialistDTO specialistDTO = programDTO.specialist;
      SpecialistState specialistState = SpecialistState(
          specialistDTO.id,
          specialistDTO.username,
          specialistDTO.firstName,
          specialistDTO.lastName,
          specialistDTO.birth,
          specialistDTO.gender,
          specialistDTO.degree,
          specialistDTO.field,
          specialistDTO.resume,
          specialistDTO.stars,
          specialistDTO.image,
          specialistDTO.imageContentType,
          specialistDTO.busy);

      ProgramState programState = ProgramState(
          programDTO.id,
          programDTO.createdAt,
          programDTO.expirationDate,
          programDTO.designed,
          programDTO.nutritionDone,
          programDTO.fitnessDone,
          programDTO.paid,
          programDTO.fitnessProgramId,
          programDTO.nutritionProgramId,
          programDTO.clientId,
          programDTO.specialistId,
          clientState,
          specialistState,
          programDevelopmentStateList,
          bodyCompositionStateList,
          nutritionProgramState,
          fitnessProgramState);
      return programState;
    }).toList();
    try {
      StoreProvider.of<AppState>(context).dispatch(
          GetProgramListAction(payload: ProgramListState(programList)));
      return 1;
    } catch (e) {
      store.dispatch(
          GetProgramListAction(payload: ProgramListState(programList)));
      return 1;
    }
  }
  return 0;
}


Future<int> setProgramDevelopment(BuildContext context,
    ProgramDevelopmentDTO programDevelopmentDTO, int programIndex) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)..putIfAbsent(
        "Content-Type", () => "application/json");

  String content = jsonEncode(programDevelopmentDTO.toJson());

  http.Response response = await http.post(
      PROGRAM_DEVELOPMENT_URL, headers: headers,
      body: content,
      encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<ProgramDevelopmentState> programDevelopmentStateList = l.map((e) {
      ProgramDevelopmentDTO programDevelopmentDTO = ProgramDevelopmentDTO
          .fromJson(e);
      ProgramDevelopmentState programDevelopmentState = ProgramDevelopmentState(
          programDevelopmentDTO.id, programDevelopmentDTO.date,
          programDevelopmentDTO.nutritionScore,
          programDevelopmentDTO.fitnessScore,
          programDevelopmentDTO.adjustProgramId);
      return programDevelopmentState;
    }).toList();

    ProgramState programState = store.state.programListState.programs.reversed.toList()[programIndex];
    programState.programDevelopmentStateList = programDevelopmentStateList;
    store.state.programListState.programs.reversed.toList()[programIndex] = programState;
    store.dispatch(SetProgramListAction(payload: store.state.programListState));
    return 1;
  }
  return 0;
}


Future<int> getAdjustNutritionList(BuildContext context) async {
  String jwt = await getJwt(context);
  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);
  http.Response response = await http.get(ADJUST_NUTRITION_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<NutritionState> nutritionStateList = l.map((e) {
      NutritionDTO nutritionDTO = NutritionDTO.fromJson(e);
      List<FoodState> foodStateList = nutritionDTO.foods.map((foodDTO) {
        FoodState foodState = FoodState(foodDTO.id, foodDTO.name, foodDTO.description, foodDTO.nutritionId);
        return foodState;
      }).toList();
      NutritionState nutritionState = NutritionState(nutritionDTO.id, nutritionDTO.name, nutritionDTO.description, nutritionDTO.unit, nutritionDTO.adjustNutritionId, nutritionDTO.caloriesPerUnit, nutritionDTO.proteinPerUnit, nutritionDTO.carbsPerUnit, nutritionDTO.fatInUnit, nutritionDTO.mealId, foodStateList);
      return nutritionState;
    }).toList();
    try {
      StoreProvider.of<AppState>(context).dispatch(GetAdjustNutritions(payload: NutritionStateList(nutritionStateList)));
    } catch (e) {
      store.dispatch(GetAdjustNutritions(payload: NutritionStateList(nutritionStateList)));
    }
    return 1;
  }
  return 0;
}

Future<int> designNutritionProgram(BuildContext context, ProgramDTO programDTO) async {
  String jwt = await getJwt(context);
  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)
    ..putIfAbsent("Content-Type", () => "application/json");

  String content = jsonEncode(programDTO.toJson());

  http.Response response = await http.post(DESIGN_NUTRITION_PROGRAM_URL, headers: headers, body: content, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    return 1;
  }
  return 0;
}