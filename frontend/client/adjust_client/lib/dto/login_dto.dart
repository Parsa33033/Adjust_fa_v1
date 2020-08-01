import 'package:adjust_client/model/login.dart';
import 'package:json_annotation/json_annotation.dart';

part 'login_dto.g.dart';

@JsonSerializable()
class LoginDTO extends Login {

  LoginDTO(String username, String password, bool rememberMe)
      : super(username, password, rememberMe);

  factory LoginDTO.fromJson(Map<String, dynamic> json) =>
      _$LoginDTOFromJson(json);

  Map<String, dynamic> toJson() => _$LoginDTOToJson(this);
}

