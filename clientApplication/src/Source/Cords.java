package Source;

public class Cords {
    private int corXp1;
    private int corYp1;

    private int corXp2;
    private int corYp2;

    private int corXp3;
    private int corYp3;

    private int corXp4;
    private int corYp4;

    public Cords(int xp1,int yp1,int xp2,int yp2,int xp3,int yp3,int xp4,int yp4){
        this.corXp1 = xp1;
        this.corXp2 = xp2;
        this.corXp3 = xp3;
        this.corXp4 = xp4;

        this.corYp1 = yp1;
        this.corYp2 = yp2;
        this.corYp3 = yp3;
        this.corYp4 = yp4;
    }

    public Cords(){
        this.corXp1 = 0;
        this.corXp2 = 0;
        this.corXp3 = 0;
        this.corXp4 = 0;

        this.corYp1 = 0;
        this.corYp2 = 0;
        this.corYp3 = 0;
        this.corYp4 = 0;
    }

    public void setCorXp1(int cordX){
        this.corXp1 = cordX;
    }
    public void setCorYp1(int cordY){
        this.corYp1 = cordY;
    }

    public void setCorXp2(int cordX){
        this.corXp1 = cordX;
    }
    public void setCorYp2(int cordY){
        this.corYp1 = cordY;
    }

    public void setCorXp3(int cordX){
        this.corXp1 = cordX;
    }
    public void setCorYp3(int cordY){
        this.corYp1 = cordY;
    }

    public void setCorXp4(int cordX){
        this.corXp1 = cordX;
    }
    public void setCorYp4(int cordY){
        this.corYp1 = cordY;
    }

    public int getCorXp1(){
        return this.corXp1;
    }
    public int getCorYp1(){
        return this.corYp1;
    }

    public int getCorXp2(){
        return this.corXp2;
    }
    public int getCorYp2(){
        return this.corYp2;
    }

    public int getCorXp3(){
        return this.corXp3;
    }
    public int getCorYp3(){
        return this.corYp3;
    }

    public int getCorXp4(){
        return this.corXp4;
    }
    public int getCorYp4(){
        return this.corYp4;
    }
}
