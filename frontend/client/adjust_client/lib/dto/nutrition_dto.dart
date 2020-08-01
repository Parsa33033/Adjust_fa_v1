import 'package:adjust_client/dto/food_dto.dart';
import 'package:adjust_client/model/nutrition.dart';
import 'package:json_annotation/json_annotation.dart';

part 'nutrition_dto.g.dart';

@JsonSerializable()
class NutritionDTO extends Nutrition {
  List<FoodDTO> foods;

  NutritionDTO(
      int id,
      String name,
      String description,
      int unit,
      int adjustNutritionId,
      int caloriesPerUnit,
      int proteinPerUnit,
      int carbsPerUnit,
      int fatInUnit,
      int mealId,
      this.foods)
      : super(id, name, description, unit, adjustNutritionId, caloriesPerUnit,
            proteinPerUnit, carbsPerUnit, fatInUnit, mealId);

  factory NutritionDTO.fromJson(Map<String, dynamic> json) =>
      _$NutritionDTOFromJson(json);

  Map<String, dynamic> toJson() => _$NutritionDTOToJson(this);
}
