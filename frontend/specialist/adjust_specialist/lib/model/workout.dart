
class Workout {
  int id;

  int dayNumber;

  int programId;

  Workout(this.id, this.dayNumber, this.programId);
}

enum WorkoutType {
  POWER,
  BODYBUILDING_MASS,
  BODYBUILDING,
  BODYBUILDING_CUT,
  ENDURANCE,
  CARDIO
}

class ExerciseType {
  WorkoutType workoutType;
  int sets;
  int minReps;
  int maxReps;

  ExerciseType(this.workoutType, this.sets, this.minReps, this.maxReps);
}

final Map<WorkoutType, ExerciseType> exerciseForWorkout = Map<WorkoutType, ExerciseType>()
  ..putIfAbsent(WorkoutType.POWER, () => ExerciseType(WorkoutType.POWER, 3, 2, 4))
  ..putIfAbsent(WorkoutType.BODYBUILDING_MASS, () => ExerciseType(WorkoutType.BODYBUILDING_MASS, 3, 4, 6))
  ..putIfAbsent(WorkoutType.BODYBUILDING, () => ExerciseType(WorkoutType.BODYBUILDING, 3, 6, 8))
  ..putIfAbsent(WorkoutType.BODYBUILDING_CUT, () => ExerciseType(WorkoutType.BODYBUILDING_CUT, 3, 8, 12))
  ..putIfAbsent(WorkoutType.ENDURANCE, () => ExerciseType(WorkoutType.ENDURANCE, 3, 12, 15))
  ..putIfAbsent(WorkoutType.CARDIO, () => ExerciseType(WorkoutType.CARDIO, 3, 15, 30));