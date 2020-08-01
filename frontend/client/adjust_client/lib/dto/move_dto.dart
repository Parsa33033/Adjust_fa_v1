import 'package:adjust_client/model/move.dart';
import 'package:json_annotation/json_annotation.dart';

part 'move_dto.g.dart';

@JsonSerializable()
class MoveDTO extends Move {
  MoveDTO(
      int id,
      String name,
      String muscleName,
      MuscleType muscleType,
      String equipment,
      String picture,
      String pictureContentType,
      int adjustMoveId)
      : super(id, name, muscleName, muscleType, equipment, picture,
            pictureContentType, adjustMoveId);

  factory MoveDTO.fromJson(Map<String, dynamic> json) =>
      _$MoveDTOFromJson(json);

  Map<String, dynamic> toJson() => _$MoveDTOToJson(this);
}
