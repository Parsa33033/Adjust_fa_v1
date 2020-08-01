import 'dart:convert';
import 'dart:typed_data';
import 'dart:math';

import 'package:adjust_client/components/adjust_info_button.dart';
import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:redux/redux.dart';

class SpecialistPage extends StatefulWidget {
  @override
  _SpecialistPageState createState() => _SpecialistPageState();
}

class _SpecialistPageState extends State<SpecialistPage> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Align(
          alignment: Alignment.centerRight,
          child: Directionality(
            textDirection: TextDirection.rtl,
            child: Text(
              "متخصصین",
              style:
              TextStyle(fontFamily: "Iransans", fontSize: 20, color: WHITE_COLOR),
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
      body: StoreConnector<AppState, AppState>(
        converter: (Store store) => store.state,
        builder: (BuildContext context, AppState state) {
          return Container(
            color: LIGHT_GREY_COLOR,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  child: ListView.builder(
                      itemCount: state.specialistListState.specialists.length,
                      itemBuilder: (BuildContext context, int pos) {
                        SpecialistState e = state.specialistListState.specialists[pos];
                        Uint8List imageByte = Uint8List.fromList(base64Decode(e.image));
                        Image image = Image.memory(imageByte);

                        return AdjustInfoButton(
                          id: e.id.toString(),
                          width: 150,
                          height: 150,
                          title: e == null ? "" : e.username,
                          description: "",
                          name: e == null ? "" : e.firstName + " " + e.lastName,
                          fontSize: 14,
                          isVertical: false,
                          primaryColor: ORANGE_COLOR,
                          primaryColorLight: ORANGE_COLOR,
                          secondaryColor: WHITE_COLOR,
                          image: image,
                          func: () async {

                          },
                        );
                      }),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
