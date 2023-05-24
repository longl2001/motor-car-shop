package CarShop.Constants;

import javafx.scene.image.ImageView;

public enum CarPath {
    FRAME(0,null,null,null,null,null),
    TESLA_VER_1(1,"/Images/car/complete/Tesla/ModelY/white-vertical.png","/Images/car/complete/Tesla/ModelY/white.png","/Images/car/complete/Tesla/ModelY/white-horizontal.png"),
    TESLA_VER_2(51,"/Images/car/complete/Tesla/ModelS/red-vertical.png","/Images/car/complete/Tesla/ModelS/red.png","/Images/car/complete/Tesla/ModelS/red-horizontal.png"),
    TESLA_VER_3(101,"/Images/car/complete/Tesla/ModelX/white-vertical.png","/Images/car/complete/Tesla/ModelX/white-wing.png","/Images/car/complete/Tesla/ModelX/white-horizontal.png"),

    BUGATTI_VER_1(10,"/Images/car/complete/Bugatti/Veyron/blackOrage-vertical.png","/Images/car/complete/Bugatti/Veyron/orage.png","/Images/car/complete/Bugatti/Veyron/orage-horizontal.png"),
    BUGATTI_VER_2(60,"/Images/car/complete/Bugatti/Chiron/blue-vertical2.png","/Images/car/complete/Bugatti/Chiron/blue2.png","/Images/car/complete/Bugatti/Chiron/blue-horizontal2.png"),
    BUGATTI_VER_3(110,"/Images/car/complete/Bugatti/Mistral/white-vertical.png","/Images/car/complete/Bugatti/Mistral/white.png","/Images/car/complete/Bugatti/Mistral/white-horizontal.png"),

    ROLLS_ROYCE_VER_1(20,"/Images/car/complete/Rolls-Royce/phantom/blue-vertical.png","/Images/car/complete/Rolls-Royce/phantom/blue.png","/Images/car/complete/Rolls-Royce/phantom/blue-horizontal.png"),
    ROLLS_ROYCE_VER_2(70,"/Images/car/complete/Rolls-Royce/ghost/black-vertical.png","/Images/car/complete/Rolls-Royce/ghost/black.png","/Images/car/complete/Rolls-Royce/ghost/black-horizon.png"),
    ROLLS_ROYCE_VER_3(120,"/Images/car/complete/Rolls-Royce/culinan/red-vertical.png","/Images/car/complete/Rolls-Royce/culinan/red.png","/Images/car/complete/Rolls-Royce/culinan/red-horizontal.png");

    private final String verticalImg;
    private final String normalImg;
    private final String horizontalImg;
    private final int id;
    private ImageView frame1;
    private ImageView frame2;
    private CarPath(int id,String verticalImg,String normalImg,String horizontalImg, ImageView frame1,ImageView frame2){
        this(id,verticalImg,normalImg,horizontalImg);
        this.frame1=frame1;
        this.frame2=frame2;
    }
    private CarPath(int id,String verticalImg,String normalImg,String horizontalImg){
        this.id=id;
        this.verticalImg=verticalImg;
        this.normalImg=normalImg;
        this.horizontalImg=horizontalImg;
    }
    public int getId(){return id;}
    public String getVerticalImg(){
        return verticalImg;
    }
    public String getNormalImg(){return normalImg;}
    public String getHorizontalImg(){return horizontalImg;}
    public void setFrame(ImageView frame1,ImageView frame2){
        this.frame1=frame1;
        this.frame2=frame2;
    }
    public ImageView getFrame1(){
        return frame1;
    }
    public ImageView getFrame2(){
        return frame2;
    }


}
