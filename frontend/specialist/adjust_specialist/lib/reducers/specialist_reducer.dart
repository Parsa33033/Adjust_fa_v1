
import 'package:adjust_specialist/actions/specialist_action.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';

import 'package:adjust_specialist/actions/client_action.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/client_state.dart';

AppState specialistReducer(AppState state, dynamic action) {
  if (action is CreateSpecialistAction || action is UpdateSpecialistAction) {
    SpecialistState specialistState = state.specialistState;
    specialistState.id = action.payload.id != null ? action.payload.id : specialistState.id;
    specialistState.username = action.payload.username != null ? action.payload.username : specialistState.username;
    specialistState.firstName = action.payload.firstName != null ? action.payload.firstName : specialistState.firstName;
    specialistState.lastName = action.payload.lastName != null ? action.payload.lastName : specialistState.lastName;
    specialistState.stars = action.payload.stars != null ? action.payload.stars : specialistState.stars;
    specialistState.field = action.payload.field != null ? action.payload.field : specialistState.field;
    specialistState.degree = action.payload.degree != null ? action.payload.degree : specialistState.degree;
    specialistState.resume = action.payload.resume != null ? action.payload.resume : specialistState.resume;
    specialistState.busy = action.payload.busy != null ? action.payload.busy : specialistState.busy;
    specialistState.imageContentType = action.payload.imageContentType != null ? action.payload.imageContentType : specialistState.imageContentType;
    specialistState.image = action.payload.image != null ? action.payload.image : specialistState.image;
    specialistState.gender = action.payload.gender != null ? action.payload.gender : specialistState.gender;
    specialistState.birth = action.payload.birth != null ? action.payload.birth : specialistState.birth;
    state.specialistState = specialistState;
    return state;
  } else {
    return state;
  }

}
