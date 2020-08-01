//import 'package:adjust_client/config/database.dart';
//import 'package:adjust_client/model/user.dart';
//import 'package:adjust_client/states/app_state.dart';
//import 'package:adjust_client/states/user_state.dart';
//import 'package:flutter/cupertino.dart';
//import 'package:flutter_redux/flutter_redux.dart';
//import 'package:jaguar_orm/jaguar_orm.dart';
//import 'package:jaguar_query_sqflite/jaguar_query_sqflite.dart';
//
//part 'user_domain.jorm.dart';
//
//class UserDomain {
//  @PrimaryKey()
//  String id;
//
//  String login;
//  String firstName;
//  String lastName;
//  String email;
//  String imageUrl;
//  String langKey;
//
//  UserDomain({this.id, this.login, this.firstName, this.lastName, this.email,
//      this.imageUrl, this.langKey});
//
//  static const String tableName = '_user';
//
//  String toString() => "User($id, $login, $firstName, $lastName, $email, $imageUrl, $langKey)";
//
//
//}
//
//
//@GenBean()
//class UserDomainBean extends Bean<UserDomain> with _UserDomainBean {
//  UserDomainBean(Adapter adapter) : super(adapter);
//
//  @override
//  // TODO: implement tableName
//  String get tableName => "user";
//}
//
//void setUserDomain(BuildContext context, User user) async {
//  Database db = Database();
//  SqfliteAdapter adapter = await db.get();
//  UserDomainBean userDomainBean = UserDomainBean(adapter);
//
//  UserState userState = StoreProvider.of<AppState>(context).state.userState;
//
//  UserDomain userDomain = UserDomain(
//    lastName: user.lastName != null ? user.lastName : userState.lastName,
//    firstName: user.firstName != null ? user.firstName : userState.firstName,
//    id: "1",
//    langKey: user.langKey != null ? user.langKey : userState.langKey,
//    imageUrl: user.imageUrl != null ? user.imageUrl : userState.imageUrl,
//    email: user.email != null ? user.email : userState.email,
//    login: user.login != null ? user.login : userState.login,
//  );
//
//  final userFound = await userDomainBean.find("1");
//  if (userFound == null) {
//    await userDomainBean.insert(userDomain);
//  } else {
//    await userDomainBean.remove("1");
//    await userDomainBean.insert(userDomain);
//  }
//}