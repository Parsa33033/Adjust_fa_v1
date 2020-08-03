

import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class DevelopmentPage extends StatefulWidget {

  @override
  _DevelopmentPageState createState() => _DevelopmentPageState();
}

class _DevelopmentPageState extends State<DevelopmentPage> {
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
      body: Container(
        color: ORANGE_COLOR.withAlpha(55),
        child: Text("sdofji"),
      ),
    );
  }
}