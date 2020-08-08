import 'package:adjust_specialist/model/Specialist.dart';
import 'package:adjust_specialist/model/client.dart';

final SpecialistState specialistStateInit = SpecialistState(null, "", "", "", DateTime(2000), null, "", "", "", 0, null, "", false);

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
