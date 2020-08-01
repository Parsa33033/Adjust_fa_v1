// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'program_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ProgramDTO _$ProgramDTOFromJson(Map<String, dynamic> json) {
  return ProgramDTO(
    json['id'] as int,
    json['createdAt'] == null
        ? null
        : DateTime.parse(json['createdAt'] as String),
    json['expirationDate'] == null
        ? null
        : DateTime.parse(json['expirationDate'] as String),
    json['designed'] as bool,
    json['done'] as bool,
    json['paid'] as bool,
    json['bodyCompostionId'] as int,
    json['fitnessProgramId'] as int,
    json['nutritionProgramId'] as int,
    json['clientId'] as int,
    json['specialistId'] as int,
    json['client'] == null
        ? null
        : ClientDTO.fromJson(json['client'] as Map<String, dynamic>),
    json['specialist'] == null
        ? null
        : SpecialistDTO.fromJson(json['specialist'] as Map<String, dynamic>),
    json['bodyComposition'] == null
        ? null
        : BodyCompositionDTO.fromJson(
            json['bodyComposition'] as Map<String, dynamic>),
    json['nutritionProgram'] == null
        ? null
        : NutritionProgramDTO.fromJson(
            json['nutritionProgram'] as Map<String, dynamic>),
    json['fitnessProgram'] == null
        ? null
        : FitnessProgramDTO.fromJson(
            json['fitnessProgram'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$ProgramDTOToJson(ProgramDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'createdAt': instance.createdAt?.toIso8601String(),
      'expirationDate': instance.expirationDate?.toIso8601String(),
      'designed': instance.designed,
      'done': instance.done,
      'paid': instance.paid,
      'bodyCompostionId': instance.bodyCompostionId,
      'fitnessProgramId': instance.fitnessProgramId,
      'nutritionProgramId': instance.nutritionProgramId,
      'clientId': instance.clientId,
      'specialistId': instance.specialistId,
      'client': instance.client,
      'specialist': instance.specialist,
      'bodyComposition': instance.bodyComposition,
      'nutritionProgram': instance.nutritionProgram,
      'fitnessProgram': instance.fitnessProgram,
    };
