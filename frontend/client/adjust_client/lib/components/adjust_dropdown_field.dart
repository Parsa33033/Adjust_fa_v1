import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AdjustDropDownField extends StatefulWidget {
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
  List items;
  String value;
  TextAlign textAlign;
  Alignment alignment;
  Function(String) setValue;

  AdjustDropDownField({
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
    this.items,
    this.value,
    this.textAlign,
    this.alignment,
    this.setValue});

  @override
  _AdjustDropDownFieldState createState() => _AdjustDropDownFieldState();
}

class _AdjustDropDownFieldState extends State<AdjustDropDownField> {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(widget.margin),
      padding: EdgeInsets.all(widget.padding),
      child: Directionality(
        textDirection: this.widget.textDirection,
        child: DropdownButtonFormField<String>(
          decoration: InputDecoration(
              focusColor: widget.primaryColor,
              hoverColor: widget.primaryColor,
              fillColor: widget.primaryColor,
              border: OutlineInputBorder(
                borderRadius: BorderRadius.all(Radius.circular(5)),
                borderSide: BorderSide(
                    color: widget.primaryColor,
                    width: 2,
                    style: BorderStyle.solid
                ),
              ),
              enabledBorder: OutlineInputBorder(
                borderRadius: BorderRadius.all(Radius.circular(5)),
                borderSide: BorderSide(
                    color: widget.primaryColor,
                    width: 2,
                    style: BorderStyle.solid
                ),
              ),
              focusedBorder: OutlineInputBorder(
                borderRadius: BorderRadius.all(Radius.circular(5)),
                borderSide: BorderSide(
                    color: widget.primaryColor,
                    width: 2,
                    style: BorderStyle.solid
                ),
              ),
              disabledBorder: OutlineInputBorder(
                borderRadius: BorderRadius.all(Radius.circular(5)),
                borderSide: BorderSide(
                    color: widget.primaryColor,
                    width: 2,
                    style: BorderStyle.solid
                ),
              ),
              labelText: widget.hintText,
              prefixIcon: widget.icon,
              hintStyle: TextStyle(
                fontFamily: "Iransans", fontSize: 16, color: FONT_COLOR,),
              labelStyle: TextStyle(
                  fontFamily: "Iransans", fontSize: 16, color: FONT_COLOR),
              enabled: true,
              isDense: false
          ),
          validator: (String text) {
            return this.widget.validator(text);
          },
          isExpanded: true,
          items: this.widget.items.map((e) =>
              DropdownMenuItem<String>(value: e.toString(), child: Align(
                alignment: this.widget.alignment,
                child: Directionality(
                  textDirection: this.widget.textDirection,
                  child: Text(e, style: TextStyle(
                    fontFamily: "Iransans", fontSize: 16, color: FONT_COLOR,),),
                ),
              ))).toList(),
          value: this.widget.value,
          onChanged: (String val) {
            String keyMap;
            GENDER_LIST.forEach((key, value) {
              if (value == val) this.widget.setValue(key);
            });
            setState(() {
              this.widget.value = val;
            });
          },
        ),
      )
    );
  }
}