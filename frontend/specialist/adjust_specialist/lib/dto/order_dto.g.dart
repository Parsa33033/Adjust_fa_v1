// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'order_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

OrderDTO _$OrderDTOFromJson(Map<String, dynamic> json) {
  return OrderDTO(
    json['id'] as int,
    json['username'] as String,
    json['firstName'] as String,
    json['lastName'] as String,
    json['phoneNumber'] as String,
    json['email'] as String,
    json['country'] as String,
    json['state'] as String,
    json['city'] as String,
    json['address1'] as String,
    json['address2'] as String,
    json['done'] as bool,
    json['received'] as bool,
    json['cartId'] as int,
    json['cart'] == null
        ? null
        : CartDTO.fromJson(json['cart'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$OrderDTOToJson(OrderDTO instance) => <String, dynamic>{
      'id': instance.id,
      'username': instance.username,
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'phoneNumber': instance.phoneNumber,
      'email': instance.email,
      'country': instance.country,
      'state': instance.state,
      'city': instance.city,
      'address1': instance.address1,
      'address2': instance.address2,
      'done': instance.done,
      'received': instance.received,
      'cartId': instance.cartId,
      'cart': instance.cart,
    };
