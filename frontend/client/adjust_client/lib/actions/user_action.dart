import 'dart:convert';
import 'dart:io';

import 'package:adjust_client/actions/authentication_action.dart';
import 'package:adjust_client/config/database.dart';
import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/domains/user_domain.dart';
import 'package:adjust_client/dto/user_dto.dart';
import 'package:adjust_client/model/user.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/authentication_state.dart';
import 'package:adjust_client/states/user_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;
import 'package:jaguar_query_sqflite/jaguar_query_sqflite.dart';

import 'jwt.dart';

class RegisterUserAction {
  UserState payload;

  RegisterUserAction({this.payload});
}

Future<int> registerUser(BuildContext context, ManagedUserDTO userDTO) async {
  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent(
        "Authorization", () => "Basic " + base64.encode(utf8.encode(API_KEY)))
    ..putIfAbsent("Content-Type", () => "application/json");

  String content = jsonEncode(userDTO.toJson());

  http.Response response = await http.post(REGISTER_URL,
      headers: headers, body: content, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    // save jwt in storage
    String jwt = jsonDecode(response.body)["id_token"];
    FlutterSecureStorage storage = FlutterSecureStorage();
    await storage.write(key: "jwt", value: jwt);


    String login = userDTO.login;
    String email = userDTO.email;

    UserState userState = UserState(login, null, null, email, null, null);
    AuthenticationState authenticationState = AuthenticationState(jwt: jwt);

    StoreProvider.of<AppState>(context)
        .dispatch(RegisterUserAction(payload: userState));
    StoreProvider.of<AppState>(context)
        .dispatch(AuthenticateAction(payload: authenticationState));

    //return 1 for success
    return 1;
  }
  return 0;
}

Future<int> getUser(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(ACCOUNT_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    UserDTO userDTO = UserDTO.fromJson(jsonDecode(response.body));
    User user = userDTO;
    setUserState(context, user);
//    await setUserDomain(context, user);
    return 1;
  }
  return 0;
}

Future<int> sendPasswordRecoveryEmail(String email) async {
  http.Response response = await http.post(RECOVER_PASS_URL, body: email);
  if (response.statusCode == HttpStatus.ok) {
    return 1;
  }
  return 0;
}
