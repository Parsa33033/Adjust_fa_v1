import 'package:adjust_client/model/nutrition.dart';
import 'package:adjust_client/states/food_state.dart';

class NutritionState extends Nutrition {
  List<FoodState> foods;
  NutritionState(
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
}
