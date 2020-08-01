// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'meal_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MealDTO _$MealDTOFromJson(Map<String, dynamic> json) {
  return MealDTO(
    json['id'] as int,
    json['name'] as String,
    json['number'] as int,
    json['nutritionProgramId'] as int,
    (json['nutritions'] as List)
        ?.map((e) =>
            e == null ? null : NutritionDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$MealDTOToJson(MealDTO instance) => <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'number': instance.number,
      'nutritionProgramId': instance.nutritionProgramId,
      'nutritions': instance.nutritions,
    };
