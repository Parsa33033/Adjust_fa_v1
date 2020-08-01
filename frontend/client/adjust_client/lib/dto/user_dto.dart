import 'package:adjust_client/model/user.dart';
import 'package:json_annotation/json_annotation.dart';

part 'user_dto.g.dart';

@JsonSerializable()
class UserDTO extends User {
  UserDTO(String login, String firstName, String lastName, String email,
      String imageUrl, String langKey)
      : super(login, firstName, lastName, email, imageUrl, langKey);

  factory UserDTO.fromJson(Map<String, dynamic> json) =>
      _$UserDTOFromJson(json);

  Map<String, dynamic> toJson() => _$UserDTOToJson(this);
}

@JsonSerializable()
class ManagedUserDTO extends ManagedUser {
  ManagedUserDTO(String password, String login, String firstName,
      String lastName, String email, String imageUrl, String langKey)
      : super(password, login, firstName, lastName, email, imageUrl, langKey);


  factory ManagedUserDTO.fromJson(Map<String, dynamic> json) =>
      _$ManagedUserDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ManagedUserDTOToJson(this);
}
