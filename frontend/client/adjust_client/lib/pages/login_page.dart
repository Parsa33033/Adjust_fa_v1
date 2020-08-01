import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/signup.dart';
import 'package:adjust_client/components/singin.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage>
    with SingleTickerProviderStateMixin {
  AnimationController curtainAnimationController;
  Animation curtainAnimation;
  Widget content = Container();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    curtainAnimationController =
        AnimationController(duration: Duration(milliseconds: 500), vsync: this);
    curtainAnimation =
        IntTween(begin: 65, end: 35).animate(curtainAnimationController)
          ..addListener(() {
            setState(() {});
          });
    setState(() {
      content = initWidget();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Expanded(
            flex: curtainAnimation.value,
            child: Container(
              child: Stack(
                children: <Widget>[
                  Positioned(
                    top: 0,
                    right: 0,
                    bottom: 100,
                    left: 0,
                    child: Container(
                      color: GREEN_COLOR,
                    ),
                  ),
                  Positioned(
                      bottom: 0,
                      left: 0,
                      right: 0,
                      height: 180,
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: <Widget>[
                          Expanded(
                            flex: 8,
                            child: Container(
                                child: Hero(
                              tag: "logo",
                              child: Image.asset("assets/adjust_logo.png"),
                            )),
                          ),
                          Expanded(
                              flex: 2,
                              child: Container(
                                padding: EdgeInsets.only(top: 7),
                                child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: Text(
                                    "مطب آنلاین " + RAMSARI,
                                    style: TextStyle(
                                        color: FONT_COLOR,
                                        fontFamily: "Iransans",
                                        fontSize: 16),
                                  ),
                                ),
                              ))
                        ],
                      ))
                ],
              ),
            ),
          ),
          Expanded(
            flex: 100 - curtainAnimation.value,
            child: content,
          )
        ],
      ),
    ));
  }

  Widget initWidget() {
    return Container(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Container(
            child: AdjustRaisedButton(
              text: LOGIN,
              textDirection: TextDirection.rtl,
              primaryColor: GREEN_COLOR,
              secondaryColor: GREEN_COLOR,
              height: 50,
              width: 90,
              onPressed: () {
                openCurtain(SignIn(
                  func: () {
                    setState(() {
                      this.content = Container();
                      this
                          .curtainAnimationController
                          .animateBack(0)
                          .then((value) {
                        closeCurtain();
                      });
                    });
                  },
                ));
              },
            ),
          ),
          Container(
            child: AdjustRaisedButton(
              text: REGISTER,
              textDirection: TextDirection.rtl,
              primaryColor: GREEN_COLOR,
              secondaryColor: GREEN_COLOR,
              height: 50,
              width: 90,
              onPressed: () {
                openCurtain(SignUp(
                  func: () {
                    setState(() {
                      this.content = Container();
                      this
                          .curtainAnimationController
                          .animateBack(0)
                          .then((value) {
                        closeCurtain();
                      });
                    });
                  },
                ));
              },
            ),
          ),
        ],
      ),
    );
  }

  void openCurtain(Widget content) {
    setState(() {
      this.content = Container();
      curtainAnimationController.forward().then((value) {
        this.content = content;
      });
    });
  }

  void closeCurtain() {
    setState(() {
      curtainAnimationController.animateBack(0).then((value) {
        this.content = initWidget();
      });
    });
  }
}
