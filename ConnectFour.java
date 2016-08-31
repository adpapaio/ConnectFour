/*
 *Title: Connect Four aka Captains Mistress
 *Author: Aristotelis Papaioannou
 *Date: 10/22/15
 * Description: Make the game Connect Four
 */
package connect.four; 

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ConnectFour extends Application
{
    
    private String whoseTurn = "Player 1";//Initialize who goes first
    private Cell[][] cell = new Cell[6][7];// Create and initialize cell
    private Label label = new Label(whoseTurn + "'s turn"); // Create and initialize a status label 

    @Override // Override the start method in the Application class

    public void start(Stage primaryStage) 
    {        
        label.setFont(new Font("Stencil", 32)); //change the labael font
        
        GridPane grid = new GridPane();  
        for (int i = 0; i < 6; i++) //6 rows
        {
            for (int j = 0; j < 7; j++) //7 columns
            {
                grid.add(cell[i][j] = new Cell(), j, i);   //make the cells and fill them with blanks
                
                if (i == 5)                                //The last row gets filled with 'o' so a chip can be placed
                { 
                    grid.add(cell[i][j] = new Cell('o'), j, i); 
                }
            }
        }
        grid.setStyle("-fx-background-color: #ffd700;");//making background color gold
    
        BorderPane border = new BorderPane(); //Initializing the new border
        border.setCenter(grid);             //center the grid
        border.setTop(label);               //set the label to display on top
        
        Scene scene = new Scene(border, 700, 600); //Initializing the new scene
        primaryStage.setResizable(true);            //making the window resizeable
        primaryStage.setTitle("Connect 4");        //Setting the Title
        primaryStage.setScene(scene); 
        primaryStage.show();                   // Display the primary stage
        
    }
    
    //Check to see if there is a tie Game
      public boolean tieGame()
    {
         for (int i = 0; i < 6; i++)//6 is rows
             for (int j = 0; j < 7; j++)//7 is columns
                 if (cell[i][j].getChip() == ' ' || cell[i][j].getChip() == 'o') 
                 {
                     return false; //No tie game return false
                 }
         
         return true;              //return true if there is a tie game
    }
    //Check to see if there is a winner
    public boolean winner()
    {
        // Checking for a winner
        for (int i = 0; i < 6; i++)
             for (int j = 0; j < 7; j++)
             {
                 //Horizontal Winner
                 if (j < 4 && cell[i][j].getChip() == 'r' && cell[i][j+1].getChip() == 'r'
                         && cell[i][j+2].getChip() == 'r' &&cell[i][j+3].getChip() == 'r')
                 {//j<4 keeps the if statement inbounds
                     return true; //Horizontal Red Win
                 }
                 else if(j < 4 && cell[i][j].getChip() == 'b' && cell[i][j+1].getChip() == 'b' 
                         && cell[i][j+2].getChip() == 'b' &&cell[i][j+3].getChip() == 'b')
                 {//j<4 keeps the if statement inbounds
                     return true; //Horizontal Black Win
                 }     
                 //Vertical Winner
                  if (i > 2 && cell[i][j].getChip() == 'r' && cell[i-1][j].getChip() == 'r' 
                         && cell[i-2][j].getChip() == 'r' && cell[i-3][j].getChip() == 'r')
                 {//i>2 keeps the if statement inbounds
                     return true;//Vertical Red Win
                 }
                 else if (i > 2 && cell[i][j].getChip() == 'b' && cell[i-1][j].getChip() == 'b'
                         && cell[i-2][j].getChip() == 'b' && cell[i-3][j].getChip() == 'b')
                 {//i>2 keeps the if statement inbounds
                     return true;//Vertical Black Win
                 }
                 //Diagonal Winner
                 else if (i > 2 && j < 4 && cell[i][j].getChip() == 'r' && cell[i-1][j+1].getChip() == 'r' 
                         && cell[i-2][j+2].getChip() == 'r' && cell[i-3][j+3].getChip() == 'r' ) 
                 {
                     return true;//Red Diagonal winner    (diagonal to the right)
                 }
                 else if (i > 2 && j < 4 && cell[i][j].getChip() == 'b' && cell[i-1][j+1].getChip() == 'b' 
                         && cell[i-2][j+2].getChip() == 'b' && cell[i-3][j+3].getChip() == 'b')
                 {
                     return true; //Black Diagonal Winner (diagonal to the right)
                 }
                  else if (i > 2 && j > 2 && cell[i][j].getChip() == 'r' && cell[i-1][j-1].getChip() == 'r' 
                         && cell[i-2][j-2].getChip() == 'r' && cell[i-3][j-3].getChip() == 'r')
                 {
                     return true; //red Diagonal Winner (diagonal to the left)
                 }
                  else if (i > 2 && j > 2 && cell[i][j].getChip() == 'b' && cell[i-1][j-1].getChip() == 'b' 
                         && cell[i-2][j-2].getChip() == 'b' && cell[i-3][j-3].getChip() == 'b')
                 {
                     return true; //Black Diagonal Winner (diagonal to the left)
                 }
                 
             }
        return false;
    }
    //  
    public class Cell extends Pane //Cell class which extends Pane
    {
        private char chip = ' ';    //what is in the grid
        public Cell() 
        { 
            setStyle("-fx-border-color: black");    //Grid color is black
            this.setPrefSize(2500, 2500); 
            this.setOnMouseClicked(e -> mouseClick());  //handle the mouseclick
        }
        
        public Cell (char chip) //sets the first row to be open for a chip
        {
            this.chip = chip;
            this.setPrefSize(2500, 2500); 
            if (chip == 'o') 
            { 
                this.setOnMouseClicked(e -> mouseClick()); 
            }  //handle the mouseclick
        }
       
            //mouseclick method
         public void mouseClick()
          {
            if(chip == 'o') //if the grid is o set a chip
            {
              setChip();    //calls the set chip method
              
              for(int i = 0; i < 6; i++)//for loop to make the above cell open for a chip
                  for(int j = 0; j < 7; j++)
                  {
                      if(i > 0 && (cell[i][j].getChip() == 'b' || cell[i][j].getChip() == 'r')
                              && cell[i-1][j].getChip() == ' ')
                      {
                          cell[i-1][j].chip = 'o'; //the cell above the current cell is now open for a chip
                      }
                  }
            if( winner() && whoseTurn != " ")//if there is a winnner
             {
                label.setText(whoseTurn + " has won the Game! CONGRATS!!"); 
                
                whoseTurn = " ";    //no one can place a chip any longer
             }
            else if(tieGame()) //if there is a tie game
             { 
                label.setTextFill(Color.BLACK);
                label.setText("Tie Game: No More Moves");
                whoseTurn = " ";
             }
            else    //change the turn
             {
                if(whoseTurn == "Player 1") //if it was player 1's turn change to player 2
                {
                    whoseTurn = "Player 2";
                    label.setTextFill(Color.RED);
                    label.setText(whoseTurn + "'s turn.");
                }
                else if (whoseTurn == "Player 2")   //player 2 to player 1
                {   
                    whoseTurn = "Player 1";
                    label.setTextFill(Color.BLACK);
                    label.setText(whoseTurn + "'s turn.");
                }
             }
            
           }
          }
         
         //getting chip
         public char getChip()
         {
             return chip;
         }
        //setting the chip  
        public void setChip()
        {
            
           if(whoseTurn == "Player 1") //black chip for player 1
           {
               Ellipse black = new Ellipse();
               black.centerXProperty().bind( this.widthProperty().divide(2));
               black.centerYProperty().bind( this.heightProperty().divide(2)); 
               black.radiusXProperty().bind( this.widthProperty().divide(2).subtract(10));
               black.radiusYProperty().bind( this.heightProperty().divide(2).subtract(10));  
               black.setFill(Color.BLACK); //fill color is black
               
               chip = 'b';                  //set the cell to have a 'b' for black
               getChildren().add(black); // Add the ellipse to the pane
           }
           else if(whoseTurn == "Player 2") //red chip for player 2
           {
              Ellipse red = new Ellipse();
               red.centerXProperty().bind( this.widthProperty().divide(2));
               red.centerYProperty().bind( this.heightProperty().divide(2)); 
               red.radiusXProperty().bind( this.widthProperty().divide(2).subtract(10));
               red.radiusYProperty().bind( this.heightProperty().divide(2).subtract(10));  
               red.setFill(Color.RED);//fill color is red
               
               chip = 'r';      //add 'r' to cell to represent red
               
               getChildren().add(red);//place red ellipse in
           }
        }
    }

public static void main(String[] args)
    {
        launch(args);   //launch the application
    }
}