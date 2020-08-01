import 'package:adjust_client/dto/shoping_item_dto.dart';
import 'package:adjust_client/model/cart.dart';
import 'package:adjust_client/model/shoping_item.dart';
import 'package:json_annotation/json_annotation.dart';

part 'cart_dto.g.dart';

@JsonSerializable()
class CartDTO extends Cart {
  List<ShopingItemDTO> items;
  CartDTO(int id, String username, String firstName, String lastName, this.items)
      : super(id, username, firstName, lastName);

  factory CartDTO.fromJson(Map<String, dynamic> json) =>
      _$CartDTOFromJson(json);

  Map<String, dynamic> toJson() => _$CartDTOToJson(this);
}
