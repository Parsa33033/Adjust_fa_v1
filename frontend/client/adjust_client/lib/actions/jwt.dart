
import 'package:adjust_client/main.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

Future<String> getJwt(BuildContext context) async {
  String jwt;
  if (context != null) {
    try {
      jwt = StoreProvider.of<AppState>(context).state.authenticationState.jwt;
      if (jwt == null) {
        FlutterSecureStorage storage = FlutterSecureStorage();
        jwt = await storage.read(key: "jwt");
        return jwt;
      }
      return jwt;
    } catch(e) {
      jwt = await _getJwtByStore();
      return jwt;
    }
  } else {
    jwt = await _getJwtByStore();
    return jwt;
  }
}

Future<String> _getJwtByStore() async {
  String jwt;
  try {
    jwt = store.state.authenticationState.jwt;
    if (jwt == null) {
      FlutterSecureStorage storage = FlutterSecureStorage();
      jwt = await storage.read(key: "jwt");
      return jwt;
    }
    return jwt;
  } catch (e) {
    FlutterSecureStorage storage = FlutterSecureStorage();
    jwt = await storage.read(key: "jwt");
    return jwt;
  }
}