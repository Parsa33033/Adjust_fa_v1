

import 'package:adjust_client/actions/authentication_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/authentication_state.dart';
import 'package:adjust_client/states/user_state.dart';

AppState authenticationReducer(AppState state, dynamic action) {
  if (action is AuthenticateAction) {
    AuthenticationState authenticationState = state.authenticationState;
    authenticationState.jwt =
    action.payload.jwt == "" || action.payload.jwt == null ? authenticationState
        .jwt : action.payload.jwt;
    AppState appState = state;
    appState.authenticationState = authenticationState;
    return appState;
  } else if (action is LoginAction) {
    UserState userState = state.userState;
    userState.login = userState.login == "" || userState.login == null ? action.payload.login : userState.login;
    userState.email = userState.email == "" || userState.email == null ? action.payload.email : userState.email;
    userState.firstName = userState.firstName == "" || userState.firstName == null ? action.payload.firstName : userState.firstName;
    userState.lastName = userState.lastName == "" || userState.lastName == null ? action.payload.lastName : userState.lastName;
    userState.langKey = userState.langKey == "" || userState.langKey == null ? action.payload.langKey : userState.langKey;
    AppState appState = state;
    appState.userState = userState;
    return appState;
  } else {
    return state;
  }
}