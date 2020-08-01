import 'dart:typed_data';

import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/pages/cart_page.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:badges/badges.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_zoom_drawer/flutter_zoom_drawer.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';


class Dashboard extends StatefulWidget {
  String firstName;
  String lastName;
  double token;
  double score;
  Image image;

  Dashboard({this.firstName, this.lastName, this.token, this.score, this.image});


  @override
  _DashboardState createState() => _DashboardState();
}

class _DashboardState extends State<Dashboard> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        int l = state.cartState.items.length;
        return Container(
          margin: EdgeInsets.only(top: 20),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Expanded(
                flex: 15,
                child: InkWell(
                  child: Container(
                    margin: EdgeInsets.only(right: 10),
                    decoration: BoxDecoration(
                      color: WHITE_COLOR,
                      borderRadius: BorderRadius.only(
                          bottomRight: Radius.circular(5),
                          topRight: Radius.circular(5)),
                      boxShadow: [
                        BoxShadow(
                            color: SHADOW_COLOR,
                            blurRadius: 5,
                            spreadRadius: 5,
                            offset: Offset(2, 2))
                      ],
                    ),
                    child: Center(
                        child: Badge(
                          showBadge: l > 0 ? true : false,
                          badgeContent: Text(l.toString()),
                          child: Icon(Icons.add_shopping_cart),
                        )
                    ),
                  ),
                  onTap: () {
                    Navigator.of(context).push(MaterialPageRoute(builder: (context) => CartPage()));
                  },
                )
              ),
              Expanded(
                flex: 70,
                child: Container(
                    padding: EdgeInsets.only(
                        left: 15, right: 15, bottom: 15, top: 20),
                    decoration: BoxDecoration(
                      color: WHITE_COLOR,
                      borderRadius: BorderRadius.only(
                          bottomRight: Radius.circular(5),
                          topRight: Radius.circular(5),
                          bottomLeft: Radius.circular(5),
                          topLeft: Radius.circular(5)),
                      boxShadow: [
                        BoxShadow(
                            color: SHADOW_COLOR,
                            blurRadius: 5,
                            spreadRadius: 5,
                            offset: Offset(2, 2))
                      ],
                    ),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        Expanded(
                          flex: 6,
                          child: Container(
                            padding: EdgeInsets.only(right: 7),
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                              children: <Widget>[
                                Expanded(
                                  flex: 50,
                                  child: FittedBox(
                                    alignment: Alignment.center,
                                    fit: BoxFit.scaleDown,
                                    child: Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        this.widget.firstName +
                                            " " +
                                            this.widget.lastName,
                                        style: TextStyle(
                                          fontFamily: "Iransans",
                                          color: FONT_COLOR,
                                          fontSize: 18,
                                        ),
                                      ),
                                    ),
                                  )
                                ),
                                Expanded(
                                  flex: 25,
                                  child: FittedBox(
                                    alignment: Alignment.center,
                                    fit: BoxFit.scaleDown,
                                    child: Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        "امتیاز: " + NumberUtility.changeDigit(this.widget.score.round().toString(), NumStrLanguage.Farsi),
                                        style: TextStyle(
                                          fontFamily: "Iransans",
                                          color: FONT_COLOR,
                                          fontSize: 16,
                                        ),
                                      ),
                                    ),
                                  )
                                ),
                                Expanded(
                                  flex: 25,
                                  child: FittedBox(
                                    alignment: Alignment.center,
                                    fit: BoxFit.scaleDown,
                                    child: Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        "توکن: " + NumberUtility.changeDigit(this.widget.token.round().toString(), NumStrLanguage.Farsi),
                                        style: TextStyle(
                                          fontFamily: "Iransans",
                                          color: FONT_COLOR,
                                          fontSize: 16,
                                        ),
                                      ),
                                    ),
                                  )
                                )
                              ],
                            ),
                          ),
                        ),
                        Expanded(
                          flex: 4,
                          child: Container(
                            child: CircleAvatar(
                                radius: MediaQuery.of(context).size.width *
                                    7 /
                                    10 *
                                    18 /
                                    100,
//                          maxRadius: 52,
                                backgroundImage: this.widget.image.image),
                          ),
                        )
                      ],
                    )),
              ),
              Expanded(
                  flex: 15,
                  child: InkWell(
                    child: Container(
                      margin: EdgeInsets.only(left: 10),
                      decoration: BoxDecoration(
                        color: WHITE_COLOR,
                        borderRadius: BorderRadius.only(
                            bottomLeft: Radius.circular(5),
                            topLeft: Radius.circular(5)),
                        boxShadow: [
                          BoxShadow(
                              color: SHADOW_COLOR,
                              blurRadius: 5,
                              spreadRadius: 5,
                              offset: Offset(2, 2))
                        ],
                      ),
                      child: Center(
                        child: Icon(Icons.menu),
                      ),
                    ),
                    onTap: () {
                      zoomDrawerController.toggle();
                    },
                  )),
            ],
          ),
        );
      },
    );
  }
}
