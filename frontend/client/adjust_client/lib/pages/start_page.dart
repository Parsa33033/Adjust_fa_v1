

import 'dart:async';
import 'dart:math';

import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/pages/login_page.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class StartPage extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    Timer(Duration(seconds: 2), () {
      Navigator.of(context).pushReplacement(
          PageRouteBuilder(transitionDuration: Duration(seconds: 2),pageBuilder: (BuildContext context, Animation<double> animation1, Animation<double> animation2) {
            return LoginPage();
          },
              opaque: true)
      );
    });
    return Scaffold(
      body: Container(
        color: GREEN_COLOR,
        child: Center(
            child: Container(
              height: 300,
                child: Hero(
                  tag: "logo",
                  child: Image.asset("assets/adjust_logo.png"),
                )
            ),
        ),
      )
    );
  }

}