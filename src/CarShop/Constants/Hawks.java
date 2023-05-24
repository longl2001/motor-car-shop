package CarShop.Constants;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public enum Hawks {
    HAWK(null, null, null, null, null, null, null, null, null);
    public     HBox img1,img2,img3,img4,img5,img6;
    public    Label lbl1,lbl2,lbl3;
    private   Hawks (HBox img1, HBox img2, HBox img3, HBox img4, HBox img5, HBox img6, Label lbl1, Label lbl2,Label lbl3){
        this.img1=img1;
        this.img2=img2;
        this.img3=img3;
        this.img4=img4;
        this.img5=img5;
        this.img6=img6;
        this.lbl1=lbl1;
        this.lbl2=lbl2;
        this.lbl3=lbl3;
    }

    public HBox getImg1() {
        return img1;
    }

    public HBox getImg2() {
        return img2;
    }

    public HBox getImg3() {
        return img3;
    }

    public HBox getImg4() {
        return img4;
    }

    public HBox getImg5() {
        return img5;
    }

    public HBox getImg6() {
        return img6;
    }

    public Label getLbl1() {
        return lbl1;
    }

    public Label getLbl2() {
        return lbl2;
    }

    public Label getLbl3() {
        return lbl3;
    }

    public void setImg1(HBox img1) {
        this.img1 = img1;
    }

    public void setImg2(HBox img2) {
        this.img2 = img2;
    }

    public void setImg3(HBox img3) {
        this.img3 = img3;
    }

    public void setImg4(HBox img4) {
        this.img4 = img4;
    }

    public void setImg5(HBox img5) {
        this.img5 = img5;
    }

    public void setImg6(HBox img6) {
        this.img6 = img6;
    }

    public void setLbl1(Label lbl1) {
        this.lbl1 = lbl1;
    }

    public void setLbl2(Label lbl2) {
        this.lbl2 = lbl2;
    }

    public void setLbl3(Label lbl3) {
        this.lbl3 = lbl3;
    }
    public static void createHawks(HBox img1, HBox img2, HBox img3, HBox img4, HBox img5, HBox img6, Label lbl1, Label lbl2,Label lbl3){
        HAWK.setImg1(img1);
        HAWK.setImg2(img2);
        HAWK.setImg3(img3);
        HAWK.setImg4(img4);
        HAWK.setImg5(img5);
        HAWK.setImg6(img6);
        HAWK.setLbl1(lbl1);
        HAWK.setLbl2(lbl2);
        HAWK.setLbl3(lbl3);
    }
}
