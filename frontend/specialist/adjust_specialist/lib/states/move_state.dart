import 'package:adjust_specialist/model/move.dart';


final MoveStateList moveStateListInit = MoveStateList(moves: List());

class MoveStateList {
  List<MoveState> moves;
  MoveStateList({this.moves});
}

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
