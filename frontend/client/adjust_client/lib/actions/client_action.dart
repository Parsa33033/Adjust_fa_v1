import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';

import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/dto/client_dto.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;

import 'jwt.dart';

class CreateClientAction {
  ClientState payload;

  CreateClientAction({this.payload});
}

class UpdateClientAction {
  ClientState payload;

  UpdateClientAction({this.payload});
}

Future<int> updateClient(BuildContext context, ClientDTO clientDTO) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)
    ..putIfAbsent("Content-Type", () => "application/json");

  String content = jsonEncode(clientDTO.toJson());


  http.Response response = await http.put(CLIENT_URL,
      headers: headers, body: content, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    Map<String, dynamic> m = jsonDecode(utf8.decode(response.bodyBytes));


    ClientDTO client = ClientDTO.fromJson(m);
    setClientState(context, client);

    return 1;
  }
  return 0;
}

Future<int> getClient(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);
  http.Response response = await http.get(CLIENT_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    Map<String, dynamic> m = jsonDecode(utf8.decode(response.bodyBytes));

    ClientDTO clientDTO = ClientDTO.fromJson(m);
    Client client = clientDTO;
    setClientState(context, client);

    return 1;
  }
  return 0;
}

Future<int> getClientToken(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(GET_CLIENT_TOKENS_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    double token = double.parse(response.body);
    ClientState clientState = ClientState(
        null, null, null, null, null, null, null, token, null, null, null);
    StoreProvider.of<AppState>(context)
        .dispatch(UpdateClientAction(payload: clientState));
    return 1;
  }
  return 0;
}
