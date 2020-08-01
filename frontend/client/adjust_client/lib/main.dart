import 'package:adjust_client/actions/app_init.dart';
import 'package:adjust_client/pages/login_page.dart';
import 'package:adjust_client/pages/logo_page.dart';
import 'package:adjust_client/pages/start_page.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/profile_page.dart';
import 'package:adjust_client/reducers/authentication_reducer.dart';
import 'package:adjust_client/reducers/client_reducer.dart';
import 'package:adjust_client/reducers/program_reducer.dart';
import 'package:adjust_client/reducers/shoping_item_reducer.dart';
import 'package:adjust_client/reducers/specialist_reducer.dart';
import 'package:adjust_client/reducers/tutorial_reducer.dart';
import 'package:adjust_client/reducers/user_reducer.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_phoenix/flutter_phoenix.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:redux/redux.dart';

Store<AppState> store;

void main() {
  final reducers = combineReducers<AppState>([
    TypedReducer<AppState, dynamic>(userReducer),
    TypedReducer<AppState, dynamic>(authenticationReducer),
    TypedReducer<AppState, dynamic>(clientReducer),
    TypedReducer<AppState, dynamic>(shopingItemReducer),
    TypedReducer<AppState, dynamic>(tutorialReducer),
    TypedReducer<AppState, dynamic>(programReducer),
    TypedReducer<AppState, dynamic>(specialistReducer),
  ]);
  store = Store<AppState>(reducers, initialState: appStateInit);

  WidgetsFlutterBinding.ensureInitialized();
  SystemChrome.setPreferredOrientations(
          [DeviceOrientation.portraitUp])
      .then((value) => runApp(Phoenix(child: MyApp(),)));
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return StoreProvider<AppState>(
      store: store,
      child: MaterialApp(
        title: 'Adjust Client',
        home: AppInit()
      ),
    );
  }
}

class AppInit extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: appInit(context),
      builder: (BuildContext context, AsyncSnapshot<int> snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return LogoPage();
        } else {
          if (snapshot.hasData) {
            int i = snapshot.data;
            if (i == 1) {
              return MainPage();
            } else if (i == 0){
              return StartPage();
            } else if (i == 2) {
              return ProfilePage(isFromMainPage: false,);
            } else {
              return StartPage();
            }
          } else {
            return LogoPage();
          }
        }
      },
    );
  }
}
