package com.twentyfive.signeehlistview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Button Press, LongPress;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

    public void init() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem("Tắt nguồn", "a"));
        items.add(new MenuItem("Wifi", "a"));
        items.add(new MenuItem("Cập nhật", "a"));
        items.add(new MenuItem("Thiết đặt lại", "a"));
        items.add(new MenuItem("Khởi động", "a"));

        com.twentyfive.hlistview.HListView listView = (com.twentyfive.hlistview.HListView) findViewById(R.id.list_view);
        listView.setAdapter(new MyListAdapter(items));
        listView.setSelection(0);
        //
        Press = (Button) findViewById(R.id.press);
        LongPress = (Button) findViewById(R.id.longPress);
        //
        Press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setSelection(i);
                i++;
                if (i > items.size() - 1) {
                    i = 0;
                }

                Log.d("signee", "press: "+ i);
            }
        });

        LongPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int a = listView.getSelectedItemPosition();

                Toast.makeText(MainActivity.this, "Choose" + items.get(i).Name + a, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class MenuItem{
        private String Name;
        private String Image;

        public MenuItem(){

        }

        public MenuItem(String Name, String Image){
            this.Name = Name;
            this.Image = Image;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }
    }

    private class MyListAdapter extends  BaseAdapter
    {
        ArrayList<MenuItem> menuItems;

        public MyListAdapter(ArrayList<MenuItem> menuItems){
            this.menuItems = menuItems;
        }

        @Override
        public int getCount() {
            return menuItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (view == null)
            {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

                ImageView photo = (ImageView)view.findViewById(R.id.imageMenu);
                TextView name = (TextView)view.findViewById(R.id.txt);

                view.setTag(new ViewHolder2(photo, name));
            }

            ViewHolder2 holder = (ViewHolder2)view.getTag();

            holder.getPhoto().setBackgroundResource(R.drawable.ic_smiley_success);
            holder.getName().setText(menuItems.get(position).Name);

            return view;
        }
    }

   public class ViewHolder2
    {
        private ImageView Photo;
        private TextView Name;

        public ViewHolder2(){}

        public ViewHolder2(ImageView Photo, TextView Name){
            this.Photo = Photo;
            this.Name = Name;
        }

        public ImageView getPhoto() {
            return Photo;
        }

        public void setPhoto(ImageView photo) {
            Photo = photo;
        }

        public TextView getName() {
            return Name;
        }

        public void setName(TextView name) {
            Name = name;
        }
    }
}