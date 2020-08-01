import 'package:adjust_client/model/Specialist.dart';
import 'package:adjust_client/model/client.dart';

final SpecialistListState specialistListStateInit = SpecialistListState(List());

class SpecialistListState {
  List<SpecialistState> specialists;

  SpecialistListState(this.specialists);
}

class SpecialistState extends Specialist {
  SpecialistState(
      int id,
      String username,
      String firstName,
      String lastName,
      DateTime birth,
      Gender gender,
      String degree,
      String field,
      String resume,
      double stars,
      String image,
      String imageContentType,
      bool busy)
      : super(id, username, firstName, lastName, birth, gender, degree, field,
            resume, stars, image, imageContentType, busy);
}
