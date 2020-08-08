// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'move_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MoveDTO _$MoveDTOFromJson(Map<String, dynamic> json) {
  return MoveDTO(
    json['id'] as int,
    json['name'] as String,
    json['muscleName'] as String,
    _$enumDecodeNullable(_$MuscleTypeEnumMap, json['muscleType']),
    json['equipment'] as String,
    json['picture'] as String,
    json['pictureContentType'] as String,
    json['adjustMoveId'] as int,
  );
}

Map<String, dynamic> _$MoveDTOToJson(MoveDTO instance) => <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'muscleName': instance.muscleName,
      'muscleType': _$MuscleTypeEnumMap[instance.muscleType],
      'equipment': instance.equipment,
      'picture': instance.picture,
      'pictureContentType': instance.pictureContentType,
      'adjustMoveId': instance.adjustMoveId,
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

const _$MuscleTypeEnumMap = {
  MuscleType.CHEST: 'CHEST',
  MuscleType.SHOULDER: 'SHOULDER',
  MuscleType.TRAPEZOID: 'TRAPEZOID',
  MuscleType.BACK: 'BACK',
  MuscleType.LATERAL: 'LATERAL',
  MuscleType.BICEP: 'BICEP',
  MuscleType.TRICEP: 'TRICEP',
  MuscleType.FOREARM: 'FOREARM',
  MuscleType.LEG: 'LEG',
  MuscleType.HAMSTRING: 'HAMSTRING',
  MuscleType.GLUTES: 'GLUTES',
  MuscleType.CALVES: 'CALVES',
  MuscleType.ABS: 'ABS',
  MuscleType.MISC: 'MISC',
};
