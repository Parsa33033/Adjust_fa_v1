

import 'dart:async';
import 'dart:convert';

import 'package:adjust_specialist/actions/jwt.dart';
import 'package:adjust_specialist/constants/urls.dart';
import 'package:adjust_specialist/dto/message_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

StompClient mainStompClient = StompClient();
class StompInstance {
  final StompClient stompClient = StompClient(
      config: StompConfig(
          url: BASE_WS,
          stompConnectHeaders: getHeaders(),
          webSocketConnectHeaders: getHeaders(),
          onConnect: onConnected,
          onDisconnect: (StompFrame frame) {print("disconnected: " + frame.body);},
          onWebSocketError: (err) {print("websocket error: " + err.toString());},
          onStompError: (err) {print("stomp error: " + err.toString());}
      )
  );

  static final StompInstance stompInstance = StompInstance();

  static dynamic onConnected(StompClient client, StompFrame frame) {
    print("connected");
    mainStompClient = client;
    return client;
  }

  static String getUsername() {
    return store.state.userState.login;
  }

  static Map<String, String> getHeaders() {
    String jwt = store.state.authenticationState.jwt;
    Map<String, String> headers = Map<String, String>()
      ..putIfAbsent("Authorization", () => "Bearer " + jwt);
    return headers;
  }

  static StompInstance getInstance() {
    return stompInstance;
  }

  static void sendMessage(BuildContext context, StompClient stompClient, MessageDTO message) async {

    Map<String, String> headers = await getHeaders();
    stompClient.send(destination: MESSAGE_MAPPING, body: jsonEncode(message.toJson()), headers: headers);
  }
}