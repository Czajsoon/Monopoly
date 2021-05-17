package Source;



public class Properties {
    private String Name;
    private int paymentForStay;
    private int IDproperty;
    private int buyCost;
    private String countryName;
    private int ownerID;
    private int propertyLVL;
    private int lvl0;
    private int lvl1;
    private int lvl2;
    private int lvl3;
    private int lvl4;
    private int actualLvlProperty;

    public Properties(){
        this.IDproperty = 0;
        this.Name = " ";
        this.paymentForStay = 0;
        this.countryName = " ";
        this.buyCost = 0;
        this.ownerID = 0;
        this.propertyLVL = 0;
        this.lvl0 = 0;
        this.lvl1 = 0;
        this.lvl2 = 0;
        this.lvl3 = 0;
        this.lvl4 = 0;
        this.actualLvlProperty = 0;
    }

    public Properties(int IDproperty,int paymentForStay, String nameProperty,String countryName,int buyCost,int lvl1,int lvl2,int lvl3,int lvl4){
        this.IDproperty = IDproperty;
        this.Name = nameProperty;
        this.paymentForStay = paymentForStay;
        this.lvl0 = this.paymentForStay;
        this.countryName = countryName;
        this.buyCost = buyCost;
        this.ownerID = 0;
        this.propertyLVL = 0;
        this.lvl1 = lvl1;
        this.lvl2 = lvl2;
        this.lvl3 = lvl3;
        this.lvl4 = lvl4;
        this.actualLvlProperty = 0;
    }

    public int getHouseCost(){
        if(this.IDproperty>1 && this.IDproperty<11 && ifBuyableHouseproperty())
            return 100;
        else if(this.IDproperty>11 && this.IDproperty<21 && ifBuyableHouseproperty())
            return 200;
        else if(this.IDproperty>21 && this.IDproperty<31 && ifBuyableHouseproperty())
            return 300;
        else if(this.IDproperty>31 && this.IDproperty<=40 && ifBuyableHouseproperty())
            return 400;
        else
            return 0;
    }

    private boolean ifBuyableHouseproperty(){
        if(this.Name.startsWith("Linie") || this.Name.startsWith("Go")
                ||this.Name.startsWith("Elektrownia") || this.Name.startsWith("Wodo")
                || this.Name.startsWith("Red") || this.Name.startsWith("Blue")
                || this.Name.startsWith("Parking") || this.Name.startsWith("Wiezienie")
                || this.Name.startsWith("Darmowy") || this.Name.startsWith("Idziesz")
                || this.Name.startsWith("Podatek"))
            return false;
        else
            return true;
    }

    public void buildHouseOnProperty(){
        if(this.actualLvlProperty == 0){
            this.paymentForStay = this.lvl1;
            this.actualLvlProperty = 1;
        }
        else if(this.actualLvlProperty == 1){
            this.paymentForStay = this.lvl2;
            this.actualLvlProperty = 2;
        }
        else if(this.actualLvlProperty == 2){
            this.paymentForStay = this.lvl3;
            this.actualLvlProperty = 3;
        }
        else if(this.actualLvlProperty == 3){
            this.paymentForStay = this.lvl4;
            this.actualLvlProperty = 4;
        }
    }

    public void destroyHouses(){
        setPaymentForStay(this.lvl0);
        this.actualLvlProperty = 0;
    }

    public int getActualLvlProperty(){
        return actualLvlProperty;
    }

    public int getLvl1(){
        return this.lvl1;
    }

    public int getLvl2(){
        return this.lvl2;
    }

    public int getLvl3(){
        return this.lvl3;
    }

    public int getLvl4(){
        return this.lvl4;
    }

    public void setLvl1(int cash){
        this.lvl1 = cash;
    }

    public void setLvl2(int cash){
        this.lvl2 = cash;
    }

    public void setLvl3(int cash){
        this.lvl3 = cash;
    }

    public void setLvl4(int cash){
        this.lvl4 = cash;
    }

    public int getPropertyLVL(){
        return propertyLVL;
    }

    public String getNameProperty(){
        return this.Name;
    }

    public int getPaymentForStay(){
        return this.paymentForStay;
    }

    public int getIDproperty(){
        return this.IDproperty;
    }

    public int getBuyCost(){
        return this.buyCost;
    }

    public String getCountryName(){
        return this.countryName;
    }

    public int getOwnerID(){
        return this.ownerID;
    }

    public void setPropertyLVL(int lvlProperty){
        this.propertyLVL = lvlProperty;
    }

    public void setNameProperty(String nameProperty){
        this.Name = nameProperty;
    }

    public void setPaymentForStay(int paymentForStay){
        this.paymentForStay = paymentForStay;
    }

    public void setIDproperty(int IdPropertyrty){
        this.IDproperty = IdPropertyrty;
    }

    public void setBuyCost(int buyCost){
        this.buyCost = buyCost;
    }

    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    public void setOwnerID(int ownerID){
        this.ownerID = ownerID;
    }


}
