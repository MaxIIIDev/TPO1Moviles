package com.example.conversordemonedatp1.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.conversordemonedatp1.R;
import com.example.conversordemonedatp1.ViewModel.MainViewModel;
import com.example.conversordemonedatp1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Inicializacion del Binding*/
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        EdgeToEdge.enable(this);
        /* Inicializacion de la instancia de View Model*/
        mainViewModel =  ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
        /* Toast dinamico para observar mensajes de error del ViewModel */
        mainViewModel.getErrorMessage().observe(this, errorMessage -> {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        });
        /* ====================================================== */
        /* Observador para el cambio de resultados dentro de cada EditText correspondiente USD | EUR */
        mainViewModel.getResultadoDolares().observe(this, resultadoDolares -> {
            mainBinding.editTextDolar.setText(resultadoDolares.toString());
        });
        mainViewModel.getResultadoEuros().observe(this, resultadoEuros -> {
            mainBinding.editTextEuro.setText(resultadoEuros.toString());
        });
        /* ====================================================== */
        /* Evento click para cambiar la tasa de cambio  */
        mainBinding.ButtonTasaDeCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tasaDeCambio = mainBinding.editTextTasaDeCambio.getText().toString();
                mainViewModel.setTasa(tasaDeCambio);
            }
        });
        /* Inicializacion del radio button por defecto  */
        mainBinding.radioButtonDolares.setChecked(true);
        /* Observador para el cambio de radio button donde deshabilita el correspondiente editText */
        mainViewModel.getIsDolar().observe(this, isDolar -> {
            mainBinding.editTextEuro.setEnabled(isDolar);
            mainBinding.editTextDolar.setEnabled(!isDolar);
            mainBinding.editTextEuro.setText("");
            mainBinding.editTextDolar.setText("");
        });
        /* Es un evento que permite setear el radio button seleccionado para identificar el tipo de conversion  */
        mainBinding.radioGroup.setOnCheckedChangeListener((radioGroup, i)->{
            mainViewModel.setIsDolar( i == mainBinding.radioButtonDolares.getId());
        });
        /* Evento click para convertir  */
        mainBinding.ButtonConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.convertir(mainBinding.editTextDolar.getText().toString(),mainBinding.editTextEuro.getText().toString());
            }
        });

    }
}