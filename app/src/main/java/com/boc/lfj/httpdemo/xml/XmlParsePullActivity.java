package com.boc.lfj.httpdemo.xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.boc.lfj.httpdemo.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XmlParsePullActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parse_pull);
        textView = (TextView) findViewById(R.id.textView);

        xmlParse();
    }

    public void xmlParse(){
        try {
            InputStream xml = getResources().getAssets().open("person.xml");
            List<Person> persons = PersonService.getPersonXml(xml);
            for (Person person : persons){
                String text = textView.getText()+"\n id:"+person.getId()+"\n name:"+person.getName()+"\n age:"+person.getAge();
                textView.setText(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
