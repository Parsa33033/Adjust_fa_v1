
import 'package:adjust_client/actions/specialist_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/specialist_state.dart';

AppState specialistReducer(AppState state, dynamic action) {
  if (action is GetSpecialistListAction) {
    SpecialistListState specialistListState = state.specialistListState;
    specialistListState.specialists = action.payload.specialists != null ? action.payload.specialists : specialistListState.specialists;
    state.specialistListState = specialistListState;
    return state;
  } else {
    return state;
  }
}