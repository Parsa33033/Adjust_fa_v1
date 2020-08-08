import 'package:adjust_specialist/model/fitness_program.dart';
import 'package:adjust_specialist/model/workout.dart';
import 'package:adjust_specialist/states/workout_state.dart';

final FitnessProgramState fitnessProgramStateInit = FitnessProgramState(null, "", "", null);

class FitnessProgramState extends FitnessProgram {
  List<WorkoutState> workouts;
  FitnessProgramState(int id, String type, String description, this.workouts)
      : super(id, type, description);
}
