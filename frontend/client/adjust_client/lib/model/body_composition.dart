

import 'package:adjust_client/model/client.dart';

class BodyComposition {
  int id;

  DateTime createdAt;

  double height;

  double weight;

  double bmi;

  double wrist;

  double waist;

  double lbm;

  double muscleMass;

  int muscleMassPercentage;

  double fatMass;

  int fatMassPercentage;

  Gender gender;

  int age;

  String bodyImage;

  String bodyImageContentType;

  String bodyCompositionFile;

  String bodyCompositionFileContentType;

  String bloodTestFile;

  String bloodTestFileContentType;

  int programId;

  BodyComposition(
      this.id,
      this.createdAt,
      this.height,
      this.weight,
      this.bmi,
      this.wrist,
      this.waist,
      this.lbm,
      this.muscleMass,
      this.muscleMassPercentage,
      this.fatMass,
      this.fatMassPercentage,
      this.gender,
      this.age,
      this.bodyImage,
      this.bodyImageContentType,
      this.bodyCompositionFile,
      this.bodyCompositionFileContentType,
      this.bloodTestFile,
      this.bloodTestFileContentType,
      this.programId);
}