

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AdjustGridViewItem extends StatefulWidget {
  String name;
  Color selectedColor;
  Color notSelectedColor;
  Color selectedFontColor;
  Color notSelectedFontColor;
  ValueChanged<bool> isSelected;


  AdjustGridViewItem({this.name, this.selectedColor,
      this.notSelectedColor, this.selectedFontColor, this.notSelectedFontColor, this.isSelected});

  @override
  _AdjustGridViewItemState createState() => _AdjustGridViewItemState();
}

class _AdjustGridViewItemState extends State<AdjustGridViewItem> {
  bool selected;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    selected = false;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        height: MediaQuery.of(context).size.height,
        width: MediaQuery.of(context).size.width,
        child: InkWell(
          child: Container(
              padding: EdgeInsets.all(4),
              margin : EdgeInsets.all(4),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(10)),
                  border: Border.all(color: this.widget.selectedColor, width: 2),
                  color: this.selected ? this.widget.selectedColor : this.widget.notSelectedColor
              ),
              child: FittedBox(
                fit: BoxFit.scaleDown,
                child: Directionality(
                  textDirection: TextDirection.rtl,
                  child: Text(this.widget.name, style: TextStyle(fontFamily: "Iransans", color: this.selected ? this.widget.selectedFontColor: this.widget.notSelectedFontColor, fontSize: 14),),
                ),
              )
          ),
          onTap: () {
            setState(() {
              this.selected = !this.selected;
              this.widget.isSelected(this.selected);
            });
          },
        )
    );
  }
}