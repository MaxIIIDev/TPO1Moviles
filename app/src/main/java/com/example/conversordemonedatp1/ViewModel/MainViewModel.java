package com.example.conversordemonedatp1.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.conversordemonedatp1.Model.ConversorService;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Double> resultadoDolares, resultadoEuros; /* Mutable que permite devolver la conversion  */
    private MutableLiveData<Boolean> isDolar; /* Mutable que refleja el radio button seleccionado  */
    private MutableLiveData<String> errorMessage; /* Mutable que permite mostrar mensajes de error  */
    private Double tasa; /* Variable que permite setear la tasa de cambio  */
    private ConversorService conversorService ; /* Servicio que permite realizar la conversion  */


    public MainViewModel(@NonNull Application application) {
        super(application);
        conversorService = new ConversorService();
    }

    public LiveData<Double> getResultadoDolares() {
        if (resultadoDolares == null) {
            resultadoDolares = new MutableLiveData<>();
        }
        return resultadoDolares;
    }
    public void setResultadoDolares(Double resultadoDolares){
        this.resultadoDolares.setValue(resultadoDolares);
    }
    public LiveData<Double> getResultadoEuros() {
        if (resultadoEuros == null) {
            resultadoEuros = new MutableLiveData<>();
        }
        return resultadoEuros;
    }
    public void setResultadoEuros(Double resultadoEuros){
        this.resultadoEuros.setValue(resultadoEuros);
    }
    public LiveData<Boolean> getIsDolar() {
        if (isDolar == null) {
            isDolar = new MutableLiveData<>();
            isDolar.setValue(true);
        }
        return isDolar;
    }
    public void setIsDolar(boolean isDolar){
        this.isDolar.setValue(isDolar);
    }
    public LiveData<String> getErrorMessage() {
        if (errorMessage == null) {
            errorMessage = new MutableLiveData<>();
        }
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage.setValue(errorMessage);
    }
    /* Metodo que setea la tasa de cambio  */
    public void setTasa(String tasa){
        if(!tasa.isEmpty() && Double.parseDouble(tasa) > 0){
            this.tasa = Double.parseDouble(tasa);
        }else{
            this.setErrorMessage("Debe ingresar un valor positivo");
        }
        Log.d("TASA", "setTasa: " + this.tasa);
    }
   /* Metodo que realiza la logica de conversion de moneda  */
    public void convertir(String eu,String dolar){
        if(tasa == null){
            this.setErrorMessage("Debe ingresar una tasa de cambio");
            return;
        }
        if(isDolar.getValue() && !dolar.isEmpty()){
            this.resultadoDolares.setValue(conversorService.conversorDeMoneda(Double.parseDouble(dolar),tasa));
            return;
        }else if(!isDolar.getValue() && !eu.isEmpty()) {
            this.resultadoEuros.setValue(conversorService.conversorDeMoneda(Double.parseDouble(eu),tasa));
            return;
        }
        this.setErrorMessage("Debe ingresar un valor");
    }

}
