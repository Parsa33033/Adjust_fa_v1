

import 'dart:io';

import 'package:jaguar_query_sqflite/jaguar_query_sqflite.dart';
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';

class Database {

  SqfliteAdapter adapter;

  Future<String> getDatabasePath() async {
    Directory dir = await getApplicationDocumentsDirectory();
    return join(dir.path, "adjust_db.db");
  }

  Future<SqfliteAdapter> get() async {
    adapter = SqfliteAdapter(await getDatabasePath());
    await adapter.connect();
    return adapter;
  }

  void disconnect() {
    adapter.close();
  }

}