// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'food_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

FoodDTO _$FoodDTOFromJson(Map<String, dynamic> json) {
  return FoodDTO(
    json['id'] as int,
    json['name'] as String,
    json['description'] as String,
    json['nutritionId'] as int,
  );
}

Map<String, dynamic> _$FoodDTOToJson(FoodDTO instance) => <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'description': instance.description,
      'nutritionId': instance.nutritionId,
    };
