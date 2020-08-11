import 'dart:collection';

import 'package:adjust_specialist/actions/program_action.dart';
import 'package:adjust_specialist/components/adjust_dialog.dart';
import 'package:adjust_specialist/components/adjust_gridview_selector.dart';
import 'package:adjust_specialist/components/adjust_raised_button.dart';
import 'package:adjust_specialist/components/adjust_text_field.dart';
import 'package:adjust_specialist/components/icon_stepper.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/dto/fitness_program_dto.dart';
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

class FitnessProgramPage extends StatefulWidget {
  int programIndex;
  ProgramState programState;

  FitnessProgramPage(this.programIndex, this.programState);

  @override
  _FitnessProgramPageState createState() => _FitnessProgramPageState();
}

class _FitnessProgramPageState extends State<FitnessProgramPage> {
  int selectedIndex = 0;

  FitnessProgramDTO fitnessProgramDTO;

  @override
  initState() {

  }


  Widget steps(BuildContext context, int index) {
    if (index == 0) {
      return Container();
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
                    child: steps(context, selectedIndex),
                  ),
                ),
              ),
            ],
          ),
        ));
  }



  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
  }
}
