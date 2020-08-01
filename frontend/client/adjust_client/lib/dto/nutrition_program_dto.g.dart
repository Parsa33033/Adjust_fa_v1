// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'nutrition_program_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

NutritionProgramDTO _$NutritionProgramDTOFromJson(Map<String, dynamic> json) {
  return NutritionProgramDTO(
    json['id'] as int,
    (json['dailyCalories'] as num)?.toDouble(),
    json['proteinPercentage'] as int,
    json['carbsPercentage'] as int,
    json['fatPercentage'] as int,
    json['description'] as String,
    (json['meals'] as List)
        ?.map((e) =>
            e == null ? null : MealDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$NutritionProgramDTOToJson(
        NutritionProgramDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'dailyCalories': instance.dailyCalories,
      'proteinPercentage': instance.proteinPercentage,
      'carbsPercentage': instance.carbsPercentage,
      'fatPercentage': instance.fatPercentage,
      'description': instance.description,
      'meals': instance.meals,
    };
