import 'package:adjust_client/model/client.dart';
import 'package:json_annotation/json_annotation.dart';

part 'client_dto.g.dart';

@JsonSerializable()
class ClientDTO extends Client {

  ClientDTO(
      int id,
      String username,
      String firstName,
      String lastName,
      DateTime birthDate,
      int age,
      Gender gender,
      double token,
      double score,
      String image,
      String imageContentType)
      : super(id, username, firstName, lastName, birthDate, age, gender, token,
            score, image, imageContentType);

  factory ClientDTO.fromJson(Map<String, dynamic> json) =>
      _$ClientDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ClientDTOToJson(this);
}
