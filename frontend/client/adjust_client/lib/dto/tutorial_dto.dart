import 'package:adjust_client/model/tutorial.dart';
import 'package:json_annotation/json_annotation.dart';

part 'tutorial_dto.g.dart';

@JsonSerializable()
class TutorialListDTO {
  List<TutorialDTO> items;

  TutorialListDTO(this.items);

}

@JsonSerializable()
class TutorialDTO extends Tutorial {
  TutorialDTO(
      int id,
      String title,
      String description,
      String text,
      String thumbnail,
      String thumbnailContentType,
      double tokenPrice,
      int videoId,
      int clientId)
      : super(id, title, description, text, thumbnail,
            thumbnailContentType, tokenPrice, videoId, clientId);

  factory TutorialDTO.fromJson(Map<String, dynamic> json) =>
      _$TutorialDTOFromJson(json);

  Map<String, dynamic> toJson() => _$TutorialDTOToJson(this);
}
