import 'dart:convert';
import 'dart:typed_data';
import 'dart:math';

import 'package:adjust_client/actions/shoping_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_info_button.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/model/shoping_item.dart';
import 'package:adjust_client/pages/order_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';

class CartPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Align(
          alignment: Alignment.centerRight,
          child: Directionality(
            textDirection: TextDirection.rtl,
            child: Text(
              "سبد خرید",
              style:
                  TextStyle(fontFamily: "Iransans", fontSize: 20, color: WHITE_COLOR),
            ),
          ),
        ),
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

          double amount = 0;
          state.cartState.items.forEach((element) {
            amount += element.price;
          });
          return Container(
            color: LIGHT_GREY_COLOR,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  flex: 8,
                  child: ListView.builder(
                      itemCount: state.cartState.items.length,
                      itemBuilder: (BuildContext context, int pos) {
                        ShopingItem e = state.cartState.items[pos];
                        Uint8List imageByte = Uint8List.fromList(base64Decode(e.image));
                        Image image = Image.memory(imageByte);
                        return Dismissible(
                          key: Key(e.name),
                          onDismissed: (direction) {
                            state.cartState.items.removeAt(pos);
                            StoreProvider.of<AppState>(context).dispatch(
                                RemoveFromCartAction(payload: state.cartState));
                          },
                          child: AdjustInfoButton(
                            id: e.id.toString(),
                            width: 130,
                            height: 130,
                            title: e == null
                                ? ""
                                : NumberUtility.changeDigit(
                                    e.price.round().toString() + " ریال",
                                    NumStrLanguage.Farsi),
                            description: "",
                            name: e == null ? "" : e.name,
                            fontSize: 14,
                            isVertical: false,
                            primaryColor: LIGHT_GREEN_COLOR,
                            primaryColorLight: GREEN_COLOR,
                            secondaryColor: WHITE_COLOR,
                            image: image,
                            func: () async {},
                          ),
                        );
                      }),
                ),
                Expanded(
                  flex: 2,
                  child: Container(
                    color: WHITE_COLOR,
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        Expanded(
                          flex: 3,
                          child: Container(
                            padding: EdgeInsets.all(10),
                            child: Directionality(
                              textDirection: TextDirection.rtl,
                              child: Text(
                                NumberUtility.changeDigit(amount.toString(),
                                        NumStrLanguage.Farsi) +
                                    " ریال",
                                style: TextStyle(
                                    fontFamily: "Iransans",
                                    fontSize: 20,
                                    color: FONT_COLOR),
                              ),
                            ),
                          ),
                        ),
                        Expanded(
                            flex: 7,
                            child: Container(
                              padding: EdgeInsets.all(10),
                              child: AdjustRaisedButton(
                                height: 50,
                                width: 200,
                                textDirection: TextDirection.rtl,
                                text: ORDER,
                                fontColor: WHITE_COLOR,
                                primaryColor: GREEN_COLOR,
                                secondaryColor: GREEN_COLOR,
                                onPressed: () {
                                  if (state.cartState.items.length <= 0) {
                                    showAdjustDialog(
                                        context, CART_EMPTY, false, null, null);
                                  } else {
                                    Navigator.of(context).push(
                                        MaterialPageRoute(
                                            builder: (context) => OrderPage()));
                                  }
                                },
                              ),
                            )),
                      ],
                    ),
                  ),
                )
              ],
            ),
          );
        },
      ),
    );
  }
}
