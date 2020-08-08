import 'dart:convert';
import 'dart:io';

import 'package:adjust_specialist/actions/jwt.dart';
import 'package:adjust_specialist/constants/urls.dart';
import 'package:adjust_specialist/dto/specialist_dto.dart';
import 'package:adjust_specialist/model/Specialist.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;

import '../main.dart';

class CreateSpecialistAction {
  SpecialistState payload;

  CreateSpecialistAction({this.payload});
}

class UpdateSpecialistAction {
  SpecialistState payload;

  UpdateSpecialistAction({this.payload});
}

Future<int> updateSpecialist(
    BuildContext context, SpecialistDTO specialistDTO) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)
    ..putIfAbsent("Content-Type", () => "application/json");

  String content = jsonEncode(specialistDTO.toJson());

  http.Response response = await http.put(SPECIALIST_URL,
      headers: headers, body: content, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    Map<String, dynamic> m = jsonDecode(utf8.decode(response.bodyBytes));

    SpecialistDTO specialistDTO = SpecialistDTO.fromJson(m);

    SpecialistState specialistState = SpecialistState(
        specialistDTO.id,
        specialistDTO.username,
        specialistDTO.firstName,
        specialistDTO.lastName,
        specialistDTO.birth,
        specialistDTO.gender,
        specialistDTO.degree,
        specialistDTO.field,
        specialistDTO.resume,
        specialistDTO.stars,
        specialistDTO.image,
        specialistDTO.imageContentType,
        specialistDTO.busy);

    try {
      StoreProvider.of<AppState>(context)
          .dispatch(UpdateSpecialistAction(payload: specialistState));
    } catch (e) {
      store.dispatch(UpdateSpecialistAction(payload: specialistState));
    }

    return 1;
  }
  return 0;
}

Future<int> getSpecialist(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);
  http.Response response = await http.get(SPECIALIST_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    Map<String, dynamic> m = jsonDecode(utf8.decode(response.bodyBytes));

    SpecialistDTO specialistDTO = SpecialistDTO.fromJson(m);

    SpecialistState specialistState = SpecialistState(
        specialistDTO.id,
        specialistDTO.username,
        specialistDTO.firstName,
        specialistDTO.lastName,
        specialistDTO.birth,
        specialistDTO.gender,
        specialistDTO.degree,
        specialistDTO.field,
        specialistDTO.resume,
        specialistDTO.stars,
        specialistDTO.image,
        specialistDTO.imageContentType,
        specialistDTO.busy);

    try {
      StoreProvider.of<AppState>(context).dispatch(UpdateSpecialistAction(payload: specialistState));
    } catch (e) {
      store.dispatch(UpdateSpecialistAction(payload: specialistState));
    }
    return 1;
  }
  return 0;
}
