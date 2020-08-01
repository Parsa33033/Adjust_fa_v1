// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'exercise_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ExerciseDTO _$ExerciseDTOFromJson(Map<String, dynamic> json) {
  return ExerciseDTO(
    json['id'] as int,
    json['number'] as int,
    json['sets'] as int,
    json['repsMin'] as int,
    json['repsMax'] as int,
    json['moveId'] as int,
    json['workoutId'] as int,
    json['move'] == null
        ? null
        : MoveDTO.fromJson(json['move'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$ExerciseDTOToJson(ExerciseDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'number': instance.number,
      'sets': instance.sets,
      'repsMin': instance.repsMin,
      'repsMax': instance.repsMax,
      'moveId': instance.moveId,
      'workoutId': instance.workoutId,
      'move': instance.move,
    };
