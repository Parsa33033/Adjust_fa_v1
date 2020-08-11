import 'dart:collection';

import 'package:adjust_specialist/actions/program_action.dart';
import 'package:adjust_specialist/components/adjust_dialog.dart';
import 'package:adjust_specialist/components/adjust_dropdown_field.dart';
import 'package:adjust_specialist/components/adjust_gridview_selector.dart';
import 'package:adjust_specialist/components/adjust_raised_button.dart';
import 'package:adjust_specialist/components/adjust_text_field.dart';
import 'package:adjust_specialist/components/icon_stepper.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/dto/exercise_dto.dart';
import 'package:adjust_specialist/dto/fitness_program_dto.dart';
import 'package:adjust_specialist/dto/meal_dto.dart';
import 'package:adjust_specialist/dto/nutrition_dto.dart';
import 'package:adjust_specialist/dto/nutrition_program_dto.dart';
import 'package:adjust_specialist/dto/program_dto.dart';
import 'package:adjust_specialist/dto/workout_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/model/client.dart';
import 'package:adjust_specialist/model/move.dart';
import 'package:adjust_specialist/model/workout.dart';
import 'package:adjust_specialist/pages/main_page.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/nutrition_program_state.dart';
import 'package:adjust_specialist/states/nutrition_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:autocomplete_textfield/autocomplete_textfield.dart';
import 'package:curved_navigation_bar/curved_navigation_bar.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:expandable_card/expandable_card.dart';
import 'package:fl_chart/fl_chart.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:stepo/stepo.dart';
import 'package:string_validator/string_validator.dart';

class FitnessProgramPage extends StatefulWidget {
  int programIndex;
  ProgramState programState;

  FitnessProgramPage(this.programIndex, this.programState);

  @override
  _FitnessProgramPageState createState() => _FitnessProgramPageState();
}

class _FitnessProgramPageState extends State<FitnessProgramPage> {
  int selectedIndex = 0;
  int selectedWorkoutDaySetting = 0;
  String workoutTypeValue = "";
  FitnessProgramDTO fitnessProgramDTO;

  List<WorkoutDTO> workoutDTOList = new List<WorkoutDTO>();
  Map<WorkoutDTO, Set<MuscleType>> muscleTypesForWorkoutMap = new Map<WorkoutDTO, Set<MuscleType>>();
  int lastWorkoutDayNumber = 1;

  TextEditingController workoutTypeTextFieldController = TextEditingController();

  Widget _chooseMuscleTypesGrid = Container();

  @override
  initState() {

  }

  void setWorkoutTypeValue(String workoutTypeValue) {
    setState(() {
      this.workoutTypeValue = workoutTypeValue;
    });
  }

  Widget steps(BuildContext context, int index, AppState state) {
    if (index == 0) {
      return firstPageWorkoutSetting(context, state);
    } else if (index == 1) {
      return Container();
    } else if (index == 2) {
      return Container();
    } else if (index == 3) {
      return Container();
    } else if (index == 4) {
      return Container();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: WHITE_COLOR,
        appBar: AppBar(
          title: Align(
              alignment: Alignment.centerRight,
              child: FittedBox(
                fit: BoxFit.scaleDown,
                child: Directionality(
                  textDirection: TextDirection.rtl,
                  child: Text(
                    "برنامه ی تغذیه " +
                        this.widget.programState.clientState.firstName +
                        " " +
                        this.widget.programState.clientState.lastName,
                    style: TextStyle(
                        fontFamily: "Iransans",
                        fontSize: 20,
                        color: WHITE_COLOR),
                  ),
                ),
              )),
          backgroundColor: GREEN_COLOR,
          elevation: 4,
          leading: InkWell(
            child: Icon(Icons.arrow_back),
            onTap: () {
              Navigator.of(context).pop();
            },
          ),
        ),
        body: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            return Container(
              color: WHITE_COLOR,
              child: Row(
                children: <Widget>[
                  Container(
                    // margin: EdgeInsets.all(5.0),
                      decoration: BoxDecoration(
                        color: WHITE_COLOR,
                        boxShadow: [
                          BoxShadow(
                            color: Colors.grey,
                            spreadRadius: 1.0,
                            blurRadius: 2.0,
                          )
                        ],
                        // borderRadius: BorderRadius.circular(5.0),
                      ),
                      child: Container(
                        padding: EdgeInsets.only(top: 20),
                        child: IconStepper(
                          key: fitnessIconStepperStateKey,
                          direction: Axis.vertical,
                          stepColor: GREEN_COLOR,
                          activeStepColor: WHITE_COLOR,
                          enableNextPreviousButtons: false,
                          lineColor: GREEN_COLOR,
                          // lineDotRadius: 2,
                          lineLength: 75,
                          onStepReached: (value) {
                            setState(() {
                              selectedIndex = value;
                            });
                          },
                          enableStepTapping: false,
                          steppingEnabled: true,
                          icons: [
                            Icon(Icons.filter_1),
                            Icon(Icons.filter_2),
                            Icon(Icons.filter_3),
                            Icon(Icons.filter_4),
                            Icon(Icons.filter_5),
                          ],
                        ),
                      )),
//            Container(
//              decoration: BoxDecoration(
//                border: Border.all(width: 0.1),
//              ),
//              padding: EdgeInsets.all(8.0),
//              alignment: Alignment.centerLeft,
//              child: Text(header()),
//            ),
                  Expanded(
                    child: Container(
                      margin: EdgeInsets.all(5.0),
                      child: Center(
                        child: steps(context, selectedIndex, state),
                      ),
                    ),
                  ),
                ],
              ),
            );
          },

        ));
  }

  Widget firstPageWorkoutSetting(BuildContext context, AppState state) {

    return Container(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Expanded(
            flex: 20,
            child: AdjustDropDownField(
                itemsMap: WORKOUT_TYPE_LIST,
                items: [
                  WORKOUT_TYPE_LIST[EnumToString.parse(WorkoutType.POWER)],
                  WORKOUT_TYPE_LIST[EnumToString.parse(WorkoutType.BODYBUILDING_MASS)],
                  WORKOUT_TYPE_LIST[EnumToString.parse(WorkoutType.BODYBUILDING)],
                  WORKOUT_TYPE_LIST[EnumToString.parse(WorkoutType.BODYBUILDING_CUT)],
                  WORKOUT_TYPE_LIST[EnumToString.parse(WorkoutType.ENDURANCE)],
                  WORKOUT_TYPE_LIST[EnumToString.parse(WorkoutType.CARDIO)],
                ],
                value: workoutTypeValue == null
                    ? null
                    : GENDER_LIST[workoutTypeValue],
                setValue: setWorkoutTypeValue,
                textDirection: TextDirection.rtl,
                alignment: Alignment.centerRight,
                controller: workoutTypeTextFieldController,
                hintText: WORKOUT_TYPE,
                enabled: true,
                icon: Icon(
                  Icons.perm_identity,
                  color: YELLOW_COLOR,
                ),
                validator: (String val) {
                  if (val == null || val == "") {
                    return EMPTY;
                  } else
                    return null;
                },
                isPassword: false,
                primaryColor: YELLOW_COLOR,
                padding: 0,
                margin: 20),
          ),
          Expanded(
            flex: 15,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  flex: 2,
                  child: Directionality(
                    textDirection: TextDirection.rtl,
                    child: Text(WORKOUT_DAY_NUMBERS, style: TextStyle(fontFamily: "Iransans", color: FONT_COLOR, fontSize: 12),),
                  ),
                ),
                Expanded(
                  flex: 8,
                  child: Container(
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: <Widget>[
                          Expanded(
                            flex: 1,
                            child: Icon(Icons.keyboard_arrow_left),
                          ),
                          Expanded(
                            flex: 1,
                            child: InkWell(
                              child: Icon(Icons.add, size: 30,),
                              onTap: () {
                                setState(() {
                                  WorkoutDTO workoutDTOTemp = WorkoutDTO(null, lastWorkoutDayNumber, this.widget.programState.id, null);
                                  this.workoutDTOList.add(workoutDTOTemp);
                                  this.muscleTypesForWorkoutMap.putIfAbsent(workoutDTOTemp, () => Set());
                                  lastWorkoutDayNumber ++;
                                  if (this.workoutDTOList.length == 1) {
                                    _chooseMuscleTypesGrid = firstPageWorkoutSettingMuscleType();
                                  }
                                });
                              },
                            ),
                          ),
                          Expanded(
                            flex: 4,
                            child: Directionality(
                              textDirection: TextDirection.rtl,
                              child: ListView.builder(
                                scrollDirection: Axis.horizontal,
                                itemCount: this.workoutDTOList.length,
                                itemBuilder: (BuildContext context, int pos) {
                                  WorkoutDTO workoutDTO = this.workoutDTOList[pos];
                                  return InkWell(
                                      child: Container(
                                        height: 60,
                                        width: 60,
                                        margin: EdgeInsets.all(2),
                                        padding: EdgeInsets.all(5),
                                        child: FittedBox(
                                          fit: BoxFit.scaleDown,
                                          child: Column(
                                            children: <Widget>[
                                              Directionality(
                                                textDirection: TextDirection.rtl,
                                                child: Text(
                                                  NumberUtility.changeDigit("روز " + workoutDTO.dayNumber.toString(), NumStrLanguage.Farsi),
                                                  style: TextStyle(
                                                      fontFamily: "Iransans",
                                                      color: GREEN_COLOR,
                                                      fontSize: 15),
                                                ),
                                              ),
                                              this.selectedWorkoutDaySetting == pos ? Container(
                                                height: 10,
                                                width: 30,
                                                child: Divider(thickness: 2, color: GREEN_COLOR,),
                                              ) : Container()
                                            ],
                                          ),

                                        ),
                                      ),
                                      onTap: () {
                                        setState(() {
                                          this.selectedWorkoutDaySetting = pos;
                                          _chooseMuscleTypesGrid = firstPageWorkoutSettingMuscleType();
                                        });
                                       },
                                  );
                                },
                              ),
                            ),
                          ),
                          Expanded(
                            flex: 1,
                            child: InkWell(
                              child: Icon(Icons.remove, size: 30,),
                              onTap: () {
                                setState(() {
                                  this.workoutDTOList.removeLast();
                                  lastWorkoutDayNumber --;
                                });
                              },
                            ),
                          ),
                          Expanded(
                            flex: 1,
                            child: Icon(Icons.keyboard_arrow_right),
                          )
                        ],
                      )
                  ),
                )
              ],
            )
          ),
          Expanded(
            flex: 50,
            child: _chooseMuscleTypesGrid
          ),
          Expanded(
            flex: 10,
            child: Container(
              margin: EdgeInsets.all(10),
              child: AdjustRaisedButton(
                textDirection: TextDirection.rtl,
                width: 200,
                height: 60,
                fontColor: WHITE_COLOR,
                text: OK,
                secondaryColor: GREEN_COLOR,
                fontSize: 16,
                primaryColor: GREEN_COLOR,
                onPressed: () {
                  fitnessIconStepperStateKey.currentState.next();
                },
              ),
            )
          )
        ],
      ),
    );
  }

  Widget firstPageWorkoutSettingMuscleType() {
    WorkoutDTO workoutDTOTemp = this.workoutDTOList[this.selectedWorkoutDaySetting];
//    final muscleTypeSelectedMap = Map<String, bool>();
//    MUSCLE_TYPE_LIST.entries.map((e) => muscleTypeSelectedMap[e.key] = false);
    return Container(
      child: GridView.count(
          crossAxisCount: 2,
          children: MUSCLE_TYPE_LIST.entries.map((entry) {
            String muscleTypeString = entry.key;
            String muscleTypeTranslated = entry.value;
            return AdjustGridViewItem(
                selectedFontColor: WHITE_COLOR,
                selectedColor: GREEN_COLOR,
                name: muscleTypeTranslated,
                notSelectedFontColor: FONT_COLOR,
                notSelectedColor: WHITE_COLOR,
                selected: muscleTypesForWorkoutMap[workoutDTOTemp].contains(EnumToString.fromString(MuscleType.values, muscleTypeString)),
                isSelected: (value) {
                  if (value) {
                    this.muscleTypesForWorkoutMap[workoutDTOTemp].add(EnumToString.fromString(MuscleType.values, muscleTypeString));
                  } else {
                    this.muscleTypesForWorkoutMap[workoutDTOTemp].remove(EnumToString.fromString(MuscleType.values, muscleTypeString));
                  }
                });
          }).toList()
      ),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }
}
