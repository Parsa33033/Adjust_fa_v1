import 'dart:collection';

import 'package:adjust_specialist/actions/program_action.dart';
import 'package:adjust_specialist/components/adjust_dialog.dart';
import 'package:adjust_specialist/components/adjust_gridview_selector.dart';
import 'package:adjust_specialist/components/adjust_raised_button.dart';
import 'package:adjust_specialist/components/adjust_text_field.dart';
import 'package:adjust_specialist/components/icon_stepper.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/dto/meal_dto.dart';
import 'package:adjust_specialist/dto/nutrition_dto.dart';
import 'package:adjust_specialist/dto/nutrition_program_dto.dart';
import 'package:adjust_specialist/dto/program_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/pages/main_page.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/nutrition_program_state.dart';
import 'package:adjust_specialist/states/nutrition_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:autocomplete_textfield/autocomplete_textfield.dart';
import 'package:curved_navigation_bar/curved_navigation_bar.dart';
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

class NutritionProgramPage extends StatefulWidget {
  int programIndex;
  ProgramState programState;

  NutritionProgramPage(this.programIndex, this.programState);

  @override
  _NutritionProgramPageState createState() => _NutritionProgramPageState();
}

class _NutritionProgramPageState extends State<NutritionProgramPage> {
  int selectedIndex = 0;
  int selectedMeal = 0;
  TextEditingController mealNameTextEditingController;

  NutritionProgramDTO nutritionProgramDTO;
  List<MealDTO> mealDTOList = List<MealDTO>();
  Set<NutritionState> nutritionStateSet = Set<NutritionState>();
  List<NutritionDTO> nutritionDTOList = List<NutritionDTO>();

  TextEditingController descriptionTextEditingController;
  TextEditingController caloriesTextEditingController;
  TextEditingController proteinPercentageTextEditingController;
  TextEditingController carbsPercentageTextEditingController;
  TextEditingController fatPercentageTextEditingController;

  double calories;
  int proteinP;
  int carbsP;
  int fatP;

  double caloriesSet;
  int proteinPSet;
  int carbsPSet;
  int fatPSet;

  int p;

  @override
  initState() {
    super.initState();
    mealNameTextEditingController = TextEditingController();
    descriptionTextEditingController = TextEditingController();
    caloriesTextEditingController = TextEditingController();
    proteinPercentageTextEditingController = TextEditingController();
    carbsPercentageTextEditingController = TextEditingController();
    fatPercentageTextEditingController = TextEditingController();

    calories = 0;
    proteinP = 0;
    carbsP = 0;
    fatP = 0;

    caloriesSet = 0;
    proteinPSet = 0;
    carbsPSet = 0;
    fatPSet = 0;

    p = 0;
  }

  void setPercentage() {
    setState(() {
      calories = double.parse(caloriesTextEditingController.text);
      proteinP = int.parse(proteinPercentageTextEditingController.text);
      carbsP = int.parse(carbsPercentageTextEditingController.text);
      fatP = int.parse(fatPercentageTextEditingController.text);
      p = this.proteinP + this.fatP + this.carbsP;
    });
  }

  Widget steps(BuildContext context, int index) {
    if (index == 0) {
      return mainNutritionProgramPage(context);
    } else if (index == 1) {
      return nutritionProgramPage(context);
    } else if (index == 2) {
      return mealsProgramPage(context);
    } else if (index == 3) {
      return setMealsProgramPage(context);
    } else if (index == 4) {
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
          backgroundColor: RED_COLOR,
          elevation: 4,
          leading: InkWell(
            child: Icon(Icons.arrow_back),
            onTap: () {
              Navigator.of(context).pop();
            },
          ),
        ),
        body: Container(
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
                      key: iconStepperStateKey,
                      direction: Axis.vertical,
                      stepColor: RED_COLOR,
                      activeStepColor: WHITE_COLOR,
                      enableNextPreviousButtons: false,
                      lineColor: RED_COLOR,
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
                    child: steps(context, selectedIndex),
                  ),
                ),
              ),
            ],
          ),
        ));
  }

  Widget nutritionProgramPage(BuildContext context) {
    List<NutritionState> nutritionStateList =
        store.state.nutritionStateList.nutritions;
    return Container(
        height: MediaQuery.of(context).size.height,
        width: MediaQuery.of(context).size.width,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
                flex: 7,
                child: Container(
                  padding: EdgeInsets.all(5),
                  child: Directionality(
                    textDirection: TextDirection.rtl,
                    child: Text(
                      CHOOSE_NUTRITION,
                      style: TextStyle(
                          fontFamily: "Iransans",
                          color: FONT_COLOR,
                          fontSize: 16),
                    ),
                  ),
                )),
            Expanded(
              flex: 83,
              child: GridView.count(
                  crossAxisCount: 2,
                  children: nutritionStateList.map((e) {
                    return AdjustGridViewItem(
                        selectedFontColor: WHITE_COLOR,
                        selectedColor: RED_COLOR,
                        name: e.name,
                        notSelectedFontColor: FONT_COLOR,
                        notSelectedColor: WHITE_COLOR,
                        isSelected: (value) {
                          if (value) {
                            nutritionStateSet.add(e);
                          } else {
                            nutritionStateSet.remove(e);
                          }
                        });
                  }).toList()),
            ),
            Expanded(
              flex: 10,
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
                          secondaryColor: RED_COLOR,
                          fontSize: 16,
                          primaryColor: RED_COLOR,
                          onPressed: () {
                            iconStepperStateKey.currentState.previous();
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
                          secondaryColor: RED_COLOR,
                          fontSize: 16,
                          primaryColor: RED_COLOR,
                          onPressed: () {
                            if (nutritionStateSet.length >= 1) {
                              nutritionDTOList =
                                  this.nutritionStateSet.map((nutritionState) {
                                NutritionDTO nutritionDTO = NutritionDTO(
                                    null,
                                    nutritionState.name,
                                    nutritionState.description,
                                    0,
                                    //nutritionState.unit,
                                    nutritionState.id,
                                    nutritionState.caloriesPerUnit,
                                    nutritionState.proteinPerUnit,
                                    nutritionState.carbsPerUnit,
                                    nutritionState.fatInUnit,
                                    nutritionState.mealId,
                                    null);
                                return nutritionDTO;
                              }).toList();
                              disperseUnitsInNutrition();
                              iconStepperStateKey.currentState.next();
                            } else {
                              showAdjustDialog(context, ATLEAST_ONE_NUTRITION,
                                  false, null, RED_COLOR);
                            }
                          },
                        ),
                      ))
                ],
              ),
            )
          ],
        ));
  }

  void disperseUnitsInNutrition() {
    while (caloriesSet <= calories) {
      for (var i = 0; i < this.nutritionDTOList.length; i++) {
        setState(() {
          this.nutritionDTOList[i].unit++;
          caloriesSet += this.nutritionDTOList[i].caloriesPerUnit;
          proteinPSet += this.nutritionDTOList[i].proteinPerUnit;
          carbsPSet += this.nutritionDTOList[i].carbsPerUnit;
          fatPSet += this.nutritionDTOList[i].fatInUnit;
        });
      }
    }
  }

  Widget mealsProgramPage(BuildContext context) {
    return Container(
        height: MediaQuery.of(context).size.height,
        width: MediaQuery.of(context).size.width,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
                flex: 1,
                child: Container(
                  padding: EdgeInsets.only(top: 15),
                  child: Directionality(
                    textDirection: TextDirection.rtl,
                    child: Text(
                      CHOOSE_MEAL,
                      style: TextStyle(
                          fontFamily: "Iransans",
                          color: FONT_COLOR,
                          fontSize: 12),
                    ),
                  ),
                )),
            Expanded(
              flex: 1,
              child: Container(
                  child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                    flex: 5,
                    child: Icon(Icons.keyboard_arrow_left),
                  ),
                  Expanded(
                    flex: 15,
                    child: InkWell(
                      child: Container(
                          padding: EdgeInsets.all(5),
                          child: FittedBox(
                            fit: BoxFit.scaleDown,
                            child: Icon(
                              Icons.add,
                              size: 40,
                            ),
                          )),
                      onTap: () {
                        showDialog(
                            context: context,
                            child: Dialog(
                              child: Container(
                                  alignment: Alignment.centerRight,
                                  height: 300,
                                  child: SingleChildScrollView(
                                    child: Column(
                                      children: <Widget>[
                                        Container(
                                          alignment: Alignment.centerRight,
                                          height: 50,
                                          width: 200,
                                          child: Directionality(
                                            textDirection: TextDirection.rtl,
                                            child: SimpleAutoCompleteTextField(
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  fontSize: 16,
                                                  color: FONT_COLOR,
                                                  locale: Locale("fa", "IR")),
                                              decoration: InputDecoration(
                                                focusColor: RED_COLOR,
                                                hoverColor: RED_COLOR,
                                                fillColor: RED_COLOR,
                                                border: OutlineInputBorder(
                                                  borderRadius:
                                                      BorderRadius.all(
                                                          Radius.circular(5)),
                                                  borderSide: BorderSide(
                                                      color: RED_COLOR,
                                                      width: 2,
                                                      style: BorderStyle.solid),
                                                ),
                                                enabledBorder:
                                                    OutlineInputBorder(
                                                  borderRadius:
                                                      BorderRadius.all(
                                                          Radius.circular(5)),
                                                  borderSide: BorderSide(
                                                      color: RED_COLOR,
                                                      width: 2,
                                                      style: BorderStyle.solid),
                                                ),
                                                focusedBorder:
                                                    OutlineInputBorder(
                                                  borderRadius:
                                                      BorderRadius.all(
                                                          Radius.circular(5)),
                                                  borderSide: BorderSide(
                                                      color: RED_COLOR,
                                                      width: 2,
                                                      style: BorderStyle.solid),
                                                ),
                                                disabledBorder:
                                                    OutlineInputBorder(
                                                  borderRadius:
                                                      BorderRadius.all(
                                                          Radius.circular(5)),
                                                  borderSide: BorderSide(
                                                      color: RED_COLOR,
                                                      width: 2,
                                                      style: BorderStyle.solid),
                                                ),
                                                labelText: CHOOSE_MEAL,
                                                prefixIcon: null,
                                                hintStyle: TextStyle(
                                                    fontFamily: "Iransans",
                                                    fontSize: 16,
                                                    color: FONT_COLOR),
                                                labelStyle: TextStyle(
                                                    fontFamily: "Iransans",
                                                    fontSize: 16,
                                                    color: FONT_COLOR),
                                                enabled: true,
                                              ),
                                              controller:
                                                  mealNameTextEditingController,
                                              suggestions: MEAL_NAMES,
//                                          textChanged: (text) => mealNameTextEditingController.text = text,
                                              clearOnSubmit: false,
                                              textSubmitted: (text) =>
                                                  setState(() {
                                                if (text != "") {
                                                  mealNameTextEditingController
                                                      .text = text;
                                                }
                                              }),
                                            ),
                                          ),
                                        ),
                                        Container(
                                          padding: EdgeInsets.all(10),
                                          child: Row(
                                            mainAxisAlignment:
                                                MainAxisAlignment.spaceEvenly,
                                            children: <Widget>[
                                              Expanded(
                                                  flex: 5,
                                                  child: Container(
                                                    padding: EdgeInsets.all(5),
                                                    child: AdjustRaisedButton(
                                                      textDirection:
                                                          TextDirection.rtl,
                                                      width: 200,
                                                      height: 60,
                                                      fontColor: WHITE_COLOR,
                                                      text: BACK,
                                                      secondaryColor: RED_COLOR,
                                                      fontSize: 16,
                                                      primaryColor: RED_COLOR,
                                                      onPressed: () {
                                                        Navigator.of(context,
                                                                rootNavigator:
                                                                    true)
                                                            .pop("dialog");
                                                      },
                                                    ),
                                                  )),
                                              Expanded(
                                                  flex: 5,
                                                  child: Container(
                                                    padding: EdgeInsets.all(5),
                                                    child: AdjustRaisedButton(
                                                      textDirection:
                                                          TextDirection.rtl,
                                                      width: 200,
                                                      height: 60,
                                                      fontColor: WHITE_COLOR,
                                                      text: OK,
                                                      secondaryColor: RED_COLOR,
                                                      fontSize: 16,
                                                      primaryColor: RED_COLOR,
                                                      onPressed: () {
                                                        if (mealNameTextEditingController
                                                                    .text !=
                                                                "" &&
                                                            mealNameTextEditingController
                                                                    .text !=
                                                                null) {
                                                          setState(() {
                                                            this
                                                                .mealDTOList
                                                                .add(MealDTO(
                                                                    null,
                                                                    mealNameTextEditingController
                                                                        .text,
                                                                    null,
                                                                    null,
                                                                    this.nutritionDTOList));
                                                            mealNameTextEditingController
                                                                .text = "";
                                                          });
                                                        }
                                                      },
                                                    ),
                                                  ))
                                            ],
                                          ),
                                        )
                                      ],
                                    ),
                                  )),
                            ));
                      },
                    ),
                  ),
                  Expanded(
                      flex: 75,
                      child: Container(
                          alignment: Alignment.centerRight,
                          height: 60,
                          width: 300,
                          child: Directionality(
                            textDirection: TextDirection.rtl,
                            child: ListView.builder(
                              scrollDirection: Axis.horizontal,
                              itemCount: this.mealDTOList.length,
                              itemBuilder: (BuildContext context, int pos) {
                                MealDTO mealDTO = this.mealDTOList[pos];
                                return InkWell(
                                  child: Container(
                                    height: 60,
                                    width: 80,
                                    margin: EdgeInsets.all(2),
                                    padding: EdgeInsets.all(5),
                                    child: FittedBox(
                                      fit: BoxFit.scaleDown,
                                      child: Text(
                                        mealDTO.name,
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: RED_COLOR,
                                            fontSize: 14),
                                      ),
                                    ),
                                  ),
                                  onTap: () {
                                    showAdjustDialog(
                                        context, WANT_MEAL_REMOVED, true, () {
                                      setState(() {
                                        this
                                            .mealDTOList
                                            .remove(this.mealDTOList[pos]);
                                      });
                                    }, RED_COLOR);
                                  },
                                );
                              },
                            ),
                          ))),
                  Expanded(
                    flex: 5,
                    child: Icon(Icons.keyboard_arrow_right),
                  ),
                ],
              )),
            ),
            Expanded(
                flex: 1,
                child: Container(
                  padding: EdgeInsets.only(top: 15),
                  child: Directionality(
                    textDirection: TextDirection.rtl,
                    child: Text(
                      CHOOSE_UNIT,
                      style: TextStyle(
                          fontFamily: "Iransans",
                          color: FONT_COLOR,
                          fontSize: 12),
                    ),
                  ),
                )),
            Expanded(
              flex: 5,
              child: Container(
                height: MediaQuery.of(context).size.height,
                width: MediaQuery.of(context).size.width,
//              child: SingleChildScrollView(
//                child: Container(

                child: ListView.builder(
                  itemCount: this.nutritionDTOList.length,
                  itemBuilder: (BuildContext context, int pos) {
                    NutritionDTO nutritionDTO = this.nutritionDTOList[pos];
                    return Container(
                      alignment: Alignment.centerRight,
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: <Widget>[
                          Expanded(
                              flex: 35,
                              child: Container(
                                padding: EdgeInsets.all(10),
                                child: FittedBox(
                                  fit: BoxFit.scaleDown,
                                  child: Directionality(
                                    textDirection: TextDirection.ltr,
                                    child: Stepo(
                                      key: UniqueKey(),
                                      width: 200,
                                      //Optional
                                      backgroundColor: Colors.deepPurple,
                                      //Optional
                                      style: Style.horizontal,
                                      //Optional
                                      textColor: Colors.white,
                                      //Optional
                                      animationDuration:
                                          Duration(milliseconds: 0),
                                      //Optional
                                      iconColor: Colors.white,
                                      //Optional
                                      fontSize: 20,
                                      //Optional
                                      iconSize: 30,
                                      //Optional
                                      initialCounter: nutritionDTO.unit,
                                      //Optional
                                      lowerBound: 0,
                                      //Optional
                                      upperBound: 2000,
                                      //Optional
                                      onIncrementClicked: (counter) {
                                        //Optional
                                        if (this.mounted) {
                                          setState(() {
                                            this.nutritionDTOList[pos].unit++;
                                            this.caloriesSet +=
                                                nutritionDTO.caloriesPerUnit;
                                            this.proteinPSet +=
                                                nutritionDTO.proteinPerUnit;
                                            this.carbsPSet +=
                                                nutritionDTO.carbsPerUnit;
                                            this.fatPSet +=
                                                nutritionDTO.fatInUnit;
                                          });
                                        }
                                      },
                                      onDecrementClicked: (counter) {
                                        //Optional
                                        if (this.mounted) {
                                          setState(() {
                                            this.nutritionDTOList[pos].unit--;
                                            this.caloriesSet -=
                                                nutritionDTO.caloriesPerUnit;
                                            this.proteinPSet -=
                                                nutritionDTO.proteinPerUnit;
                                            this.carbsPSet -=
                                                nutritionDTO.carbsPerUnit;
                                            this.fatPSet -=
                                                nutritionDTO.fatInUnit;
                                          });
                                        }
                                      },
                                    ),
                                  ),
                                ),
                              )),
                          Expanded(
                              flex: 75,
                              child: Container(
                                  padding: EdgeInsets.all(10),
                                  child: Column(
                                    children: <Widget>[
                                      FittedBox(
                                        fit: BoxFit.scaleDown,
                                        child: Directionality(
                                          textDirection: TextDirection.rtl,
                                          child: Text(
                                            nutritionDTO.name,
                                            style: TextStyle(
                                                fontFamily: "Iransans",
                                                color: FONT_COLOR,
                                                fontSize: 15),
                                          ),
                                        ),
                                      ),
                                      FittedBox(
                                        fit: BoxFit.scaleDown,
                                        child: Directionality(
                                          textDirection: TextDirection.rtl,
                                          child: Text(
                                            CALORY +
                                                " " +
                                                nutritionDTO.caloriesPerUnit
                                                    .toString() +
                                                " " +
                                                PROTEIN +
                                                " " +
                                                nutritionDTO.proteinPerUnit
                                                    .toString() +
                                                " " +
                                                CARBS +
                                                " " +
                                                nutritionDTO.carbsPerUnit
                                                    .toString() +
                                                " " +
                                                FAT +
                                                " " +
                                                nutritionDTO.fatInUnit
                                                    .toString() +
                                                " ",
                                            style: TextStyle(
                                                fontFamily: "Iransans",
                                                color: FONT_COLOR,
                                                fontSize: 15),
                                          ),
                                        ),
                                      ),
                                    ],
                                  ))),
                        ],
                      ),
                    );
                  },
                ),
              ),
            ),
//            ),
//          ),
            Expanded(
                flex: 3,
                child: Container(
                  height: 100,
                  width: MediaQuery.of(context).size.width,
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
                              child: Text(FAT,
                                  style: TextStyle(
                                      fontFamily: "Iransans",
                                      color: FONT_COLOR,
                                      fontSize: 13)),
                            ),
                          ),
                        )),
                        DataColumn(
                            label: Expanded(
                          child: Align(
                            alignment: Alignment.centerRight,
                            child: Directionality(
                              textDirection: TextDirection.rtl,
                              child: Text(CARBS,
                                  style: TextStyle(
                                      fontFamily: "Iransans",
                                      color: FONT_COLOR,
                                      fontSize: 13)),
                            ),
                          ),
                        )),
                        DataColumn(
                            label: Expanded(
                          child: Align(
                            alignment: Alignment.centerRight,
                            child: Directionality(
                              textDirection: TextDirection.rtl,
                              child: Text(PROTEIN,
                                  style: TextStyle(
                                      fontFamily: "Iransans",
                                      color: FONT_COLOR,
                                      fontSize: 13)),
                            ),
                          ),
                        )),
                        DataColumn(
                            label: Expanded(
                          child: Align(
                            alignment: Alignment.centerRight,
                            child: Directionality(
                              textDirection: TextDirection.rtl,
                              child: Text(CALORY + " روزانه",
                                  style: TextStyle(
                                      fontFamily: "Iransans",
                                      color: FONT_COLOR,
                                      fontSize: 13)),
                            ),
                          ),
                        )),
                      ],
                      rows: <DataRow>[
                        DataRow(cells: <DataCell>[
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: Directionality(
                              textDirection: TextDirection.rtl,
                              child: Text(
                                NumberUtility.changeDigit(
                                    this.fatP.toString() + "%",
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
                                NumberUtility.changeDigit(
                                    this.carbsP.toString() + "%",
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
                                NumberUtility.changeDigit(
                                    this.proteinP.toString() + "%",
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
                                NumberUtility.changeDigit(
                                    this.caloriesTextEditingController.text,
                                    NumStrLanguage.Farsi),
                                style: TextStyle(
                                    fontFamily: "Iransans",
                                    color: FONT_COLOR,
                                    fontSize: 13),
                              ),
                            ),
                          )),
                        ]),
                        DataRow(cells: <DataCell>[
                          DataCell(Align(
                            alignment: Alignment.centerRight,
                            child: Directionality(
                              textDirection: TextDirection.rtl,
                              child: Text(
                                NumberUtility.changeDigit(
                                    (this.fatPSet != 0 &&
                                            this.proteinPSet != 0 &&
                                            this.carbsPSet != 0)
                                        ? ((this.fatPSet /
                                                    (this.fatPSet +
                                                        this.proteinPSet +
                                                        this.carbsPSet)) *
                                                100)
                                            .round()
                                            .toString()
                                        : "0" + "%",
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
                                NumberUtility.changeDigit(
                                    (this.fatPSet != 0 &&
                                            this.proteinPSet != 0 &&
                                            this.carbsPSet != 0)
                                        ? ((this.carbsPSet /
                                                    (this.fatPSet +
                                                        this.proteinPSet +
                                                        this.carbsPSet)) *
                                                100)
                                            .round()
                                            .toString()
                                        : "0" + "%",
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
                                NumberUtility.changeDigit(
                                    (this.fatPSet != 0 &&
                                            this.proteinPSet != 0 &&
                                            this.carbsPSet != 0)
                                        ? ((this.proteinPSet /
                                                    (this.fatPSet +
                                                        this.proteinPSet +
                                                        this.carbsPSet)) *
                                                100)
                                            .round()
                                            .toString()
                                        : "0" + "%",
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
                                NumberUtility.changeDigit(
                                    this.caloriesSet.round().toString(),
                                    NumStrLanguage.Farsi),
                                style: TextStyle(
                                    fontFamily: "Iransans",
                                    color: FONT_COLOR,
                                    fontSize: 13),
                              ),
                            ),
                          )),
                        ])
                      ],
                    ),
                  ),
                )),
            Expanded(
              flex: 1,
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
                          secondaryColor: RED_COLOR,
                          fontSize: 16,
                          primaryColor: RED_COLOR,
                          onPressed: () {
                            setState(() {
                              this.nutritionStateSet = Set<NutritionState>();
                              caloriesSet = 0;
                              proteinPSet = 0;
                              carbsPSet = 0;
                              fatPSet = 0;
                            });
                            iconStepperStateKey.currentState.previous();
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
                          secondaryColor: RED_COLOR,
                          fontSize: 16,
                          primaryColor: RED_COLOR,
                          onPressed: () {
                            if (this.mealDTOList.length > 0) {
                              this.nutritionProgramDTO.dailyCalories =
                                  this.caloriesSet;
                              int total = this.proteinPSet +
                                  this.carbsPSet +
                                  this.fatPSet;
                              this.nutritionProgramDTO.proteinPercentage =
                                  ((this.proteinPSet / total) * 100).round();
                              this.nutritionProgramDTO.carbsPercentage =
                                  ((this.carbsPSet / total) * 100).round();
                              this.nutritionProgramDTO.fatPercentage =
                                  ((this.fatPSet / total) * 100).round();
                              List<NutritionDTO> l = []
                                ..addAll(this.nutritionDTOList);
                              this.mealDTOList.forEach((mealDTO) {
                                mealDTO.nutritions =
                                    nutritionDTOList.map((nutritionDTO) {
                                  NutritionDTO temp = NutritionDTO(
                                      nutritionDTO.id,
                                      nutritionDTO.name,
                                      nutritionDTO.description,
                                      0,
                                      //nutritionDTO.unit,
                                      nutritionDTO.adjustNutritionId,
                                      nutritionDTO.caloriesPerUnit,
                                      nutritionDTO.proteinPerUnit,
                                      nutritionDTO.carbsPerUnit,
                                      nutritionDTO.fatInUnit,
                                      nutritionDTO.mealId,
                                      null);
                                  return temp;
                                }).toList();
                              });
                              iconStepperStateKey.currentState.next();
                            } else {
                              showAdjustDialog(context, ATLEAST_ONE_MEAL, false,
                                  null, RED_COLOR);
                            }
                          },
                        ),
                      ))
                ],
              ),
            )
          ],
        ));
  }

  Widget setMealsProgramPage(BuildContext context) {
    return Container(
        height: MediaQuery.of(context).size.height,
        width: MediaQuery.of(context).size.width,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
                flex: 1,
                child: Container(
                  padding: EdgeInsets.all(5),
                  child: Directionality(
                    textDirection: TextDirection.rtl,
                    child: Text(
                      CHOOSE_MEAL,
                      style: TextStyle(
                          fontFamily: "Iransans",
                          color: FONT_COLOR,
                          fontSize: 16),
                    ),
                  ),
                )),
            Expanded(
              flex: 1,
              child: Container(
                  child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                    flex: 5,
                    child: Icon(Icons.keyboard_arrow_left),
                  ),
                  Expanded(
                      flex: 75,
                      child: Container(
                          alignment: Alignment.centerRight,
                          height: 60,
                          width: 300,
                          child: Directionality(
                            textDirection: TextDirection.rtl,
                            child: ListView.builder(
                              scrollDirection: Axis.horizontal,
                              itemCount: this.mealDTOList.length,
                              itemBuilder: (BuildContext context, int pos) {
                                MealDTO mealDTO = this.mealDTOList[pos];
                                return InkWell(
                                  child: Container(
                                    height: 60,
                                    width: 100,
                                    child: Column(
                                      children: <Widget>[
                                        Container(
                                          height: 40,
                                          width: 100,
                                          margin: EdgeInsets.all(2),
                                          padding: EdgeInsets.all(5),
                                          child: FittedBox(
                                            fit: BoxFit.scaleDown,
                                            child: Text(
                                              mealDTO.name,
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  color: RED_COLOR,
                                                  fontSize: 14),
                                            ),
                                          ),
                                        ),
                                        this.selectedMeal == pos
                                            ? Container(
                                                height: 10,
                                                width: 30,
                                                child: Divider(
                                                  height: 5,
                                                  thickness: 2,
                                                  color: RED_COLOR,
                                                ),
                                              )
                                            : Container(
                                                height: 10,
                                                width: 30,
                                              )
                                      ],
                                    ),
                                  ),
                                  onTap: () {
                                    setState(() {
                                      selectedMeal = pos;
                                    });
                                  },
                                );
                              },
                            ),
                          ))),
                  Expanded(
                    flex: 5,
                    child: Icon(Icons.keyboard_arrow_right),
                  ),
                ],
              )),
            ),
            Expanded(
              flex: 6,
              child: Container(
                height: MediaQuery.of(context).size.height,
                width: MediaQuery.of(context).size.width,
                child: ListView.builder(
                  itemCount: this.nutritionDTOList.length,
                  itemBuilder: (BuildContext context, int pos) {
                    NutritionDTO nutritionDTO =
                        this.mealDTOList[selectedMeal].nutritions[pos];
                    return Container(
                      alignment: Alignment.centerRight,
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: <Widget>[
                          Expanded(
                            flex: 50,
                            child: Container(
                              height: 100,
                              width: 100,
                              padding: EdgeInsets.all(30),
                              child: Stepo(
                                key: UniqueKey(),
                                width: 100,
                                //Optional
                                backgroundColor: Colors.deepPurple,
                                //Optional
                                style: Style.horizontal,
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
                                initialCounter: nutritionDTO.unit,
                                //Optional
                                lowerBound: 0,
                                //Optional
                                upperBound: 2000,
                                //Optional
                                onIncrementClicked: (counter) {
                                  //Optional
                                  if (this.mounted) {
                                    setState(() {
                                      nutritionDTOList[pos].unit--;
                                      nutritionDTO.unit++;
                                    });
                                  }
                                },
                                onDecrementClicked: (counter) {
                                  //Optional
                                  if (this.mounted) {
                                    setState(() {
                                      nutritionDTOList[pos].unit++;
                                      nutritionDTO.unit--;
                                    });
                                  }
                                },
                              ),
                            ),
                          ),
                          Expanded(
                              flex: 25,
                              child: Container(
                                alignment: Alignment.centerRight,
                                padding: EdgeInsets.all(10),
                                child: FittedBox(
                                  fit: BoxFit.scaleDown,
                                  child: Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        NumberUtility.changeDigit(
                                            this
                                                    .nutritionDTOList[pos]
                                                    .unit
                                                    .toString() +
                                                " واحد",
                                            NumStrLanguage.Farsi),
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            fontSize: 14,
                                            color: FONT_COLOR),
                                      )),
                                ),
                              )),
                          Expanded(
                              flex: 25,
                              child: Container(
                                alignment: Alignment.centerRight,
                                padding: EdgeInsets.all(10),
                                child: FittedBox(
                                  fit: BoxFit.scaleDown,
                                  child: Directionality(
                                    textDirection: TextDirection.rtl,
                                    child: Text(
                                      nutritionDTO.name,
                                      style: TextStyle(
                                          fontFamily: "Iransans",
                                          color: FONT_COLOR,
                                          fontSize: 15),
                                    ),
                                  ),
                                ),
                              )),
                        ],
                      ),
                    );
                  },
                ),
              ),
            ),
            Expanded(
              flex: 2,
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
                          secondaryColor: RED_COLOR,
                          fontSize: 16,
                          primaryColor: RED_COLOR,
                          onPressed: () {
                            setState(() {
                              this.nutritionStateSet = Set<NutritionState>();
                              caloriesSet = 0;
                              proteinPSet = 0;
                              carbsPSet = 0;
                              fatPSet = 0;
                              this.nutritionDTOList.forEach((element) {
                                element.unit = 0;
                              });
                            });
                            disperseUnitsInNutrition();
                            iconStepperStateKey.currentState.previous();
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
                          secondaryColor: RED_COLOR,
                          fontSize: 16,
                          primaryColor: RED_COLOR,
                          onPressed: () {
                            //
                            iconStepperStateKey.currentState.next();
                          },
                        ),
                      ))
                ],
              ),
            )
          ],
        ));
  }

  Widget mainNutritionProgramPage(BuildContext context) {
    return StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        return Container(
            height: MediaQuery.of(context).size.height - 100,
            width: MediaQuery.of(context).size.width,
            padding: EdgeInsets.all(10),
            child: SingleChildScrollView(
              child: Column(
                children: <Widget>[
                  Container(
                    height: 100,
                    child: AdjustTextField(
                        textInputType: TextInputType.number,
                        fontColor: RED_COLOR,
                        maxLines: 1,
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: caloriesTextEditingController,
                        hintText: CALORIES,
                        enabled: true,
                        icon: null,
                        validator: (String val) {
                          if (val == null || val == "") {
                            return EMPTY;
                          } else
                            return null;
                        },
                        isPassword: false,
                        primaryColor: RED_COLOR,
                        padding: 0,
                        margin: 20),
                  ),
                  Container(
                    height: 100,
                    child: AdjustTextField(
                        textInputType: TextInputType.number,
                        fontColor: RED_COLOR,
                        maxLines: 1,
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: fatPercentageTextEditingController,
                        hintText: FAT_PERCENTAGE,
                        enabled: true,
                        icon: null,
                        validator: (String val) {
                          if (val == null || val == "") {
                            return EMPTY;
                          } else
                            return null;
                        },
                        isPassword: false,
                        primaryColor: RED_COLOR,
                        padding: 0,
                        margin: 20),
                  ),
                  Container(
                    height: 100,
                    child: AdjustTextField(
                        textInputType: TextInputType.number,
                        fontColor: RED_COLOR,
                        maxLines: 1,
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: carbsPercentageTextEditingController,
                        hintText: CARBS_PERCENTAGE,
                        enabled: true,
                        icon: null,
                        validator: (String val) {
                          if (val == null || val == "") {
                            return EMPTY;
                          } else
                            return null;
                        },
                        isPassword: false,
                        primaryColor: RED_COLOR,
                        padding: 0,
                        margin: 20),
                  ),
                  Container(
                    height: 100,
                    child: AdjustTextField(
                        textInputType: TextInputType.number,
                        fontColor: RED_COLOR,
                        maxLines: 1,
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: proteinPercentageTextEditingController,
                        hintText: PROTEIN_PERCENTAGE,
                        enabled: true,
                        icon: null,
                        validator: (String val) {
                          if (val == null || val == "") {
                            return EMPTY;
                          } else
                            return null;
                        },
                        isPassword: false,
                        primaryColor: RED_COLOR,
                        padding: 0,
                        margin: 20),
                  ),
                  Container(
                    height: 80,
                    width: 280,
                    padding: EdgeInsets.all(10),
                    child: AdjustRaisedButton(
                      text: OK,
                      textDirection: TextDirection.rtl,
                      primaryColor: RED_COLOR,
                      secondaryColor: RED_COLOR,
                      height: 50,
                      width: 90,
                      onPressed: () {
                        if (caloriesTextEditingController.text != null &&
                            caloriesTextEditingController.text != "") {
                          if (proteinPercentageTextEditingController.text !=
                                  null &&
                              proteinPercentageTextEditingController.text !=
                                  "" &&
                              carbsPercentageTextEditingController.text !=
                                  null &&
                              carbsPercentageTextEditingController.text != "" &&
                              fatPercentageTextEditingController.text != null &&
                              fatPercentageTextEditingController.text != "") {
                            double calory = double.parse(
                                caloriesTextEditingController.text);
                            setPercentage();
                            if (carbsP + proteinP + fatP == 100) {
                              nutritionProgramDTO = NutritionProgramDTO(null,
                                  calory, proteinP, carbsP, fatP, null, null);
                              iconStepperStateKey.currentState.next();
                            } else {
                              showAdjustDialog(
                                  context,
                                  "مجموع درصد های پروتئین، کربوهیدات و چربی باید 100 باشد",
                                  false,
                                  null,
                                  RED_COLOR);
                            }
                          } else {
                            showAdjustDialog(
                                context,
                                "لطفا درصد های پروتئین، کربوهیدات و چربی را پر کنید",
                                false,
                                null,
                                RED_COLOR);
                          }
                        } else {
                          showAdjustDialog(
                              context,
                              "لطفا مقدار کالری را پر کنید",
                              false,
                              null,
                              RED_COLOR);
                        }
//                        iconStepperStateKey.currentState.next();
                      },
                    ),
                  )
                ],
              ),
            ));
      },
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
                            color: RED_COLOR,
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
                      primaryColor: RED_COLOR,
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
                              secondaryColor: RED_COLOR,
                              fontSize: 16,
                              primaryColor: RED_COLOR,
                              onPressed: () {
                                iconStepperStateKey.currentState.previous();
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
                              secondaryColor: RED_COLOR,
                              fontSize: 16,
                              primaryColor: RED_COLOR,
                              onPressed: () {
                                showAdjustDialog(
                                    context, SURE_WITH_DECISION, true,
                                    () async {
                                  ProgramState programState =
                                      this.widget.programState;
                                  this.nutritionProgramDTO.description =
                                      descriptionTextEditingController.text;
                                  this.nutritionProgramDTO.meals =
                                      this.mealDTOList;
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
                                      this.nutritionProgramDTO,
                                      null);
                                  int i = await designNutritionProgram(
                                      context, programDTO);
                                  if (i == 1) {
                                    showAdjustDialog(context, SUCCESS, false,
                                        null, RED_COLOR);
                                    Navigator.of(context).pushReplacement(
                                        MaterialPageRoute(
                                            builder: (context) => MainPage()));
                                  } else {
                                    showAdjustDialog(context, FAILURE, false,
                                        null, RED_COLOR);
                                  }
                                }, RED_COLOR);
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
    mealNameTextEditingController.dispose();
    descriptionTextEditingController.dispose();
    caloriesTextEditingController.dispose();
    proteinPercentageTextEditingController.dispose();
    carbsPercentageTextEditingController.dispose();
    fatPercentageTextEditingController.dispose();
    super.dispose();
  }
}
