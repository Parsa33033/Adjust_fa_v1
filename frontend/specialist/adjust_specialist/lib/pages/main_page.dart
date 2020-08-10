import 'dart:async';
import 'dart:convert';
import 'dart:typed_data';

import 'package:adjust_specialist/actions/program_action.dart';
import 'package:adjust_specialist/components/adjust_dialog.dart';
import 'package:adjust_specialist/components/dashboard.dart';
import 'package:adjust_specialist/components/preloader.dart';
import 'package:adjust_specialist/config/stomp.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/pages/fitness_program_page.dart';
import 'package:adjust_specialist/pages/menu_page.dart';
import 'package:adjust_specialist/pages/nutrition_program_page.dart';
import 'package:adjust_specialist/pages/program_page.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:curved_navigation_bar/curved_navigation_bar.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_zoom_drawer/flutter_zoom_drawer.dart';
import 'package:redux/redux.dart';
import 'package:stomp_dart_client/stomp.dart';

import '../main.dart';

final ZoomDrawerController zoomDrawerController = ZoomDrawerController();
final StreamController<int> mainPageStreamController =
    StreamController<int>.broadcast();
final Stream<int> mainPageStream = mainPageStreamController.stream;

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage>
    with SingleTickerProviderStateMixin {
  Widget _content;
  GlobalKey _bottomNavigationKey = GlobalKey();

  String firstName;
  String lastName;
  String field;
  double stars;
  Image _image;

  StompInstance stompInstance;
  StompClient stompClient;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    mainPageStream.asBroadcastStream().listen((event) {
      setMainPageState(true);
    });
    stompInstance = StompInstance.getInstance();
    stompInstance.stompClient.activate();

    fetchDependencies();

    _content = mainMenu();
    setMainPageState(false);
  }

  void fetchDependencies() async {
    await getAdjustNutritionList(context);
    await getSpecialistPrograms(context);
  }

  void setMainPageState(bool fromNotification) {
    SpecialistState specialistState = store.state.specialistState;
    if (fromNotification) {
      setState(() {
        stateSetter(specialistState);
      });
    } else {
      stateSetter(specialistState);
    }
  }

  void stateSetter(SpecialistState specialistState) {
    firstName = specialistState.firstName;
    lastName = specialistState.lastName;
    field = specialistState.field;
    stars = specialistState.stars;

    if (specialistState.image == null) {
      _image = Image.asset("assets/adjust_logo1.png");
    } else {
      Uint8List imageByte = Uint8List.fromList(base64Decode(specialistState.image));
      _image = Image.memory(imageByte);
    }
  }

  @override
  Widget build(BuildContext context) {
    return ZoomDrawer(
      controller: zoomDrawerController,
      menuScreen: MenuPage(image: _image),
      mainScreen: mainPage(),
      borderRadius: 24.0,
      showShadow: false,
      angle: 0.0,
      backgroundColor: Colors.grey[300],
      slideWidth: MediaQuery.of(context).size.width * (true ? -.45 : 0.65),
    );
  }

  Widget mainPage() {
    return SafeArea(
      child: Scaffold(
        bottomNavigationBar: CurvedNavigationBar(
          key: _bottomNavigationKey,
          backgroundColor: LIGHT_GREY_COLOR,
          color: GREEN_COLOR,
          index: 0,
          items: <Widget>[
            CircleAvatar(
                radius: 30, child: Image.asset("assets/adjust_logo1.png")),
          ],
          onTap: (index) {
            //Handle button tap
            if (index == 1) {
              setState(() {
                _content = mainMenu();
              });
            }
          },
        ),
        body: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            return Container(
                color: LIGHT_GREY_COLOR,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(
                        flex: 25,
                        child: Dashboard(
                          firstName: firstName,
                          lastName: lastName,
                          field: field,
                          stars: stars,
                          image: _image,
                        )),
                    Expanded(
                        flex: 5,
                        child: Container(
                          padding: EdgeInsets.only(top: 20),
                          child: Divider(
                            color: SHADOW_COLOR,
                            endIndent: 20,
                            indent: 20,
                            thickness: 2,
                          ),
                        )),
                    Expanded(flex: 70, child: _content),
                  ],
                ));
          },
        ),
      ),
    );
  }

  Widget mainMenu() {
    return Container(
      padding: EdgeInsets.only(bottom: 60, left: 20, right: 20, top: 20),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Expanded(
            flex: 5,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  flex: 5,
                  child: menuItem("برنامه ی تمرینی من",
                      "assets/workout_icon.png", GREEN_COLOR, () {
                    Store<AppState> store = StoreProvider.of<AppState>(context);
                    if (store.state.programListState.programs.length >= 1&&
                        store.state.programListState.programs.reversed
                            .toList()[0]
                            .fitnessProgramState != null) {
                      store.dispatch(SetFitnessProgramAction(
                          payload: store
                              .state.programListState.programs.reversed
                              .toList()[0]
                              .fitnessProgramState));
                      Navigator.of(context).push(MaterialPageRoute(
                          builder: (context) => FitnessProgramPage(0)));
                    } else {
                      showAdjustDialog(context, "برنامه ای موجود نمی باشد",
                          false, null, GREEN_COLOR);
                    }
                  }),
                ),
                Expanded(
                  flex: 5,
                  child: menuItem("برنامه ی تغذیه من",
                      "assets/nutrition_icon.png", RED_COLOR, () {
//                    Store<AppState> store = StoreProvider.of<AppState>(context);
//                    if (store.state.programListState.programs.length >= 1 &&
//                        store.state.programListState.programs.reversed
//                                .toList()[0]
//                                .nutritionProgramState != null) {
//                      store.dispatch(SetNutritionProgramAction(
//                          payload: store
//                              .state.programListState.programs.reversed
//                              .toList()[0]
//                              .nutritionProgramState));
//                      Navigator.of(context).push(MaterialPageRoute(
//                          builder: (context) => NutritionProgramPage(0)));
//                    } else {
//                      showAdjustDialog(context, "برنامه ای موجود نمی باشد",
//                          false, null, RED_COLOR);
//                    }
                  }),
                )
              ],
            ),
          ),
          Expanded(
            flex: 5,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  flex: 5,
                  child: menuItem("آموزش", "assets/game_icon.png", ORANGE_COLOR,
                      () async {
//                    preloader(context);
//                    int j = await getClientTutorials(context);
//                    int i = await getTutorials(context);
//                    if (i == 1 && j == 1) {
//                      Navigator.of(context, rootNavigator: true).pop("dialog");
//                      Navigator.of(context).push(MaterialPageRoute(
//                          builder: (context) => TutorialPage()));
//                    }
                  }),
                ),
                Expanded(
                  flex: 5,
                  child: menuItem(
                      "برنامه ها", "assets/tutorial_icon.png", YELLOW_COLOR,
                      () async {
                    preloader(context);
                    int i = await getSpecialistPrograms(context);
                    if (i == 1) {
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      Navigator.of(context).push(MaterialPageRoute(
                          builder: (context) => ProgramPage()));
                    } else {
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      showAdjustDialog(
                          context, FAILURE, false, null, GREEN_COLOR);
                    }
                  }),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget menuItem(String text, String imageAsset, Color color, Function func) {
    return InkWell(
      child: Container(
        padding: EdgeInsets.only(top: 15),
        margin: EdgeInsets.all(10),
        decoration: BoxDecoration(
          color: WHITE_COLOR,
          boxShadow: [
            BoxShadow(
                color: SHADOW_COLOR,
                offset: Offset(2, 2),
                spreadRadius: 5,
                blurRadius: 5)
          ],
          borderRadius: BorderRadius.all(Radius.circular(10)),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
              flex: 60,
              child: Image(
                image: AssetImage(imageAsset),
              ),
            ),
            Expanded(
              flex: 10,
              child: Divider(
                thickness: 1,
                color: color,
                indent: 10,
                endIndent: 10,
              ),
            ),
            Expanded(
              flex: 30,
              child: Container(
                  padding: EdgeInsets.all(7),
                  decoration: BoxDecoration(
                      color: color,
                      borderRadius: BorderRadius.only(
                          bottomLeft: Radius.circular(10),
                          bottomRight: Radius.circular(5))),
                  child: Center(
                    child: Directionality(
                      textDirection: TextDirection.rtl,
                      child: Text(
                        text,
                        style: TextStyle(
                            fontFamily: "Iransans",
                            fontSize: 14,
                            color: WHITE_COLOR),
                      ),
                    ),
                  )),
            )
          ],
        ),
      ),
      onTap: () {
        func();
      },
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }
}
