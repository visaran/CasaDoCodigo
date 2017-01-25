package br.com.caelum.casadocodigo_vinicius.application;

import android.app.Application;

import br.com.caelum.casadocodigo_vinicius.component.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo_vinicius.component.DaggerCasaDoCodigoComponent;

/**
 * Created by android6587 on 25/01/17.
 */

public class CasaDoCodigoApplication extends Application {
    private CasaDoCodigoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerCasaDoCodigoComponent.builder().build();
    }

    public CasaDoCodigoComponent getComponent() {
        return component;
    }
}
