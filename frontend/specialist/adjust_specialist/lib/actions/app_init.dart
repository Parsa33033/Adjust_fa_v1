import 'package:adjust_specialist/actions/authentication_action.dart';
import 'package:adjust_specialist/actions/specialist_action.dart';
import 'package:adjust_specialist/actions/user_action.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/authentication_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
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
      i = await getSpecialist(context);
      if (i == 1) {
        SpecialistState specialistState;
        try {
          specialistState = StoreProvider.of<AppState>(context).state.specialistState;
        } catch (e) {
          specialistState = store.state.specialistState;
        }
        if (specialistState.firstName != null && specialistState.firstName !="" &&
            specialistState.lastName != null && specialistState.lastName !="" &&
            specialistState.birth != null && specialistState.birth !="" &&
            specialistState.gender != null && specialistState.busy != null && specialistState.degree != null &&
            specialistState.field != null && specialistState.stars != null && specialistState.resume != null) {
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