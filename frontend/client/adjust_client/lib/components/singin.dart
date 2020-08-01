import 'package:adjust_client/actions/authentication_action.dart';
import 'package:adjust_client/actions/user_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text_field.dart';
import 'package:adjust_client/components/preloader.dart';
import 'file:///F:/Projects/Adjust/alpha/frontend/adjust_client/lib/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/login_dto.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:string_validator/string_validator.dart';

class SignIn extends StatefulWidget {
  Function func;

  SignIn({this.func});

  @override
  _SignInState createState() => _SignInState();
}

class _SignInState extends State<SignIn> {
  TextEditingController emailTextFieldController;
  TextEditingController passwordTextFieldController;
  TextEditingController emailRecoverPassTextFieldController;

  final _formKey = GlobalKey<FormState>();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    emailTextFieldController = TextEditingController();
    passwordTextFieldController = TextEditingController();
    emailRecoverPassTextFieldController = TextEditingController();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        child: Form(
      child: SingleChildScrollView(
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
                  if (!isEmail(value)) {
                    return WRONG_EMAIL;
                  }
                  return null;
                },
                padding: 0,
                margin: 20),
            SizedBox(
              height: 40,
            ),
            Container(
              height: 40,
              padding: EdgeInsets.only(right: 20),
              child: InkWell(
                child: Directionality(
                  textDirection: TextDirection.rtl,
                  child: Text(
                    FORGOTTEN_PASS,
                    style: TextStyle(
                        fontFamily: "Iransans",
                        fontSize: 16,
                        color: Colors.blue),
                  ),
                ),
                onTap: () {
                  emailRecovery();
                },
              ),
            ),
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
                  text: LOGIN,
                  textDirection: TextDirection.rtl,
                  primaryColor: GREEN_COLOR,
                  secondaryColor: GREEN_COLOR,
                  height: 50,
                  width: 90,
                  onPressed: () async {
                    preloader(context);
                    String username = emailTextFieldController.text.toLowerCase();
                    String password = passwordTextFieldController.text;
                    LoginDTO loginDTO = LoginDTO(username, password, true);
                    int i = await userLogin(context, loginDTO);
                    if (i == 1) {
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      Navigator.of(context).pushReplacement(
                          MaterialPageRoute(builder: (context) => MainPage()));
                    } else if (i == 0) {
                      Navigator.of(context, rootNavigator: true).pop("dialog");
                      showAdjustDialog(context, LOGIN_FAILED, false, null, null);
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
  }
  
  void emailRecovery() {
    showDialog(
        context: context,
        child: Dialog(
          child: Container(
            padding: EdgeInsets.all(20),
            height: 400,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Directionality(
                  textDirection: TextDirection.rtl,
                  child: Text(
                    RECOVER_PASSWORD,
                    style: TextStyle(
                        fontFamily: "Iransans",
                        fontSize: 16,
                        color: Colors.blue),
                  ),
                ),
                SizedBox(height: 10,),
                AdjustTextField(
                    textDirection: TextDirection.ltr,
                    controller: emailRecoverPassTextFieldController,
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
                SizedBox(height: 10,),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    Container(
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          height: 40,
                          width: 80,
                          primaryColor: GREEN_COLOR,
                          secondaryColor: GREEN_COLOR,
                          text: CANCEL,
                          onPressed: () {
                            Navigator.of(context, rootNavigator: true).pop("dialog");
                          },
                        )
                    ),
                    Container(
                        child: AdjustRaisedButton(
                          textDirection: TextDirection.rtl,
                          height: 40,
                          width: 80,
                          primaryColor: GREEN_COLOR,
                          secondaryColor: GREEN_COLOR,
                          text: OK,
                          onPressed: () async{
                            preloader(context);
                            int i = await sendPasswordRecoveryEmail(emailRecoverPassTextFieldController.text);
                            if (i == 1) {
                              Navigator.of(context, rootNavigator: true).pop("dialog");
                              Navigator.of(context, rootNavigator: true).pop("dialog");
                              showAdjustDialog(context, SUCCESS, false, null, null);
                            } else if (i == 0) {
                              Navigator.of(context, rootNavigator: true).pop("dialog");
                              Navigator.of(context, rootNavigator: true).pop("dialog");
                              showAdjustDialog(context, FAILURE, false, null, null);
                            }
                            
                          },
                        )
                    )
                  ],
                )
              ],
            ),
          ),
        ));
  }

  @override
  void dispose() {
    // TODO: implement dispose
    emailTextFieldController.dispose();
    passwordTextFieldController.dispose();
    emailRecoverPassTextFieldController.dispose();
    super.dispose();
  }
}
