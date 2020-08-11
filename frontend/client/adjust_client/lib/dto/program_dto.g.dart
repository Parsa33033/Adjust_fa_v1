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
    json['nutritionDone'] as bool,
    json['fitnessDone'] as bool,
    json['paid'] as bool,
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
    (json['bodyCompositions'] as List)
        ?.map((e) => e == null
            ? null
            : BodyCompositionDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
    json['nutritionProgram'] == null
        ? null
        : NutritionProgramDTO.fromJson(
            json['nutritionProgram'] as Map<String, dynamic>),
    json['fitnessProgram'] == null
        ? null
        : FitnessProgramDTO.fromJson(
            json['fitnessProgram'] as Map<String, dynamic>),
  )..programDevelopments = (json['programDevelopments'] as List)
      ?.map((e) => e == null
          ? null
          : ProgramDevelopmentDTO.fromJson(e as Map<String, dynamic>))
      ?.toList();
}

Map<String, dynamic> _$ProgramDTOToJson(ProgramDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'createdAt': instance.createdAt?.toIso8601String(),
      'expirationDate': instance.expirationDate?.toIso8601String(),
      'designed': instance.designed,
      'nutritionDone': instance.nutritionDone,
      'fitnessDone': instance.fitnessDone,
      'paid': instance.paid,
      'fitnessProgramId': instance.fitnessProgramId,
      'nutritionProgramId': instance.nutritionProgramId,
      'clientId': instance.clientId,
      'specialistId': instance.specialistId,
      'client': instance.client,
      'specialist': instance.specialist,
      'programDevelopments': instance.programDevelopments,
      'bodyCompositions': instance.bodyCompositions,
      'nutritionProgram': instance.nutritionProgram,
      'fitnessProgram': instance.fitnessProgram,
    };
