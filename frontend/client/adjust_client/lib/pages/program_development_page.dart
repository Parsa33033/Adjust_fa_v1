import 'package:adjust_client/actions/program_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/config/localization.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/program_development_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/program_development.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/program_development_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:syncfusion_flutter_charts/charts.dart';

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
    chart = scoreChart();
  }

  Widget scoreChart() {
    return Container(
        child: SfCartesianChart(
            // Initialize category axis
            primaryXAxis: CategoryAxis(),
            series: <LineSeries<ProgramDevelopmentState, String>>[
          LineSeries<ProgramDevelopmentState, String>(
              // Bind data source
              dataSource: this.programDevelopmentStateList,
              xValueMapper:
                  (ProgramDevelopmentState programDevelopmentState, _) =>
                      NumberUtility.changeDigit(
                          programDevelopmentState.id.toString(),//georgianToJalali(programDevelopmentState.date).toString(),
                          NumStrLanguage.Farsi),
              yValueMapper:
                  (ProgramDevelopmentState programDevelopmentState, _) =>
                      programDevelopmentState.fitnessScore),
        ]));
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
                color: ORANGE_COLOR.withAlpha(55),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Expanded(flex: 5, child: chart),
                    Expanded(
                        flex: 5,
                        child: SingleChildScrollView(
                          child: Column(
                            children: <Widget>[
                              Container(
                                height: 60,
                                width: MediaQuery.of(context).size.width,
                                color: WHITE_COLOR,
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
                                height: 60,
                                width: MediaQuery.of(context).size.width,
                                color: WHITE_COLOR,
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
                                width: MediaQuery.of(context).size.width,
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
                                        () async {
                                      ProgramDevelopmentDTO
                                          programDevelopmentDTO =
                                          ProgramDevelopmentDTO(
                                              null,
                                              null,
                                              this.nutritionRating,
                                              this.fitnessRating,
                                              this.widget.programId);
                                      int i = await setProgramDevelopment(
                                          context,
                                          programDevelopmentDTO,
                                          this.widget.programIndex);
                                      if (i == 1) {
                                        setState(() {
                                          this.programDevelopmentStateList =
                                              state.programListState.programs
                                                  .reversed
                                                  .toList()[
                                                      this.widget.programIndex]
                                                  .programDevelopmentStateList;
                                          chart = scoreChart();
                                        });
                                      } else {
                                        showAdjustDialog(context, FAILURE,
                                            false, null, ORANGE_COLOR);
                                      }
                                    }, ORANGE_COLOR);
                                  },
                                ),
                              ),
                              Container(
                                child: Text("--->" +
                                    (this
                                        .programDevelopmentStateList[0]
                                        .fitnessScore
                                        .toString())),
                              )
                            ],
                          ),
                        ))
                  ],
                ));
          },
        ));
  }
}
