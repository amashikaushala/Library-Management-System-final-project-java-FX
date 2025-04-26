package entity.custom;

import entity.SuperEntity;

public class Author implements SuperEntity {
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

    public void setId(int anInt){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String string){
        this.name=name;
    }

    public String getContact(){
        return contact;
    }

    public void setContact(String string){
        this.contact=contact;
    }
}
