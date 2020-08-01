import 'package:adjust_client/model/shoping_item.dart';
import 'package:json_annotation/json_annotation.dart';

part 'shoping_item_dto.g.dart';

@JsonSerializable()
class ShopingItemDTO extends ShopingItem {
  ShopingItemDTO(
      int id,
      String name,
      String description,
      double token,
      double price,
      String image,
      String imageContentType,
      bool orderable,
      int cartId)
      : super(id, name, description, token, price, image, imageContentType,
            orderable, cartId);

  factory ShopingItemDTO.fromJson(Map<String, dynamic> json) =>
      _$ShopingItemDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ShopingItemDTOToJson(this);
}
