package entity;

public class Author {
    private int id;
    private  String name;
    private String contact;

    public  Author(){

    }

    public Author(int id,String name,String contact){
        this.id=id;
        this.name=name;
        this.contact=contact;
    }
    public int getId(){
        return id;
    }

    public void setId(){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name=name;
    }

    public String getContact(){
        return contact;
    }

    public void setContact(){
        this.contact=contact;
    }
}
