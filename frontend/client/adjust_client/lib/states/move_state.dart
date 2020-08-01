import 'package:adjust_client/model/move.dart';

class MoveState extends Move {
  MoveState(
      int id,
      String name,
      String muscleName,
      MuscleType muscleType,
      String equipment,
      String picture,
      String pictureContentType,
      int adjustMoveId)
      : super(id, name, muscleName, muscleType, equipment, picture,
            pictureContentType, adjustMoveId);
}
