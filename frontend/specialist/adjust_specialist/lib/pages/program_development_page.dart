import 'package:adjust_specialist/actions/client_action.dart';
import 'package:adjust_specialist/actions/program_action.dart';
import 'package:adjust_specialist/components/adjust_dialog.dart';
import 'package:adjust_specialist/components/adjust_info_button.dart';
import 'package:adjust_specialist/components/adjust_raised_button.dart';
import 'package:adjust_specialist/config/localization.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/dto/program_development_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/model/program_development.dart';
import 'package:adjust_specialist/pages/main_page.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/body_composition_state.dart';
import 'package:adjust_specialist/states/program_development_state.dart';
import 'package:fl_chart/fl_chart.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';

class ProgramDevelopmentPage extends StatefulWidget {
  int programId;
  int programIndex;

  ProgramDevelopmentPage(this.programId, this.programIndex);

  @override
  _ProgramDevelopmentPageState createState() => _ProgramDevelopmentPageState();
}

class _ProgramDevelopmentPageState extends State<ProgramDevelopmentPage> {
  bool isShowingMainData;
  double nutritionRating;
  double fitnessRating;

  Widget chart;

  List<ProgramDevelopmentState> programDevelopmentStateList;

  @override
  initState() {
    super.initState();
    isShowingMainData = true;
    nutritionRating = 3;
    fitnessRating = 3;
    this.programDevelopmentStateList = store.state.programListState.programs.reversed.toList()[this.widget.programIndex].programDevelopmentStateList;
  }


  List<Color> gradientColors = [
    const Color(0xff23b6e6),
    const Color(0xff02d39a),
  ];

  Widget chartListView() {
    return Container(
      height: 200,
      child: ListView(
        scrollDirection: Axis.horizontal,
        children: <Widget>[
          Container(
              margin: EdgeInsets.all(10),
              width: MediaQuery.of(context).size.width - 60,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                    flex: 1,
                    child: Directionality(
                      textDirection: TextDirection.rtl,
                      child: Text("ارزیابی تمرینی", style: TextStyle(fontFamily: "Iransans", color: FONT_COLOR, fontSize: 18),),
                    ),
                  ),
                  Expanded(
                    flex: 9,
                    child: AspectRatio(
                      aspectRatio: 1.2,
                      child: Container(
                        decoration: const BoxDecoration(
                            borderRadius: BorderRadius.all(
                              Radius.circular(18),
                            ),
                            color: Color(0xff232d37)),
                        child: Padding(
                          padding: const EdgeInsets.only(right: 18.0, left: 12.0, top: 24, bottom: 12),
                          child: LineChart(
                              LineChartData(
                                gridData: FlGridData(
                                  show: true,
                                  drawVerticalLine: true,
                                  getDrawingHorizontalLine: (value) {
                                    return FlLine(
                                      color: const Color(0xff37434d),
                                      strokeWidth: 1,
                                    );
                                  },
                                  getDrawingVerticalLine: (value) {
                                    return FlLine(
                                      color: const Color(0xff37434d),
                                      strokeWidth: 1,
                                    );
                                  },
                                ),
                                titlesData: FlTitlesData(
                                  show: true,
                                  bottomTitles: SideTitles(
                                    showTitles: true,
                                    reservedSize: 22,
                                    textStyle:
                                    const TextStyle(color: Color(0xff68737d), fontWeight: FontWeight.bold, fontSize: 16),
                                    getTitles: (value) {
                                      int listSize = this.programDevelopmentStateList.length;
                                      if (listSize <= 0) {
                                        return "0";
                                      } else if (listSize == 1) {
                                        return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[0].date)), NumStrLanguage.Farsi);
                                      } else if (listSize == 2) {
                                        if (value.toInt() == 0) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[0].date)), NumStrLanguage.Farsi);
                                        } else if (value.toInt() == 1) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[1].date)), NumStrLanguage.Farsi);
                                        }
                                      } else if (listSize >= 3) {
                                        if (value.toInt() == 0) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[0].date)), NumStrLanguage.Farsi);
                                        } else if (value.toInt() == (listSize / 2).round()) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[(listSize / 2).round()].date)), NumStrLanguage.Farsi);
                                        } else if (value.toInt() == listSize - 1) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[listSize - 1].date)), NumStrLanguage.Farsi);
                                        }
                                      }
                                      return '';
                                    },
                                    margin: 8,
                                  ),
                                  leftTitles: SideTitles(
                                    showTitles: true,
                                    textStyle: const TextStyle(
                                      color: Color(0xff67727d),
                                      fontWeight: FontWeight.bold,
                                      fontSize: 15,
                                    ),
                                    getTitles: (value) {
                                      switch (value.toInt()) {
                                        case 1:
                                          return NumberUtility.changeDigit('1', NumStrLanguage.Farsi);
                                        case 3:
                                          return NumberUtility.changeDigit('3', NumStrLanguage.Farsi);
                                        case 5:
                                          return NumberUtility.changeDigit('5', NumStrLanguage.Farsi);
                                      }
                                      return '';
                                    },
                                    reservedSize: 28,
                                    margin: 12,
                                  ),
                                ),
                                borderData:
                                FlBorderData(show: true, border: Border.all(color: const Color(0xff37434d), width: 1)),
                                minX: 0,
                                maxX: this.programDevelopmentStateList.length.toDouble(),
                                minY: 0,
                                maxY: 6,
                                lineBarsData: [
                                  LineChartBarData(
                                    spots: this.programDevelopmentStateList.length <= 0 ? [FlSpot(0,0)] : this.programDevelopmentStateList.map((e) => FlSpot(programDevelopmentStateList.indexOf(e).toDouble(), e.nutritionScore)).toList(),
                                    isCurved: true,
                                    colors: gradientColors,
                                    barWidth: 5,
                                    isStrokeCapRound: true,
                                    dotData: FlDotData(
                                      show: false,
                                    ),
                                    belowBarData: BarAreaData(
                                      show: true,
                                      colors: gradientColors.map((color) => color.withOpacity(0.3)).toList(),
                                    ),
                                  ),
                                ],
                              )
                          ),
                        ),
                      ),
                    ),
                  )
                ],
              )
          ),

          Container(
              margin: EdgeInsets.all(10),
              width: MediaQuery.of(context).size.width - 60,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                    flex: 1,
                    child: Directionality(
                      textDirection: TextDirection.rtl,
                      child: Text("ارزیابی تغذیه", style: TextStyle(fontFamily: "Iransans", color: FONT_COLOR, fontSize: 18),),
                    ),
                  ),
                  Expanded(
                    flex: 9,
                    child: AspectRatio(
                      aspectRatio: 1.2,
                      child: Container(
                        decoration: const BoxDecoration(
                            borderRadius: BorderRadius.all(
                              Radius.circular(18),
                            ),
                            color: Color(0xff232d37)),
                        child: Padding(
                          padding: const EdgeInsets.only(right: 18.0, left: 12.0, top: 24, bottom: 12),
                          child: LineChart(
                              LineChartData(
                                gridData: FlGridData(
                                  show: true,
                                  drawVerticalLine: true,
                                  getDrawingHorizontalLine: (value) {
                                    return FlLine(
                                      color: const Color(0xff37434d),
                                      strokeWidth: 1,
                                    );
                                  },
                                  getDrawingVerticalLine: (value) {
                                    return FlLine(
                                      color: const Color(0xff37434d),
                                      strokeWidth: 1,
                                    );
                                  },
                                ),
                                titlesData: FlTitlesData(
                                  show: true,
                                  bottomTitles: SideTitles(
                                    showTitles: true,
                                    reservedSize: 22,
                                    textStyle:
                                    const TextStyle(color: Color(0xff68737d), fontWeight: FontWeight.bold, fontSize: 16),
                                    getTitles: (value) {
                                      int listSize = this.programDevelopmentStateList.length;
                                      if (listSize <= 0) {
                                        return "0";
                                      } else if (listSize == 1) {
                                        return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[0].date)), NumStrLanguage.Farsi);
                                      } else if (listSize == 2) {
                                        if (value.toInt() == 0) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[0].date)), NumStrLanguage.Farsi);
                                        } else if (value.toInt() == 1) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[1].date)), NumStrLanguage.Farsi);
                                        }
                                      } else if (listSize >= 3) {
                                        if (value.toInt() == 0) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[0].date)), NumStrLanguage.Farsi);
                                        } else if (value.toInt() == (listSize / 2).round()) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[(listSize / 2).round()].date)), NumStrLanguage.Farsi);
                                        } else if (value.toInt() == listSize - 1) {
                                          return NumberUtility.changeDigit(jalaliToString(georgianToJalali(this.programDevelopmentStateList[listSize - 1].date)), NumStrLanguage.Farsi);
                                        }
                                      }
                                      return '';
                                    },
                                    margin: 8,
                                  ),
                                  leftTitles: SideTitles(
                                    showTitles: true,
                                    textStyle: const TextStyle(
                                      color: Color(0xff67727d),
                                      fontWeight: FontWeight.bold,
                                      fontSize: 15,
                                    ),
                                    getTitles: (value) {
                                      switch (value.toInt()) {
                                        case 1:
                                          return NumberUtility.changeDigit('1', NumStrLanguage.Farsi);
                                        case 3:
                                          return NumberUtility.changeDigit('3', NumStrLanguage.Farsi);
                                        case 5:
                                          return NumberUtility.changeDigit('5', NumStrLanguage.Farsi);
                                      }
                                      return '';
                                    },
                                    reservedSize: 28,
                                    margin: 12,
                                  ),
                                ),
                                borderData:
                                FlBorderData(show: true, border: Border.all(color: const Color(0xff37434d), width: 1)),
                                minX: 0,
                                maxX: this.programDevelopmentStateList.length.toDouble(),
                                minY: 0,
                                maxY: 6,
                                lineBarsData: [
                                  LineChartBarData(
                                    spots: this.programDevelopmentStateList.length <= 0 ? [FlSpot(0,0)] : this.programDevelopmentStateList.map((e) => FlSpot(programDevelopmentStateList.indexOf(e).toDouble(), e.fitnessScore)).toList(),
                                    isCurved: true,
                                    colors: gradientColors,
                                    barWidth: 5,
                                    isStrokeCapRound: true,
                                    dotData: FlDotData(
                                      show: false,
                                    ),
                                    belowBarData: BarAreaData(
                                      show: true,
                                      colors: gradientColors.map((color) => color.withOpacity(0.3)).toList(),
                                    ),
                                  ),
                                ],
                              )
                          ),
                        ),
                      ),
                    ),
                  )
                ],
              )
          ),
        ],
      ),
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
                "پیشرفت",
                style: TextStyle(
                    fontFamily: "Iransans", fontSize: 20, color: WHITE_COLOR),
              ),
            ),
          ),
          backgroundColor: ORANGE_COLOR,
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
                height: MediaQuery.of(context).size.height,
                width: MediaQuery.of(context).size.width,
//                color: ORANGE_COLOR.withAlpha(55),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(
                      flex: 5,
                      child: Container(
                        child: chartListView(),
                      )
                    ),
                    Expanded(
                        flex: 5,
                        child: SingleChildScrollView(
                          child: Column(
                            children: <Widget>[
//                              Container(
//                                  height: 160,
//                                  width: MediaQuery.of(context).size.width,
//                                  padding: EdgeInsets.all(15),
//                                  child: Row(
//                                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
//                                    children: <Widget>[
//                                      Expanded(
//                                        flex: 3,
//                                        child: Icon(Icons.add, size: 50, ),
//                                      ),
//                                      Expanded(
//                                        flex: 6,
//                                        child: ListView(
//                                          scrollDirection: Axis.horizontal,
//                                          children: state.programListState.programs.reversed.toList()[this.widget.programIndex].bodyCompositionStateList.map((bodyCompositionState) {
//                                            return AdjustInfoButton(
//                                              width: 100,
//                                              height: 100,
//                                              id: bodyCompositionState.id.toString(),
//                                              title: NumberUtility.changeDigit(georgianToJalali(bodyCompositionState.createdAt).year.toString() + "/" + georgianToJalali(bodyCompositionState.createdAt).month.toString() + "/" + georgianToJalali(bodyCompositionState.createdAt).day.toString(), NumStrLanguage.Farsi),
//                                              description: "",
//                                              name: bodyCompositionState.id.toString(),
//                                              fontSize: 14,
//                                              isVertical: true,
//                                              primaryColor: ORANGE_COLOR,
//                                              primaryColorLight: ORANGE_COLOR,
//                                              secondaryColor: WHITE_COLOR,
//                                              image: null,);
//                                          }).toList(),
//                                        ),
//                                      )
//                                    ],
//                                  )
//                              ),
                              Container(
                                height: 120,
                                width: MediaQuery.of(context).size.width,
//                                color: WHITE_COLOR,
                                child: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceEvenly,
                                  children: <Widget>[
                                    Expanded(
                                        flex: 5,
                                        child: FittedBox(
                                          fit: BoxFit.scaleDown,
                                          child: Container(
                                            padding: EdgeInsets.all(5),
                                            child: RatingBar(
                                              initialRating: this.fitnessRating,
                                              itemCount: 5,
                                              itemBuilder: (context, index) {
                                                switch (index) {
                                                  case 0:
                                                    return Icon(
                                                      Icons.sentiment_very_dissatisfied,
                                                      color: Colors.red,
                                                    );
                                                  case 1:
                                                    return Icon(
                                                      Icons.sentiment_dissatisfied,
                                                      color: Colors.redAccent,
                                                    );
                                                  case 2:
                                                    return Icon(
                                                      Icons.sentiment_neutral,
                                                      color: Colors.amber,
                                                    );
                                                  case 3:
                                                    return Icon(
                                                      Icons.sentiment_satisfied,
                                                      color: Colors.lightGreen,
                                                    );
                                                  case 4:
                                                    return Icon(
                                                      Icons.sentiment_very_satisfied,
                                                      color: Colors.green,
                                                    );
                                                }
                                                return null;
                                              },
                                              onRatingUpdate: (rating) {
                                                setState(() {
                                                  this.fitnessRating = rating;

                                                });
                                              },
                                            )
                                          ),
                                        )),
                                    Expanded(
                                        flex: 5,
                                        child: FittedBox(
                                          fit: BoxFit.scaleDown,
                                          child: Container(
                                              padding: EdgeInsets.all(5),
                                              child: Directionality(
                                                textDirection:
                                                    TextDirection.rtl,
                                                child: Text(
                                                  "ارزیابی تمرین امروز",
                                                  style: TextStyle(
                                                      fontFamily: "Iransans",
                                                      fontSize: 18,
                                                      color: GREEN_COLOR),
                                                ),
                                              )),
                                        ))
                                  ],
                                ),
                              ),
                              Container(
                                height: 120,
                                width: MediaQuery.of(context).size.width,
//                                color: WHITE_COLOR,
                                child: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceEvenly,
                                  children: <Widget>[
                                    Expanded(
                                        flex: 5,
                                        child: FittedBox(
                                          fit: BoxFit.scaleDown,
                                          child: Container(
                                            padding: EdgeInsets.all(5),
                                            child: RatingBar(
                                              initialRating: this.nutritionRating,
                                              itemCount: 5,
                                              itemBuilder: (context, index) {
                                                switch (index) {
                                                  case 0:
                                                    return Icon(
                                                      Icons.sentiment_very_dissatisfied,
                                                      color: Colors.red,
                                                    );
                                                  case 1:
                                                    return Icon(
                                                      Icons.sentiment_dissatisfied,
                                                      color: Colors.redAccent,
                                                    );
                                                  case 2:
                                                    return Icon(
                                                      Icons.sentiment_neutral,
                                                      color: Colors.amber,
                                                    );
                                                  case 3:
                                                    return Icon(
                                                      Icons.sentiment_satisfied,
                                                      color: Colors.lightGreen,
                                                    );
                                                  case 4:
                                                    return Icon(
                                                      Icons.sentiment_very_satisfied,
                                                      color: Colors.green,
                                                    );
                                                }
                                                return null;
                                              },
                                              onRatingUpdate: (rating) {
                                                setState(() {
                                                  this.nutritionRating = rating;

                                                });
                                              },
                                            )
                                          ),
                                        )),
                                    Expanded(
                                        flex: 5,
                                        child: FittedBox(
                                          fit: BoxFit.scaleDown,
                                          child: Container(
                                              padding: EdgeInsets.all(5),
                                              child: Directionality(
                                                textDirection:
                                                    TextDirection.rtl,
                                                child: Text(
                                                  "ارزیابی تغذیه ی امروز",
                                                  style: TextStyle(
                                                      fontFamily: "Iransans",
                                                      fontSize: 18,
                                                      color: RED_COLOR),
                                                ),
                                              )),
                                        ))
                                  ],
                                ),
                              ),
                              Container(
                                color: WHITE_COLOR,
                                height: 60,
                                width: MediaQuery.of(context).size.width - 80,
                                child: AdjustRaisedButton(
                                  width: MediaQuery.of(context).size.width,
                                  height: 40,
                                  textDirection: TextDirection.rtl,
                                  fontColor: WHITE_COLOR,
                                  primaryColor: ORANGE_COLOR,
                                  fontSize: 18,
                                  secondaryColor: ORANGE_COLOR,
                                  text: "ذخیره ی ارزیابی",
                                  onPressed: () {
                                    showAdjustDialog(context, ASSESS_NOTE, true,
                                        () async {}, ORANGE_COLOR);
                                  },
                                ),
                              ),

                            ],
                          ),
                        )),
                  ],
                ));
          },
        ));
  }
}
