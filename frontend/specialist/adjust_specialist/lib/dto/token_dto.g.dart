// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'token_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

TokenItemDTO _$TokenItemDTOFromJson(Map<String, dynamic> json) {
  return TokenItemDTO(
    json['id'] as int,
    json['name'] as String,
    json['description'] as String,
    (json['token'] as num)?.toDouble(),
    (json['price'] as num)?.toDouble(),
    json['image'] as String,
    json['imageContentType'] as String,
    json['orderable'] as bool,
  );
}

Map<String, dynamic> _$TokenItemDTOToJson(TokenItemDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'description': instance.description,
      'token': instance.token,
      'price': instance.price,
      'image': instance.image,
      'imageContentType': instance.imageContentType,
      'orderable': instance.orderable,
    };
