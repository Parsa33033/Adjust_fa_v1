import 'package:adjust_client/dto/nutrition_dto.dart';
import 'package:adjust_client/model/meal.dart';
import 'package:json_annotation/json_annotation.dart';

part 'meal_dto.g.dart';

@JsonSerializable()
class MealDTO extends Meal {
  List<NutritionDTO> nutritions;
  MealDTO(int id, String name, int number, int nutritionProgramId, this.nutritions)
      : super(id, name, number, nutritionProgramId);

  factory MealDTO.fromJson(Map<String, dynamic> json) =>
      _$MealDTOFromJson(json);

  Map<String, dynamic> toJson() => _$MealDTOToJson(this);
}
