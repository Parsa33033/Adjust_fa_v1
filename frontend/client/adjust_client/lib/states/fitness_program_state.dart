import 'package:adjust_client/model/fitness_program.dart';
import 'package:adjust_client/model/workout.dart';
import 'package:adjust_client/states/workout_state.dart';

class FitnessProgramState extends FitnessProgram {
  List<WorkoutState> workouts;
  FitnessProgramState(int id, String type, String description, this.workouts)
      : super(id, type, description);
}
