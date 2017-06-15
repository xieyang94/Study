package xy.com.cloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import xy.com.cloud.bean.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //第一：默认初始化
        Bmob.initialize(this,"b10a0ba509e858781fc3914d537b7a4c");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation().save();
        // 启动推送服务
        BmobPush.startWork(this);
    }

    public void onClick1(View view) {
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");

        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    toast("添加数据成功，返回objectId为："+objectId);
                }else{
                    toast("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    public void onClick2(View view) {
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.addWhereEqualTo("name","lucky");
        bmobQuery.findObjects(new FindListener<Person>(){

            @Override
            public void done(List<Person> list, BmobException e) {
                if(e==null){
                    toast("查询成功");
                }else{
                    toast("查询失败：" + e.getMessage());
                }
            }
        });

//
//        bmobQuery.getObject("81c773f957", new QueryListener<Person>() {
//            @Override
//            public void done(Person object,BmobException e) {
//                if(e==null){
//                    toast("查询成功");
//                }else{
//                    toast("查询失败：" + e.getMessage());
//                }
//            }
//        });
    }

    public void onClick3(View view) {
        //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
        final Person p2 = new Person();
        p2.setAddress("北京朝阳");
        p2.update("81c773f957", new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    toast("更新成功:"+p2.getUpdatedAt());
                }else{
                    toast("更新失败：" + e.getMessage());
                }
            }

        });
    }

    public void onClick4(View view) {
        final Person p2 = new Person();
        p2.setObjectId("81c773f957");
        p2.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    toast("删除成功:"+p2.getUpdatedAt());
                }else{
                    toast("删除失败：" + e.getMessage());
                }
            }

        });
    }

    private void toast(String value){
        Toast.makeText(this,value,Toast.LENGTH_LONG).show();
    }
}
