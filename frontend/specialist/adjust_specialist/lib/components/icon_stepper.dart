import 'package:flutter/material.dart';


/// Callback is fired when a step is reached.
typedef OnStepReached = void Function(int index);
GlobalKey<_IconStepperState> iconStepperStateKey = GlobalKey();
class IconStepper extends StatefulWidget {

  /// Each icon defines a step. Hence, total number of icons determines the total number of steps.
  final List<Icon> icons;

  /// Whether to enable or disable the next and previous buttons.
  final bool enableNextPreviousButtons;

  /// Whether to allow tapping a step to move to that step or not.
  final bool enableStepTapping;

  /// Icon to be used for the previous button.
  final Icon previousButtonIcon;

  /// Icon to be used for the next button.
  final Icon nextButtonIcon;

  /// Determines what should happen when a step is reached. This callback provides the __index__ of the step that was reached.
  final OnStepReached onStepReached;

  /// Whether to show the steps horizontally or vertically. __Note: Ensure horizontal stepper goes inside a column and vertical goes inside a row.__
  final Axis direction;

  /// The color of the step when it is not reached.
  final Color stepColor;

  /// The color of a step when it is reached.
  final Color activeStepColor;

  /// The border color of a step when it is reached.
  final Color activeStepBorderColor;

  /// The color of the line that separates the steps.
  final Color lineColor;

  /// The length of the line that separates the steps.
  final double lineLength;

  /// The radius of individual dot within the line that separates the steps.
  final double lineDotRadius;

  /// The radius of a step.
  final double stepRadius;

  /// The animation effect to show when a step is reached.
  final Curve stepReachedAnimationEffect;

  /// The duration of the animation effect to show when a step is reached.
  final Duration stepReachedAnimationDuration;

  /// Whether the stepping is enabled or disabled.
  final bool steppingEnabled;


  IconStepper({
    Key key,
    this.icons,
    this.enableNextPreviousButtons = true,
    this.enableStepTapping = true,
    this.previousButtonIcon,
    this.nextButtonIcon,
    this.onStepReached,
    this.direction = Axis.horizontal,
    this.stepColor,
    this.activeStepColor,
    this.activeStepBorderColor,
    this.lineColor,
    this.lineLength = 50.0,
    this.lineDotRadius = 1.0,
    this.stepRadius = 24.0,
    this.stepReachedAnimationEffect = Curves.bounceOut,
    this.stepReachedAnimationDuration = const Duration(seconds: 1),
    this.steppingEnabled = true,
  }) : super(key: key);
//  {
//    assert(
//    lineDotRadius <= 10 && lineDotRadius > 0,
//    'lineDotRadius must be less than or equal to 10 and greater than 0',
//    );
//
//    assert(
//    stepRadius > 0,
//    'iconIndicatorRadius must be greater than 0',
//    );
//  }

  @override
  _IconStepperState createState() => _IconStepperState();
}

class _IconStepperState extends State<IconStepper> {
  ScrollController _scrollController;
  int _selectedIndex;

  @override
  void initState() {
    super.initState();

    _selectedIndex = 0;
    this._scrollController = ScrollController();
  }

  /// Controls the step scrolling.
  void _afterLayout(_) {
    // * This owes an explanation.
    for (int i = 0; i < widget.icons.length; i++) {
      _scrollController.animateTo(
        i * ((widget.stepRadius * 2) + widget.lineLength),
        duration: widget.stepReachedAnimationDuration,
        curve: widget.stepReachedAnimationEffect,
      );

      if (_selectedIndex == i) break;
    }
  }

  @override
  Widget build(BuildContext context) {
    WidgetsBinding.instance.addPostFrameCallback(_afterLayout);

    return widget.direction == Axis.horizontal
        ? Row(
      children: <Widget>[
        widget.enableNextPreviousButtons
            ? _previousButton()
            : Container(),
        Expanded(
          child: _stepperBuilder(),
        ),
        widget.enableNextPreviousButtons ? _nextButton() : Container(),
      ],
    )
        : Column(
      children: <Widget>[
        widget.enableNextPreviousButtons
            ? _previousButton()
            : Container(),
        Expanded(
          child: _stepperBuilder(),
        ),
        widget.enableNextPreviousButtons ? _nextButton() : Container(),
      ],
    );
  }

  /// Builds the stepper.
  Widget _stepperBuilder() {
    return SingleChildScrollView(
      scrollDirection: widget.direction,
      controller: _scrollController,
      child: Container(
        margin: const EdgeInsets.symmetric(horizontal: 8.0),
        padding: const EdgeInsets.all(8.0),
        child: widget.direction == Axis.horizontal
            ? Row(children: _buildSteps())
            : Column(children: _buildSteps()),
      ),
    );
  }

  /// Builds the stepper steps.
  List<Widget> _buildSteps() {
    return List.generate(
      widget.icons.length,
          (index) {
        return widget.direction == Axis.horizontal
            ? Row(
          children: <Widget>[
            _iconStepCustomized(index),
            _dottedLineCustomized(index, Axis.horizontal),
          ],
        )
            : Column(
          children: <Widget>[
            _iconStepCustomized(index),
            _dottedLineCustomized(index, Axis.vertical),
          ],
        );
      },
    );
  }

  /// A customized IconStep.
  Widget _iconStepCustomized(int index) {
    return IconIndicator(
      index: index + 1,
      icon: Icon(
        widget.icons[index].icon,
        size: widget.stepRadius,
        color: widget.icons[index].color,
      ),
      isSelected: _selectedIndex == index,
      onPressed: widget.enableStepTapping
          ? () {
        if (widget.steppingEnabled) {
          setState(() {
            _selectedIndex = index;
            widget.onStepReached(_selectedIndex);
          });
        }
      }
          : null,
      color: widget.stepColor,
      activeColor: widget.activeStepColor,
      activeBorderColor: widget.activeStepBorderColor,
      radius: widget.stepRadius,
    );
  }

  /// A customized DottedLine.
  Widget _dottedLineCustomized(int index, Axis axis) {
    return index < widget.icons.length - 1
        ? DottedLine(
      length: widget.lineLength ?? 50,
      color: widget.lineColor ?? Colors.blue,
      dotRadius: widget.lineDotRadius ?? 1.0,
      spacing: 5.0,
      axis: axis,
    )
        : Container();
  }

  /// The previous button.
  Widget _previousButton() {
    return IgnorePointer(
      ignoring: _selectedIndex == 0,
      child: IconButton(
        visualDensity: VisualDensity.compact,
        icon: widget?.nextButtonIcon ??
            Icon(
              widget.direction == Axis.horizontal
                  ? Icons.arrow_left
                  : Icons.arrow_drop_up,
            ),
        onPressed: () {
          if (_selectedIndex > 0) {
            setState(() {
              _selectedIndex--;
              widget.onStepReached(_selectedIndex);
            });
          }
        },
      ),
    );
  }

  void previous() {
    if (_selectedIndex > 0) {
      setState(() {
        _selectedIndex--;
        widget.onStepReached(_selectedIndex);
      });
    }
  }
  void next() {
    if (_selectedIndex < widget.icons.length - 1 &&
        widget.steppingEnabled) {
      setState(() {
        _selectedIndex++;
        widget.onStepReached(_selectedIndex);
      });
    }
  }
  /// The next button.
  Widget _nextButton() {
    return IgnorePointer(
      ignoring: _selectedIndex == widget.icons.length - 1,
      child: IconButton(
        visualDensity: VisualDensity.compact,
        icon: widget?.nextButtonIcon ??
            Icon(
              widget.direction == Axis.horizontal
                  ? Icons.arrow_right
                  : Icons.arrow_drop_down,
            ),
        onPressed: () {
          if (_selectedIndex < widget.icons.length - 1 &&
              widget.steppingEnabled) {
            setState(() {
              _selectedIndex++;
              widget.onStepReached(_selectedIndex);
            });
          }
        },
      ),
    );
  }
}

class IconIndicator extends StatefulWidget {
  final int index;
  final bool isSelected;
  final Icon icon;
  final Function onPressed;
  final Color color;
  final Color activeColor;
  final Color activeBorderColor;
  final double radius;

  IconIndicator({
    @required this.index,
    this.isSelected = false,
    this.icon,
    this.onPressed,
    this.color,
    this.activeColor,
    this.activeBorderColor,
    this.radius = 24.0,
  });

  @override
  _IconIndicatorState createState() => _IconIndicatorState();
}

class _IconIndicatorState extends State<IconIndicator>
    with SingleTickerProviderStateMixin {
  AnimationController animationController;
  Animation opacityAnimation, backwardAnimation;

  @override
  void initState() {
    super.initState();

    animationController = AnimationController(
      vsync: this,
      duration: Duration(milliseconds: 1000),
    )..addListener(() {
      setState(() {});
    });

    opacityAnimation = Tween(
      begin: 0.0,
      end: 1.0,
    ).animate(animationController);

    animationController.forward();
  }

  @override
  void didUpdateWidget(IconIndicator oldWidget) {
    super.didUpdateWidget(oldWidget);
    if (widget.isSelected && animationController.isDismissed) {
      animationController.forward();
    } else if (widget.isSelected && animationController.isCompleted) {
      animationController.reset();
      animationController.forward();
    }
  }

  @override
  void dispose() {
    super.dispose();
    animationController.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Opacity(
      opacity: widget.isSelected ? opacityAnimation.value : 1.0,
      child: Container(
        padding: widget.isSelected ? EdgeInsets.all(1.0) : EdgeInsets.zero,
        decoration: BoxDecoration(
          border: widget.isSelected
              ? Border.all(
            color: widget.activeBorderColor ?? Colors.blue,
            width: 0.5,
          )
              : null,
          shape: BoxShape.circle,
        ),
        child: InkWell(
          onTap: widget.onPressed,
          child: Container(
            height: widget.radius * 2,
            width: widget.radius * 2,
            padding: EdgeInsets.all(5.0),
            decoration: BoxDecoration(
              color: widget.isSelected
                  ? widget.activeColor ?? Colors.green
                  : widget.color ?? Colors.grey,
              shape: BoxShape.circle,
            ),
            child: Center(
              child: widget.icon ?? Text('${widget.index}'),
            ),
          ),
        ),
      ),
    );
  }
}

class DottedLine extends StatelessWidget {
  /// Width of the dotted line.
  final double length;

  /// Color of the dotted line.
  final Color color;

  /// Radius of each dot in the dotted line.
  final double dotRadius;

  /// Spacing between the dots in the dotted line.
  final double spacing;

  final Axis axis;

  DottedLine({
    this.length = 50.0,
    this.color = Colors.grey,
    this.dotRadius = 2.0,
    this.spacing = 3.0,
    this.axis = Axis.horizontal,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      // If this is not appled, top half of the dot gets offscreen, hence, hidden.
      margin: EdgeInsets.only(top: dotRadius),
      width: axis == Axis.horizontal ? length : 0.0,
      height: axis == Axis.vertical ? length : 0.0,
      child: CustomPaint(
        painter: _DottedLinePainter(
          brush: Paint()..color = color ?? Colors.grey,
          length: length,
          dotRadius: dotRadius,
          spacing: spacing,
          axis: axis,
        ),
      ),
    );
  }
}

class _DottedLinePainter extends CustomPainter {
  final double length;
  final double dotRadius;
  final double spacing;
  final Paint brush;
  final Axis axis;

  _DottedLinePainter({
    this.length = 100,
    this.brush,
    this.dotRadius = 2.0,
    this.spacing = 3.0,
    this.axis = Axis.horizontal,
  }) {
    assert(brush != null);
    assert(dotRadius > 0, 'dotRadius must be greater than 0');
  }

  @override
  void paint(Canvas canvas, Size size) {
    double start = 0.0;

    // Length of the line is calculated by dividing the supplied lenght [to] by dotRadius * space.
    int calculatedLength = length ~/ (dotRadius * spacing);

    for (int i = 1; i < calculatedLength; i++) {
      // New start becomes:
      start += dotRadius * spacing;

      canvas.drawCircle(
        Offset(axis == Axis.horizontal ? start : 0.0,
            axis == Axis.horizontal ? 0.0 : start),
        dotRadius,
        brush,
      );
    }
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) => false;
}
