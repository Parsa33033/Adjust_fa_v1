// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'workout_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

WorkoutDTO _$WorkoutDTOFromJson(Map<String, dynamic> json) {
  return WorkoutDTO(
    json['id'] as int,
    json['dayNumber'] as int,
    json['programId'] as int,
    (json['exercises'] as List)
        ?.map((e) =>
            e == null ? null : ExerciseDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$WorkoutDTOToJson(WorkoutDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'dayNumber': instance.dayNumber,
      'programId': instance.programId,
      'exercises': instance.exercises,
    };
