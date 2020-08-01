import 'package:adjust_client/model/meal.dart';
import 'package:adjust_client/states/nutrition_state.dart';

class MealState extends Meal {
  List<NutritionState> nutritions;

  MealState(int id, String name, int number, int nutritionProgramId, this.nutritions)
      : super(id, name, number, nutritionProgramId);
}
