import 'package:adjust_client/model/Specialist.dart';
import 'package:adjust_client/model/body_composition.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/fitness_program.dart';
import 'package:adjust_client/model/nutrition_program.dart';
import 'package:adjust_client/model/program.dart';
import 'package:adjust_client/states/body_composition_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/fitness_program_state.dart';
import 'package:adjust_client/states/program_development_state.dart';
import 'package:adjust_client/states/specialist_state.dart';

import 'nutrition_program_state.dart';

final ProgramListState programListStateInit = ProgramListState(List());

class ProgramListState {
  List<ProgramState> programs;

  ProgramListState(this.programs);
}

class ProgramState extends Program {
  ClientState clientState;
  SpecialistState specialistState;
  List<ProgramDevelopmentState> programDevelopmentStateList;
  List<BodyCompositionState> bodyCompositionStateList;
  NutritionProgramState nutritionProgramState;
  FitnessProgramState fitnessProgramState;

  ProgramState(
      int id,
      DateTime createdAt,
      DateTime expirationDate,
      bool designed,
      bool nutritionDone,
      bool fitnessDone,
      bool paid,
      int fitnessProgramId,
      int nutritionProgramId,
      int clientId,
      int specialistId,
      this.clientState,
      this.specialistState,
      this.programDevelopmentStateList,
      this.bodyCompositionStateList,
      this.nutritionProgramState,
      this.fitnessProgramState)
      : super(
            id,
            createdAt,
            expirationDate,
            designed,
            nutritionDone,
            fitnessDone,
            paid,
            fitnessProgramId,
            nutritionProgramId,
            clientId,
            specialistId);
}
