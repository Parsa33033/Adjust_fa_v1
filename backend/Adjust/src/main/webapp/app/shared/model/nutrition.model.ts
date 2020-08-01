export interface INutrition {
  id?: number;
  name?: string;
  description?: string;
  unit?: number;
  adjustNutritionId?: number;
  caloriesPerUnit?: number;
  proteinPerUnit?: number;
  carbsPerUnit?: number;
  fatInUnit?: number;
  mealId?: number;
}

export const defaultValue: Readonly<INutrition> = {};
