import 'package:adjust_client/model/exercise.dart';
import 'package:adjust_client/model/move.dart';


class ExerciseState extends Exercise {
  Move move;
  ExerciseState(int id, int number, int sets, int repsMin, int repsMax,
      int moveId, int workoutId, this.move)
      : super(id, number, sets, repsMin, repsMax, moveId, workoutId);
}
