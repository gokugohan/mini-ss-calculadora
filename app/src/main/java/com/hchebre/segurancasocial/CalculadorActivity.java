package com.hchebre.segurancasocial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CalculadorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResultado;
    private EditText textSalarioMensal;
    private Spinner spinnerTinan;
    private Spinner spinnerFulan;
    private Button btnCalcula;
    private String mSalario,mTinanSerbisu, mFulanSerbisu;

    private LinearLayout linearLayoutResultado;

    private final Double TAXA_DE_SEGURANCA_SOCIAL = 0.1;
    private final Double TAXA_DE_SEGURANCA_SOCIAL_TRABALHADOR = 0.04;
    private final Double TAXA_DE_SEGURANCA_SOCIAL_EMPREGADOR = 0.06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculador);

        this.textSalarioMensal = findViewById(R.id.etSalarioMensal);

        this.btnCalcula = findViewById(R.id.btnCalculaValor);
        this.spinnerTinan = findViewById(R.id.spinnerTinan);
        this.spinnerFulan = findViewById(R.id.spinnerFulan);

        this.linearLayoutResultado = findViewById(R.id.linearLayoutResultado);
        this.tvResultado = findViewById(R.id.tvResultado);

        initializaSpinner(this.spinnerTinan,R.array.tinan);

        initializaSpinner(this.spinnerFulan,R.array.fulan);

        this.btnCalcula.setOnClickListener(this);

    }

    private void initializaSpinner(Spinner spinner,int id_array){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this,id_array,R.layout.dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        int totalFulan = 0,fulan=0, tinan=0;

        this.mSalario = this.textSalarioMensal.getText().toString();
        this.mTinanSerbisu = this.spinnerTinan.getSelectedItem().toString();
        this.mFulanSerbisu = this.spinnerFulan.getSelectedItem().toString();

        if(TextUtils.isEmpty(mSalario)){
            Toast.makeText(this, "Preenche Salário Mensal!", Toast.LENGTH_SHORT).show();
        }

        if(this.mTinanSerbisu.isEmpty()){
            Toast.makeText(this, "Favor hili Opsaun ba durasaun tinan Serbisu!", Toast.LENGTH_SHORT).show();

        }

        Double salario = Double.parseDouble(this.mSalario);

        tinan = Integer.parseInt(this.mTinanSerbisu);


        int totalFulanIhaTinan = tinan*12;

        totalFulan = totalFulanIhaTinan;
        if(!TextUtils.isEmpty(this.mFulanSerbisu)){
            fulan = Integer.parseInt(this.mFulanSerbisu);
            totalFulan += fulan;
        }


        Double valorNebeTrabalhadorContribui = salario * TAXA_DE_SEGURANCA_SOCIAL_TRABALHADOR;
        Double valorNebeEmpregadorContribui = salario * TAXA_DE_SEGURANCA_SOCIAL_EMPREGADOR;

        Double valorTotalMensal = valorNebeTrabalhadorContribui + valorNebeEmpregadorContribui;

        Double valorTotal = valorTotalMensal * totalFulan;

        this.linearLayoutResultado.setVisibility(View.VISIBLE);

        StringBuilder sb = new StringBuilder();
        sb.append("Salário Mensal: ").append(this.mSalario).append("\n")
                .append("Valor Nebe Trabalhador Contribui Mensalmente: ")
                .append(valorNebeTrabalhadorContribui).append("\n")
                .append("Valor Nebe Empregador Contribui ba Trabalhador Mensalmente: ")
                .append(valorNebeEmpregadorContribui).append("\n")
                .append("Total Valor Mensal Nebe Contribui: ").append(valorTotalMensal).append("\n")
                .append("Total Tinan Serbisu: ").append(tinan).append("\n")
                .append("Total Fulan Serbisu: ").append(fulan).append(" mes(es)").append("\n")
                .append("Total Tinan e Fulan Serbisu: ").append(totalFulan).append(" mes(es)").append("\n")
                .append("Total Valor Contribui Até Agora: ").append(valorTotal);

        this.tvResultado.setText(sb.toString());


    }
}
