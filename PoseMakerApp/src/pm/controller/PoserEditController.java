/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pm.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import pm.PoseMaker;
import pm.gui.Workspace;
import saf.AppTemplate;
import saf.ui.AppGUI;
import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.scene.* ;
import javafx.stage.Stage;
import javafx.geometry.* ; // Point2D
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Xiangbin
 */
public class PoserEditController {
    // HERE'S THE APP
    AppTemplate app;
    
    // IT KNOWS THE GUI IT IS PLACED INSIDE
    AppGUI gui;
    
     boolean rectangles_Drawed=false;
     double starting_point_x, starting_point_y ;
     //Workspace workspace= (Workspace)app.getWorkspaceComponent();
    
    // HERE'S THE FULL APP, WHICH GIVES US ACCESS TO OTHER STUFF
     public PoserEditController(){
         
     }

    public PoserEditController(AppTemplate initApp) {
        
      	// KEEP THIS FOR LATER
	app = initApp;

	// KEEP THE GUI FOR LATER
	gui = app.getGUI();
        

        
    }
    
//    void adjust_rectangle_properties( double starting_point_x,double starting_point_y,double ending_point_x,double ending_point_y, Rectangle given_rectangle )
//   {
//      given_rectangle.setX(starting_point_x) ; 
//      given_rectangle.setY( starting_point_y ) ;
//      given_rectangle.setWidth( ending_point_x - starting_point_x ) ;
//      given_rectangle.setHeight( ending_point_y - starting_point_y ) ;
//     
//            if ( given_rectangle.getHeight() < 0 )
//      {
//         given_rectangle.setHeight( - given_rectangle.getHeight() ) ;
//         given_rectangle.setY( given_rectangle.getY() - given_rectangle.getHeight() ) ;
//      }
//
//      if ( given_rectangle.getWidth() < 0 )
//      {
//         given_rectangle.setWidth( - given_rectangle.getWidth() ) ;
//         given_rectangle.setX( given_rectangle.getX() - given_rectangle.getWidth() ) ;
//      }
//
//
//   }
    
//    public void Rectangles(Pane a,Color OutLineColor,Color FillColor){
//        //System.out.print(app.getWorkspaceComponent().getWorkspace().getChildren().get(2).toString()+"111");
//        
//        Group group_for_shape=new Group();
//        Rectangle new_rectangle = new Rectangle();
//        new_rectangle.setFill(FillColor);
//        new_rectangle.setStroke(OutLineColor);
//        a.setOnMousePressed( ( MouseEvent event ) ->
//      {  
//          if(rectangles_Drawed==false){
//
//          starting_point_x = event.getSceneX();
//          starting_point_y = event.getSceneY();
//          
//          group_for_shape.getChildren().add(new_rectangle);
//          rectangles_Drawed=true;
//          } 
//      });
//        a.setOnMouseDragged( ( MouseEvent event ) ->
//      {
//          if ( rectangles_Drawed == true ){
//              double current_ending_point_x = event.getSceneX() ;
//              double current_ending_point_y = event.getSceneY() ;
//
//            adjust_rectangle_properties( starting_point_x,
//                                         starting_point_y,
//                                         current_ending_point_x,
//                                         current_ending_point_y,
//                                         new_rectangle ) ;
//              
//          }
//      });
//        
//      a.setOnMouseReleased( ( MouseEvent event ) ->
//      {
//         if ( rectangles_Drawed == true )
//         {
//             
//             rectangles_Drawed = false;
//             
//         }
//    } );
//
//      a.getChildren().addAll(group_for_shape);
//    
//}
}



                

