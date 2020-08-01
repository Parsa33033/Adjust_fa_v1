// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'cart_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CartDTO _$CartDTOFromJson(Map<String, dynamic> json) {
  return CartDTO(
    json['id'] as int,
    json['username'] as String,
    json['firstName'] as String,
    json['lastName'] as String,
    (json['items'] as List)
        ?.map((e) => e == null
            ? null
            : ShopingItemDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$CartDTOToJson(CartDTO instance) => <String, dynamic>{
      'id': instance.id,
      'username': instance.username,
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'items': instance.items,
    };
