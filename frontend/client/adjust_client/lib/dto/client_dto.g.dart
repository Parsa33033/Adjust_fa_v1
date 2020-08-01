// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'client_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ClientDTO _$ClientDTOFromJson(Map<String, dynamic> json) {
  return ClientDTO(
    json['id'] as int,
    json['username'] as String,
    json['firstName'] as String,
    json['lastName'] as String,
    json['birthDate'] == null
        ? null
        : DateTime.parse(json['birthDate'] as String),
    json['age'] as int,
    _$enumDecodeNullable(_$GenderEnumMap, json['gender']),
    (json['token'] as num)?.toDouble(),
    (json['score'] as num)?.toDouble(),
    json['image'] as String,
    json['imageContentType'] as String,
  );
}

Map<String, dynamic> _$ClientDTOToJson(ClientDTO instance) => <String, dynamic>{
      'id': instance.id,
      'username': instance.username,
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'birthDate': instance.birthDate?.toIso8601String(),
      'age': instance.age,
      'gender': _$GenderEnumMap[instance.gender],
      'token': instance.token,
      'score': instance.score,
      'image': instance.image,
      'imageContentType': instance.imageContentType,
    };

T _$enumDecode<T>(
  Map<T, dynamic> enumValues,
  dynamic source, {
  T unknownValue,
}) {
  if (source == null) {
    throw ArgumentError('A value must be provided. Supported values: '
        '${enumValues.values.join(', ')}');
  }

  final value = enumValues.entries
      .singleWhere((e) => e.value == source, orElse: () => null)
      ?.key;

  if (value == null && unknownValue == null) {
    throw ArgumentError('`$source` is not one of the supported values: '
        '${enumValues.values.join(', ')}');
  }
  return value ?? unknownValue;
}

T _$enumDecodeNullable<T>(
  Map<T, dynamic> enumValues,
  dynamic source, {
  T unknownValue,
}) {
  if (source == null) {
    return null;
  }
  return _$enumDecode<T>(enumValues, source, unknownValue: unknownValue);
}

const _$GenderEnumMap = {
  Gender.MALE: 'MALE',
  Gender.FEMALE: 'FEMALE',
};
