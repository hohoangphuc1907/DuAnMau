package com.example.lamthuasm_duanmau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.Adapter.LoaiSachAdapter;
import com.example.lamthuasm_duanmau.DAO.LoaiSachDao;
import com.example.lamthuasm_duanmau.Model.LoaiSach;

import java.util.List;

public class LoaiSachActivity extends AppCompatActivity {
    List<LoaiSach> list;
    ListView lvLoaiSach;
    LoaiSachAdapter adapter;
    ImageView imgThem;
    String id="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_sach);
        lvLoaiSach=findViewById(R.id.lvLoaiSach);
        imgThem=findViewById(R.id.imgThemLoaiSach);
        list=new LoaiSachDao(this).get();
        adapter=new LoaiSachAdapter(this,list);
        lvLoaiSach.setAdapter(adapter);
        imgThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemLoaiSach();
            }
        });


    }
    public void DiaLogXoaMaLoaiSach(String ten){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(LoaiSachActivity.this);
        dialogXoa.setMessage("Bạn có muốn xóa sinh viên "+ten+" này không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoaiSachDao dao=new LoaiSachDao(LoaiSachActivity.this);
                dao.delete(ten);
                list=new LoaiSachDao(LoaiSachActivity.this).get();
                adapter=new LoaiSachAdapter(LoaiSachActivity.this,list);
                lvLoaiSach.setAdapter(adapter);


            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogXoa.show();

    }
    public void dialogSuaTenLoaiSach(String maLoai) {
        Dialog dialog = new Dialog(LoaiSachActivity.this);
        dialog.setContentView(R.layout.dialog_sualoaisach);

        dialog.setCanceledOnTouchOutside(false);
        EditText editTextTenLoai=(EditText) dialog.findViewById(R.id.edtTenLoaiSach);


        Button button=(Button) dialog.findViewById(R.id.btnThemLoaiSach);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai=editTextTenLoai.getText().toString();
                LoaiSach loaiSach=new LoaiSach(maLoai,tenLoai);
                LoaiSachDao dao=new LoaiSachDao(LoaiSachActivity.this);
                dao.update(loaiSach);
                list=new LoaiSachDao(LoaiSachActivity.this).get();
                adapter=new LoaiSachAdapter(LoaiSachActivity.this,list);
                lvLoaiSach.setAdapter(adapter);
            }
        });

        dialog.show();
    }


    public void dialogThemLoaiSach() {
        Dialog dialog = new Dialog(LoaiSachActivity.this);
        dialog.setContentView(R.layout.dialog_themloaisach);

        dialog.setCanceledOnTouchOutside(false);
        EditText editTextMaLoai=(EditText) dialog.findViewById(R.id.edtMaLoaiSach);
        EditText editTextTenLoai=(EditText) dialog.findViewById(R.id.edtTenLoaiSach);


        Button button=(Button) dialog.findViewById(R.id.btnThemLoaiSach);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLoai=editTextMaLoai.getText().toString();
                String tenLoai=editTextTenLoai.getText().toString();
                Boolean check=new LoaiSachDao(getApplicationContext()).tenloaisach(tenLoai);
                Boolean checkma=new LoaiSachDao(getApplicationContext()).maloaisach(maLoai);
                if (maLoai.isEmpty()||tenLoai.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }else if (check==true || checkma==true){
                    Toast.makeText(getApplicationContext(), "Đã có tên loại sách hoặc mã loại đó r", Toast.LENGTH_SHORT).show();
                }else {
                    LoaiSach loaiSach = new LoaiSach(maLoai, tenLoai);
                    LoaiSachDao dao = new LoaiSachDao(LoaiSachActivity.this);
                    dao.insert(loaiSach);
                    list = new LoaiSachDao(LoaiSachActivity.this).get();
                    adapter = new LoaiSachAdapter(LoaiSachActivity.this, list);
                    lvLoaiSach.setAdapter(adapter);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


}