package com.example.lamthuasm_duanmau;


import static java.time.LocalDate.now;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lamthuasm_duanmau.Adapter.PhieuMuonAdapter;
import com.example.lamthuasm_duanmau.Adapter.SpinnerMathuthuAdapter;
import com.example.lamthuasm_duanmau.Adapter.SpinnerTenSachAdapter;
import com.example.lamthuasm_duanmau.Adapter.SpinnerThanhVienAdapter;
import com.example.lamthuasm_duanmau.DAO.PhieuMuonDAO;
import com.example.lamthuasm_duanmau.DAO.SachDao;
import com.example.lamthuasm_duanmau.DAO.ThanhVienDao;
import com.example.lamthuasm_duanmau.DAO.ThuThuDao;
import com.example.lamthuasm_duanmau.Model.PhieuMuon;
import com.example.lamthuasm_duanmau.Model.Sach;
import com.example.lamthuasm_duanmau.Model.ThanhVien;
import com.example.lamthuasm_duanmau.Model.ThuThu;

import java.sql.Date;
import java.util.List;
import java.util.Random;

public class PhieuMuonActivity extends AppCompatActivity {
    List<PhieuMuon> list;
    ListView lvPhieuMuon;
    PhieuMuonAdapter adapter;
    int trangThai;
    Toolbar toolbar;

    ImageView imgThem;
    String selectedLop = null;
    int selectedsoLuong = 0;
    String selectedLopmathuthu = null;
    String tien=null;
    String selectedTenThanhVien = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_muon);
        lvPhieuMuon=findViewById(R.id.lvPhieuMuon);
        imgThem=findViewById(R.id.imgThemPhieuMuon);
        toolbar=(Toolbar) findViewById(R.id.toolbarPhieuMuon);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        list=new PhieuMuonDAO(this).get();
        adapter=new PhieuMuonAdapter(this,list);
        lvPhieuMuon.setAdapter(adapter);
        imgThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThemPhieuMuon();
            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomnagivation);
        bottomNavigationView.setSelectedItemId(R.id.bottomphieumuon);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottomphieumuon:

                        return true;

                    case R.id.bottomdanhsach:
                        startActivity(new Intent(getApplicationContext(),XemXoaPhieuDangKyActivity.class));
                        overridePendingTransition(0,0);
                       return true;

                }

                return false;
            }
        });


    }







    public void dialogSuaPhieuMuon(String maPM) {
        Dialog dialog = new Dialog(PhieuMuonActivity.this);
        dialog.setContentView(R.layout.dialog_suaphieumuon);
        dialog.setCanceledOnTouchOutside(false);
        CheckBox chkTrangThai=dialog.findViewById(R.id.chkTraSach);

        Button button=(Button) dialog.findViewById(R.id.btnThemSach);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (chkTrangThai.isChecked()){
                    trangThai=1;
                }else {
                    trangThai=0;
                }
                String ThanhVienMuon=selectedTenThanhVien;
                String tenSach=selectedLop;
                PhieuMuon phieuMuon=new PhieuMuon(maPM,null,ThanhVienMuon,tenSach,0,null,trangThai);
                PhieuMuonDAO dao=new PhieuMuonDAO(PhieuMuonActivity.this);
                dao.update(phieuMuon);
                list=new PhieuMuonDAO(PhieuMuonActivity.this).get();
                adapter=new PhieuMuonAdapter(PhieuMuonActivity.this,list);
                lvPhieuMuon.setAdapter(adapter);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DiaLogXoaPhieuMuon(String ten){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(PhieuMuonActivity.this);
        dialogXoa.setMessage("Bạn có muốn xóa sinh viên "+ten+" này không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PhieuMuonDAO dao=new PhieuMuonDAO(PhieuMuonActivity.this);
                dao.delete(ten);
                list=new PhieuMuonDAO(PhieuMuonActivity.this).get();
                adapter=new PhieuMuonAdapter(PhieuMuonActivity.this,list);
                lvPhieuMuon.setAdapter(adapter);

            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogXoa.show();

    }


    public void dialogThemPhieuMuon() {
        Dialog dialog = new Dialog(PhieuMuonActivity.this);
        dialog.setContentView(R.layout.dialog_phieumuon);
        dialog.setCanceledOnTouchOutside(false);
        Spinner spinnerThanhVienMuon=(Spinner) dialog.findViewById(R.id.spinnerTenThanhVien);
        Spinner spinnerTenSach=(Spinner) dialog.findViewById(R.id.spinnerTenSach);
        Spinner spinnermathuthu=(Spinner) dialog.findViewById(R.id.spinnermathuthu);
        TextView editTextGiaThue=(TextView) dialog.findViewById(R.id.edtGiaThueSachMuon);

        CheckBox chkTrangThai=dialog.findViewById(R.id.chkTraSach);

        Button button=(Button) dialog.findViewById(R.id.btnThemSach);

        List<Sach> sachList=(new SachDao(this)).getTenSach();
        SpinnerTenSachAdapter tenSachAdapter=new SpinnerTenSachAdapter(this,sachList);
        spinnerTenSach.setAdapter(tenSachAdapter);
        spinnerTenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sach tensach = (Sach) tenSachAdapter.getItem(position);
                selectedLop=tensach.getMaSach();
                tien=tensach.getGiaThue();
                editTextGiaThue.setText(tien);
                selectedsoLuong=tensach.getSoLuong();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLop=null;
            }
        });


        List<ThanhVien> thanhVienList=(new ThanhVienDao(this)).getTenThanhVien();
        SpinnerThanhVienAdapter spinnerThanhVienAdapter=new SpinnerThanhVienAdapter(this,thanhVienList);
        spinnerThanhVienMuon.setAdapter(spinnerThanhVienAdapter);
        spinnerThanhVienMuon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ThanhVien thanhVien = (ThanhVien) spinnerThanhVienAdapter.getItem(position);
                selectedTenThanhVien=thanhVien.getHoTen();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLop=null;
            }
        });


        List<ThuThu> thuThuList=(new ThuThuDao(this)).get();
        SpinnerMathuthuAdapter adapterthuthu=new SpinnerMathuthuAdapter(this,thuThuList);
        spinnermathuthu.setAdapter(adapterthuthu);
        spinnermathuthu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ThuThu thuthu = (ThuThu) adapterthuthu.getItem(position);
                selectedLopmathuthu=thuthu.getMaTT();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLop=null;
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (chkTrangThai.isChecked()){
                    trangThai=1;
                }else {
                    trangThai=0;
                }
                String ThanhVienMuon=selectedTenThanhVien;
                String tenSach=selectedLop;
                int giaThue=Integer.parseInt(tien);
                Random random=new Random();
                int number=random.nextInt(61);
                String mathuthu=selectedLopmathuthu;
                String maPhieuMuon=String.valueOf(number);
                Date ngaymuon= java.sql.Date.valueOf(String.valueOf(now()));
                Boolean check=new PhieuMuonDAO(getApplicationContext()).kiemtraphieumuon(ThanhVienMuon,tenSach);
                int demsoluong=new PhieuMuonDAO(getApplicationContext()).kiemtrasoluong(tenSach);
                if (check==true){
                    Toast.makeText(getApplicationContext(), "Thành viên đã mượn cuốn sách đó rồi", Toast.LENGTH_SHORT).show();
                }else if (demsoluong>selectedsoLuong){
                    Toast.makeText(getApplicationContext(), "Sách này đã hết hàng", Toast.LENGTH_SHORT).show();

                } else{
                    PhieuMuon phieuMuon=new PhieuMuon(maPhieuMuon,mathuthu,ThanhVienMuon,tenSach,giaThue,ngaymuon,trangThai);
                    PhieuMuonDAO dao=new PhieuMuonDAO(PhieuMuonActivity.this);
                    dao.insert(phieuMuon);
                    list=new PhieuMuonDAO(PhieuMuonActivity.this).get();
                    adapter=new PhieuMuonAdapter(PhieuMuonActivity.this,list);
                    lvPhieuMuon.setAdapter(adapter);
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }




}