

import 'package:adjust_specialist/actions/message_action.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/message_state.dart';

AppState messageReducer(AppState state, dynamic action) {
  if (action is GetMessagesAction) {
    MessagesState messagesState = state.messagesState;
    messagesState.messages = action.payload != null ? action.payload : messagesState;
    state.messagesState = messagesState;
    return state;
  } else {
    return state;
  }
}