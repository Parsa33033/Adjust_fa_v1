import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/client_state.dart';

AppState clientReducer(AppState state, dynamic action) {
  if (action is CreateClientAction) {
    ClientState clientState = state.clientState;
    clientState.id =
    action.payload.id == null ? clientState.id : action.payload.id;
    clientState.username =
    action.payload.username == null || action.payload.username == ""
        ? clientState.username
        : action.payload.username;
    clientState.firstName = action.payload.firstName == null ||
        action.payload.firstName == ""
        ? clientState.firstName
        : action.payload.firstName;
    clientState.lastName =
    action.payload.lastName == null || action.payload.lastName == ""
        ? clientState.lastName
        : action.payload.lastName;
    clientState.gender = action.payload.gender == null
        ? clientState.gender
        : action.payload.gender;
    clientState.age = action.payload.age == null
        ? clientState.age
        : action.payload.age;
    clientState.token = action.payload.token == null
        ? clientState.token
        : action.payload.token;
    clientState.score = action.payload.score == null
        ? clientState.score
        : action.payload.score;
    clientState.birthDate = action.payload.birthDate == null
        ? clientState.birthDate
        : action.payload.birthDate;
    clientState.image = action.payload.image == null
        ? clientState.image
        : action.payload.image;
    clientState.imageContentType =
    action.payload.imageContentType == null ||
        action.payload.imageContentType == ""
        ? clientState.imageContentType
        : action.payload.imageContentType;
    AppState appState = state;
    appState.clientState = clientState;
    return appState;
  } else if (action is UpdateClientAction) {
    ClientState clientState = state.clientState;
    clientState.id =
    action.payload.id == null ? clientState.id : action.payload.id;
    clientState.username =
    action.payload.username == null || action.payload.username == ""
        ? clientState.username
        : action.payload.username;
    clientState.firstName = action.payload.firstName == null ||
        action.payload.firstName == ""
        ? clientState.firstName
        : action.payload.firstName;
    clientState.lastName =
    action.payload.lastName == null || action.payload.lastName == ""
        ? clientState.lastName
        : action.payload.lastName;
    clientState.gender = action.payload.gender == null
        ? clientState.gender
        : action.payload.gender;
    clientState.age = action.payload.age == null
        ? clientState.age
        : action.payload.age;
    clientState.token = action.payload.token == null
        ? clientState.token
        : action.payload.token;
    clientState.score = action.payload.score == null
        ? clientState.score
        : action.payload.score;
    clientState.birthDate = action.payload.birthDate == null
        ? clientState.birthDate
        : action.payload.birthDate;
    clientState.image = action.payload.image == null
        ? clientState.image
        : action.payload.image;
    clientState.imageContentType =
    action.payload.imageContentType == null ||
        action.payload.imageContentType == ""
        ? clientState.imageContentType
        : action.payload.imageContentType;
    AppState appState = state;
    appState.clientState = clientState;
    return appState;
  } else {
    return state;
  }
}
