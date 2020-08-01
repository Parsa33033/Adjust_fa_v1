// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'specialist_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

SpecialistListDTO _$SpecialistListDTOFromJson(Map<String, dynamic> json) {
  return SpecialistListDTO(
    (json['specialists'] as List)
        ?.map((e) => e == null
            ? null
            : SpecialistDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$SpecialistListDTOToJson(SpecialistListDTO instance) =>
    <String, dynamic>{
      'specialists': instance.specialists,
    };

SpecialistDTO _$SpecialistDTOFromJson(Map<String, dynamic> json) {
  return SpecialistDTO(
    json['id'] as int,
    json['username'] as String,
    json['firstName'] as String,
    json['lastName'] as String,
    json['birth'] == null ? null : DateTime.parse(json['birth'] as String),
    _$enumDecodeNullable(_$GenderEnumMap, json['gender']),
    json['degree'] as String,
    json['field'] as String,
    json['resume'] as String,
    (json['stars'] as num)?.toDouble(),
    json['image'] as String,
    json['imageContentType'] as String,
    json['busy'] as bool,
  );
}

Map<String, dynamic> _$SpecialistDTOToJson(SpecialistDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'username': instance.username,
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'birth': instance.birth?.toIso8601String(),
      'gender': _$GenderEnumMap[instance.gender],
      'degree': instance.degree,
      'field': instance.field,
      'resume': instance.resume,
      'stars': instance.stars,
      'image': instance.image,
      'imageContentType': instance.imageContentType,
      'busy': instance.busy,
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
