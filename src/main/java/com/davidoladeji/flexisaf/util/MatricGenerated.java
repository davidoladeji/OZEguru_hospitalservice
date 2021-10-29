package com.davidoladeji.flexisaf.util;

import com.davidoladeji.flexisaf.model.Student;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

public class MatricGenerated implements ValueGenerator<String> {



    public static final String VALUE_PREFIX_DEFAULT = "FLEXISAF/";
    public static final String NUMBER_FORMAT_DEFAULT = "%06d";


    public String generateValue(Session session, Object object)
    {
        Student student = (Student) object;

        return VALUE_PREFIX_DEFAULT + String.format(NUMBER_FORMAT_DEFAULT, student.getId());
    }


}
