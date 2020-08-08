// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'message_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MessageDTO _$MessageDTOFromJson(Map<String, dynamic> json) {
  return MessageDTO(
    json['sender'] as String,
    json['receiver'] as String,
    json['message'] as String,
    json['clientId'] as int,
    json['specialistId'] as int,
  );
}

Map<String, dynamic> _$MessageDTOToJson(MessageDTO instance) =>
    <String, dynamic>{
      'sender': instance.sender,
      'receiver': instance.receiver,
      'message': instance.message,
      'clientId': instance.clientId,
      'specialistId': instance.specialistId,
    };
