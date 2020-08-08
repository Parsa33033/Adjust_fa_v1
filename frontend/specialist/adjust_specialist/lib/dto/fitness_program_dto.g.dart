// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'fitness_program_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

FitnessProgramDTO _$FitnessProgramDTOFromJson(Map<String, dynamic> json) {
  return FitnessProgramDTO(
    json['id'] as int,
    json['type'] as String,
    json['description'] as String,
    (json['workouts'] as List)
        ?.map((e) =>
            e == null ? null : WorkoutDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$FitnessProgramDTOToJson(FitnessProgramDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'type': instance.type,
      'description': instance.description,
      'workouts': instance.workouts,
    };
