import 'package:adjust_client/model/Specialist.dart';
import 'package:adjust_client/model/client.dart';
import 'package:json_annotation/json_annotation.dart';

part 'specialist_dto.g.dart';

@JsonSerializable()
class SpecialistListDTO {
  List<SpecialistDTO> specialists;

  SpecialistListDTO(this.specialists);
}

@JsonSerializable()
class SpecialistDTO extends Specialist {
  SpecialistDTO(
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

  factory SpecialistDTO.fromJson(Map<String, dynamic> json) =>
      _$SpecialistDTOFromJson(json);

  Map<String, dynamic> toJson() => _$SpecialistDTOToJson(this);
}
