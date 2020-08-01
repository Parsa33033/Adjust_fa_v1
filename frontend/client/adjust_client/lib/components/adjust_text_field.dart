

import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AdjustTextField extends StatelessWidget {
  String hintText;
  Icon icon;
  Color primaryColor;
  Color secondaryColor;
  bool enabled;
  bool isPassword;
  TextEditingController controller;
  Function(String) validator;
  TextDirection textDirection;
  double padding;
  double margin;
  TextAlign textAlign;

  AdjustTextField({
      this.hintText,
      this.icon,
      this.primaryColor,
      this.secondaryColor,
      this.enabled,
      this.isPassword,
      this.controller,
      this.validator,
      this.textDirection,
      this.padding,
      this.margin,
      this.textAlign}) {
    this.textAlign = this.textAlign == null ? TextAlign.left : this.textAlign;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(margin),
      padding: EdgeInsets.all(padding),
      child: Directionality(
        textDirection: textDirection,
        child: TextFormField(
          textAlign: this.textAlign,
          decoration: InputDecoration(
            focusColor: primaryColor,
            hoverColor: primaryColor,
            fillColor: primaryColor,
            border: OutlineInputBorder(
              borderRadius: BorderRadius.all(Radius.circular(5)),
              borderSide: BorderSide(
                  color: primaryColor,
                  width: 2,
                  style: BorderStyle.solid
              ),
            ),
            enabledBorder: OutlineInputBorder(
              borderRadius: BorderRadius.all(Radius.circular(5)),
              borderSide: BorderSide(
                  color: primaryColor,
                  width: 2,
                  style: BorderStyle.solid
              ),
            ),
            focusedBorder: OutlineInputBorder(
              borderRadius: BorderRadius.all(Radius.circular(5)),
              borderSide: BorderSide(
                  color: primaryColor,
                  width: 2,
                  style: BorderStyle.solid
              ),
            ),
            disabledBorder: OutlineInputBorder(
              borderRadius: BorderRadius.all(Radius.circular(5)),
              borderSide: BorderSide(
                  color: primaryColor,
                  width: 2,
                  style: BorderStyle.solid
              ),
            ),
            labelText: hintText,
            prefixIcon: icon,
            hintStyle: TextStyle(fontFamily: "Iransans", fontSize: 16, color: FONT_COLOR),
            labelStyle: TextStyle(fontFamily: "Iransans", fontSize: 16, color: FONT_COLOR),
            enabled: true,
          ),
          style: TextStyle(fontFamily: "Iransans", fontSize: 16, color: FONT_COLOR),
          enabled: this.enabled,
          obscureText: this.isPassword,
          controller: controller,
          maxLines: 1,
          validator: (String text) {
            return this.validator(text);
          },
          textDirection: textDirection,
        ),
      )
    );
  }
}