package br.mgobo.jsf.app.beans.mapper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMapper {

    public String asStringDate(Date d) {
        if (d != null) {
            String dAsString = d.toString();
            if(!dAsString.equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(d);
            }else{
                return null;
            }
        }
        return null;
    }

}
