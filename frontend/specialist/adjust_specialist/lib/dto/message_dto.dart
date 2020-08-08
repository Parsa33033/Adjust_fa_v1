import 'package:adjust_specialist/model/message.dart';
import 'package:json_annotation/json_annotation.dart';

part 'message_dto.g.dart';

@JsonSerializable()
class MessageDTO extends Message {
  MessageDTO(String sender, String receiver, String message, int clientId,
      int specialistId)
      : super(sender, receiver, message, clientId, specialistId);

  factory MessageDTO.fromJson(Map<String, dynamic> json) =>
      _$MessageDTOFromJson(json);

  Map<String, dynamic> toJson() => _$MessageDTOToJson(this);
}
