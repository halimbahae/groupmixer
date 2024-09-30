package  ma.zyn.app.dao.criteria.core.group;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class GroupCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String size;
    private String sizeMin;
    private String sizeMax;
    private String maxStudents;
    private String maxStudentsMin;
    private String maxStudentsMax;
    private String changeInterval;
    private String changeIntervalLike;



    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }

    public String getSize(){
        return this.size;
    }
    public void setSize(String size){
        this.size = size;
    }   
    public String getSizeMin(){
        return this.sizeMin;
    }
    public void setSizeMin(String sizeMin){
        this.sizeMin = sizeMin;
    }
    public String getSizeMax(){
        return this.sizeMax;
    }
    public void setSizeMax(String sizeMax){
        this.sizeMax = sizeMax;
    }
      
    public String getMaxStudents(){
        return this.maxStudents;
    }
    public void setMaxStudents(String maxStudents){
        this.maxStudents = maxStudents;
    }   
    public String getMaxStudentsMin(){
        return this.maxStudentsMin;
    }
    public void setMaxStudentsMin(String maxStudentsMin){
        this.maxStudentsMin = maxStudentsMin;
    }
    public String getMaxStudentsMax(){
        return this.maxStudentsMax;
    }
    public void setMaxStudentsMax(String maxStudentsMax){
        this.maxStudentsMax = maxStudentsMax;
    }
      
    public String getChangeInterval(){
        return this.changeInterval;
    }
    public void setChangeInterval(String changeInterval){
        this.changeInterval = changeInterval;
    }
    public String getChangeIntervalLike(){
        return this.changeIntervalLike;
    }
    public void setChangeIntervalLike(String changeIntervalLike){
        this.changeIntervalLike = changeIntervalLike;
    }


}
