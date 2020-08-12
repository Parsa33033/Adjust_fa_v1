

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AdjustGridViewItem extends StatefulWidget {
  String name;
  Color selectedColor;
  Color notSelectedColor;
  Color selectedFontColor;
  Color notSelectedFontColor;
  bool selected;
  bool hasPicture;
  Image picture;
  ValueChanged<bool> isSelected;


  AdjustGridViewItem({this.name, this.selectedColor,
      this.notSelectedColor, this.selectedFontColor, this.notSelectedFontColor, this.selected, this.hasPicture, this.picture, this.isSelected}) {
    this.selected = this.selected == null ? false : this.selected;
    this.hasPicture = this.hasPicture == null ? false : this.hasPicture;
    this.picture = this.picture == null ? null : this.picture;
  }

  @override
  _AdjustGridViewItemState createState() => _AdjustGridViewItemState();
}

class _AdjustGridViewItemState extends State<AdjustGridViewItem> {

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        height: 100,
        width: MediaQuery.of(context).size.width,
        child: InkWell(
          child: Container(
              padding: EdgeInsets.all(4),
              margin : EdgeInsets.all(4),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.all(Radius.circular(10)),
                  border: Border.all(color: this.widget.selectedColor, width: 2),
                  color: this.widget.selected ? this.widget.selectedColor : this.widget.notSelectedColor
              ),
              child: this.widget.hasPicture ? Row(
                children: <Widget>[
                  Expanded(
                    flex: 7,
                    child: FittedBox(
                      fit: BoxFit.scaleDown,
                      child: Directionality(
                        textDirection: TextDirection.rtl,
                        child: Text(this.widget.name, style: TextStyle(fontFamily: "Iransans", color: this.widget.selected ? this.widget.selectedFontColor: this.widget.notSelectedFontColor, fontSize: 14),),
                      ),
                    ),
                  ),
                  Expanded(
                    flex: 3,
                    child: Container(
                      child: this.widget.picture,
                    ),
                  )
                ],
              ) : FittedBox(
                fit: BoxFit.scaleDown,
                child: Directionality(
                  textDirection: TextDirection.rtl,
                  child: Text(this.widget.name, style: TextStyle(fontFamily: "Iransans", color: this.widget.selected ? this.widget.selectedFontColor: this.widget.notSelectedFontColor, fontSize: 14),),
                ),
              ),
          ),
          onTap: () {
            setState(() {
              this.widget.selected = !this.widget.selected;
              this.widget.isSelected(this.widget.selected);
            });
          },
        )
    );
  }
}