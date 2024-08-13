package com.example.test;

import static com.example.test.SoruCevap.*;

import android.app.AlertDialog;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, test {

    TextView txtToplamSoru;
    TextView txtSoru;
    Button btnCevapA,btnCevapB,btnCevapC,btnCevapD;
    Button btnGonder;
    int puan=0;
    int toplamSoru= question.length;
    int sorulacak=0;
    String secilenCevap="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtToplamSoru=findViewById(R.id.toplam_soru);
        txtSoru=findViewById(R.id.soru);
        btnCevapA=findViewById(R.id.cevap_A);
        btnCevapB=findViewById(R.id.cevap_B);
        btnCevapC=findViewById(R.id.cevap_C);
        btnCevapD=findViewById(R.id.cevap_D);
        btnGonder=findViewById(R.id.gonder);

        btnCevapA.setOnClickListener(this);
        btnCevapB.setOnClickListener(this);
        btnCevapC.setOnClickListener(this);
        btnCevapD.setOnClickListener(this);
        btnGonder.setOnClickListener(this);

        txtToplamSoru.setText("Total question: "+toplamSoru);

        SoruGetir();

    }

    private void SoruGetir() {

        if(sorulacak==toplamSoru){
            TestBitir();
            return;
        }

        txtSoru.setText(question[sorulacak]);
        btnCevapA.setText(answer[sorulacak][0]);
        btnCevapB.setText(answer[sorulacak][1]);
        btnCevapC.setText(answer[sorulacak][2]);
        btnCevapD.setText(answer[sorulacak][3]);
    }

    private void TestBitir() {
        String durum="";
        if(puan>toplamSoru*0.6){
            durum="Congratulations";
        }
        else{
            durum=" You failed ";
        }

        new AlertDialog.Builder(this)
                .setTitle(durum)
                .setMessage(toplamSoru+ " You answered  "+puan+" number of questions correctly.")
                .setPositiveButton("AGAIN",((dialog, which) -> Tekrarla()))
                .setCancelable(false)
                .show();


    }

    private void Tekrarla() {
        puan=0;
        sorulacak=0;
        SoruGetir();
    }

    @Override
    public void onClick(View v) {

        btnCevapA.setBackgroundColor(Color.WHITE);
        btnCevapB.setBackgroundColor(Color.WHITE);
        btnCevapC.setBackgroundColor(Color.WHITE);
        btnCevapD.setBackgroundColor(Color.WHITE);

        Button btnTiklanan=(Button) v;
        if(btnTiklanan.getId()==R.id.gonder){

            if(secilenCevap.equals(trueAnswer[sorulacak])){
                puan++;
            }
            else{
                Toast.makeText(this,"Wrong answer :(",Toast.LENGTH_SHORT).show();
            }
            sorulacak++;
            SoruGetir();

        }else{
            //Seceneklerden biri tıklanmış demektir.
            secilenCevap=btnTiklanan.getText().toString();
            btnTiklanan.setBackgroundColor(Color.GREEN);

        }
    }
}
