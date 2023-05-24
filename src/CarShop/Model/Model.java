package CarShop.Model;

import CarShop.View.Animation.*;
import CarShop.View.Data.Account;
import CarShop.View.Data.CarPrice;
import CarShop.View.Data.GarageList;
import CarShop.View.ViewFactory;
import CarShop.View.transition;

public class Model {
    private static Model model;
    private final GarageList garageList;
    private final Account account;
    private final ViewFactory view;
    private final transition transition;
    private final CarPrice carPrice;
    private final PriceAnimation priceAnimation;
    private final LoginAnimation loginAnimation;
    private final MenuAnimation menuAnimation;
    private final StoreAnimation storeAnimation;
    private final CarsAnimation carsAnimation;
    private final GarageAnimation garageAnimation;
    private final DashboardAnimation dashboardAnimation;

    private Model() {
        this.garageList=new GarageList();
        this.account = new Account();
        this.carPrice=new CarPrice();
        this.view = new ViewFactory();
        this.transition=new transition();
        this.priceAnimation= new PriceAnimation();
        this.loginAnimation=new LoginAnimation();
        this.menuAnimation = new MenuAnimation();
        this.storeAnimation=new StoreAnimation();
        this.carsAnimation=new CarsAnimation();
        this.garageAnimation=new GarageAnimation();
        this.dashboardAnimation= new DashboardAnimation();
    }
    public static synchronized Model getInstance(){
        if(model==null){
            model=new Model();
        }
        return model;
    }
    public GarageList getGarageList(){return garageList;}
    public Account getAccount(){return account;}
    public CarPrice getCarPrice(){return carPrice;}
    public DashboardAnimation getDashboardAnimation(){return dashboardAnimation;}
    public ViewFactory getView() {
        return view;
    }
    public transition getTransition() {
        return transition;
    }
    public PriceAnimation getPriceAnimation(){return priceAnimation;}
    public LoginAnimation getLoginAnimation(){return loginAnimation;}
    public MenuAnimation getMenuAnimation(){return menuAnimation;}
    public StoreAnimation getStoreAnimation(){return storeAnimation;}
    public CarsAnimation getCarsAnimation(){return carsAnimation;}
    public GarageAnimation getGarageAnimation(){return garageAnimation;}

}
