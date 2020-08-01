import 'package:adjust_client/dto/workout_dto.dart';
import 'package:adjust_client/model/fitness_program.dart';
import 'package:json_annotation/json_annotation.dart';

part 'fitness_program_dto.g.dart';

@JsonSerializable()
class FitnessProgramDTO extends FitnessProgram {
  List<WorkoutDTO> workouts;

  FitnessProgramDTO(int id, String type, String description, this.workouts)
      : super(id, type, description);

  factory FitnessProgramDTO.fromJson(Map<String, dynamic> json) =>
      _$FitnessProgramDTOFromJson(json);

  Map<String, dynamic> toJson() => _$FitnessProgramDTOToJson(this);
}
