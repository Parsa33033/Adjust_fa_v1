
import 'dart:math';

import 'package:adjust_client/components/adjust_dialog.dart';
import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/pages/profile_page.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class MenuPage extends StatelessWidget {

  Image image;
  MenuPage({this.image});

  void logout() async {
    FlutterSecureStorage storage = FlutterSecureStorage();
    await storage.delete(key: "jwt");
    SystemNavigator.pop();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: GREEN_COLOR,
        child: Column(
          children: <Widget>[
            Container(
              alignment: Alignment.centerRight,
              padding: EdgeInsets.only(right: 30, top: 100, bottom: 20),
              width: MediaQuery.of(context).size.width,
              child: CircleAvatar(
                radius: 70,
                backgroundImage: image.image,
              ),
            ),
            Divider(
              height: 20,
              thickness: 2,
              color: WHITE_COLOR,
            ),
            menuItem(PROFILE, Icons.person, () {Navigator.of(context).push(MaterialPageRoute(builder: (context) => ProfilePage(image: image, isFromMainPage: true,)));}),
            menuItem(LOGOUT, Icons.exit_to_app, () {showAdjustDialog(context, SURE_TO_EXIT, true, () {logout();}, null);}),

          ],
        ),
      ),
    );
  }

  Widget menuItem(String text, IconData icon, Function func) {
    return InkWell(
      child: Container(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: <Widget>[
              Container(
                padding: EdgeInsets.all(10),
                child: Directionality(
                  textDirection: TextDirection.rtl,
                  child: Text(text, style: TextStyle(fontFamily: "Iransans", fontSize: 18, color: WHITE_COLOR),),
                ),
              ),
              Container(
                padding: EdgeInsets.all(10),
                child: Icon(icon, color: WHITE_COLOR,),
              )
            ],
          )
      ),
      onTap: () {
        func();
      },
    );
  }

}