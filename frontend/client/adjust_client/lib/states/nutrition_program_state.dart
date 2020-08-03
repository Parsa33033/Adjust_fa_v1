import 'package:adjust_client/model/meal.dart';
import 'package:adjust_client/model/nutrition_program.dart';
import 'package:adjust_client/states/meal_state.dart';

final NutritionProgramState nutritionProgramStateInit = NutritionProgramState(null, null, null, null, null, "", null);

class NutritionProgramState extends NutritionProgram {
  List<MealState> meals;

  NutritionProgramState(int id, double dailyCalories, int proteinPercentage,
      int carbsPercentage, int fatPercentage, String description, this.meals)
      : super(id, dailyCalories, proteinPercentage, carbsPercentage,
            fatPercentage, description);
}
