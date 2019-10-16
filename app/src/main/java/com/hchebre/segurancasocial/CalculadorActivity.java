package com.hchebre.segurancasocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hchebre.segurancasocial.model.Contribuicao;

public class CalculadorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResultado;
    private EditText textSalarioMensal;
    private Spinner spinnerTinan;
    private Spinner spinnerFulan;
    private Button btnCalcula;
    private String mSalario,mTinanSerbisu, mFulanSerbisu;

    private LinearLayout linearLayoutResultado,calculadorMainLayout;

    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculador);

        getReferences();

        initializaSpinner(this.spinnerTinan,R.array.tinan);
        initializaSpinner(this.spinnerFulan,R.array.fulan);
        this.btnCalcula.setOnClickListener(this);
        this.calculadorMainLayout.setOnClickListener(this);


    }

    private void getReferences() {
        this.textSalarioMensal = findViewById(R.id.etSalarioMensal);
        this.btnCalcula = findViewById(R.id.btnCalculaValor);
        this.spinnerTinan = findViewById(R.id.spinnerTinan);
        this.spinnerFulan = findViewById(R.id.spinnerFulan);
        this.linearLayoutResultado = findViewById(R.id.linearLayoutResultado);
        this.tvResultado = findViewById(R.id.tvResultado);
        this.calculadorMainLayout = findViewById(R.id.calculadorMainLayout);

    }

    private void initializaSpinner(Spinner spinner,int id_array){
         this.adapter = ArrayAdapter.createFromResource(this,id_array,R.layout.dropdown_item);
         this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner.setAdapter(this.adapter);
    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnCalculaValor){
            getInputValues();
            if (isSalarioOrTinanSerbisuEmpty()) return;
            showResult();
        }

        this.hideKeyboard(view);
    }



    private void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    private void showResult() {
        Contribuicao contribuicao = new Contribuicao(this.mSalario,this.mFulanSerbisu,this.mTinanSerbisu);
        this.linearLayoutResultado.setVisibility(View.VISIBLE);
        this.tvResultado.setText(contribuicao.toString());
        this.textSalarioMensal.setText("");
    }

    private boolean isSalarioOrTinanSerbisuEmpty() {
        if(TextUtils.isEmpty(mSalario)){
            Toast.makeText(this, "Preenche Salário Mensal!", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(this.mTinanSerbisu.isEmpty()){
            Toast.makeText(this, "Favor hili Opsaun ba durasaun tinan Serbisu!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void getInputValues() {
        this.mSalario = this.textSalarioMensal.getText().toString();
        this.mTinanSerbisu = this.spinnerTinan.getSelectedItem().toString();
        this.mFulanSerbisu = this.spinnerFulan.getSelectedItem().toString();
    }


}
