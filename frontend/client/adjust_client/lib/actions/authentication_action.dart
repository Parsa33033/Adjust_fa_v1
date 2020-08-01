import 'dart:convert';
import 'dart:io';

import 'package:adjust_client/actions/user_action.dart';
import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/dto/login_dto.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/authentication_state.dart';
import 'package:adjust_client/states/user_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;

class LoginAction {
  UserState payload;

  LoginAction({this.payload});
}

class AuthenticateAction {
  AuthenticationState payload;

  AuthenticateAction({this.payload});
}


Future<int> userLogin(BuildContext context, LoginDTO loginDTO) async {
  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Content-Type", () => "application/json");

  String content = jsonEncode(loginDTO.toJson());

  http.Response authResponse = await http.post(LOGIN_URL, headers: headers,
      body: content,
      encoding: Encoding.getByName("UTF-8"));
  if (authResponse.statusCode == HttpStatus.ok) {
    //get and store jwt
    String jwt = jsonDecode(authResponse.body)["id_token"];
    FlutterSecureStorage storage = FlutterSecureStorage();
    await storage.write(key: "jwt", value: jwt);
    StoreProvider.of<AppState>(context).dispatch(AuthenticateAction(payload: AuthenticationState(jwt: jwt)));


     int i = await getUser(context);
     if (i == 1) {
       i = await getClient(context);
       if (i == 1) {
         return 1;
       }
       return 0;
     }
     return 0;
  }
  return 0;
}