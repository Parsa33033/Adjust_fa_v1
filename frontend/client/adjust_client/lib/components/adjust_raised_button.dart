

import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AdjustRaisedButton extends StatelessWidget {
  Function onPressed;
  Color primaryColor;
  Color secondaryColor;
  double height;
  double width;
  String text;
  TextDirection textDirection;
  Color fontColor;
  double fontSize;


  AdjustRaisedButton({this.onPressed, this.primaryColor, this.secondaryColor,
      this.height, this.width, this.text, this.textDirection, this.fontColor, this.fontSize}) {
    this.fontColor = this.fontColor == null ? fontColor = WHITE_COLOR : this.fontColor;
    this.fontSize = this.fontSize == null ? fontSize = 18 : this.fontSize;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: RaisedButton(
        elevation: 5,
        color: secondaryColor,
        shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(5)),
            side: BorderSide(color: primaryColor, width: 2)
        ),
        child: Container(
            height: height,
            width: width,
            child: Center(
              child: Directionality(
                textDirection: textDirection,
                child: Text(text, style: TextStyle(fontFamily: "Iransans", fontSize: this.fontSize, color: fontColor),),
              ),
            )
        ),
        onPressed: onPressed
      ),
    );
  }
}