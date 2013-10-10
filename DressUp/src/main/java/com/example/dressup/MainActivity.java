package com.example.dressup;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import it.sephiroth.android.library.util.v11.MultiChoiceModeListener;
import it.sephiroth.android.library.widget.HListView;

public class MainActivity extends Activity {

    ArrayList<Dress> dresses = new ArrayList<Dress>();
    int[] imageIDs = {
            R.drawable.lime,
            R.drawable.grayblue,
            R.drawable.pink,
            R.drawable.viol
    };
    CustomAdapter customAdapter;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    private String[] mPlanetTitles;
    String[] names = { "abc","defg" };
    Map<Integer, Integer> currentConsequence = new HashMap<Integer, Integer>();
    HListView listView;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    //TestAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        LayoutInflater ltInflater = getLayoutInflater();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setAdapter(adapter);

        //currentConsequence.addAll(imageIDs);
        currentConsequence.put((Integer)imageIDs[0],(Integer)0);
        currentConsequence.put((Integer)imageIDs[1],(Integer)2);
        currentConsequence.put((Integer)imageIDs[2],(Integer)3);
        currentConsequence.put((Integer)imageIDs[3],(Integer)1);
        fillData();
        customAdapter = new CustomAdapter(ltInflater, dresses);

        ListView lvMain = (ListView) findViewById(R.id.list);
        lvMain.setAdapter(customAdapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showImage((int) id);
            }
        });


        //mAdapter = new TestAdapter( this, R.layout.test_item_1, android.R.id.text1, items );
        listView.setHeaderDividersEnabled( true );
        listView.setFooterDividersEnabled( true );

        if( listView.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE_MODAL ) {
            listView.setMultiChoiceModeListener((MultiChoiceModeListener) this);
        }


        listView.setAdapter( customAdapter );
        listView = (HListView) findViewById( R.id.hListView1 );
    }

    void fillData() {

        for (int i = 0; i < 4; i++) {
            dresses.add(new Dress("dress " + i, imageIDs[i]));
        }
    }


    private LayerDrawable createBmp(int id) {
        BitmapDrawable d[];
        if(currentConsequence.containsKey((Integer)imageIDs[id])){
            for (Map.Entry<Integer, Integer> entry : currentConsequence.entrySet()) {
                if(entry.getValue()>currentConsequence.get((Integer) imageIDs[id])) {
                    entry.setValue((Integer) ((int) entry.getValue() - 1));
                }
            }
            currentConsequence.remove((Integer) imageIDs[id]);
            d=new BitmapDrawable[currentConsequence.size()];
        }
        else{
            d=new BitmapDrawable[currentConsequence.size()+1];
            currentConsequence.put((Integer) imageIDs[id],(Integer) (currentConsequence.size()));
        }
        for (Map.Entry<Integer, Integer> entry : currentConsequence.entrySet())
        {
            d[(int)entry.getValue()] = new BitmapDrawable(getResources(), ((BitmapDrawable) getResources().getDrawable((int)entry.getKey())).getBitmap());
            d[(int)entry.getValue()].setGravity(Gravity.CENTER);
            Log.d(entry.getKey().toString(),entry.getValue().toString());
        }

       // Drawable drawableArray[] = new Drawable[]{d[0], d[1], d[2]};
        LayerDrawable layerDraw = new LayerDrawable(d);
      //  layerDraw.setLayerInset(0,10,10,10,10);
      //  layerDraw.setLayerInset(1, 15, 15, 0, 0);//set offset of 2 layer
      //  layerDraw.setLayerInset(2, 40, 40, 0, 0);//set offset for third layer
//        layerDraw.setLayerInset(3, 40, 50, 0, 0);
        return layerDraw;
    }

    void showImage(int id) {
       /* LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        item.setLayoutParams(vp);*/
        ImageView image = (ImageView) findViewById(R.id.dresses);
        LayerDrawable ld = createBmp(id);
        image.setImageDrawable(ld);
    }

    void changeOrder(int id, LayerDrawable ld) {
        ;//Drawable drawableArray[]= new Drawable[]{d[1],d[2],d[3],d[4]};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void selectItem(int position) {
        Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();

// Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}

