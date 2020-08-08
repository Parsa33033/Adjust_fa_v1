
import 'package:flutter/material.dart';

class AdjustStateChangeNotification extends Notification with ViewportNotificationMixin {

  String state;

  @override
  void debugFillDescription(List<String> description) {
    super.debugFillDescription(description);
    description.add('$state');
  }

}
