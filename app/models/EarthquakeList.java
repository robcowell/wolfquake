package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class EarthquakeList extends Model
{
    	@Transient
	public List<earthquake> earthquakes;
}