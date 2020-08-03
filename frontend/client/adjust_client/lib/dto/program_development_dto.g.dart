// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'program_development_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ProgramDevelopmentDTO _$ProgramDevelopmentDTOFromJson(
    Map<String, dynamic> json) {
  return ProgramDevelopmentDTO(
    json['id'] as int,
    json['date'] == null ? null : DateTime.parse(json['date'] as String),
    (json['workoutScore'] as num)?.toDouble(),
    (json['fitnessScore'] as num)?.toDouble(),
    json['adjustProgramId'] as int,
  );
}

Map<String, dynamic> _$ProgramDevelopmentDTOToJson(
        ProgramDevelopmentDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'date': instance.date?.toIso8601String(),
      'workoutScore': instance.workoutScore,
      'fitnessScore': instance.fitnessScore,
      'adjustProgramId': instance.adjustProgramId,
    };
