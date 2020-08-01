
import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_phoenix/flutter_phoenix.dart';

class LogoPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
          color: GREEN_COLOR,
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                InkWell(
                  child: Container(
                    height: 300,
                    child:  Image.asset("assets/adjust_logo.png"),
                  ),
                  onTap: () {
                    Phoenix.rebirth(context);
                  },
                )
//                Container(
//                  padding: EdgeInsets.only(top: 20),
//                  child: RaisedButton(
//                    color: RED,
//                    child: Directionality(
//                      textDirection: TextDirection.rtl,
//                      child: Text("شروع", style: TextStyle(fontFamily: "Iransans", fontSize: 22, color: FONT),),
//                    ),
//                  ),
//                )
              ],
            )
          ),
        )
    );
  }
}