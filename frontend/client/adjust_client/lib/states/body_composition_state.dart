import 'package:adjust_client/model/body_composition.dart';
import 'package:adjust_client/model/client.dart';

class BodyCompositionState extends BodyComposition {
  BodyCompositionState(
      int id,
      DateTime createdAt,
      double height,
      double weight,
      double bmi,
      double wrist,
      double waist,
      double lbm,
      double muscleMass,
      int muscleMassPercentage,
      double fatMass,
      int fatMassPercentage,
      Gender gender,
      int age,
      String bodyImage,
      String bodyImageContentType,
      String bodyCompositionFile,
      String bodyCompositionFileContentType,
      String bloodTestFile,
      String bloodTestFileContentType,
      int programId)
      : super(
            id,
            createdAt,
            height,
            weight,
            bmi,
            wrist,
            waist,
            lbm,
            muscleMass,
            muscleMassPercentage,
            fatMass,
            fatMassPercentage,
            gender,
            age,
            bodyImage,
            bodyImageContentType,
            bodyCompositionFile,
            bodyCompositionFileContentType,
            bloodTestFile,
            bloodTestFileContentType,
            programId);
}
