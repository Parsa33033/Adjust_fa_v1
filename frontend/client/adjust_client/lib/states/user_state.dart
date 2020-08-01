import 'package:adjust_client/actions/authentication_action.dart';
import 'package:adjust_client/model/user.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

import 'app_state.dart';

UserState userStateInit = UserState("", "", "", "", "", "fa");

class UserState extends User {
  UserState(String login, String firstName, String lastName, String email,
      String imageUrl, String langKey)
      : super(login, firstName, lastName, email, imageUrl, langKey);
}


void setUserState(BuildContext context, User user) {
  UserState userState = UserState(user.login, user.firstName, user.lastName,
      user.email, user.imageUrl, user.langKey);
  StoreProvider.of<AppState>(context).dispatch(LoginAction(payload: userState));
}

