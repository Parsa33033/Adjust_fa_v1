

import 'package:adjust_client/actions/user_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/user_state.dart';

AppState userReducer(AppState state, dynamic action) {
  if (action is RegisterUserAction) {
    UserState userState = state.userState;
    userState.login = action.payload.login == "" || action.payload.login == null ? userState.login : action.payload.login;
    userState.email = action.payload.email == "" || action.payload.email == null ? userState.email : action.payload.email;
    userState.firstName = action.payload.firstName == "" || action.payload.firstName == null ? userState.firstName : action.payload.firstName;
    userState.lastName = action.payload.lastName == "" || action.payload.lastName == null ? userState.lastName : action.payload.lastName;
    userState.imageUrl = action.payload.imageUrl == "" || action.payload.imageUrl == null ? userState.imageUrl : action.payload.imageUrl;
    userState.langKey = action.payload.langKey == "" || action.payload.langKey == null ? userState.langKey : action.payload.langKey;
    AppState appState = state;
    appState.userState = userState;
    return appState;
  } else {
    return state;
  }
}