package xy.com.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/5
 * desc    :
 * version :   1.0
 */

public class TestAdapter<String> extends MyBaseAdapter{


    private Context mContext;
    public TestAdapter(Context mContext, List datas, LayoutInflater inflater) {
        super(mContext, datas, inflater);
        this.mContext=mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;

        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1,null);
            viewHolder.textView=(TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText("");

        return super.getView(position, convertView, parent);
    }

    public static class ViewHolder{
        private TextView textView;
    }
}
