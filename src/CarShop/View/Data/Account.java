package CarShop.View.Data;

import CarShop.Constants.GarageCarCollections;
import CarShop.Model.Model;

public class Account {
    private int accountID;
    private double balance;
    private double portfolioVal;
    private double balPercentage;
    public Account(){

    }

    public int getAccountID() {
        return accountID;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public void setBalPercentage(double balPercentage){this.balPercentage=balPercentage;}
    public double getBalPercentage(){return balPercentage;}
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getBalance(){
        return balance;
    }
    public double getPortfolioVal(){return portfolioVal;}
    public void setPortfolioVal(double portfolioVal){this.portfolioVal=portfolioVal;}
    public void subtractBalance(double taxPrice){
        balance-= taxPrice;
    }
    public void addBalance(double carPrice){
        balance+=carPrice;
    }
    public void addPortfolioBal(double amount){
        portfolioVal+=amount;
    }
    public void subtractPortfolioBal(double amount){
        portfolioVal-=amount;
    }
    public void updatePortfolio(){
        double total=0;
        for(GarageCarCollections car: Model.getInstance().getGarageList().getList()){
            total+=car.getPrice();
        }
        portfolioVal=total;
    }

}
