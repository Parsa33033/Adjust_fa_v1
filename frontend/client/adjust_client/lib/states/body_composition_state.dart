import 'package:adjust_client/model/body_composition.dart';

class BodyCompositionState extends BodyComposition {
  BodyCompositionState(
      int id,
      DateTime createdAt,
      double height,
      double weight,
      double bmi,
      String bodyCompositionFile,
      String bodyCompositionFileContentType,
      String bloodTestFile,
      String bloodTestFileContentType)
      : super(
            id,
            createdAt,
            height,
            weight,
            bmi,
            bodyCompositionFile,
            bodyCompositionFileContentType,
            bloodTestFile,
            bloodTestFileContentType);
}
