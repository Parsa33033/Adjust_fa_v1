

class BodyComposition {
  int id;

  DateTime createdAt;

  double height;

  double weight;

  double bmi;

  String bodyCompositionFile;

  String bodyCompositionFileContentType;

  String bloodTestFile;

  String bloodTestFileContentType;

  BodyComposition(
      this.id,
      this.createdAt,
      this.height,
      this.weight,
      this.bmi,
      this.bodyCompositionFile,
      this.bodyCompositionFileContentType,
      this.bloodTestFile,
      this.bloodTestFileContentType);
}