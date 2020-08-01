import 'dart:convert';
import 'dart:typed_data';

import 'package:adjust_client/actions/shoping_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_info_button.dart';
import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/cart_dto.dart';
import 'package:adjust_client/dto/shoping_item_dto.dart';
import 'package:adjust_client/model/cart.dart';
import 'package:adjust_client/model/shoping_item.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/adjust_item_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';

class ShopingPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        double width = MediaQuery.of(context).size.width;
        return Container(
          decoration: BoxDecoration(
//            boxShadow: [BoxShadow(color: SHADOW, offset: Offset(4,4), spreadRadius: 2, blurRadius: 2)],
            color: LIGHT_GREY_COLOR
          ),
            width: width,
//            margin: EdgeInsets.only(bottom: 30, top: 10),
            child: SingleChildScrollView(
              padding: EdgeInsets.only(bottom: 30, top: 10),
              child: Column(
                children: <Widget>[
//                  Container(
//                      padding: EdgeInsets.all(10),
//                      width: width,
//                      child: Center(
//                        child: Directionality(
//                          textDirection: TextDirection.rtl,
//                          child: Text(
//                            "توکن ها",
//                            style: TextStyle(
//                                fontFamily: "Iransans",
//                                fontSize: 18,
//                                color: FONT),
//                          ),
//                        ),
//                      )),
                  Container(
                    height: 200,
                    child: ListView(
//                      padding: EdgeInsets.all(10),
                        scrollDirection: Axis.horizontal,
                        children: state.tokenState.items.map((e) {
                          List i = Uint8List.fromList(base64Decode(e.image));
                          List<int> imageByte = List<int>.from(i);
                          Image image =
                              Image.memory(Uint8List.fromList(imageByte));

                          return AdjustInfoButton(
                            width: 160,
                            height: 160,
                            id: e.name,
                            title: e == null
                                ? ""
                                : NumberUtility.changeDigit(
                                    e.price.round().toString(),
                                    NumStrLanguage.Farsi) +
                                    " ریال",
                            description: "",
                            name: e == null ? "" : NumberUtility.changeDigit(
                                e.token.round().toString() + " توکن",
                                NumStrLanguage.Farsi),
                            fontSize: 14,
                            isVertical: true,
                            primaryColor: RED_COLOR,
                            primaryColorLight: LIGHT_RED_COLOR,
                            secondaryColor: WHITE_COLOR,
                            image: image,
                            func: () async {
                              int id = e.id;
                              Navigator.of(context).push(PageRouteBuilder(
                                  transitionDuration:
                                      Duration(milliseconds: 500),
                                  pageBuilder:
                                      (context, animation1, animation2) {
                                    return AdjustItemPage(
                                      image: image,
                                      name: e.name,
                                      title: e.name,
                                      description: e.description,
                                      buttonText: "خرید",
                                      isInfo: false,
                                      primaryColor: RED_COLOR,
                                      primaryColorLight: LIGHT_RED_COLOR,
                                      backgroundImagePath: "assets/bg_red.png",
                                      onButtonPressed: () async {
                                        showAdjustDialog(context, SURE_WITH_DECISION, true, () async {
                                          await buyToken(context, id);
                                          Navigator.of(context).pushReplacement(
                                              MaterialPageRoute(
                                                  builder: (context) =>
                                                      MainPage()));
                                        }, RED_COLOR);
                                      },
                                    );
                                  }));
                            },
                          );
                        }).toList()),
                  ),
//                  Container(
//                      padding: EdgeInsets.all(10),
//                      width: width,
//                      child: Center(
//                        child: Directionality(
//                          textDirection: TextDirection.rtl,
//                          child: Text(
//                            "فروشگاه",
//                            style: TextStyle(
//                                fontFamily: "Iransans",
//                                fontSize: 18,
//                                color: FONT),
//                          ),
//                        ),
//                      )),
                  Container(
                    height: 200,
                    child: ListView(
//                      padding: EdgeInsets.all(20),
                        scrollDirection: Axis.horizontal,
                        children: state.shopingState.items.map((e) {
                          List i = Uint8List.fromList(base64Decode(e.image));
                          List<int> imageByte = List<int>.from(i);
                          Image image =
                              Image.memory(Uint8List.fromList(imageByte));

                          return AdjustInfoButton(
                            width: 160,
                            height: 160,
                            id: e.name,
                            title: e == null
                                ? ""
                                : NumberUtility.changeDigit(
                                        NumberUtility.changeDigit(e.price.round().toString(), NumStrLanguage.Farsi),
                                        NumStrLanguage.Farsi) +
                                    " ریال",
                            description: "",
                            name: e == null ? "" : e.name,
                            fontSize: 14,
                            isVertical: true,
                            primaryColor: LIGHT_GREEN_COLOR,
                            primaryColorLight: GREEN_COLOR,
                            secondaryColor: WHITE_COLOR,
                            image: image,
                            func: () async {
                              int id = e.id;
                              Navigator.of(context).push(PageRouteBuilder(
                                  transitionDuration:
                                      Duration(milliseconds: 500),
                                  pageBuilder:
                                      (context, animation1, animation2) {
                                    return AdjustItemPage(
                                      image: image,
                                      name: e.name,
                                      title: e.name,
                                      description: e.description,
                                      buttonText: "افزودن به سبد خرید",
                                      isInfo: false,
                                      primaryColor: GREEN_COLOR,
                                      primaryColorLight: LIGHT_GREEN_COLOR,
                                      backgroundImagePath: "assets/bg_green.png",
                                      onButtonPressed: () async {
                                        ShopingItem item = ShopingItem(
                                            e.id,
                                            e.name,
                                            e.description,
                                            e.token,
                                            e.price,
                                            e.image,
                                            e.imageContentType,
                                            e.orderable,
                                            e.id);
                                        List<ShopingItem> items =
                                            state.cartState.items;
                                        items.add(item);
                                        AdjustCart cart = AdjustCart(
                                            null,
                                            state.userState.login,
                                            state.clientState.firstName,
                                            state.clientState.lastName,
                                            items);
                                        addToCart(context, cart);
                                        Navigator.of(context).pushReplacement(
                                            MaterialPageRoute(
                                                builder: (context) =>
                                                    MainPage()));
                                      },
                                    );
                                  }));
                            },
                          );
                        }).toList()),
                  )
                ],
              ),
            ));
      },
    );
  }
}
