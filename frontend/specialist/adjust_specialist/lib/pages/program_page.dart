import 'dart:convert';
import 'dart:typed_data';
import 'dart:math';

import 'package:adjust_specialist/actions/jwt.dart';
import 'package:adjust_specialist/actions/message_action.dart';
import 'package:adjust_specialist/components/adjust_dialog.dart';
import 'package:adjust_specialist/components/adjust_info_button.dart';
import 'package:adjust_specialist/components/preloader.dart';
import 'package:adjust_specialist/config/localization.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/pages/body_composition_page.dart';
import 'package:adjust_specialist/pages/chat_page.dart';
import 'package:adjust_specialist/pages/program_development_page.dart';
import 'package:adjust_specialist/pages/fitness_program_page.dart';
import 'package:adjust_specialist/pages/nutrition_program_page.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:expansion_card/expansion_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_slidable/flutter_slidable.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

class ProgramPage extends StatefulWidget {
  @override
  _ProgramPageState createState() => _ProgramPageState();
}

class _ProgramPageState extends State<ProgramPage> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: StoreConnector<AppState, AppState>(
        converter: (Store store) => store.state,
        builder: (BuildContext context, AppState state) {
          return Container(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  child: ListView.builder(
                      itemCount: state.programListState.programs.length,
                      itemBuilder: (BuildContext context, int pos) {
                        ProgramState program = state
                            .programListState.programs.reversed
                            .toList()[pos];
                        return Slidable(
                          actionPane: SlidableDrawerActionPane(),
                          actions: <Widget>[
                            InkWell(
                              child: Container(
                                color: GREEN_COLOR,
                                padding: EdgeInsets.all(7),
                                child: Column(
                                  children: <Widget>[
                                    Align(
                                      alignment: Alignment.center,
                                      child: Container(
                                        height: 40,
                                        width: 40,
                                        child: Image.asset(
                                            "assets/workout_icon.png"),
                                      ),
                                    ),
                                    Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        "برنامه ورزشی",
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            fontSize: 13,
                                            color: WHITE_COLOR),
                                      ),
                                    )
                                  ],
                                ),
                              ),
                              onTap: () {
                                Navigator.of(context).push(MaterialPageRoute(
                                    builder: (context) =>
                                        FitnessProgramPage(pos, program)));
                              },
                            ),
                            InkWell(
                              child: Container(
                                color: RED_COLOR,
                                padding: EdgeInsets.all(7),
                                child: Column(
                                  children: <Widget>[
                                    Container(
                                      height: 40,
                                      width: 40,
                                      child: Image.asset(
                                          "assets/nutrition_icon.png"),
                                    ),
                                    Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        "برنامه تغذیه",
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            fontSize: 13,
                                            color: WHITE_COLOR),
                                      ),
                                    )
                                  ],
                                ),
                              ),
                              onTap: () {
                                Navigator.of(context).push(MaterialPageRoute(
                                    builder: (context) =>
                                        NutritionProgramPage(pos, program)));
                              },
                            )
                          ],
                          secondaryActions: <Widget>[
                            InkWell(
                              child: Container(
                                color: ORANGE_COLOR,
                                padding: EdgeInsets.all(7),
                                child: Column(
                                  children: <Widget>[
                                    Align(
                                      alignment: Alignment.center,
                                      child: Container(
                                          height: 40,
                                          width: 40,
                                          child: Icon(Icons.show_chart)),
                                    ),
                                    Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        "کامپوزیشن",
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            fontSize: 13,
                                            color: WHITE_COLOR),
                                      ),
                                    )
                                  ],
                                ),
                              ),
                              onTap: () {
                                if (program.bodyCompositionStateList.length >= 1) {
                                  Navigator.of(context).push(MaterialPageRoute(
                                      builder: (context) =>
                                          BodyCompositionPage(program)));
                                } else {
                                  showAdjustDialog(
                                      context,
                                      "برنامه ی شما طراحی نشده است!",
                                      false,
                                      null,
                                      ORANGE_COLOR);
                                }
                              },
                            ),
                            InkWell(
                              child: Container(
                                color: YELLOW_COLOR,
                                padding: EdgeInsets.all(7),
                                child: Column(
                                  children: <Widget>[
                                    Container(
                                        height: 40,
                                        width: 40,
                                        child: Icon(Icons.message)),
                                    Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        "سوال",
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            fontSize: 13,
                                            color: WHITE_COLOR),
                                      ),
                                    )
                                  ],
                                ),
                              ),
                              onTap: () async {
                                preloader(context);
                                int i = await getMessages(context, program.clientId, program.specialistId);
                                if (i == 1) {
                                  Navigator.of(context, rootNavigator: true).pop("dialog");
                                  Navigator.of(context).push(MaterialPageRoute(builder: (context) => ChatPage(program.specialistState, program.clientState)));
                                } else {
                                  Navigator.of(context, rootNavigator: true).pop("dialog");
                                  showAdjustDialog(context, FAILURE, false, null, ORANGE_COLOR);
                                }
                              },
                            )
                          ],
                          child: Container(
                            decoration: BoxDecoration(
                                color: WHITE_COLOR,
                                border: Border(
                                    bottom: BorderSide(
                                        width: 1, color: GREEN_COLOR))),
                            child: ExpansionCard(
                              backgroundColor: GREEN_COLOR.withAlpha(55),
                              trailing: Icon(
                                Icons.description,
                                color: GREEN_COLOR,
                                size: 40,
                              ),
                              title: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                children: <Widget>[
                                  Expanded(
                                    flex: 1,
                                    child: Icon(
                                      Icons.arrow_back_ios,
                                      color: FONT_COLOR,
                                    ),
                                  ),
                                  Expanded(
                                    flex: 8,
                                    child: Container(
                                      padding: EdgeInsets.only(
                                          left: 20,
                                          right: 20,
                                          bottom: 20,
                                          top: 0),
                                      alignment: Alignment.centerRight,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: <Widget>[
                                          Container(
                                            alignment: Alignment.centerRight,
                                            child: Directionality(
                                                textDirection:
                                                    TextDirection.rtl,
                                                child: Text(
                                                  NumberUtility.changeDigit(
                                                      "برنامه تغدیه و ورزشی تاریخ: " +
                                                          jalaliToString(
                                                              georgianToJalali(
                                                                  program
                                                                      .createdAt)),
                                                      NumStrLanguage.Farsi),
                                                  style: TextStyle(
                                                      fontFamily: "Iransans",
                                                      fontSize: 13,
                                                      color: FONT_COLOR),
                                                )),
                                          ),
                                          Container(
                                            alignment: Alignment.centerRight,
                                            child: Directionality(
                                              textDirection: TextDirection.rtl,
                                              child: Text(
                                                "کلاینت: " +
                                                    program.clientState
                                                        .firstName +
                                                    " " +
                                                    program.clientState
                                                        .lastName,
                                                style: TextStyle(
                                                    fontFamily: "Iransans",
                                                    fontSize: 13,
                                                    color: FONT_COLOR),
                                              ),
                                            ),
                                          )
                                        ],
                                      ),
                                    ),
                                  ),
                                  Expanded(
                                    flex: 1,
                                    child: Icon(
                                      Icons.arrow_forward_ios,
                                      color: FONT_COLOR,
                                    ),
                                  )
                                ],
                              ),
                              children: <Widget>[
                                Container(
                                  child: Text(
                                    "Content goes over here !",
                                  ),
                                )
                              ],
                            ),
                          ),
                        );
                      }),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
