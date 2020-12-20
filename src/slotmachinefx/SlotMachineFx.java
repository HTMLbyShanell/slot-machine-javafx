package slotmachinefx;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.shape.*;

public class SlotMachineFx extends Application {

   // LAYOUTS
   VBox col1,col2,col3;
   HBox row1,row2;

   // GUI COMPONENTS
   TextField txtAmt;
   int TotalAmt=0;
   ImageView imageView1,imageView2,imageView3; // TO STORE THREE DIFFERENT IMAGES
   Label lbWonSpin,lbWonTotal;

   public static void main(String[] args)
   {
       Application.launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
       primaryStage.setTitle(" SLOT MACHINE SIMULATOR ");

       GridPane gridPane = new GridPane(); //AS ROOT PANE
       gridPane.setPadding(new Insets(10, 50, 50, 50));

       gridPane.setHgap(10);
       gridPane.setVgap(12);

       row1=new HBox(10);
       row2=new HBox(10);

       /* ADDING TWO HBOX AS TWO ROW TO GRIDPANE */
       gridPane.add(row1, 0,0); // FIRST ROW
       gridPane.add(row2, 0,1); // SECOND ROW

       /* JUST A RECTANGLE TO SHOW INITIALY ON ROW1 BEFIRE SPINNING NOT STARTED */
       Rectangle rectangle = new Rectangle(10, 10, 180, 60);
       row1.getChildren().addAll(rectangle);
       rectangle.setFill(Color.GRAY);
       ///////////////////////////////////////////

       /* THREE VBOX AND THREE COLUMNS ADDED TO SECOND ROW */
       col1 = new VBox(10);
       col2 = new VBox(10);
       col3 = new VBox(10);

       col1.setPadding(new Insets(10, 10, 10, 10)); // SET PADDING FOR EACH COLUMN FOR EACH NODE DISPAY AS DISTANCE FROM OTHER COLUMN
       col2.setPadding(new Insets(10, 10, 10, 10));
       col3.setPadding(new Insets(10, 10, 10, 10));
       row2.getChildren().addAll(col1,col2,col3);   // ADDED ALL THREE COLUMN TO SECOND ROW

       /* GUI COMPONENT TO BE ADDED IN COLUMNS OF ROW2 */
       Button btn = new Button();
       btn.setText(" Spin ");

       Label lbAmt=new Label("Amount Entered ");
       txtAmt=new TextField();

       lbWonSpin=new Label("Won for this spin : $");
       lbWonTotal=new Label("Total won : $");

       Label lbMsg=new Label("Please Enter amount ! ");
       lbMsg.setVisible(false);

       col1.getChildren().addAll(lbAmt,txtAmt,lbMsg);
       col2.getChildren().addAll(btn);
       col3.getChildren().addAll(lbWonSpin,lbWonTotal);

       /* GRIDPANE ADDED TO SCENE */
       primaryStage.setScene(new Scene(gridPane, 550, 300)); //WIDTH HEIGHT OF MAIN SCREEN
       primaryStage.show();

       btn.setOnAction(new EventHandler<ActionEvent>() // BUTTON CLICK EVENT REGISTERED
       {
           @Override
           public void handle(ActionEvent event)
           {
               lbMsg.setVisible(false);
               try
               {
                   int spinAmt=Integer.parseInt(txtAmt.getText());
                   /*RANDOM NOS GENERATED AND PASSED TO FUNCTION ALONG WITH SPIN AMOUNT*/
                   /*1-13 RANDOM NO AS WEN HAVE TAKEN ONLY 13 IMAGES OF FRUITS */
                   DispalyCalculate(getRandomInteger(13,1),getRandomInteger(13,1),getRandomInteger(13,1),spinAmt);
                   //txtAmt.setText("");
               }
               catch(NumberFormatException e) // EXCEPTION OCCURS WHEN USUER NOT INPUT OR WRONG INPUT IN AMOUNT FIELD
               {
                   lbMsg.setVisible(true); // SO MESSAGE WILL SHOWN TO RE ENTER AMOUNT
               }

           }
       }); // END EVENT ACTION

   }// START ,METHOD ENDS

   /* FUNCTION TO ADD THREE IMAGES TO ROW1 AND CALCULATE AMOUNT BASES ON RANDOM VALUES */

   public void DispalyCalculate(int random1,int random2 , int random3,int spinAmt)
   {
       row1.getChildren().clear(); // CLEAR ALL PREV IMAGES
       //IMAGE 1
       String path= "/images/"+random1+".jpg";
       Image image = new Image(path);
       imageView1 = new ImageView(image);
       row1.getChildren().addAll(imageView1);

       //IMAGE 2
       path= "/images/"+random2+".jpg";
       image = new Image(path);
       imageView2 = new ImageView(image);
       row1.getChildren().addAll(imageView2);

       //IMAGE 3
       path= "/images/"+random3+".jpg";
       image = new Image(path);
       imageView3 = new ImageView(image);
       row1.getChildren().addAll(imageView3);

       /*SETTING ALL IMAGES DIMENSTION SAME*/
       imageView1.setFitHeight(60);
       imageView1.setFitWidth(60);
       imageView2.setFitHeight(60);
       imageView2.setFitWidth(60);
       imageView3.setFitHeight(60);
       imageView3.setFitWidth(60);

       /*CALCULATE WIN AMOUNT ON CHECKING RANDOM VALUES WHETHER SAME OR NOT*/
       if (random1== random2 && random1==random3)
       {
           spinAmt=spinAmt*3;
       }
       else if (random1== random2 || random1==random3 || random2==random3)
       {
           spinAmt=spinAmt*2;
       }
       else
           spinAmt=0; // NO VALUES ARE SAME

       TotalAmt=TotalAmt+spinAmt; // TOTAL AMOUNT FOR ALL SPINS

       lbWonSpin.setText("Won for this spin : $ " + spinAmt); // DISPLAY RESULT TO LABEL
       lbWonTotal.setText("Total won : $ "+ TotalAmt);

   }

   /*CALCULATE RANDOM NO. */
   public int getRandomInteger(int maximum, int minimum)
   {
       return ((int) (Math.random()*(maximum - minimum))) + minimum;
   }
}