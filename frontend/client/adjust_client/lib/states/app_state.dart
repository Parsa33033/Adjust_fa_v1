import 'package:adjust_client/states/authentication_state.dart';
import 'package:adjust_client/states/cart_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/program_state.dart';
import 'package:adjust_client/states/shoping_state.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:adjust_client/states/token_state.dart';
import 'package:adjust_client/states/tutorial_state.dart';
import 'package:adjust_client/states/user_state.dart';

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
    specialistListState: specialistListStateInit
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
      this.specialistListState});
}
