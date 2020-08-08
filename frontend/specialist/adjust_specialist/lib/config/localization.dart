import 'package:intl/intl.dart';
import 'package:shamsi_date/shamsi_date.dart';


DateTime jalaliToGeorgianDateTime(String dateTimeString) {
  int year = int.parse(dateTimeString.split("/")[0]);
  int month = int.parse(dateTimeString.split("/")[1]);
  int day = int.parse(dateTimeString.split("/")[2]);
  Jalali jalali = Jalali(year, month, day);
  return jalali.toDateTime();
}

Jalali georgianToJalali(DateTime dateTime) {
  Jalali jalali = Jalali.fromDateTime(dateTime);
  return jalali;
}

String jalaliToString (Jalali jalali) {
  int year = jalali.year;
  int month = jalali.month;
  int day = jalali.day;
  String dateString = "${year}/${month}/${day}";
  return dateString;
}

