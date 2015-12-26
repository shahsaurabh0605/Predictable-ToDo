package com.example.good.todo_app;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity {

    final  List<Listitem> detaillist =new ArrayList<Listitem>();
    //final List<ToDoThing> TheList = new ArrayList<ToDoThing>();
    ListView listview;
    Spinner Mainspinner, Subspinner;
    TextView MainCat, SubCat;
    EditText adddetails;
    TextView priorenter,addhead;
    String[] SubCatOptions = {" ", "hey"};
    String MainTxt, SubTxt;
    int viewId_Delete = 0;

    TextView dateenter,timeenter;
    EditText date,time;
    private int mYear,mMonth,mDay,mHour,mMinute;

    TextView wantanalarm;Button notification;


    final String[] MainCatOptions = {" ","Appointments","Chores and Errands","Back to books","Shopping","Time for some fun!","Anything else?"};
    final String[] SubShop = {" ","Groceries","Medicines","Clothes and Accessories","Gift"};
    final String[] SubBook = {" ","Tests","Assignments","Library","Buy books"};
    final String[] SubAppoint = {" ","Doctor","Meeting","Get-together"};
    final String[] SubChores = {" ","Pay bills","Laundry","Cleaning","Run to the Gas Station"};
    final String[] SubFun = {" ","Travel","Sight-seeing","Restaurants","Amusement Parks"};
    final String[] SubElse = {" ","Hi","Hello"};

    RadioGroup radiopriority;
    RadioButton priorityset;

    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabs=(TabHost)findViewById(R.id.tabHost);
        tabs.setup();
//tab lists
        TabHost.TabSpec tabspec=tabs.newTabSpec("Lists");
        tabspec.setContent(R.id.lists);
        tabspec.setIndicator("Lists");
        tabs.addTab(tabspec);
//tab add
        tabspec=tabs.newTabSpec("Add");
        tabspec.setContent(R.id.add);
        tabspec.setIndicator("Make list");
        tabs.addTab(tabspec);

        listview=(ListView)findViewById(R.id.listView);


        //stuff in add..
        addhead=(TextView)findViewById(R.id.addhead);
        priorenter=(TextView)findViewById(R.id.prior);
        MainCat = (TextView)findViewById(R.id.textView);
        SubCat = (TextView)findViewById(R.id.textView2);
        Mainspinner = (Spinner)findViewById(R.id.Mainspinner);
        Subspinner = (Spinner)findViewById(R.id.Subspinner);
        adddetails=(EditText)findViewById(R.id.adddetails);
        Button btn=(Button)findViewById(R.id.addbutton);
//-----date-times
        dateenter=(TextView)findViewById(R.id.enterdate);
        timeenter=(TextView)findViewById(R.id.entertime);
        date=(EditText)findViewById(R.id.date);
        time=(EditText)findViewById(R.id.time);
//Spinners
        ArrayAdapter<String> MspinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainCatOptions);
        Mainspinner.setAdapter(MspinnerAdapter);
        Mainspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainTxt = MainCatOptions[position];
                        switch (position){
                            case 1: {SubCatOptions = SubAppoint;break;}
                            case 2: {SubCatOptions = SubChores;break;}
                            case 3: {SubCatOptions = SubBook;break;}
                            case 4: {SubCatOptions = SubShop;break;}
                            case 5: {SubCatOptions = SubFun;break;}
                            default: SubCatOptions = SubElse;
                        }

                        ArrayAdapter<String> SspinnerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,SubCatOptions);
                        Subspinner.setAdapter(SspinnerAdapter);
                        Subspinner.setEnabled(position != 0);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        ArrayAdapter<String> SspinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,SubCatOptions);
        Subspinner.setAdapter(SspinnerAdapter);
        Subspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        SubTxt = SubCatOptions[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
//-------
        //notifications---
        wantanalarm=(TextView)findViewById(R.id.wantanalarm);
        notification=(Button)findViewById(R.id.noti);

//--
        radiopriority=(RadioGroup)findViewById(R.id.radiogroup);//add setonCheckedChangeListner here if priority not changing..

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedid=radiopriority.getCheckedRadioButtonId();
                priorityset=(RadioButton)findViewById(selectedid);
                RadioButton high=(RadioButton)findViewById(R.id.radiohigh);
                RadioButton low=(RadioButton)findViewById(R.id.radiolow);
                if(i==0 || selectedid==low.getId()) {   //when priority low,add to last.
                    Listitem x = new Listitem(SubTxt, adddetails.getText().toString(),date.getText().toString(),time.getText().toString(), MainTxt);
                    detaillist.add(x);
                    //listseen.add(addname.getText().toString());
                }
                else{       //when priority high,add to first place.
                    //for(int k=0;k<i;k++){
                    //    detaillist[i]=detaillist[i-1];
                    //}
                    Listitem x =new Listitem(SubTxt, adddetails.getText().toString(),date.getText().toString(),time.getText().toString(),MainTxt);
                    detaillist.add(0,x);
                    //listseen.add(0,addname.getText().toString());
                }
                populatelist();
                i++;
                Toast.makeText(getApplicationContext(), "You've got something to do!", Toast.LENGTH_SHORT).show();
                adddetails.setText("");
                date.setText("");
                time.setText("");

            }
        });

//----date picker
        final Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(/*'this' here is not working*/this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        date.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dpd.show();
            }
        });
//-------
//----time picker----
        final Calendar ca= Calendar.getInstance();
        mHour=ca.get(Calendar.HOUR_OF_DAY);
        mMinute=ca.get(Calendar.MINUTE);

        final TimePickerDialog tpd=new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.setText(hourOfDay + ":" + minute);
            }
        },mHour,mMinute,false);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tpd.show();
            }

        });
//-------notification
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datetime=date+" "+time;
                setNotification(datetime,i);
            }
        });
        //    /----


        //registerForContextMenu(listview);
    }
    public void setNotification(String dateTimeStr,int pos){
        SimpleDateFormat formatToCompare = new SimpleDateFormat(
                "MM-dd-yyyy hh:mm");
        Date dateNotification = null;

        try {
            dateNotification = formatToCompare
                    .parse(dateTimeStr);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Intent intent = null;
        intent=new Intent(getApplicationContext(),TimeAlarm.class);
        intent.putExtra("NOTIFICATION","Time to complete your list"+detaillist.get(pos).getName().toString());
        intent.putExtra("ID", pos);
        intent.putExtra("LONG", dateNotification.getTime());

        PendingIntent sender = PendingIntent.getBroadcast(
                getApplicationContext(), pos,
                intent, 0);
        AlarmManager am = null;
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, dateNotification.getTime(),
                sender);
    }

    /*
        @Override
        public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
            super.onCreateContextMenu(menu, v, menuInfo);
            //add menu inflator and make context menu xml if this not working.
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle("Select the Action");
            menu.add(0, v.getId(), 0, "Delete");
            viewId_Delete = info.position;

        }

        @Override
        public boolean onContextItemSelected(MenuItem item){
            AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            int pos = viewId_Delete;
            try {

                if (item.getTitle().equals("Delete")) {

                    final Listitem current1 = detaillist.get(pos);
                    detaillist.remove(current1);
                    /*if(position==i-1)
                        detaillist[i-1]=null;
                    else {
                        int j;
                        for (j = position; j < i; j++) {
                            detaillist[j] = detaillist[j + 1];
                        }
                        detaillist[j]=null;
                    }
                    i--;**\

                    populatelist();
                    return true;
                } else {
                    return false;
                }
            }catch(Exception e){
                return true;
            }
        }
    */
    public void populatelist(){

        ArrayAdapter<Listitem> adapter = new ToDoListAdapter();
        listview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class ToDoListAdapter extends ArrayAdapter<Listitem> {
        public ToDoListAdapter() {
            super(MainActivity.this, R.layout.listview_item, detaillist);
        }


        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            final Listitem current = detaillist.get(position);

            TextView name = (TextView) view.findViewById(R.id.textView);
            name.setText(current.getName());

            TextView Des = (TextView) view.findViewById(R.id.textView2);
            Des.setText(current.getDetails());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,Listitemseen.class);
                    //try-(com.example.lenovo.finalapp.Listitemseen)

                    intent.putExtra("n", current.getName().toString());
                    intent.putExtra("det", current.getDetails().toString());
                    intent.putExtra("date", current.getDate().toString());
                    intent.putExtra("t", current.getTime().toString());

                    startActivity(intent);
                }


            });
            //registerForContextMenu(view);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                    popupMenu.inflate(R.menu.menu_main);
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.Delete) {
                                detaillist.remove(current);
                                ArrayAdapter<Listitem> adapter = new ToDoListAdapter();
                                listview.setAdapter(adapter);
                            }

                            else if (item.getItemId() == R.id.Edit) {
                                Toast.makeText(getApplicationContext(), "Editting",Toast.LENGTH_LONG).show();
                                // Intent i =  new Intent();
                                // startActivity(i);
                            }

                            return false;
                        }
                    });

                    return true;
                }
            });

            return view;
        }
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
