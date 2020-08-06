import 'package:adjust_client/model/Message.dart';
import 'package:json_annotation/json_annotation.dart';

part 'message_dto.g.dart';

@JsonSerializable()
class MessageDTO extends Message {
  MessageDTO(String sender, String receiver, String message, int senderId,
      int receiverId)
      : super(sender, receiver, message, senderId, receiverId);

  factory MessageDTO.fromJson(Map<String, dynamic> json) =>
      _$MessageDTOFromJson(json);

  Map<String, dynamic> toJson() => _$MessageDTOToJson(this);
}
