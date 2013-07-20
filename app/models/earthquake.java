package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class earthquake extends Model
{
    public String eqid;
    
    public String magnitude;
    
    public String lng;
    
    public String src;
    
    public String datetime;
    
    public String depth;
    
    public String lat;
}