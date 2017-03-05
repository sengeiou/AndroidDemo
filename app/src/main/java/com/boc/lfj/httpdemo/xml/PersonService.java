package com.boc.lfj.httpdemo.xml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/5.
 */

public class PersonService {
    public static String PERSON = "person";
    public static String NAME = "name";
    public static String AGE = "age";

    public static List<Person> getPersonXml(InputStream xml) throws Exception {
        List<Person> persons = null;
        Person person = null;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(xml, "UTF-8");
        int enevt = pullParser.getEventType();
        while (enevt != XmlPullParser.END_DOCUMENT) {
            switch (enevt) {
                case XmlPullParser.START_DOCUMENT:
                    persons = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    if (PERSON.equals(pullParser.getName())) {
                        int id = Integer.parseInt(pullParser.getAttributeValue(0));
                        person = new Person();
                        person.setId(id);
                    }
                    if (NAME.equals(pullParser.getName())) {
                        String name = pullParser.nextText();
                        person.setName(name);
                    }
                    if (AGE.equals(pullParser.getName())) {
                        int age = Integer.parseInt(pullParser.nextText());
                        person.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (PERSON.equals(pullParser.getName())) {
                        persons.add(person);
                        person = null;
                    }
                    break;
            }
            enevt = pullParser.next();
        }
        return persons;
    }
}
