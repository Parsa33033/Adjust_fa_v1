import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/meal_state.dart';
import 'package:adjust_client/states/nutrition_program_state.dart';
import 'package:adjust_client/states/nutrition_state.dart';
import 'package:adjust_client/states/program_state.dart';
import 'package:expandable_card/expandable_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:spincircle_bottom_bar/modals.dart';
import 'package:spincircle_bottom_bar/spincircle_bottom_bar.dart';

class NutritionProgramPage extends StatefulWidget {
  int programIndex;

  NutritionProgramPage(this.programIndex);

  @override
  _NutritionProgramPageState createState() => _NutritionProgramPageState();
}

class _NutritionProgramPageState extends State<NutritionProgramPage> {
  Widget content;
  int selected;

  @override
  initState() {
    super.initState();
    content = Container();
    selected = -1;
  }

  void nutritionInfo(NutritionState nutrition) {
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
                          nutrition.name,
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
                    Container(
                      padding: EdgeInsets.all(10),
                      child: Align(
                        alignment: Alignment.center,
                        child: Directionality(
                          textDirection: TextDirection.rtl,
                          child: Text(
                            nutrition.description,
                            style: TextStyle(
                                fontFamily: "Iransans",
                                color: FONT_COLOR,
                                fontSize: 16),
                          ),
                        ),
                      ),
                    ),
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
                                        "مقدار در واحد",
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: FONT_COLOR,
                                            fontSize: 13),
                                      ),
                                    ),
                                  ),
                                )),
                            DataColumn(
                                label: Expanded(
                                  child: Align(
                                    alignment: Alignment.centerRight,
                                    child: Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        "محتوا",
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
                                        nutrition.caloriesPerUnit.toString(),
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
                                    CALORY,
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
                                        nutrition.proteinPerUnit.toString() + " گرم",
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
                                    PROTEIN,
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
                                        nutrition.carbsPerUnit.toString() + " گرم",
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
                                    CARBS,
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
                                        nutrition.fatInUnit.toString() + " گرم",
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
                                    FAT,
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
                    Container(
                      height: 55,
                      alignment: Alignment.center,
                      padding: EdgeInsets.all(10),
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          "انواع",
                          style: TextStyle(
                              fontFamily: "Iransans",
                              color: FONT_COLOR,
                              fontSize: 13),
                        ),
                      ),
                    ),
                    Container(
                      height: 200,
                      padding: EdgeInsets.all(20),
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: GridView.count(
                            crossAxisCount: 2,
                            mainAxisSpacing: 1,
                            crossAxisSpacing: 1,
                            children: nutrition.foods.map((e) {
                              return Container(
                                color: RED_COLOR,
                                  padding: EdgeInsets.all(5),
                                  child: Center(
                                    child: FittedBox(
                                      fit: BoxFit.scaleDown,
                                      child: Directionality(
                                        textDirection: TextDirection.rtl,
                                        child: Text(
                                          e.name,
                                          style: TextStyle(
                                              fontFamily: "Iransans",
                                              color: WHITE_COLOR,
                                              fontSize: 16),
                                        ),
                                      ),
                                    )
                                  )
                              );
                            }).toList()),
                      ),
                    ),
                  ],
                ),
              )),
        ));
  }

  Widget nutritionTable(MealState meal) {
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
                          "تعداد واحد",
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
                          "ماده غذایی",
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
              rows: meal.nutritions.map((nutrition) {
                return DataRow(cells: <DataCell>[
                  DataCell(InkWell(
                    child: Align(
                      alignment: Alignment.center,
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          NumberUtility.changeDigit(
                              nutrition.unit.toString(), NumStrLanguage.Farsi),
                          style: TextStyle(
                              fontFamily: "Iransans",
                              fontSize: 16,
                              color: FONT_COLOR),
                        ),
                      ),
                    ),
                    onTap: () {
                      nutritionInfo(nutrition);
                    },
                  )),
                  DataCell(InkWell(
                    child: Align(
                      alignment: Alignment.centerRight,
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          nutrition.name,
                          style: TextStyle(
                              fontFamily: "Iransans",
                              fontSize: 16,
                              color: FONT_COLOR),
                        ),
                      ),
                    ),
                    onTap: () {
                      nutritionInfo(nutrition);
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
              "برنامه ی تغذیه",
              style: TextStyle(
                  fontFamily: "Iransans", fontSize: 20, color: WHITE_COLOR),
            ),
          ),
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
      body: ExpandableCardPage(
        page: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            NutritionProgramState nutritionProgramState = state.programListState
                .programs.reversed.toList()[this.widget.programIndex].nutritionProgramState;
            return Container(
                height: MediaQuery.of(context).size.height,
                color: RED_COLOR.withAlpha(25),
                child: SingleChildScrollView(
                  child: Column(
                    children: <Widget>[
                      Container(
                        height: 60,
                        width: MediaQuery.of(context).size.width,
                        child: Directionality(
                          textDirection: TextDirection.rtl,
                          child: ListView.builder(
                            itemCount: nutritionProgramState.meals.length,
                            scrollDirection: Axis.horizontal,
                            itemBuilder: (BuildContext context, int pos) {
                              MealState mealState =
                                  nutritionProgramState.meals[pos];
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
                                            mealState.name,
                                            NumStrLanguage.Farsi),
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: RED_COLOR,
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
                                              color: RED_COLOR,
                                            ),
                                          )
                                        : Container()
                                  ],
                                ),
                                onTap: () {
                                  setState(() {
                                    selected = pos;
                                    content = nutritionTable(mealState);
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
          backgroundColor: RED_COLOR,
          children: <Widget>[
            SingleChildScrollView(
              child: StoreConnector<AppState, AppState>(
                converter: (Store store) => store.state,
                builder: (BuildContext context, AppState state) {
                  NutritionProgramState nutritionProgramState = state
                      .programListState
                      .programs.reversed.toList()[this.widget.programIndex]
                      .nutritionProgramState;
                  return Container(
                      child: SingleChildScrollView(
                    child: Column(
                      children: <Widget>[
                        Container(
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
                                                  color: WHITE_COLOR,
                                                  fontSize: 13)),
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
                                           child: Text(CARBS,
                                               style: TextStyle(
                                                   fontFamily: "Iransans",
                                                   color: WHITE_COLOR,
                                                   fontSize: 13)),
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
                                          child: Text(PROTEIN,
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  color: WHITE_COLOR,
                                                  fontSize: 13)),
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
                                          child: Text(CALORY + " روزانه",
                                              style: TextStyle(
                                                  fontFamily: "Iransans",
                                                  color: WHITE_COLOR,
                                                  fontSize: 13)),
                                        ),
                                      ),
                                    )
                                ),
                              ],
                              rows: <DataRow>[
                                DataRow(cells: <DataCell>[
                                  DataCell(Align(
                                    alignment: Alignment.centerRight,
                                    child: Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        NumberUtility.changeDigit(
                                            nutritionProgramState.fatPercentage
                                                    .toString() +
                                                "%",
                                            NumStrLanguage.Farsi),
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: WHITE_COLOR,
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
                                            nutritionProgramState
                                                    .carbsPercentage
                                                    .toString() +
                                                "%",
                                            NumStrLanguage.Farsi),
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: WHITE_COLOR,
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
                                            nutritionProgramState
                                                    .proteinPercentage
                                                    .toString() +
                                                "%",
                                            NumStrLanguage.Farsi),
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: WHITE_COLOR,
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
                                            nutritionProgramState.dailyCalories
                                                .round()
                                                .toString(),
                                            NumStrLanguage.Farsi),
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: WHITE_COLOR,
                                            fontSize: 13),
                                      ),
                                    ),
                                  )),
                                ])
                              ],
                            ),
                          ),
                        ),
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
                                  nutritionProgramState.description == null
                                      ? Text("")
                                      : Directionality(
                                          textDirection: TextDirection.rtl,
                                          child: Text(
                                            nutritionProgramState.description +
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
