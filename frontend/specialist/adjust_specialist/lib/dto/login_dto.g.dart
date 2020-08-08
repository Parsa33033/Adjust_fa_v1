// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'login_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

LoginDTO _$LoginDTOFromJson(Map<String, dynamic> json) {
  return LoginDTO(
    json['username'] as String,
    json['password'] as String,
    json['rememberMe'] as bool,
  );
}

Map<String, dynamic> _$LoginDTOToJson(LoginDTO instance) => <String, dynamic>{
      'username': instance.username,
      'password': instance.password,
      'rememberMe': instance.rememberMe,
    };
