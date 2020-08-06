

import 'dart:async';
import 'dart:convert';

import 'package:adjust_client/actions/jwt.dart';
import 'package:adjust_client/dto/message_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

StompClient mainStompClient = StompClient();
class StompInstance {
  final StompClient stompClient = StompClient(
      config: StompConfig(
          url: 'ws://10.0.2.2:8080/websocket/websocket',
          stompConnectHeaders: getHeaders(),
          webSocketConnectHeaders: getHeaders(),
          onConnect: onConnected,
          onDisconnect: (StompFrame frame) {print("0---" + frame.body);},
          onWebSocketError: (err) {print("websocket error" + err.toString());},
          onStompError: (err) {print("stomp error" + err.toString());}
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
//    Map<String, String> headers = await getHeaders();
//    String username;
//    try {
//      username = StoreProvider.of<AppState>(context).state.userState.login;
//    } catch (e) {
//      username = store.state.userState.login;
//    }
//    StompClient client = StompClient(
//        config: StompConfig(
//            url: 'ws://10.0.2.2:8080/websocket/websocket',
//            stompConnectHeaders: headers,
//            webSocketConnectHeaders: headers,
//            onConnect: (StompClient client, StompFrame frame) {
//              print("connected");
//              client.subscribe(destination: "/topic/"+ username +"/reply", headers: headers, callback: (StompFrame frame) {
//                print("------->" + frame.body.toString());
//              });
//            },
//            onDisconnect: (StompFrame frame) {print("0---" + frame.body);},
//            onWebSocketError: (err) {print("websocket error" + err.toString());},
//            onStompError: (err) {print("stomp error" + err.toString());}
//        )
//    );
//    stompClient.activate();

    return stompInstance;
  }

  static void sendMessage(BuildContext context, StompClient stompClient, MessageDTO message) async {

    Map<String, String> headers = await getHeaders();
    stompClient.send(destination: "/app/websocket/chat.message", body: jsonEncode(message.toJson()), headers: headers);
  }
}