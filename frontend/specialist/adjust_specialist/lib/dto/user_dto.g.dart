// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

UserDTO _$UserDTOFromJson(Map<String, dynamic> json) {
  return UserDTO(
    json['login'] as String,
    json['firstName'] as String,
    json['lastName'] as String,
    json['email'] as String,
    json['imageUrl'] as String,
    json['langKey'] as String,
  );
}

Map<String, dynamic> _$UserDTOToJson(UserDTO instance) => <String, dynamic>{
      'login': instance.login,
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'email': instance.email,
      'imageUrl': instance.imageUrl,
      'langKey': instance.langKey,
    };

ManagedUserDTO _$ManagedUserDTOFromJson(Map<String, dynamic> json) {
  return ManagedUserDTO(
    json['password'] as String,
    json['login'] as String,
    json['firstName'] as String,
    json['lastName'] as String,
    json['email'] as String,
    json['imageUrl'] as String,
    json['langKey'] as String,
  );
}

Map<String, dynamic> _$ManagedUserDTOToJson(ManagedUserDTO instance) =>
    <String, dynamic>{
      'login': instance.login,
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'email': instance.email,
      'imageUrl': instance.imageUrl,
      'langKey': instance.langKey,
      'password': instance.password,
    };
