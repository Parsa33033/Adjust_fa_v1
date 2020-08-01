import 'dart:convert';
import 'dart:io';

import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/dto/tutorial_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:http/http.dart' as http;
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/tutorial_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

import 'jwt.dart';

class GetTutorialsAction {
  TutorialListState payload;

  GetTutorialsAction({this.payload});
}

class GetClientTutorialsAction {
  ClientTutorialsState payload;

  GetClientTutorialsAction({this.payload});
}

class GetTutorialAction {
  TutorialState payload;

  GetTutorialAction({this.payload});
}

Future<int> getTutorials(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(TUTORIAL_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<TutorialDTO> items = l.map((e) {
      TutorialDTO tutorialDTO = TutorialDTO.fromJson(e);
      return tutorialDTO;
    }).toList();
    TutorialListState tutorialListState = TutorialListState(items);
    StoreProvider.of<AppState>(context)
        .dispatch(GetTutorialsAction(payload: tutorialListState));
    return 1;
  }
  return 0;
}

Future<int> buyTutorial(BuildContext context, int videoId) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)
    ..putIfAbsent("Content-Type", () => "application/json");

  http.Response response = await http.post(BUY_TUTORIAL_URL,
      headers: headers,
      body: videoId.toString(),
      encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    await getClientTutorials(context);
    return 1;
  } else if (response.statusCode == 422) {
    // client has the tutorial already
    return 2;
  } else if (response.statusCode == 424) {
    // client does not have enough token
    return 3;
  }
  return 0;
}

Future<int> getClientTutorials(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)
    ..putIfAbsent("Content-Type", () => "application/json");

  http.Response response =
      await http.get(GET_CLIENT_TUTORIALS_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<TutorialDTO> items = l.map((e) {
      return TutorialDTO.fromJson(e);
    }).toList();
    ClientTutorialsState tutorialListState = ClientTutorialsState(items);
    store.dispatch(GetClientTutorialsAction(payload: tutorialListState));
    return 1;
  }
  return 0;
}
