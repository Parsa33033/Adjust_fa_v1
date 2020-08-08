// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'body_composition_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

BodyCompositionDTO _$BodyCompositionDTOFromJson(Map<String, dynamic> json) {
  return BodyCompositionDTO(
    json['id'] as int,
    json['createdAt'] == null
        ? null
        : DateTime.parse(json['createdAt'] as String),
    (json['height'] as num)?.toDouble(),
    (json['weight'] as num)?.toDouble(),
    (json['bmi'] as num)?.toDouble(),
    (json['wrist'] as num)?.toDouble(),
    (json['waist'] as num)?.toDouble(),
    (json['lbm'] as num)?.toDouble(),
    (json['muscleMass'] as num)?.toDouble(),
    json['muscleMassPercentage'] as int,
    (json['fatMass'] as num)?.toDouble(),
    json['fatMassPercentage'] as int,
    _$enumDecodeNullable(_$GenderEnumMap, json['gender']),
    json['age'] as int,
    json['bodyImage'] as String,
    json['bodyImageContentType'] as String,
    json['bodyCompositionFile'] as String,
    json['bodyCompositionFileContentType'] as String,
    json['bloodTestFile'] as String,
    json['bloodTestFileContentType'] as String,
    json['programId'] as int,
  );
}

Map<String, dynamic> _$BodyCompositionDTOToJson(BodyCompositionDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'createdAt': instance.createdAt?.toIso8601String(),
      'height': instance.height,
      'weight': instance.weight,
      'bmi': instance.bmi,
      'wrist': instance.wrist,
      'waist': instance.waist,
      'lbm': instance.lbm,
      'muscleMass': instance.muscleMass,
      'muscleMassPercentage': instance.muscleMassPercentage,
      'fatMass': instance.fatMass,
      'fatMassPercentage': instance.fatMassPercentage,
      'gender': _$GenderEnumMap[instance.gender],
      'age': instance.age,
      'bodyImage': instance.bodyImage,
      'bodyImageContentType': instance.bodyImageContentType,
      'bodyCompositionFile': instance.bodyCompositionFile,
      'bodyCompositionFileContentType': instance.bodyCompositionFileContentType,
      'bloodTestFile': instance.bloodTestFile,
      'bloodTestFileContentType': instance.bloodTestFileContentType,
      'programId': instance.programId,
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
