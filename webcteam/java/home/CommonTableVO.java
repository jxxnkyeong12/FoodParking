package home;

import java.sql.Date;

public class CommonTableVO {
   private int cord_grp, star_code;
   private Date com_writedate;

   
   
public Date getCom_writedate() {
	return com_writedate;
}

public void setCom_writedate(Date com_writedate) {
	this.com_writedate = com_writedate;
}

public int getCord_grp() {
	return cord_grp;
}

public void setCord_grp(int cord_grp) {
	this.cord_grp = cord_grp;
}

public int getStar_code() {
	return star_code;
}

public void setStar_code(int star_code) {
	this.star_code = star_code;
}
   
   
	
	
}
