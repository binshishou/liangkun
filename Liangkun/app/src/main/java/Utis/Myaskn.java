package Utis;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import Baseadapter.Mybaseadadpter;
import Bens.Myben;

/**
 * date:2017/4/8.
 * author:梁坤 lenovo
 * 类描述：
 */

public class Myaskn extends AsyncTask<String,Integer,String> {

    private Context context;
    private ListView listview;
    private Mybaseadadpter mybaseadadpter;

    public Myaskn(Context context, ListView listview) {
        this.context = context;
        this.listview = listview;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream boa = new ByteArrayOutputStream();
                byte[] byt=new byte[1024];
                int len=0;
                while ((len=inputStream.read(byt))!=-1){
                    boa.write(byt,0,len);
                }
                boa.close();
                inputStream.close();
                return boa.toString("UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Gson gson = new Gson();
        Myben myben = gson.fromJson(s,Myben.class);
        final List<Myben.ListBean> list = myben.getList();
        mybaseadadpter = new Mybaseadadpter(context,list);
        listview.setAdapter(mybaseadadpter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"点击的是："+list.get(position).getId(),Toast.LENGTH_SHORT).show();
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                mybaseadadpter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }
}
