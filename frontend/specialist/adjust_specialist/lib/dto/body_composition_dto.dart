import 'package:adjust_specialist/model/body_composition.dart';
import 'package:adjust_specialist/model/client.dart';
import 'package:json_annotation/json_annotation.dart';

part 'body_composition_dto.g.dart';

@JsonSerializable()
class BodyCompositionDTO extends BodyComposition {
  BodyCompositionDTO(
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

  factory BodyCompositionDTO.fromJson(Map<String, dynamic> json) =>
      _$BodyCompositionDTOFromJson(json);

  Map<String, dynamic> toJson() => _$BodyCompositionDTOToJson(this);
}
