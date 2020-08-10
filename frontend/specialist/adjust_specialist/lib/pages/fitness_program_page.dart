import 'dart:convert';

import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/exercise_state.dart';
import 'package:adjust_specialist/states/fitness_program_state.dart';
import 'package:adjust_specialist/states/meal_state.dart';
import 'package:adjust_specialist/states/nutrition_program_state.dart';
import 'package:adjust_specialist/states/nutrition_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:adjust_specialist/states/workout_state.dart';
import 'package:expandable_card/expandable_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:spincircle_bottom_bar/modals.dart';
import 'package:spincircle_bottom_bar/spincircle_bottom_bar.dart';

class FitnessProgramPage extends StatefulWidget {
  int programIndex;

  FitnessProgramPage(this.programIndex);

  @override
  _FitnessProgramPageState createState() => _FitnessProgramPageState();
}

class _FitnessProgramPageState extends State<FitnessProgramPage> {
  Widget content;
  int selected;

  @override
  initState() {
    super.initState();
    content = Container();
    selected = -1;
  }

  void exerciseInfo(ExerciseState exercise) {
    showDialog(
        context: context,
        child: Dialog(
          child: Container(
              padding: EdgeInsets.all(10),
              child: SingleChildScrollView(
                child: Column(
                  children: <Widget>[
                    Align(
                      alignment: Alignment.center,
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          exercise.move.name,
                          style: TextStyle(
                              fontFamily: "Iransans",
                              color: FONT_COLOR,
                              fontSize: 16),
                        ),
                      ),
                    ),
                    Container(
                      height: 10,
                      width: 100,
                      child: Divider(
                        thickness: 2,
                        height: 5,
                      ),
                    ),
//                    Container(
//                      padding: EdgeInsets.all(10),
//                      child: Align(
//                        alignment: Alignment.center,
//                        child: Directionality(
//                          textDirection: TextDirection.rtl,
//                          child: Text(
//                            exercise.,
//                            style: TextStyle(
//                                fontFamily: "Iransans",
//                                color: FONT_COLOR,
//                                fontSize: 16),
//                          ),
//                        ),
//                      ),
//                    ),
                    Container(
                      alignment: Alignment.topCenter,
                      padding: EdgeInsets.all(5),
                      child: FittedBox(
                        fit: BoxFit.scaleDown,
                        child: DataTable(
                          columns: <DataColumn>[
                            DataColumn(
                                label: Expanded(
                                  child: Align(
                                    alignment: Alignment.centerRight,
                                    child: Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        "مقدار",
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: FONT_COLOR,
                                            fontSize: 13),
                                      ),
                                    ),
                                  ),
                                )
                            ),
                            DataColumn(
                                label: Expanded(
                                  child: Align(
                                    alignment: Alignment.centerRight,
                                    child: Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        "مشخصه",
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: FONT_COLOR,
                                            fontSize: 13),
                                      ),
                                    ),
                                  ),
                                )
                            ),
                          ],
                          rows: <DataRow>[
                            DataRow(cells: <DataCell>[
                              DataCell(Align(
                                alignment: Alignment.center,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    NumberUtility.changeDigit(
                                        exercise.sets.toString(),
                                        NumStrLanguage.Farsi),
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              )),
                              DataCell(Align(
                                alignment: Alignment.centerRight,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    SET,
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              ))
                            ]),
                            DataRow(cells: <DataCell>[
                              DataCell(Align(
                                alignment: Alignment.center,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    NumberUtility.changeDigit(
                                        exercise.repsMin.toString(),
                                        NumStrLanguage.Farsi),
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              )),
                              DataCell(Align(
                                alignment: Alignment.centerRight,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    MINREP,
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              ))
                            ]),
                            DataRow(cells: <DataCell>[
                              DataCell(Align(
                                alignment: Alignment.center,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    NumberUtility.changeDigit(
                                        exercise.repsMax.toString(),
                                        NumStrLanguage.Farsi),
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              )),
                              DataCell(Align(
                                alignment: Alignment.centerRight,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    MAXREP,
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              ))
                            ]),
                            DataRow(cells: <DataCell>[
                              DataCell(Align(
                                alignment: Alignment.center,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    NumberUtility.changeDigit(
                                        exercise.move.muscleName,
                                        NumStrLanguage.Farsi),
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              )),
                              DataCell(Align(
                                alignment: Alignment.centerRight,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    MUSCLE,
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              ))
                            ]),
                            DataRow(cells: <DataCell>[
                              DataCell(Align(
                                alignment: Alignment.center,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    NumberUtility.changeDigit(
                                        exercise.move.equipment,
                                        NumStrLanguage.Farsi),
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              )),
                              DataCell(Align(
                                alignment: Alignment.centerRight,
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    EQUIPMENT,
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 13),
                                  ),
                                ),
                              ))
                            ]),
                          ],
                        ),
                      )
                    ),
//                    Container(
//                      height: 55,
//                      alignment: Alignment.center,
//                      padding: EdgeInsets.all(10),
//                      child: Directionality(
//                        textDirection: TextDirection.rtl,
//                        child: Text(
//                          "انواع",
//                          style: TextStyle(
//                              fontFamily: "Iransans",
//                              color: FONT_COLOR,
//                              fontSize: 13),
//                        ),
//                      ),
//                    ),
                    Container(
                      height: 200,
                      width: 200,
                      padding: EdgeInsets.all(20),
                      child: exercise.move.picture != null ? Image.memory(base64Decode(exercise.move.picture)) : Container()
                    ),
                  ],
                ),
              )),
        ));
  }

  Widget fitnessTable(WorkoutState workout) {
    return Container(
        padding: EdgeInsets.all(10),
        alignment: Alignment.topCenter,
        child: FittedBox(
          fit: BoxFit.scaleDown,
          child: DataTable(
              columns: <DataColumn>[
                DataColumn(
                  label: Expanded(
                    child: Align(
                      alignment: Alignment.centerRight,
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          "تعداد ست و تکرار",
                          style: TextStyle(
                              fontFamily: "Iransans",
                              fontSize: 16,
                              color: FONT_COLOR),
                        ),
                      ),
                    ),
                  )
                ),
                DataColumn(
                  label: Expanded(
                    child: Align(
                      alignment: Alignment.centerRight,
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          "حرکت",
                          style: TextStyle(
                              fontFamily: "Iransans",
                              fontSize: 16,
                              color: FONT_COLOR),
                        ),
                      ),
                    ),
                  )
                )
              ],
              rows: workout.exercises.map((exercise) {
                return DataRow(cells: <DataCell>[
                  DataCell(InkWell(
                    child: Align(
                      alignment: Alignment.center,
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          NumberUtility.changeDigit(
                              exercise.sets.toString() +
                                  " ست - " +
                                  exercise.repsMin.toString() +
                                  " تا " +
                                  exercise.repsMax.toString() + " تکرار",
                              NumStrLanguage.Farsi),
                          style: TextStyle(
                              fontFamily: "Iransans",
                              fontSize: 16,
                              color: FONT_COLOR),
                        ),
                      ),
                    ),
                    onTap: () {
                      exerciseInfo(exercise);
                    },
                  )),
                  DataCell(InkWell(
                    child: Align(
                      alignment: Alignment.centerRight,
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          exercise.move.name,
                          style: TextStyle(
                              fontFamily: "Iransans",
                              fontSize: 16,
                              color: FONT_COLOR),
                        ),
                      ),
                    ),
                    onTap: () {
                      exerciseInfo(exercise);
                    },
                  )),
                ]);
              }).toList()),
        )
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Align(
          alignment: Alignment.centerRight,
          child: Directionality(
            textDirection: TextDirection.rtl,
            child: Text(
              "برنامه ی تمرینی",
              style: TextStyle(
                  fontFamily: "Iransans", fontSize: 20, color: WHITE_COLOR),
            ),
          ),
        ),
        backgroundColor: GREEN_COLOR,
        elevation: 4,
        leading: InkWell(
          child: Icon(Icons.arrow_back),
          onTap: () {
            Navigator.of(context).pop();
          },
        ),
      ),
      body: ExpandableCardPage(
        page: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            FitnessProgramState fitnessProgramState = state.programListState
                .programs.reversed.toList()[this.widget.programIndex].fitnessProgramState;
            return Container(
                height: MediaQuery.of(context).size.height,
                color: GREEN_COLOR.withAlpha(25),
                child: SingleChildScrollView(
                  child: Column(
                    children: <Widget>[
                      Container(
                        height: 60,
                        width: MediaQuery.of(context).size.width,
                        child: Directionality(
                          textDirection: TextDirection.rtl,
                          child: ListView.builder(
                            itemCount: fitnessProgramState.workouts.length,
                            scrollDirection: Axis.horizontal,
                            itemBuilder: (BuildContext context, int pos) {
                              WorkoutState workout =
                                  fitnessProgramState.workouts[pos];
                              return InkWell(
                                child: Column(
                                  children: <Widget>[
                                    Container(
                                      padding: EdgeInsets.only(
                                          left: 15,
                                          right: 15,
                                          top: 15,
                                          bottom: 5),
                                      child: Text(
                                        NumberUtility.changeDigit(
                                            "روز " +
                                                workout.dayNumber.toString(),
                                            NumStrLanguage.Farsi),
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: GREEN_COLOR,
                                            fontSize: 15),
                                      ),
                                    ),
                                    selected == pos
                                        ? Container(
                                            height: 5,
                                            width: 30,
                                            child: Divider(
                                              thickness: 2,
                                              indent: 1,
                                              endIndent: 1,
                                              height: 5,
                                              color: GREEN_COLOR,
                                            ),
                                          )
                                        : Container()
                                  ],
                                ),
                                onTap: () {
                                  setState(() {
                                    selected = pos;
                                    content = fitnessTable(workout);
                                  });
                                },
                              );
                            },
                          ),
                        ),
                      ),
                      this.content
                    ],
                  ),
                ));
          },
        ),
        expandableCard: ExpandableCard(
          minHeight: 200,
          backgroundColor: GREEN_COLOR,
          children: <Widget>[
            SingleChildScrollView(
              child: StoreConnector<AppState, AppState>(
                converter: (Store store) => store.state,
                builder: (BuildContext context, AppState state) {
                  FitnessProgramState fitnessProgramState = state
                      .programListState
                      .programs.reversed.toList()[this.widget.programIndex]
                      .fitnessProgramState;
                  return Container(
                      child: SingleChildScrollView(
                    child: Column(
                      children: <Widget>[
                        Container(
                          padding: EdgeInsets.all(20),
                          child: Align(
                            alignment: Alignment.centerRight,
                            child: Directionality(
                              textDirection: TextDirection.rtl,
                              child: Text(
                                "توضیحات:",
                                style: TextStyle(
                                    fontFamily: "Iransans",
                                    color: WHITE_COLOR,
                                    fontSize: 16),
                              ),
                            ),
                          ),
                        ),
                        Container(
                          height: 180,
                          child: SingleChildScrollView(
                              padding: EdgeInsets.all(15),
                              child: Column(
                                children: <Widget>[
                                  fitnessProgramState.description == null
                                      ? Text("")
                                      : Directionality(
                                          textDirection: TextDirection.rtl,
                                          child: Text(
                                            fitnessProgramState.description +
                                                "",
                                            style: TextStyle(
                                                fontFamily: "Iransans",
                                                color: WHITE_COLOR,
                                                fontSize: 16),
                                          ),
                                        ),
                                ],
                              )),
                        )
                      ],
                    ),
                  ));
                },
              ),
            )
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }
}
