import 'package:adjust_client/model/body_composition.dart';
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

  factory BodyCompositionDTO.fromJson(Map<String, dynamic> json) =>
      _$BodyCompositionDTOFromJson(json);

  Map<String, dynamic> toJson() => _$BodyCompositionDTOToJson(this);
}
