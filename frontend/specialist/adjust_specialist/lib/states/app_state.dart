import 'package:adjust_specialist/states/authentication_state.dart';
import 'package:adjust_specialist/states/cart_state.dart';
import 'package:adjust_specialist/states/client_state.dart';
import 'package:adjust_specialist/states/fitness_program_state.dart';
import 'package:adjust_specialist/states/message_state.dart';
import 'package:adjust_specialist/states/nutrition_program_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:adjust_specialist/states/shoping_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:adjust_specialist/states/token_state.dart';
import 'package:adjust_specialist/states/tutorial_state.dart';
import 'package:adjust_specialist/states/user_state.dart';

AppState appStateInit = AppState(
    userState: userStateInit,
    authenticationState: authenticationStateInit,
    clientState: clientStateInit,
    shopingState: shopingStateInit,
    tokenState: tokenStateInit,
    cartState: cartStateInit,
    tutorialState: tutorialStateInit,
    tutorialListState: tutorialListStateInit,
    clientTutorialsState: clientTutorialsStateInit,
    programListState: programListStateInit,
    specialistListState: specialistListStateInit,
    nutritionProgramState: nutritionProgramStateInit,
    fitnessProgramState: fitnessProgramStateInit,
    messagesState: messagesStateInit,
    );

class AppState {
  UserState userState;
  AuthenticationState authenticationState;
  ClientState clientState;
  ShopingState shopingState;
  TokenState tokenState;
  CartState cartState;
  TutorialState tutorialState;
  TutorialListState tutorialListState;
  ClientTutorialsState clientTutorialsState;
  ProgramListState programListState;
  SpecialistListState specialistListState;
  NutritionProgramState nutritionProgramState;
  FitnessProgramState fitnessProgramState;
  MessagesState messagesState;

  AppState(
      {this.userState,
      this.authenticationState,
      this.clientState,
      this.shopingState,
      this.tokenState,
      this.cartState,
      this.tutorialState,
      this.tutorialListState,
      this.clientTutorialsState,
      this.programListState,
      this.specialistListState,
      this.nutritionProgramState,
      this.fitnessProgramState,
      this.messagesState});
}
