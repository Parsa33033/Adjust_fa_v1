import 'package:adjust_client/dto/exercise_dto.dart';
import 'package:adjust_client/model/workout.dart';
import 'package:json_annotation/json_annotation.dart';

part 'workout_dto.g.dart';

@JsonSerializable()
class WorkoutDTO extends Workout {
  List<ExerciseDTO> exercises;

  WorkoutDTO(int id, int dayNumber, int programId, this.exercises)
      : super(id, dayNumber, programId);

  factory WorkoutDTO.fromJson(Map<String, dynamic> json) =>
      _$WorkoutDTOFromJson(json);

  Map<String, dynamic> toJson() => _$WorkoutDTOToJson(this);
}
