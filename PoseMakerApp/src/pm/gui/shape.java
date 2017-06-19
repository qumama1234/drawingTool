/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.gui;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Xiangbin
 */
class shape {
//    int typeOfShap;
//    Color FillColor;
//    Color OutlineColor;
//    int outlineThickness;
      final String STYPE="render_canvas";
      public Ellipse e;
      public Rectangle r; 
      DropShadow ds = new DropShadow();


    
    public shape(Ellipse e){
        this.e=e;
        r=null;
    }
    public shape( Rectangle r){
        this.r=r;
        e=null;
    }
    
    public Object getShape(){
        if(e==null){
            return r;
        }
        else{
            return e;
        }
    }
    public void getStyle(){
        ds.setOffsetY(7.5);
        ds.setOffsetX(7.5);
        ds.setColor(Color.GRAY);
        if(e==null){ 
            r.setEffect(ds);
        }
        else{
            e.setEffect(ds);
        }
    }
    public void loseStyle(){
        if(r!=null){
            r.setEffect(null);
        }
        else{
            e.setEffect(null);
        }
    }
    public int whichShapIsIt(){
    if(e!=null){
            return 0;
        }
    else{
            return 4;
        }
    
}
}
