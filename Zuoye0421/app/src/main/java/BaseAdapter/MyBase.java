package BaseAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.zuoye0421.R;

import java.util.List;

/**
 * Created by lenovo on 2017/4/21.
 */

public class MyBase extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public MyBase(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v999;
        if (convertView == null) {
            convertView=convertView.inflate(mContext, R.layout.listvv,null);
            v999=new ViewHolder();
            v999.tv1= (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(v999);
        }else {
            v999= (ViewHolder) convertView.getTag();
        }
        v999.tv1.setText(mList.get(position));
        return convertView;
    }
    class ViewHolder{
        private TextView tv1;
    }
}
