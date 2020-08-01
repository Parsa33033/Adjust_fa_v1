import 'dart:convert';
import 'dart:io';

import 'package:adjust_client/actions/jwt.dart';
import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/dto/specialist_dto.dart';
import 'package:adjust_client/model/Specialist.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;

import '../main.dart';

class GetSpecialistListAction {
  SpecialistListState payload;

  GetSpecialistListAction({this.payload});
}


Future<int> getSpecialistList(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(SPECIALIST_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<SpecialistState> specialistList = l.map((e) {
      SpecialistDTO specialistDTO = SpecialistDTO.fromJson(e);
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
      return specialistState;
    }).toList();
    SpecialistListState specialistListState = SpecialistListState(specialistList);
    store.dispatch(GetSpecialistListAction(payload: specialistListState));
    return 1;
  }
  return 0;
}