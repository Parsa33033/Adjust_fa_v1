import 'dart:collection';

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
import 'package:adjust_specialist/main.dart';
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

  int proteinP;
  int carbsP;
  int fatP;

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

    proteinP = 0;
    carbsP = 0;
    fatP = 0;
    p = 0;
  }

  void setPercentage() {
    setState(() {
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
      return Container(
        height: 300,
        width: 300,
      );
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
                  "برنامه ی تغذیه " + this.widget.programState.clientState.firstName + " " + this.widget.programState.clientState.lastName,
                  style: TextStyle(
                      fontFamily: "Iransans", fontSize: 20, color: WHITE_COLOR),
                ),
              ),
            )
          ),
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
                        Icon(Icons.looks_one),
                        Icon(Icons.looks_two),
                        Icon(Icons.looks_3),
                        Icon(Icons.looks_4),
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
                  child: Text(CHOOSE_NUTRITION, style: TextStyle(fontFamily: "Iransans", color: FONT_COLOR, fontSize: 16),),
                ),
              )
            ),
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
                            nutritionStateList.remove(e);
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
                            nutritionDTOList =
                                this.nutritionStateSet.map((nutritionState) {
                              NutritionDTO nutritionDTO = NutritionDTO(
                                  null,
                                  nutritionState.name,
                                  nutritionState.description,
                                  nutritionState.unit,
                                  nutritionState.id,
                                  nutritionState.caloriesPerUnit,
                                  nutritionState.proteinPerUnit,
                                  nutritionState.carbsPerUnit,
                                  nutritionState.fatInUnit,
                                  nutritionState.mealId,
                                  null);
                              return nutritionDTO;
                            }).toList();
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
                padding: EdgeInsets.all(5),
                child: Directionality(
                  textDirection: TextDirection.rtl,
                  child: Text(CHOOSE_MEAL, style: TextStyle(fontFamily: "Iransans", color: FONT_COLOR, fontSize: 16),),
                ),
              )
          ),
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
                          child: Icon(Icons.add, size: 40,),
                        )
                      ),
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
                                          decoration: InputDecoration(
                                            focusColor: RED_COLOR,
                                            hoverColor: RED_COLOR,
                                            fillColor: RED_COLOR,
                                            border: OutlineInputBorder(
                                              borderRadius: BorderRadius.all(Radius.circular(5)),
                                              borderSide: BorderSide(
                                                  color: RED_COLOR,
                                                  width: 2,
                                                  style: BorderStyle.solid
                                              ),
                                            ),
                                            enabledBorder: OutlineInputBorder(
                                              borderRadius: BorderRadius.all(Radius.circular(5)),
                                              borderSide: BorderSide(
                                                  color: RED_COLOR,
                                                  width: 2,
                                                  style: BorderStyle.solid
                                              ),
                                            ),
                                            focusedBorder: OutlineInputBorder(
                                              borderRadius: BorderRadius.all(Radius.circular(5)),
                                              borderSide: BorderSide(
                                                  color: RED_COLOR,
                                                  width: 2,
                                                  style: BorderStyle.solid
                                              ),
                                            ),
                                            disabledBorder: OutlineInputBorder(
                                              borderRadius: BorderRadius.all(Radius.circular(5)),
                                              borderSide: BorderSide(
                                                  color: RED_COLOR,
                                                  width: 2,
                                                  style: BorderStyle.solid
                                              ),
                                            ),
                                            labelText: CHOOSE_MEAL,
                                            prefixIcon: null,
                                            hintStyle: TextStyle(fontFamily: "Iransans", fontSize: 16, color: FONT_COLOR),
                                            labelStyle: TextStyle(fontFamily: "Iransans", fontSize: 16, color: FONT_COLOR),
                                            enabled: true,
                                          ),
                                          controller: mealNameTextEditingController,
                                          suggestions: MEAL_NAMES,
                                          textChanged: (text) => mealNameTextEditingController.text = text,
                                          clearOnSubmit: false,
                                          textSubmitted: (text) => setState(() {
                                            if (text != "") {
                                              mealNameTextEditingController.text = text;
                                            }
                                          }
                                          ),
                                        ),
                                      ),
                                    ),
                                    Container(
                                      padding: EdgeInsets.all(10),
                                      child: Row(
                                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                        children: <Widget>[
                                          Expanded(
                                            flex: 5,
                                            child: Container(
                                              padding: EdgeInsets.all(5),
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
                                                  Navigator.of(context, rootNavigator: true).pop("dialog");
                                                },
                                              ),
                                            )
                                          ),
                                          Expanded(
                                            flex: 5,
                                            child: Container(
                                              padding: EdgeInsets.all(5),
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
                                                  if (mealNameTextEditingController.text != "" && mealNameTextEditingController.text != null) {
                                                    setState(() {
                                                      this.mealDTOList.add(MealDTO(null, mealNameTextEditingController.text, null, null, this.nutritionDTOList));
                                                      mealNameTextEditingController.text = "";
                                                    });
                                                  }
                                                },
                                              ),
                                            )
                                          )
                                        ],
                                      ),
                                    )
                                  ],
                                ),
                              )
                            ),
                          )
                        );
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
                              return Container(
                                height: 60,
                                width: 80,
                                margin: EdgeInsets.all(2),
                                padding: EdgeInsets.all(5),
                                child: FittedBox(
                                  fit: BoxFit.scaleDown,
                                  child: Text(mealDTO.name, style: TextStyle(fontFamily: "Iransans", color: RED_COLOR, fontSize: 14),),
                                ),
                              );
                            },
                          ),
                        )
                    )
                  ),
                  Expanded(
                    flex: 5,
                    child: Icon(Icons.keyboard_arrow_right),
                  ),
                ],
              )
            ),
          ),
          Expanded(
            flex: 8,
            child: Container(
              child: SingleChildScrollView(
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(
                      flex: 3,
                      child: Container(),
                    ),
                    Expanded(
                      flex: 6,
                      child: Container(
                        height: MediaQuery.of(context).size.height,
                        width: MediaQuery.of(context).size.width,
                        child: ListView.builder(
                          itemCount: this.nutritionDTOList.length,
                          itemBuilder: (BuildContext context, int pos) {
                            NutritionDTO nutritionDTO = this.nutritionDTOList[pos];
                            return Text(nutritionDTO.name);
                          },
                        ),
                      ),
                    )
                  ],
                ),
              ),
            ),
          ),
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
                          this.nutritionStateSet = Set<NutritionState>();
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
                        },
                      ),
                    ))
              ],
            ),
          )
        ],
      )
    );
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
//                        if (caloriesTextEditingController.text != null && caloriesTextEditingController.text != "") {
//                          if (proteinPercentageTextEditingController.text != null && proteinPercentageTextEditingController.text != "" &&
//                              carbsPercentageTextEditingController.text != null && carbsPercentageTextEditingController.text != "" &&
//                              fatPercentageTextEditingController.text != null && fatPercentageTextEditingController.text != "") {
//                            double calory = double.parse(caloriesTextEditingController.text);
//                            setPercentage();
//                            if (carbsP + proteinP + fatP == 100) {
//                              nutritionProgramDTO = NutritionProgramDTO(null, calory, proteinP, carbsP, fatP, null, null);
//                              iconStepperStateKey.currentState.next();
//                            } else {
//                              showAdjustDialog(context, "مجموع درصد های پروتئین، کربوهیدات و چربی باید 100 باشد", false, null, RED_COLOR);
//                            }
//                          } else {
//                            showAdjustDialog(context, "لطفا درصد های پروتئین، کربوهیدات و چربی را پر کنید", false, null, RED_COLOR);
//                          }
//                        } else{
//                          showAdjustDialog(context, "لطفا مقدار کالری را پر کنید", false, null, RED_COLOR);
//                        }
                        iconStepperStateKey.currentState.next();
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
