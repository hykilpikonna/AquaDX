package icu.samnyan.aqua.sega.maimai2.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static java.util.Collections.*;

import java.util.ArrayList;

@Converter
public class IntegerListConverter implements AttributeConverter<List<Integer>, String> {
    private static final String SPLIT_CHAR = ";";
    
    @Override
    public String convertToDatabaseColumn(List<Integer> integerList) {
        if (integerList != null && !integerList.isEmpty()) {
            StringBuilder str  = new StringBuilder();
            Iterator<Integer> iter = integerList.iterator();
            while(iter.hasNext()) {
                str.append(iter.next());
                if(iter.hasNext()){
                str.append(SPLIT_CHAR);
                }
            }
            return str.toString();
        } else {
            return "";
        }
    }

    @Override
    public List<Integer> convertToEntityAttribute(String string) {
        if (string != null && !string.isEmpty()) {
            List<Integer> iList = new ArrayList<Integer>();
            for (String s : string.split(SPLIT_CHAR)) {
                iList.add(Integer.parseInt(s));
            }
            return iList;
        } else {
            return emptyList();
        }
    }
}