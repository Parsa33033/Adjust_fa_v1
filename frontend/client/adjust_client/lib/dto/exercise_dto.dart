import 'package:adjust_client/dto/move_dto.dart';
import 'package:adjust_client/model/exercise.dart';
import 'package:json_annotation/json_annotation.dart';

part 'exercise_dto.g.dart';

@JsonSerializable()
class ExerciseDTO extends Exercise {
  MoveDTO move;

  ExerciseDTO(int id, int number, int sets, int repsMin, int repsMax,
      int moveId, int workoutId, this.move)
      : super(id, number, sets, repsMin, repsMax, moveId, workoutId);

  factory ExerciseDTO.fromJson(Map<String, dynamic> json) =>
      _$ExerciseDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ExerciseDTOToJson(this);
}
