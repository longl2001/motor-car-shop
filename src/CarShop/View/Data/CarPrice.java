package CarShop.View.Data;

import CarShop.Constants.StoreCarCollections;

import java.util.ArrayList;

public class CarPrice {
    private ArrayList<StoreCarCollections> list;
    public CarPrice(){
        list=new ArrayList<>();
    }
    public ArrayList<StoreCarCollections> getList(){
        return list;
    }
    public void addCar(StoreCarCollections car){
        list.add( car);
    }
    public void removeCar(StoreCarCollections car){
        list.remove(car);
    }
    public double getTotalPrice(){
        double totalPortfolio=0;
        for(StoreCarCollections car : list){
            totalPortfolio+=car.getPriceVal();
        }
        return totalPortfolio;
    }
    public double getEachTaxPrice(){
        double tolval=0;
        for(StoreCarCollections car : list){
            tolval=car.getPriceVal()*1.013;
        }
        return tolval;
    }
}
