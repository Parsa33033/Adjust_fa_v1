import 'package:adjust_specialist/model/food.dart';
import 'package:json_annotation/json_annotation.dart';

part 'food_dto.g.dart';

@JsonSerializable()
class FoodDTO extends Food {
  FoodDTO(int id, String name, String description, int nutritionId)
      : super(id, name, description, nutritionId);

  factory FoodDTO.fromJson(Map<String, dynamic> json) =>
      _$FoodDTOFromJson(json);

  Map<String, dynamic> toJson() => _$FoodDTOToJson(this);
}
