import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:math';
import 'dart:typed_data';
import 'package:adjust_specialist/actions/program_action.dart';
import 'package:adjust_specialist/components/adjust_dialog.dart';
import 'package:adjust_specialist/components/adjust_raised_button.dart';
import 'package:adjust_specialist/components/adjust_text_field.dart';
import 'package:adjust_specialist/components/preloader.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:adjust_specialist/dto/body_composition_dto.dart';
import 'package:adjust_specialist/dto/client_dto.dart';
import 'package:adjust_specialist/dto/program_dto.dart';
import 'package:adjust_specialist/dto/nutrition_program_dto.dart';
import 'package:adjust_specialist/main.dart';
import 'package:adjust_specialist/model/client.dart';
import 'package:adjust_specialist/pages/main_page.dart';
import 'package:adjust_specialist/pages/program_page.dart';
import 'package:adjust_specialist/states/app_state.dart';
import 'package:adjust_specialist/states/body_composition_state.dart';
import 'package:adjust_specialist/states/client_state.dart';
import 'package:adjust_specialist/states/program_state.dart';
import 'package:adjust_specialist/states/specialist_state.dart';
import 'package:age/age.dart';
import 'package:avatar_glow/avatar_glow.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:expandable_card/expandable_card.dart';
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
  ProgramState programState;

  BodyCompositionPage(this.programState);

  @override
  _BodyCompositionPageState createState() => _BodyCompositionPageState();
}

class _BodyCompositionPageState extends State<BodyCompositionPage> {
  TextEditingController nameTextFieldController;
  TextEditingController genderTextFieldController;
  TextEditingController ageTextFieldController;
  TextEditingController heightTextFieldController;
  TextEditingController weightTextFieldController;
  TextEditingController wristTextFieldController;
  TextEditingController waistTextFieldController;
  TextEditingController muscleMassTextFieldController;
  TextEditingController fatMassTextFieldController;

  final _formKey = GlobalKey<FormState>();

  Image _bodyImage;
  Image _bodyCompositionImage;
  Image _bloodTestImage;

  List<int> _bodyImageFile;
  List<int> _bodyCompositionFile;
  List<int> _bloodTestFile;

  final picker = ImagePicker();

  int heightNuminal;
  int heightDecimal;
  int weightNuminal;
  int weightDecimal;
  int wristNuminal;
  int wristDecimal;
  int waistNuminal;
  int waistDecimal;
  int muscleMassNuminal;
  int muscleMassDecimal;
  int fatMassNuminal;
  int fatMassDecimal;

  bool agreed = false;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    nameTextFieldController = TextEditingController();
    genderTextFieldController = TextEditingController();
    ageTextFieldController = TextEditingController();
    heightTextFieldController = TextEditingController();
    weightTextFieldController = TextEditingController();
    wristTextFieldController = TextEditingController();
    waistTextFieldController = TextEditingController();
    muscleMassTextFieldController = TextEditingController();
    fatMassTextFieldController = TextEditingController();

    ClientState clientState = this.widget.programState.clientState;

    // name
    nameTextFieldController.text =
        clientState.firstName + " " + clientState.lastName;

    // gender
    genderTextFieldController.text =
        GENDER_LIST[EnumToString.parse(clientState.gender)];
    AgeDuration age;

    // Find out your age
    age = Age.dateDifference(
        fromDate: clientState.birthDate,
        toDate: DateTime.now(),
        includeToDate: false);
    ageTextFieldController.text = age.years.toString();

    BodyCompositionState bodyCompositionState = this.widget.programState.bodyCompositionStateList[0];
    heightTextFieldController.text = bodyCompositionState.height == null ? "" : bodyCompositionState.height.toString();
    weightTextFieldController.text = bodyCompositionState.weight == null ? "" : bodyCompositionState.weight.toString();
    wristTextFieldController.text = bodyCompositionState.wrist == null ? "" : bodyCompositionState.wrist.toString();
    waistTextFieldController.text = bodyCompositionState.waist == null ? "" : bodyCompositionState.waist.toString();
    muscleMassTextFieldController.text = bodyCompositionState.muscleMass == null ? "" : bodyCompositionState.muscleMass.toString();
    fatMassTextFieldController.text = bodyCompositionState.fatMass == null ? "" : bodyCompositionState.fatMass.toString();

    heightNuminal = 0;
    heightDecimal = 0;
    weightNuminal = 0;
    weightDecimal = 0;
    wristNuminal = 0;
    wristDecimal = 0;
    waistNuminal = 0;
    waistDecimal = 0;
    muscleMassNuminal = 0;
    muscleMassDecimal = 0;
    fatMassNuminal = 0;
    fatMassDecimal = 0;
    agreed = false;

    _bodyImage = null;
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
        return ExpandableCardPage(
          page: page(state),
          expandableCard: ExpandableCard(
            minHeight: 100,
            backgroundColor: GREEN_COLOR,
            children: <Widget>[
              SingleChildScrollView(
                child: StoreConnector<AppState, AppState>(
                  converter: (Store store) => store.state,
                  builder: (BuildContext context, AppState state) {
                    return Container(
                        child: SingleChildScrollView(
                      child: Column(
                        children: <Widget>[
                          Container(
                              alignment: Alignment.centerRight,
                              child: Directionality(
                                  textDirection: TextDirection.rtl,
                                  child: FittedBox(
                                    fit: BoxFit.scaleDown,
                                    child: Row(
                                      children: <Widget>[
                                        Container(
                                          padding: EdgeInsets.only(
                                              right: 20, bottom: 20),
                                          child: Align(
                                            alignment: Alignment.centerRight,
                                            child: Directionality(
                                              textDirection: TextDirection.rtl,
                                              child: Text(
                                                "در صورت موافقت با شرایط ذیل تیک بزنید:",
                                                style: TextStyle(
                                                    fontFamily: "Iransans",
                                                    color: WHITE_COLOR,
                                                    fontSize: 14),
                                              ),
                                            ),
                                          ),
                                        ),
                                        Container(
                                          height: 30,
                                          width: 30,
                                          padding: EdgeInsets.only(
                                              right: 20, bottom: 20),
                                          child: Checkbox(
                                            value: agreed,
                                            onChanged: (bool val) {
                                              setState(() {
                                                agreed = val;
                                              });
                                            },
                                          ),
                                        ),
                                      ],
                                    ),
                                  ))),
                          Container(
                            height: 400,
                            child: SingleChildScrollView(
                                padding: EdgeInsets.all(15),
                                child: Column(
                                  children: <Widget>[
                                    Directionality(
                                      textDirection: TextDirection.rtl,
                                      child: Text(
                                        TERMS_BODY_COMPOSITION,
                                        style: TextStyle(
                                            fontFamily: "Iransans",
                                            color: WHITE_COLOR,
                                            fontSize: 16),
                                      ),
                                    ),
                                  ],
                                )),
                          )
                        ],
                      ),
                    ));
                  },
                ),
              )
            ],
          ),
        );
      },
    ));
  }

  Widget page(AppState state) {
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
                    AdjustTextField(
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: nameTextFieldController,
                        hintText: FIRST_NAME + " و " + LAST_NAME,
                        enabled: false,
                        icon: Icon(
                          Icons.person,
                          color: GREY_COLOR,
                        ),
                        isPassword: false,
                        primaryColor: GREY_COLOR,
                        validator: (String value) {
                          if (value == null || value == "") {
                            return EMPTY;
                          }
                          return null;
                        },
                        padding: 0,
                        margin: 20),
                    AdjustTextField(
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: genderTextFieldController,
                        hintText: GENDER,
                        enabled: false,
                        icon: Icon(
                          Icons.person,
                          color: GREY_COLOR,
                        ),
                        isPassword: false,
                        primaryColor: GREY_COLOR,
                        validator: (String value) {
                          if (value == null || value == "") {
                            return EMPTY;
                          }
                          return null;
                        },
                        padding: 0,
                        margin: 20),
                    AdjustTextField(
                        textAlign: TextAlign.right,
                        textDirection: TextDirection.rtl,
                        controller: ageTextFieldController,
                        hintText: AGE,
                        enabled: false,
                        icon: Icon(
                          Icons.person,
                          color: GREY_COLOR,
                        ),
                        isPassword: false,
                        primaryColor: GREY_COLOR,
                        validator: (String value) {
                          if (value == null || value == "") {
                            return EMPTY;
                          }
                          return null;
                        },
                        padding: 0,
                        margin: 20),
                    decimalNumberPicker(
                        context,
                        heightTextFieldController,
                        HEIGHT + " (180.50 سانتیمتر)" + "*",
                        GREEN_COLOR,
                        HEIGHT + " به سانتی متر",
                        this.heightNuminal,
                        this.heightDecimal, (value) {
                      if (value == null || value == "") {
                        return EMPTY;
                      }
                      return null;
                    }),
                    decimalNumberPicker(
                        context,
                        weightTextFieldController,
                        WEIGHT + " (70.50 کیلوگرم)" + "*",
                        RED_COLOR,
                        WEIGHT + " به کیلوگرم",
                        this.weightNuminal,
                        this.weightDecimal, (value) {
                      if (value == null || value == "") {
                        return EMPTY;
                      }
                      return null;
                    }),
                    decimalNumberPicker(
                        context,
                        wristTextFieldController,
                        WRIST + " (18.05 سانتیمتر)" + "*",
                        ORANGE_COLOR,
                        WRIST + " به سانتی متر",
                        this.wristNuminal,
                        this.wristDecimal, (value) {
                      if (value == null || value == "") {
                        return EMPTY;
                      }
                      return null;
                    }),
                    decimalNumberPicker(
                        context,
                        waistTextFieldController,
                        WAIST + " (سانتیمتر)" + "*",
                        GREEN_COLOR,
                        WAIST + " به سانتی متر",
                        this.wristNuminal,
                        this.wristDecimal, (value) {
                      if (value == null || value == "") {
                        return EMPTY;
                      }
                      return null;
                    }),
                    decimalNumberPicker(
                        context,
                        muscleMassTextFieldController,
                        MUSCLE_MASS + " (کیلوگرم)",
                        RED_COLOR,
                        MUSCLE_MASS + " به کیلوگرم",
                        this.wristNuminal,
                        this.wristDecimal, (value) {
                      return null;
                    }),
                    decimalNumberPicker(
                        context,
                        fatMassTextFieldController,
                        FAT_MASS + " (کیلوگرم)",
                        ORANGE_COLOR,
                        FAT_MASS + " به کیلوگرم",
                        this.wristNuminal,
                        this.wristDecimal, (value) {
                      return null;
                    }),
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
                                        color: GREEN_COLOR,
                                        child: Text(
                                          "انتخاب تصویر",
                                          style: TextStyle(
                                              fontFamily: "Iransans",
                                              color: WHITE_COLOR,
                                              fontSize: 14),
                                        ),
                                        onPressed: () async {
                                          Uint8List imageByte =
                                              await getImage();
                                          _bodyImageFile =
                                              List<int>.of(imageByte);
                                          Image image = Image.memory(imageByte);
                                          setState(() {
                                            _bodyImage = image;
                                          });
                                        },
                                      )),
                                  _bodyImage != null
                                      ? InkWell(
                                          child: Container(
                                            height: 60,
                                            width: 40,
                                            child: _bodyImage,
                                          ),
                                          onTap: () {
                                            showDialog(
                                                context: context,
                                                child: Dialog(
                                                  child: _bodyImage,
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
                                  "تصویر اندام از گردن به پایین" + ":",
                                  style: TextStyle(
                                      fontFamily: "Iransans",
                                      color: FONT_COLOR,
                                      fontSize: 14),
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
                                        color: RED_COLOR,
                                        child: Text(
                                          "انتخاب تصویر",
                                          style: TextStyle(
                                              fontFamily: "Iransans",
                                              color: WHITE_COLOR,
                                              fontSize: 14),
                                        ),
                                        onPressed: () async {
                                          Uint8List imageByte =
                                              await getImage();
                                          _bodyCompositionFile =
                                              List<int>.of(imageByte);
                                          Image image = Image.memory(imageByte);
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
                                                  child: _bodyCompositionImage,
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
                                        color: ORANGE_COLOR,
                                        child: Text(
                                          "انتخاب تصویر",
                                          style: TextStyle(
                                              fontFamily: "Iransans",
                                              color: WHITE_COLOR,
                                              fontSize: 14),
                                        ),
                                        onPressed: () async {
                                          Uint8List imageByte =
                                              await getImage();
                                          _bloodTestFile =
                                              List<int>.of(imageByte);
                                          Image image = Image.memory(imageByte);
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
                      padding: EdgeInsets.only(
                          top: 20, right: 20, left: 20, bottom: 120),
                      child: AdjustRaisedButton(
                          text: OK,
                          textDirection: TextDirection.rtl,
                          primaryColor: GREEN_COLOR,
                          secondaryColor: GREEN_COLOR,
                          height: 50,
                          width: MediaQuery.of(context).size.width,
                          onPressed: () async {
                            if (_formKey.currentState.validate()) {
                              if (agreed) {
                                preloader(context);
                                double height = double.parse(
                                    this.heightTextFieldController.text);
                                double weight = double.parse(
                                    this.weightTextFieldController.text);
                                double bmi = weight / pow(height, 2);
                                double wrist = double.parse(
                                    this.wristTextFieldController.text);
                                double waist = double.parse(
                                    this.waistTextFieldController.text);
                                double muscleMass = null;
                                if (muscleMassTextFieldController.text != "" &&
                                    muscleMassTextFieldController.text !=
                                        null) {
                                  muscleMass = double.parse(
                                      this.muscleMassTextFieldController.text);
                                }
                                double fatMass = null;
                                if (fatMassTextFieldController.text != "" &&
                                    fatMassTextFieldController.text != null) {
                                  fatMass = double.parse(
                                      this.fatMassTextFieldController.text);
                                }

                                double lbm =
                                  this.widget.programState.clientState.gender == Gender.MALE
                                        ? (0.32810 * weight) +
                                            (0.33929 * height) -
                                            29.5336
                                        : (0.29569 * weight) +
                                            (0.41813 * height) -
                                            43.2933;

                                AgeDuration age;

                                // Find out your age
                                age = Age.dateDifference(
                                    fromDate: this.widget.programState.clientState.birthDate,
                                    toDate: DateTime.now(),
                                    includeToDate: false);
                                BodyCompositionDTO bodyCompositionDTO =
                                    BodyCompositionDTO(
                                        null,
                                        DateTime.now(),
                                        height,
                                        weight,
                                        bmi,
                                        wrist,
                                        waist,
                                        lbm,
                                        muscleMass,
                                        null,
                                        fatMass,
                                        null,
                                        this.widget.programState.clientState.gender,
                                        age.years,
                                        _bodyImageFile != null
                                            ? base64Encode(_bodyImageFile)
                                            : null,
                                        "image/jpg",
                                        _bodyCompositionFile != null
                                            ? base64Encode(_bodyCompositionFile)
                                            : null,
                                        "image/jpg",
                                        _bloodTestFile != null
                                            ? base64Encode(_bloodTestFile)
                                            : null,
                                        "image/jpg",
                                        null);
                                List<BodyCompositionDTO>
                                    bodyCompositionDTOList = List()
                                      ..add(bodyCompositionDTO);
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
                                    this.widget.programState.clientState.id,
                                    this.widget.programState.specialistState.id,
                                    null,
                                    null,
                                    bodyCompositionDTOList,
                                    null,
                                    null);
//                                int i = await requestForProgram(
//                                    context, programDTO);
//                                if (i == 1) {
//                                  Navigator.of(context, rootNavigator: true)
//                                      .pop();
//                                  showAdjustDialog(context, SUCCESS, true, () {
//                                    Navigator.of(context).pushReplacement(
//                                        MaterialPageRoute(
//                                            builder: (context) => MainPage()));
//                                  }, GREEN_COLOR);
//                                } else {
//                                  Navigator.of(context, rootNavigator: true)
//                                      .pop();
//                                  showAdjustDialog(context, FAILURE, false,
//                                      null, GREEN_COLOR);
//                                }
                              } else {
                                showAdjustDialog(
                                    context,
                                    "برای ادامه باید با شرایط شرح داده شده در پایین صفحه موافقت فرمایید.",
                                    false,
                                    null,
                                    GREEN_COLOR);
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
  }

  Widget decimalNumberPicker(
      BuildContext context,
      TextEditingController controller,
      String text,
      Color color,
      String pickerText,
      int numinal,
      int decimal,
      Function validator) {
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
            return validator(value);
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
                                  Text(
                                    ".",
                                    style: TextStyle(fontSize: 25),
                                  ),
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
                            controller.text =
                                (numinal + (decimal / 100)).toString();
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
    nameTextFieldController.dispose();
    genderTextFieldController.dispose();
    ageTextFieldController.dispose();
    heightTextFieldController.dispose();
    weightTextFieldController.dispose();
    wristTextFieldController.dispose();
    waistTextFieldController.dispose();
    muscleMassTextFieldController.dispose();
    fatMassTextFieldController.dispose();
    super.dispose();
  }
}
