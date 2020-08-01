// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'nutrition_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

NutritionDTO _$NutritionDTOFromJson(Map<String, dynamic> json) {
  return NutritionDTO(
    json['id'] as int,
    json['name'] as String,
    json['description'] as String,
    json['unit'] as int,
    json['adjustNutritionId'] as int,
    json['caloriesPerUnit'] as int,
    json['proteinPerUnit'] as int,
    json['carbsPerUnit'] as int,
    json['fatInUnit'] as int,
    json['mealId'] as int,
    (json['foods'] as List)
        ?.map((e) =>
            e == null ? null : FoodDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$NutritionDTOToJson(NutritionDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'description': instance.description,
      'unit': instance.unit,
      'adjustNutritionId': instance.adjustNutritionId,
      'caloriesPerUnit': instance.caloriesPerUnit,
      'proteinPerUnit': instance.proteinPerUnit,
      'carbsPerUnit': instance.carbsPerUnit,
      'fatInUnit': instance.fatInUnit,
      'mealId': instance.mealId,
      'foods': instance.foods,
    };
