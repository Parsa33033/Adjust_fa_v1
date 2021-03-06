import 'package:adjust_specialist/model/exercise.dart';
import 'package:adjust_specialist/model/workout.dart';

import 'exercise_state.dart';

class WorkoutState extends Workout {
  List<ExerciseState> exercises;

  WorkoutState(int id, int dayNumber, int programId, this.exercises)
      : super(id, dayNumber, programId);
}
