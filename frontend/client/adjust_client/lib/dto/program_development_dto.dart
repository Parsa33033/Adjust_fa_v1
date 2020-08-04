import 'package:adjust_client/model/program_development.dart';
import 'package:json_annotation/json_annotation.dart';

part 'program_development_dto.g.dart';

@JsonSerializable()
class ProgramDevelopmentDTO extends ProgramDevelopment {
  ProgramDevelopmentDTO(int id, DateTime date, double nutritionScore,
      double fitnessScore, int adjustProgramId)
      : super(id, date, nutritionScore, fitnessScore, adjustProgramId);

  factory ProgramDevelopmentDTO.fromJson(Map<String, dynamic> json) =>
      _$ProgramDevelopmentDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ProgramDevelopmentDTOToJson(this);
}
