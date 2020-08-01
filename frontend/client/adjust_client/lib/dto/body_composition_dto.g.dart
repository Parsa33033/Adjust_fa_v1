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
    json['bodyCompositionFile'] as String,
    json['bodyCompositionFileContentType'] as String,
    json['bloodTestFile'] as String,
    json['bloodTestFileContentType'] as String,
  );
}

Map<String, dynamic> _$BodyCompositionDTOToJson(BodyCompositionDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'createdAt': instance.createdAt?.toIso8601String(),
      'height': instance.height,
      'weight': instance.weight,
      'bmi': instance.bmi,
      'bodyCompositionFile': instance.bodyCompositionFile,
      'bodyCompositionFileContentType': instance.bodyCompositionFileContentType,
      'bloodTestFile': instance.bloodTestFile,
      'bloodTestFileContentType': instance.bloodTestFileContentType,
    };
