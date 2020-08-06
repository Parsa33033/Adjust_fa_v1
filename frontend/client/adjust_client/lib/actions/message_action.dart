

import 'package:adjust_client/actions/jwt.dart';
import 'package:flutter/cupertino.dart';

Future<int> getMessages(BuildContext context, int clientId, int specialistId) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  
}