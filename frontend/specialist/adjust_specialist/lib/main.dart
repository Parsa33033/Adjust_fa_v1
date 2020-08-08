import 'package:adjust_specialist/actions/app_init.dart';
import 'package:adjust_specialist/pages/login_page.dart';
import 'package:adjust_specialist/pages/logo_page.dart';
import 'package:adjust_specialist/pages/start_page.dart';
import 'package:adjust_specialist/pages/main_page.dart';
import 'package:adjust_specialist/pages/profile_page.dart';
import 'package:adjust_specialist/reducers/authentication_reducer.dart';
import 'package:adjust_specialist/reducers/message_reducer.dart';
import 'package:adjust_specialist/reducers/program_reducer.dart';
import 'package:adjust_specialist/reducers/specialist_reducer.dart';
import 'package:adjust_specialist/reducers/user_reducer.dart';
import 'package:adjust_specialist/states/app_state.dart';
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
    TypedReducer<AppState, dynamic>(specialistReducer),
    TypedReducer<AppState, dynamic>(programReducer),
    TypedReducer<AppState, dynamic>(messageReducer),
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
