package xy.com.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/5
 * desc    :
 * version :   1.0
 */

public class MyBaseAdapter<T> extends BaseAdapter {

    //上下文
    private Context mContext;

    //数据源
    private List<T> datas;

    //
    private LayoutInflater inflater;

    public MyBaseAdapter(Context mContext, List<T> datas,LayoutInflater inflater) {
        this.mContext = mContext;
        this.datas = datas;
        this.inflater=inflater;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
