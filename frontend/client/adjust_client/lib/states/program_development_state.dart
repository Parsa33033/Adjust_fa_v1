import 'package:adjust_client/model/program_development.dart';

class ProgramDevelopmentState extends ProgramDevelopment {
  ProgramDevelopmentState(int id, DateTime date, double workoutScore,
      double fitnessScore, int adjustProgramId)
      : super(id, date, workoutScore, fitnessScore, adjustProgramId);
}
