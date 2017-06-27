package com.sda.servlets.todo;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by RENT on 2017-06-12.
 *
 * klasa z uzytecznimi funkcjami
 */


public class TodoUtil {

    //sprawdza, czy w przekazanym requescie sa wszystkie parametry potrzebne to
    //stworzenia obiektu typu TodoModel
    public static boolean isWriteRequest(HttpServletRequest req) {
        //mapa parametrów
        Map<String, String[]> parameterMap = req.getParameterMap();

/*          pierwotna wersja
        boolean hasName = parameterMap.containsKey("name");
        boolean hasDescription = parameterMap.containsKey("description");
        //boolean hasChecked = parameterMap.containsKey("checked"); odrzucamy, jesli nie przyjdzie, to znaczy, ze user nie zaznaczyl
        boolean hasPriority = parameterMap.containsKey("priority");
        boolean hasDate = parameterMap.containsKey("date");

        return hasName && hasDescription && hasPriority && hasDate;
        */

        return exists("name", parameterMap) &&
                exists("description", parameterMap) &&
                exists("priority", parameterMap) &&
                exists("date", parameterMap);
    }

    //funkcja pomocnicza do sprawdzenia, czy parametr z mapy istnieje i czy nie jest pusty
    //do sprawdzenia tej drugiej rzeczy korzystamy z zewnetrznej biblioteki StringUtils
    //zaimportowanej w pom.xml
    private static boolean exists(String param, Map<String, String[]> map) {
        return map.containsKey(param) && StringUtils.isNotEmpty(map.get(param)[0]);
    }

}
