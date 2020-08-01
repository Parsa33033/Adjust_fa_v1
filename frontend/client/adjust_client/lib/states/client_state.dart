import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/model/client.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

import 'app_state.dart';

final ClientState clientStateInit = ClientState(1, "", "", "", DateTime(2000), 0, null, 0, 0, null, "image/jpg");

class ClientState extends Client {
  ClientState(
      int id,
      String username,
      String firstName,
      String lastName,
      DateTime birthDate,
      int age,
      Gender gender,
      double token,
      double score,
      String image,
      String imageContentType)
      : super(id, username, firstName, lastName, birthDate, age, gender, token,
            score, image, imageContentType);
}



void setClientState(BuildContext context, Client client) {
  ClientState clientState = ClientState(
      client.id,
      client.username,
      client.firstName,
      client.lastName,
      client.birthDate,
      client.age,
      client.gender,
      client.token,
      client.score,
      client.image,
      client.imageContentType);

  StoreProvider.of<AppState>(context)
      .dispatch(UpdateClientAction(payload: clientState));
}
