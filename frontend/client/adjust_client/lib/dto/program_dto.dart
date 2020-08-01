import 'package:adjust_client/dto/body_composition_dto.dart';
import 'package:adjust_client/dto/client_dto.dart';
import 'package:adjust_client/dto/fitness_program_dto.dart';
import 'package:adjust_client/dto/nutrition_program_dto.dart';
import 'package:adjust_client/dto/specialist_dto.dart';
import 'package:adjust_client/model/program.dart';
import 'package:json_annotation/json_annotation.dart';

part 'program_dto.g.dart';

@JsonSerializable()
class ProgramDTO extends Program {
  ClientDTO client;
  SpecialistDTO specialist;

  BodyCompositionDTO bodyComposition;
  NutritionProgramDTO nutritionProgram;
  FitnessProgramDTO fitnessProgram;

  ProgramDTO(
      int id,
      DateTime createdAt,
      DateTime expirationDate,
      bool designed,
      bool done,
      bool paid,
      int bodyCompostionId,
      int fitnessProgramId,
      int nutritionProgramId,
      int clientId,
      int specialistId,
      this.client,
      this.specialist,
      this.bodyComposition,
      this.nutritionProgram,
      this.fitnessProgram)
      : super(
            id,
            createdAt,
            expirationDate,
            designed,
            done,
            paid,
            bodyCompostionId,
            fitnessProgramId,
            nutritionProgramId,
            clientId,
            specialistId);

  factory ProgramDTO.fromJson(Map<String, dynamic> json) =>
      _$ProgramDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ProgramDTOToJson(this);
}
