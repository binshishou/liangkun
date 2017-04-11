package Baseadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import Bens.Myben;
import liangkun.bawei.com.liangkun.R;

/**
 * date:2017/4/7.
 * author:梁坤 lenovo
 * 类描述：
 */

public class Mybaseadadpter extends BaseAdapter {
    private Context context;
    private List<Myben.ListBean> mlist;
    public Mybaseadadpter(Context context, List<Myben.ListBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }
    @Override
    public int getCount() {
        return mlist.size();
    }



    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int positin, View contentview, ViewGroup viewGroup) {

                ViewHolder holder;
                if(contentview == null){
                    contentview = contentview.inflate(context, R.layout.item,null);
                    holder = new ViewHolder();
                    holder.tv1 = (TextView) contentview.findViewById(R.id.textview1);
                    holder.tv2 = (TextView) contentview.findViewById(R.id.textview2);
                }else{
                    holder = (ViewHolder) contentview.getTag();
                }
                holder.tv1.setText(mlist.get(positin).getSite_name());
                holder.tv2.setText(mlist.get(positin).getAddress());


                return contentview;
    }
    class  ViewHolder{
        private TextView tv1 , tv2;
    }

}
