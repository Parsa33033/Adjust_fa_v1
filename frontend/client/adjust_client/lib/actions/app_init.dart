import 'package:adjust_client/actions/authentication_action.dart';
import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/actions/user_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/authentication_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

Future<int> appInit(BuildContext context) async{

  FlutterSecureStorage storage = FlutterSecureStorage();
  String jwt = await storage.read(key: "jwt");
  if (jwt != null) {
    StoreProvider.of<AppState>(context).dispatch(AuthenticateAction(payload: AuthenticationState(jwt: jwt)));
    int i = await getUser(context);
    if (i == 1) {
      i = await getClient(context);
      if (i == 1) {
        ClientState clientState = StoreProvider.of<AppState>(context).state.clientState;
        if (clientState.firstName != null && clientState.firstName !="" &&
            clientState.lastName != null && clientState.lastName !="" &&
            clientState.birthDate != null && clientState.birthDate !="" &&
            clientState.gender != null) {
          return 1;
        } else {
          return 2;
        }
      }
      return 0;
    }
    return 0;
  }
  return 0;
}