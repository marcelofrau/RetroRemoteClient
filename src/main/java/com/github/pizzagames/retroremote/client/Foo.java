package com.github.pizzagames.retroremote.client;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.StringTokenizer;

public class Foo {

    public static void main(String[] args) throws ParseException {
        final String message = "{bar:'value', foo:1}," +
                "{xpto:'boo'}";

        final StringTokenizer jsonObjectTokenizer = new StringTokenizer(message, "{}");
        while (jsonObjectTokenizer.hasMoreElements()) {
            final String jsonObject = ((String) jsonObjectTokenizer.nextElement()).trim();

            if (jsonObject.length() == 0) {
                continue;
            }

            final StringTokenizer messageTokenizer = new StringTokenizer(jsonObject, ",");
            while (messageTokenizer.hasMoreElements()) {
                final String element = ((String) messageTokenizer.nextElement()).trim();
                final String[] keyValue = element.split(":");
                System.out.println("key:" + keyValue[0]);
                System.out.println("value:" + keyValue[1]);

            }

        }

    }
}
