package com.cpetsolut.com.voliveassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class SaveDetailsAdapter extends BaseAdapter {

    private ArrayList<DetailsModel> appItmList;
    private Activity activity;
    private LayoutInflater inflater;
    private TextView date,orderid,orderstatus,paidamount,productid,ordertitle,ratting;
    private ImageView imageView;
    Bitmap bitmap;

    public SaveDetailsAdapter(Activity activity, ArrayList<DetailsModel> articleList) {
        this.activity=activity;
        this.appItmList=articleList;
    }

    @Override
    public int getCount() {
        return appItmList.size();
    }

    @Override
    public Object getItem(int i) {
        return appItmList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater==null)
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            convertView=inflater.inflate(R.layout.list_item_style,null);
        if (position % 2 == 1) {
            convertView.setBackground(ContextCompat.getDrawable(activity, R.drawable.orangeedittextbox));
        } else {
            convertView.setBackground(ContextCompat.getDrawable(activity, R.drawable.lightgrayedittextbox));
        }
        date=(TextView)convertView.findViewById(R.id.date);
        orderid=(TextView)convertView.findViewById(R.id.orderid);
        orderstatus=(TextView)convertView.findViewById(R.id.orderstatus);
        paidamount=(TextView)convertView.findViewById(R.id.paidamount);
        productid=(TextView)convertView.findViewById(R.id.productid);
        ordertitle=(TextView)convertView.findViewById(R.id.ordertitle);
        ratting=(TextView)convertView.findViewById(R.id.ratting);
        imageView=(ImageView)convertView.findViewById(R.id.imageview);

        DetailsModel savedartilcemodel=appItmList.get(position);
        date.setText(savedartilcemodel.getAdded_on());
        orderid.setText(" Order ID  :  "+savedartilcemodel.getOrder_id());
        orderstatus.setText(" Order Status  :  "+savedartilcemodel.getOrder_status());
        paidamount.setText(" Paid Amount  :  "+savedartilcemodel.getPaid_amount());
        productid.setText(" Product ID  :  "+savedartilcemodel.getProduct_id());
        ordertitle.setText(" Order Title  :  "+savedartilcemodel.getOrder_title());
        ratting.setText(" Ratting  :  "+savedartilcemodel.getRating());
       // new GetImageFromURL(imageView).execute("http://volive.in/dresscode_new/"+savedartilcemodel.getImage());
        Picasso.with(activity).load("http://volive.in/dresscode_new/"+savedartilcemodel.getImage()).into(imageView);
        return convertView;
    }

    public class GetImageFromURL extends AsyncTask<String,Void, Bitmap> {

        ImageView imgView;

        public GetImageFromURL(ImageView imgv) {
            this.imgView=imgv;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay=url[0];
            bitmap=null;
            try{
                InputStream ist=new java.net.URL(urldisplay).openStream();
                bitmap= BitmapFactory.decodeStream(ist);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
        }
    }

}
