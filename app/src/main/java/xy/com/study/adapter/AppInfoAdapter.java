package xy.com.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xy.com.study.R;
import xy.com.study.bean.PMAppInfo;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/5
 * desc    :
 * version :   1.0
 */

public class AppInfoAdapter extends BaseAdapter {

    private Context context;

    private List<PMAppInfo> pmAppInfos;

    public AppInfoAdapter(Context context, List<PMAppInfo> pmAppInfos) {
        this.context = context;
        this.pmAppInfos = pmAppInfos;
    }

    @Override
    public int getCount() {
        return pmAppInfos.size();
    }

    @Override
    public PMAppInfo getItem(int position) {
        return pmAppInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_app_info, null);
            viewHolder.imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
            viewHolder.textName = (TextView) convertView.findViewById(R.id.textName);
            viewHolder.textPackage = (TextView) convertView.findViewById(R.id.textPackage);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView1.setImageDrawable(pmAppInfos.get(position).getAppIcon());
        viewHolder.textName.setText(pmAppInfos.get(position).getAppLabel());
        viewHolder.textPackage.setText(pmAppInfos.get(position).getPkgName());

        return convertView;
    }

    public static final class ViewHolder {
        private ImageView imageView1;
        private TextView textName;
        private TextView textPackage;
    }
}
