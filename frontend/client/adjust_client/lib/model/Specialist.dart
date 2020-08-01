

import 'package:adjust_client/model/client.dart';

class Specialist {
  int id;

  String username;

  String firstName;

  String lastName;

  DateTime birth;

  Gender gender;

  String degree;

  String field;

  String resume;

  double stars;

  String image;

  String imageContentType;
  bool busy;

  Specialist(
      this.id,
      this.username,
      this.firstName,
      this.lastName,
      this.birth,
      this.gender,
      this.degree,
      this.field,
      this.resume,
      this.stars,
      this.image,
      this.imageContentType,
      this.busy);
}