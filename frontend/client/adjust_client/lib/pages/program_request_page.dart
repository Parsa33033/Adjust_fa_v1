

import 'dart:convert';
import 'dart:typed_data';

import 'package:adjust_client/actions/specialist_action.dart';
import 'package:adjust_client/components/adjust_info_button.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/pages/adjust_item_page.dart';
import 'package:adjust_client/pages/body_composition_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'dart:ui' as ui;

import 'package:rainbow_color/rainbow_color.dart';
import 'package:redux/redux.dart';

class ProgramRequestPage extends StatefulWidget {
  @override
  _ProgramRequestPageState createState() => _ProgramRequestPageState();
}

class _ProgramRequestPageState extends State<ProgramRequestPage> with TickerProviderStateMixin{
  AnimationController _leftCardTipController;
  AnimationController _rightCardTipController;
  Animation leftCardTipAnimation;
  Animation rightCardTipAnimation;

  Color color;

  bool hasNext = true;
  bool hasPrevious = false;

  Widget content;
  int index;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    color = GREEN_COLOR;
    index = 0;
    content = Container();
    _leftCardTipController = AnimationController(vsync: this, duration: Duration(milliseconds: 250));
    _rightCardTipController = AnimationController(vsync: this, duration: Duration(milliseconds: 250));
    leftCardTipAnimation = Tween<double>(begin: 0, end: 100).animate(_leftCardTipController)..addListener(() {setState(() {

    });});
    rightCardTipAnimation = Tween<double>(begin: 0, end: 100).animate(_rightCardTipController)..addListener(() {setState(() {

    });});
  }

  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        return Container(
            padding: EdgeInsets.all(20),
            child: Stack(
              children: <Widget>[
                CustomPaint(
                  painter: Background(
                    right: rightCardTipAnimation.value,
                    left: leftCardTipAnimation.value,
                    r: 30,
                    color: color,
                  ),
                  child: Container(
                    height: 400,
                    width: MediaQuery.of(context).size.width,
                  ),
                ),
                this.content,
                index == 1 ? Positioned(
                    left: 0,
                    top: 0,
                    height: 200,
                    width: 200,
                    child: Image.asset("assets/nutrition_icon_200.png")
                ) : Container(),
                index == 2 ? Positioned(
                    right: 0,
                    top: 0,
                    height: 190,
                    width: 190,
                    child: Image.asset("assets/workout_icon_200.png")
                ) : Container(),
                hasNext ?
                Positioned(
                  right: 30,
                  bottom: 30,
                  height: 70,
                  width: 70,
                  child: InkWell(
                    child: Icon(Icons.arrow_right, size: 70, color: LIGHT_GREY_COLOR,),
                    onTap: () async {
                      index++;
                      if (index == 1) {
                        setState(() {
                          hasPrevious = true;
                          color = RED_COLOR;
                        });
                        _rightCardTipController.forward();
                      } else if (index == 2) {
                        setState(() {
                          color = GREEN_COLOR;
                        });
                        _rightCardTipController.animateBack(0);
                        _leftCardTipController.forward();
                      } else if (index == 3) {
                        setState(() {
                          color = ORANGE_COLOR;
                        });
                        _leftCardTipController.animateBack(0);
                      } else if (index == 4) {
                        setState(() {
                          color = YELLOW_COLOR;
                        });
                        int i = await getSpecialistList(context);
                        if (i == 1){
                          setState(() {
                            hasNext = false;
                            content = specialistList(state);
                          });
                        }
                      }
                    },
                  ),
                ) :
                    Container(),
                hasPrevious ?
                Positioned(
                  left: 30,
                  bottom: 30,
                  height: 70,
                  width: 70,
                  child: InkWell(
                    child: Icon(Icons.arrow_left, size: 70, color: LIGHT_GREY_COLOR,),
                    onTap: () async {
                      index--;
                      if (index == 0) {
                        setState(() {
                          color = GREEN_COLOR;
                        });
                        setState(() {
                          hasPrevious = false;
                        });
                        _rightCardTipController.animateBack(0);
                      } else if (index == 1) {
                        setState(() {
                          color = RED_COLOR;
                        });
                        _rightCardTipController.forward();
                        _leftCardTipController.animateBack(0);
                      } else if (index == 2) {
                        setState(() {
                          color = GREEN_COLOR;
                        });
                        _leftCardTipController.forward();
                      } else if (index == 3) {
                        setState(() {
                          color = ORANGE_COLOR;
                          content = Container();
                          hasNext = true;
                        });
                      }
                    },
                  ),
                ) :
                Container()
              ],
            )
        );
      },
    );
  }

  Widget specialistList(AppState state) {
    return Container(
      padding: EdgeInsets.all(20),
      child: ListView.builder(
          itemCount: state.specialistListState.specialists.length,
          itemBuilder: (BuildContext context, int pos) {
            SpecialistState e = state.specialistListState.specialists[pos];
            if (e.busy) {
              return null;
            } else {
              Uint8List imageByte = Uint8List.fromList(base64Decode(e.image));
              Image image = Image.memory(imageByte);

              return AdjustInfoButton(
                id: e.username,
                width: 150,
                height: 150,
                title: e == null ? "" : "رشته: " + e.field + " - " + e.degree,
                description: "", //e == null ? "" : "رزومه: " + e.resume ,
                name: e == null ? "" : e.firstName + " " + e.lastName,
                fontSize: 14,
                isVertical: false,
                primaryColor: YELLOW_COLOR,
                primaryColorLight: LIGHT_YELLOW_COLOR,
                secondaryColor: FONT_COLOR,
                image: image,
                imageIsCircular: true,
                func: () async {
                  Navigator.of(context).push(MaterialPageRoute(builder: (context) => AdjustItemPage(
                      image: image,
                      name: e.firstName + " " + e.lastName,
                      title: e.degree + " - " + e.field,
                      description: e.resume,
                      buttonText: "انتخاب",
                      isInfo: false,
                      primaryColor: YELLOW_COLOR,
                      primaryColorLight: LIGHT_YELLOW_COLOR,
                      backgroundImagePath: "assets/bg_yellow.png",
                      onButtonPressed: () {
                        Navigator.of(context).push(MaterialPageRoute(builder: (context) => BodyCompositionPage(e)));
                      },
                      imageIsCircular: true
                  )));
                },
              );
            }
          }),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    _leftCardTipController.dispose();
    _rightCardTipController.dispose();
    super.dispose();
  }
}

class Background extends CustomPainter {
  Color color;
  double r;
  double left;
  double right;
  Background({this.r, this.color, this.left, this.right});

  @override
  void paint(Canvas canvas, Size size) {
    double h = size.height;
    double w = size.width;

    Paint paint = Paint()
    ..color = this.color
    ..shader = ui.Gradient.linear(Offset(0, 0), Offset(w, h), [HSLColor.fromColor(color).withLightness(0.8).toColor(),color,]);

    Path path = Path();
    path.moveTo(0, left + r);
    path.lineTo(0, h - r);
    path.quadraticBezierTo(0, h, r, h);
    path.lineTo(w - r, h);
    path.quadraticBezierTo(w, h, w, h - r);
    path.lineTo(w, right + r);
    path.quadraticBezierTo(w, right + right/10, w - r, right );
    path.lineTo(r, left - left/10);
    path.quadraticBezierTo(0, left, 0, left + r);
    path.close();

    canvas.drawPath(path, paint);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    return true;
  }

}