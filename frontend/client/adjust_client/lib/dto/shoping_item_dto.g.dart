// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'shoping_item_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ShopingItemDTO _$ShopingItemDTOFromJson(Map<String, dynamic> json) {
  return ShopingItemDTO(
    json['id'] as int,
    json['name'] as String,
    json['description'] as String,
    (json['token'] as num)?.toDouble(),
    (json['price'] as num)?.toDouble(),
    json['image'] as String,
    json['imageContentType'] as String,
    json['orderable'] as bool,
    json['cartId'] as int,
  );
}

Map<String, dynamic> _$ShopingItemDTOToJson(ShopingItemDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'description': instance.description,
      'token': instance.token,
      'price': instance.price,
      'image': instance.image,
      'imageContentType': instance.imageContentType,
      'orderable': instance.orderable,
      'cartId': instance.cartId,
    };
