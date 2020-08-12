import 'dart:collection';
import 'dart:convert';

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
import 'package:adjust_specialist/dto/move_dto.dart';
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
import 'package:adjust_specialist/states/move_state.dart';
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
  int selectedWorkoutDayForMoveSetting = 0;
  int selectedWorkoutDayForMoveDesign = 0;
  String workoutTypeValue = "";
  FitnessProgramDTO fitnessProgramDTO;

  List<WorkoutDTO> workoutDTOList = new List<WorkoutDTO>();

  Map<WorkoutDTO, Map<MuscleType, Map<int, ExerciseDTO>>>
      muscleTypesForWorkoutMap =
      new Map<WorkoutDTO, Map<MuscleType, Map<int, ExerciseDTO>>>();
  Map<WorkoutDTO, List<ExerciseDTO>> workoutExerciseMap =
      new Map<WorkoutDTO, List<ExerciseDTO>>();
  Map<WorkoutDTO, int> selectedMuscleForWorkout = Map<WorkoutDTO, int>();
  int lastWorkoutDayNumber = 1;

  TextEditingController workoutTypeTextFieldController =
      TextEditingController();
  TextEditingController descriptionTextEditingController =
      TextEditingController();

  Widget _chooseMuscleTypesGrid = Container();
  Widget _chooseMoveListTile = Container();
  Widget _setMoveListTile = Container();

  @override
  initState() {}

  void setWorkoutTypeValue(String workoutTypeValue) {
    setState(() {
      this.workoutTypeValue = workoutTypeValue;
    });
  }

  Widget steps(BuildContext context, int index, AppState state) {
    if (index == 0) {
      return firstPageWorkoutSetting(context, state);
    } else if (index == 1) {
      return secondPageExerciseSetting(context, state);
    } else if (index == 2) {
      return thirdPageExerciseSetting(context, state);
    } else if (index == 3) {
      return descriptionPage();
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
                  WORKOUT_TYPE_LIST[
                      EnumToString.parse(WorkoutType.BODYBUILDING_MASS)],
                  WORKOUT_TYPE_LIST[
                      EnumToString.parse(WorkoutType.BODYBUILDING)],
                  WORKOUT_TYPE_LIST[
                      EnumToString.parse(WorkoutType.BODYBUILDING_CUT)],
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
                      child: Text(
                        WORKOUT_DAY_NUMBERS,
                        style: TextStyle(
                            fontFamily: "Iransans",
                            color: FONT_COLOR,
                            fontSize: 12),
                      ),
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
                            child: Icon(
                              Icons.add,
                              size: 30,
                            ),
                            onTap: () {
                              setState(() {
                                WorkoutDTO workoutDTOTemp = WorkoutDTO(
                                    null,
                                    lastWorkoutDayNumber,
                                    this.widget.programState.id,
                                    null);
                                this.workoutDTOList.add(workoutDTOTemp);
                                this
                                    .muscleTypesForWorkoutMap
                                    .putIfAbsent(workoutDTOTemp, () => Map());
                                this
                                    .workoutExerciseMap
                                    .putIfAbsent(workoutDTOTemp, () => List());
                                lastWorkoutDayNumber++;
                                if (this.workoutDTOList.length == 1) {
                                  _chooseMuscleTypesGrid =
                                      firstPageWorkoutSettingMuscleType();
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
                                WorkoutDTO workoutDTO =
                                    this.workoutDTOList[pos];
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
                                              NumberUtility.changeDigit(
                                                  "روز " +
                                                      workoutDTO.dayNumber
                                                          .toString(),
                                                  NumStrLanguage.Farsi),
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  color: GREEN_COLOR,
                                                  fontSize: 15),
                                            ),
                                          ),
                                          this.selectedWorkoutDaySetting == pos
                                              ? Container(
                                                  height: 10,
                                                  width: 30,
                                                  child: Divider(
                                                    thickness: 2,
                                                    color: GREEN_COLOR,
                                                  ),
                                                )
                                              : Container()
                                        ],
                                      ),
                                    ),
                                  ),
                                  onTap: () {
                                    setState(() {
                                      this.selectedWorkoutDaySetting = pos;
                                      _chooseMuscleTypesGrid =
                                          firstPageWorkoutSettingMuscleType();
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
                            child: Icon(
                              Icons.remove,
                              size: 30,
                            ),
                            onTap: () {
                              setState(() {
                                this.workoutDTOList.removeLast();
                                lastWorkoutDayNumber--;
                              });
                            },
                          ),
                        ),
                        Expanded(
                          flex: 1,
                          child: Icon(Icons.keyboard_arrow_right),
                        )
                      ],
                    )),
                  )
                ],
              )),
          Expanded(flex: 50, child: _chooseMuscleTypesGrid),
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
                    if (this.workoutTypeValue != "" &&
                        this.workoutTypeValue != null) {
                      if (this.workoutDTOList.length > 0) {
                        this.selectedMuscleForWorkout = this
                            .muscleTypesForWorkoutMap
                            .map((key, value) => MapEntry(key, 0));
                        fitnessIconStepperStateKey.currentState.next();
                      } else {
                        showAdjustDialog(context, ATLEAST_ONE_WORKOUT_DAY,
                            false, null, GREEN_COLOR);
                      }
                    } else {
                      showAdjustDialog(context, WORKOUT_TYPE_EMPTY, false, null,
                          GREEN_COLOR);
                    }
                  },
                ),
              ))
        ],
      ),
    );
  }

  Widget firstPageWorkoutSettingMuscleType() {
    WorkoutDTO workoutDTOTemp =
        this.workoutDTOList[this.selectedWorkoutDaySetting];
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
                selected: muscleTypesForWorkoutMap[workoutDTOTemp].containsKey(
                    EnumToString.fromString(
                        MuscleType.values, muscleTypeString)),
                isSelected: (value) {
                  if (value) {
                    this.muscleTypesForWorkoutMap[workoutDTOTemp].putIfAbsent(
                        EnumToString.fromString(
                            MuscleType.values, muscleTypeString),
                        () => Map());
                  } else {
                    this.muscleTypesForWorkoutMap[workoutDTOTemp].remove(
                        EnumToString.fromString(
                            MuscleType.values, muscleTypeString));
                  }
                });
          }).toList()),
    );
  }

  Widget secondPageExerciseSetting(BuildContext context, AppState state) {
    return Container(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Expanded(
              flex: 10,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
//                  Expanded(
//                    flex: 2,
//                    child: Directionality(
//                      textDirection: TextDirection.rtl,
//                      child: Text(WORKOUT_DAY_NUMBERS, style: TextStyle(fontFamily: "Iransans", color: FONT_COLOR, fontSize: 12),),
//                    ),
//                  ),
                  Expanded(
//                    flex: 8,
                    child: Container(
                        child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        Expanded(
                          flex: 1,
                          child: Icon(Icons.keyboard_arrow_left),
                        ),
                        Expanded(
                          flex: 6,
                          child: Directionality(
                            textDirection: TextDirection.rtl,
                            child: ListView.builder(
                              scrollDirection: Axis.horizontal,
                              itemCount: this.workoutDTOList.length,
                              itemBuilder: (BuildContext context, int pos) {
                                WorkoutDTO workoutDTO =
                                    this.workoutDTOList[pos];
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
                                              NumberUtility.changeDigit(
                                                  "روز " +
                                                      workoutDTO.dayNumber
                                                          .toString(),
                                                  NumStrLanguage.Farsi),
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  color: GREEN_COLOR,
                                                  fontSize: 15),
                                            ),
                                          ),
                                          this.selectedWorkoutDayForMoveSetting ==
                                                  pos
                                              ? Container(
                                                  height: 10,
                                                  width: 30,
                                                  child: Divider(
                                                    thickness: 2,
                                                    color: GREEN_COLOR,
                                                  ),
                                                )
                                              : Container()
                                        ],
                                      ),
                                    ),
                                  ),
                                  onTap: () {
                                    setState(() {
                                      this.selectedWorkoutDayForMoveSetting =
                                          pos;
                                      WorkoutDTO workoutDTOTemp = this
                                              .workoutDTOList[
                                          this.selectedWorkoutDayForMoveSetting];
                                      this.selectedMuscleForWorkout[
                                          workoutDTOTemp] = 0;
                                      _chooseMoveListTile =
                                          secondPageExerciseSettingMuscleType(
                                              context, state);
                                    });
                                  },
                                );
                              },
                            ),
                          ),
                        ),
                        Expanded(
                          flex: 1,
                          child: Icon(Icons.keyboard_arrow_right),
                        )
                      ],
                    )),
                  )
                ],
              )),
          Expanded(
              flex: 10,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
//                  Expanded(
//                    flex: 2,
//                    child: Directionality(
//                      textDirection: TextDirection.rtl,
//                      child: Text(WORKOUT_DAY_NUMBERS, style: TextStyle(fontFamily: "Iransans", color: FONT_COLOR, fontSize: 12),),
//                    ),
//                  ),
                  Expanded(
//                    flex: 8,
                    child: Container(
                        child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        Expanded(
                          flex: 1,
                          child: Icon(Icons.keyboard_arrow_left),
                        ),
                        Expanded(
                          flex: 6,
                          child: Directionality(
                            textDirection: TextDirection.rtl,
                            child: ListView.builder(
                              scrollDirection: Axis.horizontal,
                              itemCount: this
                                  .muscleTypesForWorkoutMap[this.workoutDTOList[
                                      this.selectedWorkoutDayForMoveSetting]]
                                  .length,
                              itemBuilder: (BuildContext context, int pos) {
                                WorkoutDTO workoutDTOTemp = this.workoutDTOList[
                                    this.selectedWorkoutDayForMoveSetting];
                                MuscleType muscleType = this
                                    .muscleTypesForWorkoutMap[workoutDTOTemp]
                                    .keys
                                    .toList()[pos];
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
                                              MUSCLE_TYPE_LIST[
                                                  EnumToString.parse(
                                                      muscleType)],
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  color: GREEN_COLOR,
                                                  fontSize: 15),
                                            ),
                                          ),
                                          this.selectedMuscleForWorkout[
                                                      workoutDTOTemp] ==
                                                  pos
                                              ? Container(
                                                  height: 10,
                                                  width: 30,
                                                  child: Divider(
                                                    thickness: 2,
                                                    color: GREEN_COLOR,
                                                  ),
                                                )
                                              : Container()
                                        ],
                                      ),
                                    ),
                                  ),
                                  onTap: () {
                                    setState(() {
                                      this.selectedMuscleForWorkout[
                                          workoutDTOTemp] = pos;
                                      _chooseMoveListTile =
                                          secondPageExerciseSettingMuscleType(
                                              context, state);
                                    });
                                  },
                                );
                              },
                            ),
                          ),
                        ),
                        Expanded(
                          flex: 1,
                          child: Icon(Icons.keyboard_arrow_right),
                        )
                      ],
                    )),
                  )
                ],
              )),
          Expanded(flex: 50, child: _chooseMoveListTile),
          Expanded(
              flex: 10,
              child: Container(
                padding: EdgeInsets.all(10),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(
                      flex: 5,
                      child: Container(
                        margin: EdgeInsets.all(10),
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          width: 200,
                          height: 60,
                          fontColor: WHITE_COLOR,
                          text: BACK,
                          secondaryColor: GREEN_COLOR,
                          fontSize: 16,
                          primaryColor: GREEN_COLOR,
                          onPressed: () {
                            fitnessIconStepperStateKey.currentState.previous();
                          },
                        ),
                      ),
                    ),
                    Expanded(
                      flex: 5,
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
                            selectedWorkoutDayForMoveDesign = 0;
                            _setMoveListTile =
                                thirdPageExerciseSettingMuscleType(
                                    context, state);
                            fitnessIconStepperStateKey.currentState.next();
                          },
                        ),
                      ),
                    ),
                  ],
                ),
              ))
        ],
      ),
    );
  }

  Widget secondPageExerciseSettingMuscleType(
      BuildContext context, AppState state) {
    WorkoutDTO workoutDTOTemp =
        this.workoutDTOList[this.selectedWorkoutDayForMoveSetting];
    int selectedMuscleIndexForWorkout =
        this.selectedMuscleForWorkout[workoutDTOTemp];
    MuscleType muscleType = this
        .muscleTypesForWorkoutMap[workoutDTOTemp]
        .keys
        .toList()[selectedMuscleIndexForWorkout];
    List<MoveState> moveStateList = state.moveStateList.moves
        .where((move) => move.muscleType == muscleType)
        .toList();
    return Container(
      child: ListView(
          children: moveStateList.map((moveState) {
        Image image = moveState.picture == null || moveState.picture == ""
            ? null
            : Image.memory(base64Decode(moveState.picture));
        ExerciseType exerciseType = exerciseForWorkout[
            EnumToString.fromString(WorkoutType.values, this.workoutTypeValue)];
        MoveDTO moveDTO = MoveDTO(
            moveState.id,
            moveState.name,
            moveState.muscleName,
            moveState.muscleType,
            moveState.equipment,
            moveState.picture,
            moveState.pictureContentType,
            moveState.adjustMoveId);
        ExerciseDTO exerciseDTO = ExerciseDTO(
            null,
            null,
            exerciseType.sets,
            exerciseType.minReps,
            exerciseType.maxReps,
            moveState.id,
            null,
            moveDTO);
        return AdjustGridViewItem(
            selectedFontColor: WHITE_COLOR,
            selectedColor: GREEN_COLOR,
            name: moveState.name,
            notSelectedFontColor: FONT_COLOR,
            notSelectedColor: WHITE_COLOR,
            hasPicture: true,
            picture: image,
            selected: muscleTypesForWorkoutMap[workoutDTOTemp][muscleType]
                .containsKey(moveState.id),
            isSelected: (value) {
              if (value) {
                muscleTypesForWorkoutMap[workoutDTOTemp][muscleType]
                    .putIfAbsent(moveState.id, () => exerciseDTO);
                workoutExerciseMap[workoutDTOTemp].add(exerciseDTO);
              } else {
                muscleTypesForWorkoutMap[workoutDTOTemp][muscleType]
                    .remove(moveState.id);
                workoutExerciseMap[workoutDTOTemp].removeWhere((element) => element.move.name == exerciseDTO.move.name);
              }
            });
      }).toList()),
    );
  }

  Widget thirdPageExerciseSetting(BuildContext context, AppState state) {
    return Container(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Expanded(
              flex: 10,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                    child: Container(
                        child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        Expanded(
                          flex: 1,
                          child: Icon(Icons.keyboard_arrow_left),
                        ),
                        Expanded(
                          flex: 6,
                          child: Directionality(
                            textDirection: TextDirection.rtl,
                            child: ListView.builder(
                              scrollDirection: Axis.horizontal,
                              itemCount: this.workoutDTOList.length,
                              itemBuilder: (BuildContext context, int pos) {
                                WorkoutDTO workoutDTO =
                                    this.workoutDTOList[pos];
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
                                              NumberUtility.changeDigit(
                                                  "روز " +
                                                      workoutDTO.dayNumber
                                                          .toString(),
                                                  NumStrLanguage.Farsi),
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  color: GREEN_COLOR,
                                                  fontSize: 15),
                                            ),
                                          ),
                                          this.selectedWorkoutDayForMoveDesign ==
                                                  pos
                                              ? Container(
                                                  height: 10,
                                                  width: 30,
                                                  child: Divider(
                                                    thickness: 2,
                                                    color: GREEN_COLOR,
                                                  ),
                                                )
                                              : Container()
                                        ],
                                      ),
                                    ),
                                  ),
                                  onTap: () {
                                    setState(() {
                                      selectedWorkoutDayForMoveDesign = pos;
                                      _setMoveListTile =
                                          thirdPageExerciseSettingMuscleType(
                                              context, state);
                                    });
                                  },
                                );
                              },
                            ),
                          ),
                        ),
                        Expanded(
                          flex: 1,
                          child: Icon(Icons.keyboard_arrow_right),
                        )
                      ],
                    )),
                  )
                ],
              )),
          Expanded(flex: 50, child: _setMoveListTile),
          Expanded(
              flex: 10,
              child: Container(
                padding: EdgeInsets.all(10),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(
                      flex: 5,
                      child: Container(
                        margin: EdgeInsets.all(10),
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          width: 200,
                          height: 60,
                          fontColor: WHITE_COLOR,
                          text: BACK,
                          secondaryColor: GREEN_COLOR,
                          fontSize: 16,
                          primaryColor: GREEN_COLOR,
                          onPressed: () {
                            fitnessIconStepperStateKey.currentState.previous();
                          },
                        ),
                      ),
                    ),
                    Expanded(
                      flex: 5,
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
                            this.workoutDTOList =
                                this.workoutDTOList.map((workoutDTOTemp) {
                              workoutDTOTemp.exercises =
                                  this.workoutExerciseMap[workoutDTOTemp];
                              return workoutDTOTemp;
                            }).toList();
                            fitnessIconStepperStateKey.currentState.next();
                          },
                        ),
                      ),
                    ),
                  ],
                ),
              ))
        ],
      ),
    );
  }

  Widget thirdPageExerciseSettingMuscleType(
      BuildContext context, AppState state) {
    return Container(
      child: ReorderableListView(
          header: Directionality(
            textDirection: TextDirection.rtl,
            child: Text(
              "برای تغییر در ترتیب حرکات بر روی حرکت به صورت طولانی فشار داده و سپس حرکت دهید!",
              style: TextStyle(
                  fontFamily: "Iransans", fontSize: 12, color: FONT_COLOR),
            ),
          ),
          onReorder: (int oldIndex, int newIndex) {
            setState(() {
              // These two lines are workarounds for ReorderableListView problems
              if (oldIndex < newIndex) {
                // removing the item at oldIndex will shorten the list by 1.
                newIndex -= 1;
              }
              final ExerciseDTO element = this
                  .workoutExerciseMap[
                      this.workoutDTOList[this.selectedWorkoutDayForMoveDesign]]
                  .removeAt(oldIndex);
              this
                  .workoutExerciseMap[
                      this.workoutDTOList[this.selectedWorkoutDayForMoveDesign]]
                  .insert(newIndex, element);
              _setMoveListTile =
                  thirdPageExerciseSettingMuscleType(context, state);
            });
          },
          children: this
              .workoutExerciseMap[
                  this.workoutDTOList[this.selectedWorkoutDayForMoveDesign]]
              .map((exercise) {
            return Container(
                key: Key(exercise.toJson().toString()),
                margin: EdgeInsets.all(5),
                height: 100,
                width: MediaQuery.of(context).size.width,
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(10)),
                    border: Border.all(color: GREEN_COLOR, width: 2),
                    color: WHITE_COLOR),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(
                      flex: 5,
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: <Widget>[
                          Expanded(
                              flex: 2,
                              child: Container(
                                padding: EdgeInsets.all(2),
                                child: Stepo(
                                  key: UniqueKey(),
                                  width: 50,
                                  //Optional
                                  backgroundColor: Colors.deepPurple,
                                  //Optional
                                  style: Style.vertical,
                                  //Optional
                                  textColor: Colors.white,
                                  //Optional
                                  animationDuration: Duration(milliseconds: 0),
                                  //Optional
                                  iconColor: Colors.white,
                                  //Optional
                                  fontSize: 20,
                                  //Optional
                                  iconSize: 30,
                                  //Optional
                                  initialCounter: exercise.sets,
                                  //Optional
                                  lowerBound: 0,
                                  //Optional
                                  upperBound: 100,
                                  //Optional
                                  onIncrementClicked: (counter) {
                                    //Optional
                                    if (this.mounted) {
                                      setState(() {
                                        exercise.sets = counter;
                                      });
                                    }
                                  },
                                  onDecrementClicked: (counter) {
                                    //Optional
                                    if (this.mounted) {
                                      setState(() {
                                        exercise.sets = counter;
                                      });
                                    }
                                  },
                                ),
                              )),
                          Expanded(
                              flex: 1,
                              child: Center(
                                child: Directionality(
                                  textDirection: TextDirection.ltr,
                                  child: Text(
                                    "x",
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 14),
                                  ),
                                ),
                              )),
                          Expanded(
                              flex: 2,
                              child: Container(
                                padding: EdgeInsets.all(2),
                                child: Stepo(
                                  key: UniqueKey(),
                                  width: 50,
                                  //Optional
                                  backgroundColor: Colors.deepPurple,
                                  //Optional
                                  style: Style.vertical,
                                  //Optional
                                  textColor: Colors.white,
                                  //Optional
                                  animationDuration: Duration(milliseconds: 0),
                                  //Optional
                                  iconColor: Colors.white,
                                  //Optional
                                  fontSize: 20,
                                  //Optional
                                  iconSize: 30,
                                  //Optional
                                  initialCounter: exercise.repsMin,
                                  //Optional
                                  lowerBound: 0,
                                  //Optional
                                  upperBound: 100,
                                  //Optional
                                  onIncrementClicked: (counter) {
                                    //Optional
                                    if (this.mounted) {
                                      setState(() {
                                        exercise.repsMin = counter;
                                      });
                                    }
                                  },
                                  onDecrementClicked: (counter) {
                                    //Optional
                                    if (this.mounted) {
                                      setState(() {
                                        exercise.repsMin = counter;
                                      });
                                    }
                                  },
                                ),
                              )),
                          Expanded(
                              flex: 1,
                              child: Center(
                                child: Directionality(
                                  textDirection: TextDirection.ltr,
                                  child: Text(
                                    "-",
                                    style: TextStyle(
                                        fontFamily: "Iransans",
                                        color: FONT_COLOR,
                                        fontSize: 14),
                                  ),
                                ),
                              )),
                          Expanded(
                              flex: 2,
                              child: Container(
                                padding: EdgeInsets.all(2),
                                child: Stepo(
                                  key: UniqueKey(),
                                  width: 50,
                                  //Optional
                                  backgroundColor: Colors.deepPurple,
                                  //Optional
                                  style: Style.vertical,
                                  //Optional
                                  textColor: Colors.white,
                                  //Optional
                                  animationDuration: Duration(milliseconds: 0),
                                  //Optional
                                  iconColor: Colors.white,
                                  //Optional
                                  fontSize: 20,
                                  //Optional
                                  iconSize: 30,
                                  //Optional
                                  initialCounter: exercise.repsMax,
                                  //Optional
                                  lowerBound: 0,
                                  //Optional
                                  upperBound: 100,
                                  //Optional
                                  onIncrementClicked: (counter) {
                                    //Optional
                                    if (this.mounted) {
                                      setState(() {
                                        exercise.repsMax = counter;
                                      });
                                    }
                                  },
                                  onDecrementClicked: (counter) {
                                    //Optional
                                    if (this.mounted) {
                                      setState(() {
                                        exercise.repsMax = counter;
                                      });
                                    }
                                  },
                                ),
                              )),
                        ],
                      ),
                    ),
                    Expanded(
                      flex: 5,
                      child: Container(
                        padding: EdgeInsets.all(4),
                        child: Directionality(
                          textDirection: TextDirection.rtl,
                          child: Text(
                            exercise.move.name,
                            style: TextStyle(
                                fontFamily: "Iransans",
                                color: FONT_COLOR,
                                fontSize: 14),
                          ),
                        ),
                      ),
                    )
                  ],
                ));
          }).toList()),
    );
  }

  Widget descriptionPage() {
    return Container(
      child: SingleChildScrollView(
        child: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            return Container(
                child: SingleChildScrollView(
              child: Column(
                children: <Widget>[
                  Container(
                    padding: EdgeInsets.all(20),
                    child: Directionality(
                      textDirection: TextDirection.rtl,
                      child: Text(
                        DESCRIPTION,
                        style: TextStyle(
                            fontFamily: "Iransans",
                            color: GREEN_COLOR,
                            fontSize: 18),
                      ),
                    ),
                  ),
                  AdjustTextField(
                      fontColor: FONT_COLOR,
                      maxLines: 8,
                      textAlign: TextAlign.right,
                      textDirection: TextDirection.rtl,
                      controller: descriptionTextEditingController,
                      hintText: DESCRIPTION,
                      enabled: true,
                      icon: null,
                      validator: (String val) {
                        if (val == null || val == "") {
                          return EMPTY;
                        } else
                          return null;
                      },
                      isPassword: false,
                      primaryColor: GREEN_COLOR,
                      padding: 0,
                      margin: 20),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      Expanded(
                          flex: 5,
                          child: Container(
                            margin: EdgeInsets.all(10),
                            child: AdjustRaisedButton(
                              textDirection: TextDirection.rtl,
                              width: 200,
                              height: 60,
                              fontColor: WHITE_COLOR,
                              text: BACK,
                              secondaryColor: GREEN_COLOR,
                              fontSize: 16,
                              primaryColor: GREEN_COLOR,
                              onPressed: () {
                                fitnessIconStepperStateKey.currentState
                                    .previous();
                              },
                            ),
                          )),
                      Expanded(
                          flex: 5,
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
                                showAdjustDialog(
                                    context, SURE_WITH_DECISION, true,
                                    () async {
                                  ProgramState programState =
                                      this.widget.programState;
                                  this.fitnessProgramDTO = FitnessProgramDTO(
                                      null,
                                      WORKOUT_TYPE_LIST[this.workoutTypeValue],
                                      descriptionTextEditingController.text,
                                      this.workoutDTOList);
                                  ProgramDTO programDTO = ProgramDTO(
                                      programState.id,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      null,
                                      this.fitnessProgramDTO);
                                  int i = await designFitnessProgram(
                                      context, programDTO);
                                  if (i == 1) {
                                    showAdjustDialog(context, SUCCESS, false,
                                        null, GREEN_COLOR);
                                    Navigator.of(context).pushReplacement(
                                        MaterialPageRoute(
                                            builder: (context) => MainPage()));
                                  } else {
                                    showAdjustDialog(context, FAILURE, false,
                                        null, GREEN_COLOR);
                                  }
                                }, GREEN_COLOR);
                              },
                            ),
                          ))
                    ],
                  ),
                ],
              ),
            ));
          },
        ),
      ),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    workoutTypeTextFieldController.dispose();
    descriptionTextEditingController.dispose();
    super.dispose();
  }
}
