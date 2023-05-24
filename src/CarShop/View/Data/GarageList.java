package CarShop.View.Data;

import CarShop.Constants.GarageCarCollections;

import java.util.ArrayList;

public class GarageList {
    private final ArrayList<GarageCarCollections> list;
    public GarageList(){
        list= new ArrayList<>();
    }
    public void addCar(int id){
        for(GarageCarCollections car:GarageCarCollections.values()){
            if(car.getId()==id){
                list.add(car);
            }
        }
    }
    public void removeCar(int id){
        for(GarageCarCollections car:GarageCarCollections.getSubset()){
            if(car.getId()==id){
                list.remove(car);
            }
        }
    }

    public ArrayList<GarageCarCollections> getList(){
        return list;
    }

}
