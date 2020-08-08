// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'tutorial_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

TutorialListDTO _$TutorialListDTOFromJson(Map<String, dynamic> json) {
  return TutorialListDTO(
    (json['items'] as List)
        ?.map((e) =>
            e == null ? null : TutorialDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$TutorialListDTOToJson(TutorialListDTO instance) =>
    <String, dynamic>{
      'items': instance.items,
    };

TutorialDTO _$TutorialDTOFromJson(Map<String, dynamic> json) {
  return TutorialDTO(
    json['id'] as int,
    json['title'] as String,
    json['description'] as String,
    json['text'] as String,
    json['thumbnail'] as String,
    json['thumbnailContentType'] as String,
    (json['tokenPrice'] as num)?.toDouble(),
    json['videoId'] as int,
    json['clientId'] as int,
  );
}

Map<String, dynamic> _$TutorialDTOToJson(TutorialDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'title': instance.title,
      'description': instance.description,
      'text': instance.text,
      'thumbnail': instance.thumbnail,
      'thumbnailContentType': instance.thumbnailContentType,
      'tokenPrice': instance.tokenPrice,
      'videoId': instance.videoId,
      'clientId': instance.clientId,
    };
