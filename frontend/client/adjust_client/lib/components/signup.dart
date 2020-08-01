import 'package:adjust_client/actions/user_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text_field.dart';
import 'package:adjust_client/components/preloader.dart';
import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/user_dto.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/profile_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:redux/redux.dart';
import 'package:string_validator/string_validator.dart';

class SignUp extends StatefulWidget {
  Function func;

  SignUp({this.func});

  @override
  _SignUpState createState() => _SignUpState();
}

class _SignUpState extends State<SignUp> {
  TextEditingController emailTextFieldController;
  TextEditingController passwordTextFieldController;
  TextEditingController passwordConfirmTextFieldController;

  final _formKey = GlobalKey<FormState>();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    emailTextFieldController = TextEditingController();
    passwordTextFieldController = TextEditingController();
    passwordConfirmTextFieldController = TextEditingController();
  }

  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        return Container(
            child: Form(
          child: SingleChildScrollView(
            //            padding: EdgeInsets.all(20),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                AdjustTextField(
                    textDirection: TextDirection.ltr,
                    controller: emailTextFieldController,
                    hintText: EMAIL,
                    enabled: true,
                    icon: Icon(
                      Icons.email,
                      color: GREEN_COLOR,
                    ),
                    isPassword: false,
                    primaryColor: GREEN_COLOR,
                    validator: (String value) {
                      if (!isEmail(value)) {
                        return WRONG_EMAIL;
                      }
                      return null;
                    },
                    padding: 0,
                    margin: 20),
                AdjustTextField(
                    textDirection: TextDirection.ltr,
                    controller: passwordTextFieldController,
                    hintText: PASSWORD,
                    enabled: true,
                    icon: Icon(
                      Icons.lock,
                      color: RED_COLOR,
                    ),
                    isPassword: true,
                    primaryColor: RED_COLOR,
                    validator: (String value) {
                      if (isNull(value)) {
                        return EMPTY;
                      }
                      if (isNumeric(value) || isAlpha(value)) {
                        return WRONG_PASSWORD;
                      }
                      return null;
                    },
                    padding: 0,
                    margin: 20),
                AdjustTextField(
                    textDirection: TextDirection.ltr,
                    controller: passwordConfirmTextFieldController,
                    hintText: PASSWORD_CONFIRM,
                    enabled: true,
                    icon: Icon(
                      Icons.lock,
                      color: ORANGE_COLOR,
                    ),
                    isPassword: true,
                    primaryColor: ORANGE_COLOR,
                    validator: (String value) {
                      if (value != passwordTextFieldController.text) {
                        return PASS_NOT_MATCH;
                      }
                      return null;
                    },
                    padding: 0,
                    margin: 20),
                SizedBox(
                  height: 40,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    AdjustRaisedButton(
                      text: BACK,
                      textDirection: TextDirection.rtl,
                      primaryColor: GREEN_COLOR,
                      secondaryColor: GREEN_COLOR,
                      height: 50,
                      width: 90,
                      onPressed: () {
                        this.widget.func();
                      },
                    ),
                    AdjustRaisedButton(
                      text: REGISTER,
                      textDirection: TextDirection.rtl,
                      primaryColor: GREEN_COLOR,
                      secondaryColor: GREEN_COLOR,
                      height: 50,
                      width: 90,
                      onPressed: () async {
                        if (_formKey.currentState.validate()) {
                          preloader(context);

                          String login = emailTextFieldController.text.toLowerCase();
                          String email = login;
                          String password = passwordTextFieldController.text;

                          ManagedUserDTO userDTO = ManagedUserDTO(
                            password,
                            login,
                            null,
                            null,
                            email,
                            null,
                            null,
                          );

                          int i = await registerUser(context, userDTO);
                          if (i == 1) {
                            Navigator.of(context, rootNavigator: true)
                                .pop("dialog");
                            Navigator.of(context).pushReplacement(
                                MaterialPageRoute(
                                    builder: (context) => ProfilePage()));
                          } else if (i == 0) {
                            Navigator.of(context, rootNavigator: true)
                                .pop("dialog");
                            showAdjustDialog(
                                context, REGISTRATION_FAILED, false, null, null);
                          }
                        }
                      },
                    )
                  ],
                ),
                SizedBox(
                  height: 20,
                )
              ],
            ),
          ),
          key: _formKey,
        ));
      },
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    emailTextFieldController.dispose();
    passwordTextFieldController.dispose();
    passwordConfirmTextFieldController.dispose();
    super.dispose();
  }
}
