

import 'package:adjust_client/actions/program_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/fitness_program_state.dart';
import 'package:adjust_client/states/nutrition_program_state.dart';
import 'package:adjust_client/states/program_state.dart';

AppState programReducer(AppState state, dynamic action) {
  if (action is GetProgramListAction) {
    ProgramListState programListState = state.programListState;
    programListState.programs =
    action.payload.programs != null ? action.payload.programs : state
        .programListState;
    state.programListState = programListState;
    return state;
  } else if (action is SetNutritionProgramAction) {
    NutritionProgramState nutritionProgramState = state.nutritionProgramState;
    nutritionProgramState.description = action.payload.description != null && action.payload.description != "" ? action.payload.description : nutritionProgramState.description;
    nutritionProgramState.meals = action.payload.meals != null ? action.payload.meals : nutritionProgramState.meals;
    nutritionProgramState.carbsPercentage = action.payload.carbsPercentage != null ? action.payload.carbsPercentage : nutritionProgramState.carbsPercentage;
    nutritionProgramState.proteinPercentage = action.payload.proteinPercentage != null ? action.payload.proteinPercentage : nutritionProgramState.proteinPercentage;
    nutritionProgramState.fatPercentage = action.payload.fatPercentage != null ? action.payload.fatPercentage : nutritionProgramState.fatPercentage;
    nutritionProgramState.dailyCalories = action.payload.dailyCalories != null ? action.payload.dailyCalories : nutritionProgramState.dailyCalories;
    nutritionProgramState.id = action.payload.id != null ? action.payload.id : nutritionProgramState.id;
    state.nutritionProgramState = nutritionProgramState;
    return state;
  } else if (action is SetFitnessProgramAction) {
    FitnessProgramState fitnessProgramState = state.fitnessProgramState;
    fitnessProgramState.description = action.payload.description != null && action.payload.description != "" ? action.payload.description : fitnessProgramState.description;
    fitnessProgramState.workouts = action.payload.workouts != null ? action.payload.workouts : fitnessProgramState.workouts;
    fitnessProgramState.id = action.payload.id != null ? action.payload.id : fitnessProgramState.id;
    fitnessProgramState.type = action.payload.type != null && action.payload.type != "" ? action.payload.type : fitnessProgramState.type;
    state.fitnessProgramState = fitnessProgramState;
    return state;
  } else {
    return state;
  }
}