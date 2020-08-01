import 'package:adjust_client/model/shoping_item.dart';
import 'package:adjust_client/model/token.dart';
import 'package:json_annotation/json_annotation.dart';

part 'token_dto.g.dart';

@JsonSerializable()
class TokenItemDTO extends TokenItem {
  TokenItemDTO(int id, String name, String description, double token,
      double price, String image, String imageContentType, bool orderable)
      : super(id, name, description, token, price, image, imageContentType,
      orderable);

  factory TokenItemDTO.fromJson(Map<String, dynamic> json) =>
      _$TokenItemDTOFromJson(json);

  Map<String, dynamic> toJson() => _$TokenItemDTOToJson(this);
}
