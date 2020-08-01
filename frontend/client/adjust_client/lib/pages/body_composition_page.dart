import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:math';
import 'dart:typed_data';
import 'package:adjust_client/actions/program_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text_field.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/body_composition_dto.dart';
import 'package:adjust_client/dto/client_dto.dart';
import 'package:adjust_client/dto/program_dto.dart';
import 'package:adjust_client/dto/nutrition_program_dto.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/program_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:avatar_glow/avatar_glow.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:image_picker/image_picker.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:shamsi_date/shamsi_date.dart';
import 'package:simple_image_crop/simple_image_crop.dart';
import 'package:stepper_counter_swipe/stepper_counter_swipe.dart';

class BodyCompositionPage extends StatefulWidget {
  SpecialistState specialist;

  BodyCompositionPage(this.specialist);

  @override
  _BodyCompositionPageState createState() => _BodyCompositionPageState();
}

class _BodyCompositionPageState extends State<BodyCompositionPage> {
  TextEditingController heightTextFieldController;
  TextEditingController weightTextFieldController;
  TextEditingController wristTextFieldController;

  final _formKey = GlobalKey<FormState>();

  Image _bodyCompositionImage;
  Image _bloodTestImage;
  List<int> _bodyCompositionFile;
  List<int> _bloodTestFile;

  final picker = ImagePicker();

  int heightNuminal;
  int heightDecimal;
  int weightNuminal;
  int weightDecimal;
  int wristNuminal;
  int wristDecimal;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    heightTextFieldController = TextEditingController();
    weightTextFieldController = TextEditingController();
    wristTextFieldController = TextEditingController();

    heightNuminal = 0;
    heightDecimal = 0;
    weightNuminal = 0;
    weightDecimal = 0;
    wristNuminal = 0;
    wristDecimal = 0;

    _bodyCompositionImage = null;
    _bloodTestImage = null;
  }

  Future<Uint8List> getImage() async {
    File imageFile = await ImagePicker.pickImage(source: ImageSource.camera);
    Uint8List imageByte = imageFile.readAsBytesSync();
    return imageByte;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        return Container(
          color: LIGHT_GREY_COLOR,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Expanded(
                flex: 30,
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
                        child: Container(
                          child: Image.asset("assets/adjust_logo.png"),
                        ),
                      ),
                      Positioned(
                          left: 20,
                          top: 50,
                          child: Container(
                            height: 50,
                            width: 50,
                            child: InkWell(
                              child: Icon(
                                Icons.arrow_back,
                                color: WHITE_COLOR,
                                size: 50,
                              ),
                              onTap: () {
                                Navigator.of(context).pop();
                              },
                            ),
                          )),
                    ],
                  ),
                ),
              ),
              Expanded(
                flex: 70,
                child: Container(
                  child: SingleChildScrollView(
                      child: Form(
                    key: _formKey,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        decimalNumberPicker(
                            context,
                            heightTextFieldController,
                            HEIGHT + " (سانتیمتر)",
                            GREEN_COLOR,
                            HEIGHT + " به سانتی متر",
                            this.heightNuminal,
                            this.heightDecimal),
                        decimalNumberPicker(
                            context,
                            weightTextFieldController,
                            WEIGHT + " (کیلوگرم)",
                            RED_COLOR,
                            WEIGHT + " به کیلوگرم",
                            this.weightNuminal,
                            this.weightDecimal),
                        decimalNumberPicker(
                            context,
                            wristTextFieldController,
                            WRIST + " (سانتیمتر)",
                            ORANGE_COLOR,
                            WRIST + " به سانتی متر",
                            this.wristNuminal,
                            this.wristDecimal),
                        Container(
                          padding: EdgeInsets.all(20),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              Expanded(
                                  flex: 5,
                                  child: Column(
                                    children: <Widget>[
                                      Container(
                                          padding: const EdgeInsets.all(8.0),
                                          child: RaisedButton(
                                            child: Text("انتخاب تصویر"),
                                            onPressed: () async {
                                              Uint8List imageByte =
                                                  await getImage();
                                              _bodyCompositionFile =
                                                  List<int>.of(imageByte);
                                              Image image =
                                                  Image.memory(imageByte);
                                              setState(() {
                                                _bodyCompositionImage = image;
                                              });
                                            },
                                          )),
                                      _bodyCompositionImage != null
                                          ? InkWell(
                                              child: Container(
                                                height: 60,
                                                width: 40,
                                                child: _bodyCompositionImage,
                                              ),
                                              onTap: () {
                                                showDialog(
                                                    context: context,
                                                    child: Dialog(
                                                      child:
                                                          _bodyCompositionImage,
                                                    ));
                                              },
                                            )
                                          : Container()
                                    ],
                                  )),
                              Expanded(
                                flex: 5,
                                child: Container(
                                  padding: EdgeInsets.only(right: 20),
                                  child: Directionality(
                                    textDirection: TextDirection.rtl,
                                    child: Text(
                                      "تصویر بادی کامپوزیشن" + ":",
                                      style: TextStyle(
                                          fontFamily: "Iransans",
                                          color: FONT_COLOR,
                                          fontSize: 18),
                                    ),
                                  ),
                                ),
                              )
                            ],
                          ),
                        ),
                        Container(
                          padding: EdgeInsets.all(20),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              Expanded(
                                  flex: 5,
                                  child: Column(
                                    children: <Widget>[
                                      Container(
                                          padding: const EdgeInsets.all(8.0),
                                          child: RaisedButton(
                                            child: Text("انتخاب تصویر"),
                                            onPressed: () async {
                                              Uint8List imageByte =
                                                  await getImage();
                                              _bloodTestFile =
                                                  List<int>.of(imageByte);
                                              Image image =
                                                  Image.memory(imageByte);
                                              setState(() {
                                                _bloodTestImage = image;
                                              });
                                            },
                                          )),
                                      _bloodTestImage != null
                                          ? InkWell(
                                              child: Container(
                                                  height: 60,
                                                  width: 40,
                                                  child: _bloodTestImage),
                                              onTap: () {
                                                showDialog(
                                                    context: context,
                                                    child: Dialog(
                                                      child: _bloodTestImage,
                                                    ));
                                              },
                                            )
                                          : Container()
                                    ],
                                  )),
                              Expanded(
                                flex: 5,
                                child: Container(
                                  padding: EdgeInsets.only(right: 20),
                                  child: Directionality(
                                    textDirection: TextDirection.rtl,
                                    child: Text(
                                      "تصویر آزمایش خون" + ":",
                                      style: TextStyle(
                                          fontFamily: "Iransans",
                                          color: FONT_COLOR,
                                          fontSize: 18),
                                    ),
                                  ),
                                ),
                              )
                            ],
                          ),
                        ),
                        Container(
                          padding: EdgeInsets.all(20),
                          child: AdjustRaisedButton(
                              text: OK,
                              textDirection: TextDirection.rtl,
                              primaryColor: GREEN_COLOR,
                              secondaryColor: GREEN_COLOR,
                              height: 50,
                              width: MediaQuery.of(context).size.width,
                              onPressed: () async {
                                if (_formKey.currentState.validate()) {
                                  if (_bodyCompositionFile != null) {
                                    preloader(context);
                                    double height = double.parse(this.heightTextFieldController.text);
                                    double weight = double.parse(this.weightTextFieldController.text);
                                    double bmi = weight / pow(height, 2);


                                    BodyCompositionDTO bodyCompositionDTO =
                                    BodyCompositionDTO(
                                        null,
                                        DateTime.now(),
                                        height,
                                        weight,
                                        bmi,
                                        base64Encode(_bodyCompositionFile),
                                        "image/jpg",
                                        _bloodTestFile != null ? base64Encode(_bloodTestFile) : null,
                                        "image/jpg");
                                    ProgramDTO programDTO = ProgramDTO(
                                        null,
                                        DateTime.now(),
                                        null,
                                        false,
                                        false,
                                        false,
                                        null,
                                        null,
                                        null,
                                        state.clientState.id,
                                        this.widget.specialist.id,
                                        null,
                                        null,
                                        bodyCompositionDTO,
                                        null,
                                        null);
                                    int i = await requestForProgram(context, programDTO);
                                    if (i == 1) {
                                      Navigator.of(context, rootNavigator: true).pop();
                                      showAdjustDialog(context, SUCCESS, true, () {
                                        Navigator.of(context).pushReplacement(MaterialPageRoute(builder: (context) => MainPage()));
                                      }, GREEN_COLOR);
                                    } else {
                                      Navigator.of(context, rootNavigator: true).pop();
                                      showAdjustDialog(context, FAILURE, false, null, GREEN_COLOR);
                                    }
                                  } else {
                                    showAdjustDialog(context, "لطفا تصویر بادی کامپوزیشن را الحاق کنید!", false, null, GREEN_COLOR);
                                  }
                                }
                              }),
                        )
                      ],
                    ),
                  )),
                ),
              )
            ],
          ),
        );
      },
    ));
  }

  Widget decimalNumberPicker(
      BuildContext context,
      TextEditingController controller,
      String text,
      Color color,
      String pickerText,
      int numinal,
      int decimal) {
    return InkWell(
      child: AdjustTextField(
          textDirection: TextDirection.rtl,
          textAlign: TextAlign.right,
          controller: controller,
          hintText: text,
          enabled: false,
          icon: Icon(
            Icons.location_on,
            color: color,
          ),
          isPassword: false,
          primaryColor: color,
          validator: (String value) {
            if (value == null || value == "") {
              return EMPTY;
            }
            return null;
          },
          padding: 0,
          margin: 20),
      onTap: () {
        showDialog(
            context: context,
            child: Dialog(
              child: Container(
                  color: color,
                  height: 300,
                  width: 200,
                  padding: EdgeInsets.all(20),
                  child: SingleChildScrollView(
                    child: Column(
                      children: <Widget>[
                        Directionality(
                          textDirection: TextDirection.rtl,
                          child: Text(
                            pickerText,
                            style: TextStyle(
                                fontFamily: "Iransans",
                                fontSize: 18,
                                color: WHITE_COLOR),
                          ),
                        ),
                        Align(
                            alignment: Alignment.center,
                            child: Center(
                              child: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceEvenly,
                                children: <Widget>[
                                  Container(
                                    height: 150,
                                    padding: const EdgeInsets.all(8.0),
                                    child: StepperSwipe(
                                      initialValue: numinal,
                                      speedTransitionLimitCount: 8,
                                      //Trigger count for fast counting
                                      onChanged: (int value) {
                                        numinal = value;
                                      },
                                      firstIncrementDuration:
                                          Duration(milliseconds: 120),
                                      //Unit time before fast counting
                                      secondIncrementDuration:
                                          Duration(milliseconds: 60),
                                      //Unit time during fast counting
                                      direction: Axis.vertical,
                                      dragButtonColor: color,
                                      withNaturalNumbers: false,
                                      withPlusMinus: true,
                                      maxValue: 500,
                                      stepperValue: numinal,
                                      withFastCount: true,
                                    ),
                                  ),
                                  Text(".", style: TextStyle(fontSize: 25),),
                                  Container(
                                    height: 150,
                                    padding: const EdgeInsets.all(8.0),
                                    child: StepperSwipe(
                                      initialValue: decimal,
                                      speedTransitionLimitCount: 8,
                                      //Trigger count for fast counting
                                      onChanged: (int value) {
                                        decimal = value;
                                      },
                                      firstIncrementDuration:
                                          Duration(milliseconds: 120),
                                      //Unit time before fast counting
                                      secondIncrementDuration:
                                          Duration(milliseconds: 60),
                                      //Unit time during fast counting
                                      direction: Axis.vertical,
                                      dragButtonColor: color,
                                      withNaturalNumbers: true,
                                      withPlusMinus: true,
                                      maxValue: 500,
                                      stepperValue: decimal,
                                      withFastCount: true,
                                    ),
                                  ),
                                ],
                              ),
                            )),
                        AdjustRaisedButton(
                          width: 60,
                          height: 40,
                          textDirection: TextDirection.rtl,
                          text: OK,
                          fontColor: WHITE_COLOR,
                          primaryColor: color,
                          secondaryColor: color,
                          fontSize: 16,
                          onPressed: () {
                            controller.text = (numinal + (decimal / 100)).toString();
                            Navigator.of(context, rootNavigator: true)
                                .pop("dialog");
                          },
                        )
                      ],
                    ),
                  )),
            ));
      },
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    heightTextFieldController.dispose();
    weightTextFieldController.dispose();
    wristTextFieldController.dispose();
    super.dispose();
  }
}
