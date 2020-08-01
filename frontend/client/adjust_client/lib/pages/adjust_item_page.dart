import 'package:adjust_client/components/adjust_raised_button.dart';
import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AdjustItemPage extends StatelessWidget {
  Image image;
  String name;
  String title;
  String description;
  Function onButtonPressed;
  bool isInfo;
  String buttonText;

  Color primaryColor;
  Color primaryColorLight;
  String backgroundImagePath;

  bool imageIsCircular;

  AdjustItemPage(
      {this.image,
      this.name,
      this.title,
      this.description,
      this.onButtonPressed,
      this.isInfo,
      this.buttonText,
      this.primaryColor,
      this.primaryColorLight,
      this.backgroundImagePath,
      this.imageIsCircular}) {
    this.imageIsCircular = this.imageIsCircular == null ? false : this.imageIsCircular;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Expanded(
              flex: 4,
              child: Container(
                child: Stack(
                  children: <Widget>[
                    Positioned(
//                      bottom: 80,
                      top: 0,
                      right: 0,
                      child: Container(
                        width: MediaQuery.of(context).size.width,
                        child: Image.asset(this.backgroundImagePath),
                      ),
                    ),
                    Positioned(
                      bottom: 0,
                      left: 20,
                      child: Container(
                        height: 200,
                        width: 200,
                        child: Hero(
                          tag: this.name,
                          child: this.imageIsCircular ?
                          CircleAvatar(
                            backgroundImage: this.image.image,
                          )
                          : this.image,
                        ),
                      ),
                    ),
                    Positioned(
                        top: 20,
                        right: 30,
                        child: Container(
                          padding: EdgeInsets.all(30),
                          width: MediaQuery.of(context).size.width,
                          child: Directionality(
                            textDirection: TextDirection.rtl,
                            child: Text(
                              this.name,
                              style: TextStyle(
                                  fontFamily: "Iransans",
                                  fontSize: 25,
                                  color: WHITE_COLOR),
                            ),
                          ),
                        )),
                  ],
                ),
              ),
            ),
            Expanded(
              flex: 4,
              child: Container(
                  child: SingleChildScrollView(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Container(
                        padding: EdgeInsets.all(20),
                        child: Center(
                          child: Directionality(
                            textDirection: TextDirection.rtl,
                            child: Text(
                              this.title,
                              style: TextStyle(
                                  fontFamily: "Iransans",
                                  fontSize: 25,
                                  color: FONT_COLOR),
                            ),
                          ),
                        )),
                    Container(
                      padding: EdgeInsets.all(20),
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(
                          this.description,
                          style: TextStyle(
                              fontFamily: "Iransans",
                              fontSize: 18,
                              color: FONT_COLOR),
                        ),
                      ),
                    ),
                  ],
                ),
              )),
            ),
            Expanded(
              flex: 2,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                    flex: 5,
                    child: Container(
                      padding: EdgeInsets.all(10),
                      child: AdjustRaisedButton(
                        width: MediaQuery.of(context).size.width,
                        height: 60,
                        fontSize: 16,
                        textDirection: TextDirection.rtl,
                        primaryColor: this.primaryColor,
                        secondaryColor: this.primaryColor,
                        fontColor: WHITE_COLOR,
                        text: BACK,
                        onPressed: () {
                          Navigator.of(context).pop();
                        },
                      ),
                    ),
                  ),
                  Expanded(
                    flex: 5,
                    child: this.isInfo
                        ? Container()
                        : Container(
                            padding: EdgeInsets.all(10),
                            child: AdjustRaisedButton(
                              width: MediaQuery.of(context).size.width,
                              height: 60,
                              fontSize: 16,
                              textDirection: TextDirection.rtl,
                              primaryColor: this.primaryColor,
                              secondaryColor: this.primaryColor,
                              fontColor: WHITE_COLOR,
                              text: buttonText,
                              onPressed: () {
                                this.onButtonPressed();
                              },
                            ),
                          ),
                  )
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}
