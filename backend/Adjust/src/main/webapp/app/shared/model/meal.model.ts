import { INutrition } from 'app/shared/model/nutrition.model';

export interface IMeal {
  id?: number;
  name?: string;
  number?: number;
  nutritions?: INutrition[];
  nutritionProgramId?: number;
}

export const defaultValue: Readonly<IMeal> = {};
