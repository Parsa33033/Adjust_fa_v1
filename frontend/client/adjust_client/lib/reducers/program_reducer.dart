

import 'package:adjust_client/actions/program_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/program_state.dart';

AppState programReducer(AppState state, dynamic action) {
  if (action is GetProgramListAction) {
    ProgramListState programListState = state.programListState;
    programListState.programs = action.payload.programs != null ? action.payload.programs : state.programListState;
    state.programListState = programListState;
    return state;
  } else {
    return state;
  }
}