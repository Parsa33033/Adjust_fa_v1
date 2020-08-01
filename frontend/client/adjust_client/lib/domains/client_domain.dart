//
//
//import 'dart:convert';
//
//import 'package:adjust_client/config/database.dart';
//import 'package:adjust_client/states/app_state.dart';
//import 'package:adjust_client/model/client.dart';
//import 'package:adjust_client/states/client_state.dart';
//import 'package:enum_to_string/enum_to_string.dart';
//import 'package:flutter/cupertino.dart';
//import 'package:flutter_redux/flutter_redux.dart';
//import 'package:jaguar_orm/jaguar_orm.dart';
//import 'package:jaguar_query_sqflite/jaguar_query_sqflite.dart';
//
//part 'client_domain.jorm.dart';
//
//class ClientDomain {
//  @PrimaryKey()
//  int id;
//  String username;
//  String firstName;
//  String lastName;
//  DateTime birthDate;
//  int age;
//  String gender;
//  double token;
//  double score;
//  String image;
//  String imageContentType;
//
//  ClientDomain({
//      this.id,
//      this.username,
//      this.firstName,
//      this.lastName,
//      this.birthDate,
//      this.age,
//      this.gender,
//      this.token,
//      this.score,
//      this.image,
//      this.imageContentType});
//
//  static const String tableName = '_client';
//
//  String toString() => "User($id, $username, $firstName, $lastName, $birthDate, $age, $gender, $token, $score, $image, $imageContentType)";
//}
//
//@GenBean()
//class ClientDomainBean extends Bean<ClientDomain> with _ClientDomainBean {
//  ClientDomainBean(Adapter adapter) : super(adapter);
//
//  @override
//  // TODO: implement tableName
//  String get tableName => "client";
//}
//
//
//
//void setClientDomain(BuildContext context, Client client) async{
//  Database db = Database();
//  SqfliteAdapter adapter = await db.get();
//  ClientDomainBean clientDomainBean = ClientDomainBean(adapter);
//  clientDomainBean.createTable(ifNotExists: true);
//
//  ClientState lastClientState = StoreProvider.of<AppState>(context).state.clientState;
//
//  ClientDomain clientDomain = ClientDomain(
//    lastName: client.lastName != null ? client.lastName : lastClientState.lastName,
//    firstName: client.firstName != null ? client.firstName : lastClientState.firstName,
//    age: client.age != null ? client.age : lastClientState.age,
//    birthDate: client.birthDate != null ? client.birthDate : lastClientState.birthDate,
//    gender: client.gender != null ? EnumToString.parse(client.gender) : EnumToString.parse(lastClientState.gender),
//    id: 1,
//    image: client.image != null ? jsonEncode(client.image) : jsonEncode(lastClientState.image),
//    imageContentType: client.imageContentType != null ? client.imageContentType : lastClientState.imageContentType,
//    score: client.score != null ? client.score : lastClientState.score,
//    token: client.token != null ? client.token : lastClientState.token,
//    username: client.username != null ? client.username : lastClientState.username,);
//
//  //change to update
//  ClientDomain cd = await clientDomainBean.find(1);
//  if (cd == null) {
//    clientDomainBean.insert(clientDomain);
//  } else {
//    clientDomainBean.remove(1);
//    clientDomainBean.insert(clientDomain);
//  }
//}