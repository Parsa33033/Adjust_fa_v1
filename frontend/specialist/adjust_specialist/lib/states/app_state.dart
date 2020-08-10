import 'package:adjust_specialist/states/authentication_state.dart';
import 'package:adjust_specialist/states/client_state.dart';
import 'package:adjust_specialist/states/fitness_program_state.dart';
import 'package:adjust_specialist/states/message_state.dart';
import 'package:adjust_specialist/states/nutrition_program_state.dart';
import 'package:adjust_specialist/states/nutrition_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:adjust_specialist/states/user_state.dart';

AppState appStateInit = AppState(
  userState: userStateInit,
  authenticationState: authenticationStateInit,
  specialistState: specialistStateInit,
  programListState: programListStateInit,
  nutritionProgramState: nutritionProgramStateInit,
  fitnessProgramState: fitnessProgramStateInit,
  messagesState: messagesStateInit,
  nutritionStateList: nutritionStateListInit,
);

class AppState {
  UserState userState;
  AuthenticationState authenticationState;
  SpecialistState specialistState;
  ProgramListState programListState;
  NutritionProgramState nutritionProgramState;
  FitnessProgramState fitnessProgramState;
  MessagesState messagesState;
  NutritionStateList nutritionStateList;

  AppState({
      this.userState,
      this.authenticationState,
      this.specialistState,
      this.programListState,
      this.nutritionProgramState,
      this.fitnessProgramState,
      this.messagesState,
      this.nutritionStateList});
}
