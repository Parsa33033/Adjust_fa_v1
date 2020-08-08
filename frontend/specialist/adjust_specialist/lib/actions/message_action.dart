import 'dart:convert';
import 'dart:io';

import 'package:adjust_specialist/actions/jwt.dart';
import 'package:adjust_specialist/constants/urls.dart';
import 'package:adjust_specialist/dto/message_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/model/message.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/message_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;

class GetMessagesAction {
  List<MessageState> payload;

  GetMessagesAction({this.payload});
}

Future<int> getMessages(
    BuildContext context, int clientId, int specialistId) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(
      MESSAGE_URL + "?client-id=${clientId}&specialist-id=${specialistId}",
      headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<MessageState> messageStateList = l.map((e) {
      MessageDTO messageDTO = MessageDTO.fromJson(e);
      MessageState messageState = MessageState(
          messageDTO.sender,
          messageDTO.receiver,
          messageDTO.message,
          messageDTO.clientId,
          messageDTO.specialistId);
      return messageState;
    }).toList();

    try {
      StoreProvider.of<AppState>(context).dispatch(GetMessagesAction(payload: messageStateList));
    } catch (e) {
      store.dispatch(GetMessagesAction(payload: messageStateList));
    }
    return 1;
  }
  return 0;
}
