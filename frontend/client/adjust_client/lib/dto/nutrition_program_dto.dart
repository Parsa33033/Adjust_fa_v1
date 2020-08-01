import 'package:adjust_client/dto/meal_dto.dart';
import 'package:adjust_client/model/nutrition_program.dart';
import 'package:json_annotation/json_annotation.dart';

part 'nutrition_program_dto.g.dart';

@JsonSerializable()
class NutritionProgramDTO extends NutritionProgram {
  List<MealDTO> meals;

  NutritionProgramDTO(int id, double dailyCalories, int proteinPercentage,
      int carbsPercentage, int fatPercentage, String description, this.meals)
      : super(id, dailyCalories, proteinPercentage, carbsPercentage,
            fatPercentage, description);

  factory NutritionProgramDTO.fromJson(Map<String, dynamic> json) =>
      _$NutritionProgramDTOFromJson(json);

  Map<String, dynamic> toJson() => _$NutritionProgramDTOToJson(this);
}
