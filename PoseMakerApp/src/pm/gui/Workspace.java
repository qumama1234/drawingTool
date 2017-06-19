package pm.gui;

import com.sun.javafx.scene.paint.GradientUtils.Point;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.TransferHandler;
import pm.PropertyType;
import static pm.PropertyType.SELECTION_TOOL_ICON;
import pm.controller.PoserEditController;
import properties_manager.PropertiesManager;
import saf.ui.AppGUI;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;

/**
 * This class serves as the workspace component for this application, providing
 * the user interface controls for editing work.
 *
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class Workspace extends AppWorkspaceComponent {

    static final String EDIT_TOOLBAR = "edit_toolbar";
    static final String COLOR_CHOOSER_PANE = "color_chooser_pane";
    static final String MAX_PANE = "max_pane";
    static final String BORDERED_PANE = "bordered_pane";
    static final String TAG_BUTTON = "tag_button";
    static final String HEDINGLABEL = "heading_label";
    static final String SUBHEDINGLABEL = "subheading_label";
    static final String RENDERCANVAS = "render_canvas";
    //static final String SELECTION_TOOL_ICON="selection_tool_icon";

    // HERE'S THE APP
    AppTemplate app;
    PoserEditController poserEditer;

    // IT KNOWS THE GUI IT IS PLACED INSIDE
    AppGUI gui;

    HBox workspaceBorderPane;

    VBox leftPane;
    ScrollPane leftScrollPane;

    HBox editToolBar;
    ScrollPane editToolBarScrollPane;
    Button selectionToolButton;
    //Image imagSelectionToolButton=new Image(getClass.getResourceAsStream("SelectionTool"))   
    //selectionToolButton.
    Button removeButton;
    Button rectangleButton;
    Button ellipseButton;

    HBox move_up_down_Pane;
    ScrollPane moveUpDownScrollPane;
    Button moveToFront;
    Button moveToBack;

    VBox backgroundColorPane;
    ScrollPane backgroundColorScrollPane;
    Label backgroundColorLabel;
    ColorPicker backGroundColorPicker;

    VBox fillColorPane;
    ScrollPane fiilColorScrollPane;
    Label fillColorLabel;
    ColorPicker FillColorPicker;
    Color FillColor;

    VBox outlineColorPane;
    ScrollPane outlineColorScrollPane;
    Label outlineColorLabel;
    ColorPicker OutLineColorPicker;
    Color OutLineColor;
    int a;

    VBox outlineThicknessPane;
    ScrollPane outLineThicknessPane;
    Label outlineThicknessLabel;
    Slider outlineJSlider;

    HBox cameraPane;
    ScrollPane cameraPaneScrollPane;
    Button cameraButton;
    ImageView snapShot;
    WritableImage Wimage;
    BufferedImage bImage;
    Time timenow;

    Pane rightPane;
    ScrollPane rightScrollPane;

    //Selected Stuff
    Button WhichShap = null;

    //High lighted object
    shape selectedShap;

    //add shape stuff
    Group group_for_shape = new Group();
    ;
     Rectangle new_rectangle;
    double starting_point_x, starting_point_y;
    boolean rectangles_Drawed;
    boolean ellipseIsBeingDrawed;
    Ellipse newEllipse;
    ArrayList<shape> shapes;
    ArrayList<Group> groups;
    shape thisshape;
    double x;
    double y;
    double bx;
    double by;
    double scale;
    /**
     * Constructor for initializing the workspace, note that this constructor
     * will fully setup the workspace user interface for use.
     *
     * @param initApp The application this workspace is part of.
     *
     * @throws IOException Thrown should there be an error loading application
     * data for setting up the user interface.
     */
    public Workspace(AppTemplate initApp) throws IOException {
        shapes = new ArrayList();
        // KEEP THIS FOR LATER
        app = initApp;

        // KEEP THE GUI FOR LATER
        gui = app.getGUI();

        //poser Editer
        poserEditer = new PoserEditController(initApp);

        // THIS WILL PROVIDE US WITH OUR CUSTOM UI SETTINGS AND TEXT
        //PropertiesManager propsSingleton = PropertiesManager.getPropertiesManager();  
        workspace = new BorderPane();

        editToolBar = new HBox();
        editToolBar.setMinHeight(110);
        Button a=new Button();
        editToolBar.getChildren().add(a);

        selectionToolButton = gui.initChildButton(editToolBar, PropertyType.SELECTION_TOOL_ICON.toString(), PropertyType.SELECTION_TOOL_TOOLTIP.toString(), false);
        removeButton = gui.initChildButton(editToolBar, PropertyType.REMOVE_ICON.toString(), PropertyType.REMOVE_TOOLTIP.toString(), false);
        rectangleButton = gui.initChildButton(editToolBar, PropertyType.RECTANGLE_ICON.toString(), PropertyType.RECTANGLE_TOOLTIP.toString(), false);
        ellipseButton = gui.initChildButton(editToolBar, PropertyType.ELLIPSE_ICON.toString(), PropertyType.ELLIPSE_TOOLTIP.toString(), false);

        move_up_down_Pane = new HBox();
        moveToFront = gui.initChildButton(move_up_down_Pane, PropertyType.MOVETOFRONT_ICON.toString(), PropertyType.MOVETOFRONT_TOOLTIP.toString(), true);
        moveToBack = gui.initChildButton(move_up_down_Pane, PropertyType.MOVETOBACK_ICON.toString(), PropertyType.MOVETOBACK_TOOPTIP.toString(), true);
        move_up_down_Pane.setMinHeight(110);

        backgroundColorPane = new VBox();
        backGroundColorPicker = new ColorPicker();
        backgroundColorLabel = new Label("Background Color");
        backgroundColorLabel.getStyleClass().add(SUBHEDINGLABEL);
        backgroundColorPane.getChildren().addAll(backgroundColorLabel, backGroundColorPicker);
        backgroundColorPane.setMinHeight(140);

        fillColorPane = new VBox();
        FillColorPicker = new ColorPicker(Color.WHITE);
        fillColorLabel = new Label("Fill Color");
        fillColorLabel.getStyleClass().add(SUBHEDINGLABEL);
        fillColorPane.getChildren().addAll(fillColorLabel, FillColorPicker);
        fillColorPane.setMinHeight(140);
        
        //Colorset
        FillColor=Color.BLACK;
        OutLineColor=Color.RED;
        
        

        outlineColorPane = new VBox();
        OutLineColorPicker = new ColorPicker(Color.BLACK);
        outlineColorLabel = new Label("Outline Color");
        outlineColorLabel.getStyleClass().add(SUBHEDINGLABEL);
        outlineColorPane.getChildren().addAll(outlineColorLabel, OutLineColorPicker);
        outlineColorPane.setMinHeight(140);

        outlineThicknessPane = new VBox();
        outlineThicknessLabel = new Label("Outline Thickness");
        outlineThicknessLabel.getStyleClass().add(SUBHEDINGLABEL);
        outlineJSlider = new Slider();
        outlineJSlider.setMin(0);
        outlineJSlider.setMax(50);
        outlineThicknessPane.getChildren().addAll(outlineThicknessLabel, outlineJSlider);
        outlineThicknessPane.setMinHeight(130);

        cameraPane = new HBox();
        cameraButton = gui.initChildButton(cameraPane, PropertyType.SNAPSHOT_ICON.toString(), PropertyType.SELECTION_TOOL_TOOLTIP.toString(), false);
        cameraPane.setMinHeight(130);

        leftPane = new VBox();
        leftPane.getChildren().addAll(editToolBar, move_up_down_Pane, backgroundColorPane, fillColorPane, outlineColorPane, outlineThicknessPane, cameraPane);
        leftPane.fillWidthProperty();
        leftPane.setMinSize(300, 900);

        //put canvas in side of pane
        rightPane = new Pane();
        rightPane.setPrefSize(1575, 900);

        //add workspace Border Pane to the work spaces
        workspaceBorderPane = new HBox();
        workspaceBorderPane.getChildren().addAll(leftPane, rightPane);

        workspace = new Pane();
        workspace.getChildren().add(workspaceBorderPane);

        //backGroudColorPicker even handler
        backGroundColorPicker.setOnAction(e -> {
            Color fill = backGroundColorPicker.getValue();
            BackgroundFill backgroundFill = new BackgroundFill(fill, CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            rightPane.setBackground(background);
        });
        FillColorPicker.setOnAction(e -> {
            FillColor = FillColorPicker.getValue();
            if (selectedShap != null) {
                if (selectedShap.e != null) {
                    selectedShap.e.setFill(FillColorPicker.getValue());
                } else {
                    selectedShap.r.setFill(FillColorPicker.getValue());
                }
            }

        });
        OutLineColorPicker.setOnAction(e -> {
            OutLineColor = OutLineColorPicker.getValue();
            if (selectedShap != null) {
                if (selectedShap.e != null) {
                    selectedShap.e.setStroke(OutLineColorPicker.getValue());
                } else {
                    selectedShap.r.setStroke(OutLineColorPicker.getValue());
                }
            }
        });

        outlineJSlider.setOnMouseClicked(e -> {
            if (selectedShap != null) {
                if (selectedShap.e != null) {
                    selectedShap.e.setStrokeWidth(outlineJSlider.getValue());
                } else {
                    selectedShap.r.setStrokeWidth(outlineJSlider.getValue());
                }
            }
        });

        //cameraButton even handler
        cameraButton.setOnAction(e -> {
            Wimage = new WritableImage(1575, 900);
            SnapshotParameters snap = new SnapshotParameters();
            Wimage = rightPane.snapshot(snap, Wimage);
            BufferedImage bImage = SwingFXUtils.fromFXImage(Wimage, null);
            a = (int) (Math.random() * 1000);
            File imgFile = new File("./image/" + a + ".png");
            imgFile.getParentFile().mkdir();
            try {
                ImageIO.write(bImage, "png", imgFile);
            } catch (IOException ex) {
                Logger.getLogger(Workspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //rectangleButton even handler
        rectangleButton.setOnAction(e -> {
            rectangles_Drawed = false;
            WhichShap = rectangleButton;
            //poserEditer.Rectangles(rightPane,OutLineColor, FillColor);     
            //group_for_shape=new Group();
            rightPane.setOnMousePressed((MouseEvent event) -> {
                if (rectangles_Drawed == false) {
                  new_rectangle = new Rectangle();
                  new_rectangle.setFill(FillColor);
                  new_rectangle.setStroke(OutLineColor);
                  new_rectangle.setStrokeType(StrokeType.OUTSIDE);
                  new_rectangle.setStrokeWidth(outlineJSlider.getValue());
                    starting_point_x = event.getX();
                    starting_point_y = event.getY();
                    group_for_shape.getChildren().add(new_rectangle);
                    rectangles_Drawed = true;
                }
            });

            rightPane.setOnMouseDragged((MouseEvent event) -> {
                if (rectangles_Drawed == true) {
                    double current_ending_point_x = event.getX();
                    double current_ending_point_y = event.getY();
                    adjust_rectangle_properties(starting_point_x, starting_point_y, current_ending_point_x, current_ending_point_y, new_rectangle);
                }
            });
            rightPane.setOnMouseReleased((MouseEvent event) -> {
                if (rectangles_Drawed == true) {
                    thisshape = new shape(new_rectangle);
                              shapes.add(0, thisshape);
//                if(selectedShap!=null){
//                selectedShap.loseStyle();
//                }
//                selectedShap=thisshape;
//                electedShapReNow();
//                thisshape.getStyle();
                                            rectangles_Drawed = false;
                    reloadWorkspace();
          
      
                }
            });
            rightPane.getChildren().removeAll(rightPane.getChildren());
            rightPane.getChildren().addAll(group_for_shape);
            // groups.add(group_for_shape);
            reloadWorkspace();
        });

        //ellipse button even handler
        ellipseButton.setOnAction(e -> {
            WhichShap = ellipseButton;
            ellipseIsBeingDrawed = false;
            // group_for_shape=new Group();
            rightPane.setOnMousePressed((MouseEvent event) -> {
                if (ellipseIsBeingDrawed == false) {
                    newEllipse = new Ellipse();
                    newEllipse.setFill(FillColor);
                    newEllipse.setStroke(OutLineColor);
                    newEllipse.setStrokeType(StrokeType.OUTSIDE);
                    newEllipse.setStrokeWidth(outlineJSlider.getValue());
                    starting_point_x = event.getX();
                    starting_point_y = event.getY();
                    group_for_shape.getChildren().add(newEllipse);
                    ellipseIsBeingDrawed = true;
                }
            });

            rightPane.setOnMouseDragged((MouseEvent event) -> {
                if (ellipseIsBeingDrawed == true) {
                    double current_ending_point_x = event.getX();
                    double current_ending_point_y = event.getY();
                    adjust_ellipse_properties(starting_point_x, starting_point_y, current_ending_point_x, current_ending_point_y, newEllipse);
                }
            });

            rightPane.setOnMouseReleased((MouseEvent event) -> {
                if (ellipseIsBeingDrawed == true) {
                    thisshape = new shape(newEllipse);
                    shapes.add(0, thisshape);
//                if(selectedShap!=null){
//                selectedShap.loseStyle();}
//                selectedShap=thisshape;
//                electedShapReNow();
//                thisshape.getStyle();
                    ellipseIsBeingDrawed = false;
                    reloadWorkspace();
                }
                reloadWorkspace();
            });
            //groups.add(group_for_shape);
                  rightPane.getChildren().removeAll(rightPane.getChildren());
            rightPane.getChildren().addAll(group_for_shape);

        });

        selectionToolButton.setOnAction(e -> {
            WhichShap = null;
            boolean movingShap = false;
       rightPane.setOnMouseReleased(null);
//        rightPane.setOnMouseDragged(null);
            rightPane.setOnMouseClicked((MouseEvent event) -> {
                x = event.getX();
                y = event.getY();
                Point2D p = new Point2D(x, y);
                for (shape shape : shapes) {
                    if (shape.e != null) {
                        if (shape.e.contains(p)) {
                            if (selectedShap != null) {
                                selectedShap.loseStyle();
                            }
                            selectedShap = shape;
                            electedShapReNow();
                            selectedShap.getStyle();
                            break;
                        }
                    } else {
                        if (shape.r.contains(p)) {
                            if (selectedShap != null) {
                                selectedShap.loseStyle();
                            }
                            selectedShap = shape;
                            electedShapReNow();
                            selectedShap.getStyle();
                            break;
                        }
                    }
                }
                reloadWorkspace();
            });
            rightPane.setOnMouseDragged(l -> {
//                if (l.get < 0) {
//                    scale = -0.1;
//                } else {
//                    scale = 0.1;
//                }

                if (selectedShap != null) {
//                    bx=l.getX();
//                    by=l.getY();
                   if (selectedShap.e != null) {
                       bx= selectedShap.e.getCenterX();
                       by= selectedShap.e.getCenterY();       
                       selectedShap.e.setTranslateX(l.getX()-bx);
                       selectedShap.e.setTranslateY(l.getY()-by);              
                } 
                   else {    
                       bx= selectedShap.r.getX();
                       by= selectedShap.r.getY(); 
                       selectedShap.r.setTranslateX(l.getX()-bx);
                       selectedShap.r.setTranslateY(l.getY()-by);
                }
                }
            });

        });

        moveToFront.setOnAction(e -> {
            if (selectedShap != null) {
                if (selectedShap.e != null) {
                    selectedShap.e.toFront();
                } else {
                    selectedShap.r.toFront();
                }
            }
        });

        moveToBack.setOnAction(e -> {
            if (selectedShap != null) {
                if (selectedShap.e != null) {
                    selectedShap.e.toBack();
                } else {
                    selectedShap.r.toBack();
                }
            }
        });

        removeButton.setOnAction(e -> {
            if (selectedShap != null) {
                if (selectedShap.e != null) {
                    //workspace.getChildren().remove(selectedShap.e);
                    group_for_shape.getChildren().remove(selectedShap.e);
                } else {
                    group_for_shape.getChildren().remove(selectedShap.r);
                }
                shapes.remove(selectedShap);
                //System.out.print(shapes.size());
                selectedShap = null;
            }
        });
        reloadWorkspace();
        workspaceActivated = false;
    }

    public void electedShapReNow() {
        if (selectedShap != null) {
            if (selectedShap.e != null) {
                FillColorPicker.setValue((Color) selectedShap.e.getFill());
                outlineJSlider.setValue(selectedShap.e.getStrokeWidth());
                OutLineColorPicker.setValue((Color) selectedShap.e.getStroke());
//                 selectedShap.e.setFill(FillColorPicker.getValue());
//                 selectedShap.e.setStrokeWidth(outlineJSlider.getValue());
//                 selectedShap.e.setStroke( OutLineColorPicker.getValue());
            } else {
                FillColorPicker.setValue((Color) selectedShap.r.getFill());
                outlineJSlider.setValue(selectedShap.r.getStrokeWidth());
                OutLineColorPicker.setValue((Color) selectedShap.r.getStroke());
//                 selectedShap.r.setFill(FillColorPicker.getValue());
//                 selectedShap.r.setStrokeWidth(outlineJSlider.getValue());
//                 selectedShap.r.setStroke( OutLineColorPicker.getValue());
            }
        }
    }

    /**
     * This function specifies the CSS style classes for all the UI components
     * known at the time the workspace is initially constructed. Note that the
     * tag editor controls are added and removed dynamicaly as the application
     * runs so they will have their style setup separately.
     */
    @Override
    public void initStyle() {
	// NOTE THAT EACH CLASS SHOULD CORRESPOND TO
        // A STYLE CLASS SPECIFIED IN THIS APPLICATION'S
        // CSS FILE
        //Tool bar pane 
        editToolBar.getStyleClass().add(BORDERED_PANE);
        move_up_down_Pane.getStyleClass().add(BORDERED_PANE);
        backgroundColorPane.getStyleClass().add(BORDERED_PANE);
        fillColorPane.getStyleClass().add(BORDERED_PANE);
        cameraPane.getStyleClass().add(BORDERED_PANE);
        outlineColorPane.getStyleClass().add(BORDERED_PANE);
        outlineThicknessPane.getStyleClass().add(BORDERED_PANE);
        rightPane.getStyleClass().add(RENDERCANVAS);
    }

    /**
     * This function reloads all the controls for editing tag attributes into
     * the workspace.
     */
    @Override
    public void reloadWorkspace() {
        if (selectedShap != null) {
            moveToFront.setDisable(false);
            moveToBack.setDisable(false);
            removeButton.setDisable(false);
            //selectedShap.getStyle();} 
        } else {
            removeButton.setDisable(true);
            moveToFront.setDisable(true);
            moveToBack.setDisable(true);
        }
        if (WhichShap == null) {
            rectangleButton.setDisable(false);
            ellipseButton.setDisable(false);

        } else if (WhichShap == rectangleButton) {
            rectangleButton.setDisable(true);
            ellipseButton.setDisable(false);

        } else {
            rectangleButton.setDisable(false);
            ellipseButton.setDisable(true);
        }

    }

    void adjust_rectangle_properties(double StartingX, double StartY, double endX, double endY, Rectangle rectangle) {
        rectangle.setX(StartingX);
        rectangle.setY(StartY);
        rectangle.setWidth(endX - StartingX);
        rectangle.setHeight(endY - StartY);
        if (rectangle.getWidth() < 0) {
            rectangle.setWidth(-rectangle.getWidth());
            rectangle.setX(rectangle.getX() - rectangle.getWidth());
        }
        if (rectangle.getHeight() < 0) {
            rectangle.setHeight(-rectangle.getHeight());
            rectangle.setY(rectangle.getY() - rectangle.getHeight());
        }
    }

    void adjust_ellipse_properties(double startX, double startY, double endX, double endY, Ellipse ellipse) {
        ellipse.setCenterX(startX);
        ellipse.setCenterY(startY);
        ellipse.setRadiusX(endX - startX);
        ellipse.setRadiusY(endY - startY);

        if (ellipse.getRadiusX() < 0) {
            ellipse.setRadiusX(-ellipse.getRadiusX());
            ellipse.setCenterX(ellipse.getCenterX() - ellipse.getRadiusX());
        }

        if (ellipse.getRadiusY() < 0) {
            ellipse.setRadiusY(-ellipse.getRadiusY());
            ellipse.setCenterY(ellipse.getCenterY() - ellipse.getRadiusY());
        }
    }

}
