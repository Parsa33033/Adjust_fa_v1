import { IAdjustFood } from 'app/shared/model/adjust-food.model';

export interface IAdjustNutrition {
  id?: number;
  name?: string;
  description?: string;
  unit?: number;
  adjustNutritionId?: number;
  caloriesPerUnit?: number;
  proteinPerUnit?: number;
  carbsPerUnit?: number;
  fatInUnit?: number;
  foods?: IAdjustFood[];
}

export const defaultValue: Readonly<IAdjustNutrition> = {};
