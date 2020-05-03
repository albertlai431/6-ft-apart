import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreText here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreText extends Actor
{
    /**
     * Act - do whatever the ScoreText wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage maskImg = new GreenfootImage("facemask.png");
    GreenfootImage sanitizerImg = new GreenfootImage("sanitizer.png");
    public static int score = 0;
    public static int masks = 3;
    public static int sanitizer = 3;
    GreenfootImage scoreBoard = new GreenfootImage(175,20);
    GreenfootImage scoreImg = new GreenfootImage("Score: "+score+" M x"+masks+" S x"+sanitizer, 20, Color.BLACK,new Color(213,172, 11));
    public ScoreText(){
        scoreBoard.setColor(new Color(213,172, 11));
        score =0;
        masks = 3;
        sanitizer = 3;
        scoreBoard.fill();
        setImage(scoreBoard);
    }
    public int getMasks(){
        return masks;
    }
    public int getSanitizer(){
        return sanitizer;
    }
    public void addMask(int num){
        masks+=num;
        update();
    }
    public void addSanitizer(int num){
        sanitizer+=num;
        update();
    }
    public static int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
        update();
    }
    public void addScore(int num){
        score+=num;
        update();
    }
    public void update() 
    {
        //constantly updates the score and masks and sanitizers
        scoreBoard.fill();
        scoreImg = new GreenfootImage("Score: "+score, 20, Color.BLACK,new Color(213,172, 11));
        scoreBoard.drawImage(scoreImg,0,0);
        scoreBoard.drawImage(maskImg,scoreImg.getWidth(),0);
        scoreBoard.drawImage(new GreenfootImage(" x"+masks, 20, Color.BLACK,new Color(213,172, 11)),scoreImg.getWidth()+maskImg.getWidth(),0);
        scoreBoard.drawImage(sanitizerImg,scoreImg.getWidth()+maskImg.getWidth()+30,0);
        scoreBoard.drawImage(new GreenfootImage(" x"+sanitizer, 20, Color.BLACK,new Color(213,172, 11)),scoreImg.getWidth()+maskImg.getWidth()+30+sanitizerImg.getWidth(),0);
        setImage(scoreBoard);
    }    
}
