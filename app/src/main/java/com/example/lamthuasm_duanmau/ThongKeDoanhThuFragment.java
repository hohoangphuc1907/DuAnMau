package com.example.lamthuasm_duanmau;

import static java.time.LocalDate.now;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lamthuasm_duanmau.DAO.ThongKeDao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThongKeDoanhThuFragment extends Fragment {

    EditText txtTu,txtToi;
    Button btnTinh;
    TextView txt;
    int doanhthu;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thongkedoanhthu_fragment,container,false);
        txtTu=view.findViewById(R.id.txtTuNgay);
        txtToi=view.findViewById(R.id.txtDenNgay);
        btnTinh=view.findViewById(R.id.btnTinh);
        txt=view.findViewById(R.id.txtTongTien);


        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tungay=  txtTu.getText().toString();;
                String denngay= txtToi.getText().toString(); ;
                doanhthu=new ThongKeDao(getContext()).getDoanhThu(tungay,denngay);
                txt.setText(doanhthu+"");
            }
        });
        txtTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog();
            }
        });
        txtToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog2();
            }
        });



        return view;

    }



    public void datePickerDialog(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        int month=month1;
        SimpleDateFormat sdf=new SimpleDateFormat("dd");
        int day=calendar.get(Integer.parseInt(sdf.format(Calendar.DATE)));
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtTu.setText(dayOfMonth+"/"+month+"/"+year);

            }
        },year,month,day);
        datePickerDialog.show();
    }
    public void datePickerDialog2(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        int month=month1;
        int day=calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtToi.setText(dayOfMonth+"/"+month+"/"+year);

            }
        },year,month,day);
        datePickerDialog.show();
    }
}
