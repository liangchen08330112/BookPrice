package com.example.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.adapter.FirstAdapter;
import com.example.adapter.FirstInfo;
import com.example.bookprice.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class First_Fragment extends Fragment {

    private ArrayList<FirstInfo> mlist;
    private FirstAdapter adapter;
    private First_Fragment mViewModel;
    int[] imgSrcs = {R.drawable.hong,R.drawable.sang,R.drawable.shui,R.drawable.wai,R.drawable.xiyou};
    String[] names = {"红楼梦","三国演义","水浒传","儒林外史","西游记"};
    String[] intros = {"￥59","￥69","￥79","￥89","￥99"};
    public static First_Fragment first_fragment() {
        return new First_Fragment();
    }
    ListView activity_lv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_first, container, false);
        activity_lv = view.findViewById(R.id.first_lv);//变量的关联
        return view;
    }

    public int getTv() {
        return 0;
    }


    public class List extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_blank_first);
            initView();

        }

        private void initView() {
            activity_lv = (ListView)findViewById(R.id.first_lv);
            java.util.List<Map<String, Object>> userList = new ArrayList<>();
            for(int i = 0;i<imgSrcs.length;i++){
                HashMap user = new HashMap();//每一个元素
                user.put("image",imgSrcs[i]);
                user.put("userName",names[i]);
                user.put("userMassage",intros[i]);
                userList.add(user);//将用户添加到列表中


            }


            SimpleAdapter adapter = new SimpleAdapter(this,
                    userList,
                    R.layout.one,
                    new String[] {"image","userName","userMassage"},
                    new int[]{R.id.image,R.id.name,R.id.price});

            activity_lv.setAdapter(adapter);
            /**
             list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map user = (Map) parent.getItemAtPosition(position);
            String name = (String) user.get(names);
            Toast.makeText(First_Fragment.this,"你选择的是："+name, Toast.LENGTH_SHORT).show();
            }
            });点击监听事件*/

        }
    }


}