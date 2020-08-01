export interface IExercise {
  id?: number;
  number?: number;
  sets?: number;
  repsMin?: number;
  repsMax?: number;
  moveId?: number;
  workoutId?: number;
}

export const defaultValue: Readonly<IExercise> = {};
