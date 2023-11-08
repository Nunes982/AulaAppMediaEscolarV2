package app.manager.aulaappmediaescolarv2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

/**
 * Este app é um exemplo do uso do layout RelativeLayout
 */
public class MainActivity extends AppCompatActivity {

    public static final String NOME_SHARED_PREF = "mediaEscolarPref";
    Button btnPrimeiroBimestre, btnSegundoBimestre, btnTerceiroBimestre, btnQuartoBimestre, btnResultadoFinal;

    boolean primeiroBimestre,
            segundoBimestre,
            terceiroBimestre,
            quartoBimestre;

    String situacaoPrimeiroBimestre, situacaoSegundoBimestre,
            situacaoTerceiroBimestre, situacaoQuartoBimestre;
    String materiaPrimeiroBimestre, materiaSegundoBimestre,
            materiaTerceiroBimestre, materiaQuartoBimestre;
    double notaProvaPrimeiroBimestre, notaProvaSegundoBimestre,
            notaProvaTerceiroBimestre, notaProvaQuartoBimestre;
    double notaTrabalhoPrimeiroBimestre, notaTrabalhoSegundoBimestre,
            notaTrabalhoTerceiroBimestre, notaTrabalhoQuartoBimestre;
    double notaMediaPrimeiroBimestre, notaMediaSegundoBimestre,
            notaMediaTerceiroBimestre, notaMediaQuartoBimestre;

    double mediaFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));//setSupportActionBar(toolbar);

        lerSharedPreferences();

        initAplicacao();

        btnPrimeiroBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent primeiroBimestre = new Intent(MainActivity.this, PrimeiroBimestreActivity.class);
                startActivity(primeiroBimestre);
            }
        });

        btnSegundoBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent segundoBimestre = new Intent(MainActivity.this, SegundoBimestreActivity.class);
                startActivity(segundoBimestre);
            }
        });

        btnTerceiroBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent terceiroBimestre = new Intent(MainActivity.this, TerceiroBimestreActivity.class);
                startActivity(terceiroBimestre);

            }
        });

        btnQuartoBimestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quartoBimestre = new Intent(MainActivity.this, QuartoBimestreActivity.class);
                startActivity(quartoBimestre);
            }
        });

        btnResultadoFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Resultado Final Funcionando", Toast.LENGTH_LONG).show();
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App Média Escolar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                clearSharedPreferences();
            }
        });

        visualizarResultado();
    }

    private void initAplicacao() {
        btnPrimeiroBimestre = findViewById(R.id.btnPrimeiroBimestre);
        btnSegundoBimestre = findViewById(R.id.btnSegundoBimestre);
        btnTerceiroBimestre = findViewById(R.id.btnTerceiroBimestre);
        btnQuartoBimestre = findViewById(R.id.btnQuartoBimestre);
        btnResultadoFinal = findViewById(R.id.btnResultadoFinal);

        btnSegundoBimestre.setEnabled(primeiroBimestre);
        btnTerceiroBimestre.setEnabled(segundoBimestre);
        btnQuartoBimestre.setEnabled(terceiroBimestre);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_sair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lerSharedPreferences();
        visualizarResultado();

    }

    /**
     * Recuperar os dados salvos com os valores
     * das notas de cada bimestre
     */
    private void lerSharedPreferences() {

        SharedPreferences mediaEscolarPref = getSharedPreferences(NOME_SHARED_PREF, 0);

        primeiroBimestre = mediaEscolarPref.getBoolean("primeiroBimestre", false);
        segundoBimestre = mediaEscolarPref.getBoolean("segundoBimestre", false);
        terceiroBimestre = mediaEscolarPref.getBoolean("terceiroBimestre", false);
        quartoBimestre = mediaEscolarPref.getBoolean("quartoBimestre", false);

        situacaoPrimeiroBimestre = mediaEscolarPref.getString("situacaoPrimeiroBimestre", "");
        situacaoSegundoBimestre = mediaEscolarPref.getString("situacaoSegundoBimestre", "");
        situacaoTerceiroBimestre = mediaEscolarPref.getString("situacaoTerceiroBimestre", "");
        situacaoQuartoBimestre = mediaEscolarPref.getString("situacaoQuartoBimestre", "");

        materiaPrimeiroBimestre = mediaEscolarPref.getString("materiaPrimeiroBimestre", "");
        materiaSegundoBimestre = mediaEscolarPref.getString("materiaSegundoBimestre", "");
        materiaTerceiroBimestre = mediaEscolarPref.getString("materiaTerceiroBimestre", "");
        materiaQuartoBimestre = mediaEscolarPref.getString("materiaQuartoBimestre", "");

        notaProvaPrimeiroBimestre = Double.parseDouble(mediaEscolarPref.getString("notaProvaPrimeiroBimestre", "0.0"));
        notaProvaSegundoBimestre = Double.parseDouble(mediaEscolarPref.getString("notaProvaSegundoBimestre", "0.0"));
        notaProvaTerceiroBimestre = Double.parseDouble(mediaEscolarPref.getString("notaProvaTerceiroBimestre", "0.0"));
        notaProvaQuartoBimestre = Double.parseDouble(mediaEscolarPref.getString("notaProvaQuartoBimestre", "0.0"));

        notaTrabalhoPrimeiroBimestre = Double.parseDouble(mediaEscolarPref.getString("notaTrabalhoPrimeiroBimestre", "0.0"));
        notaTrabalhoSegundoBimestre = Double.parseDouble(mediaEscolarPref.getString("notaTrabalhoSegundoBimestre", "0.0"));
        notaTrabalhoTerceiroBimestre = Double.parseDouble(mediaEscolarPref.getString("notaTrabalhoTerceiroBimestre", "0.0"));
        notaTrabalhoQuartoBimestre = Double.parseDouble(mediaEscolarPref.getString("notaTrabalhoQuartoBimestre", "0.0"));

        notaMediaPrimeiroBimestre = Double.parseDouble(mediaEscolarPref.getString("notaMediaPrimeiroBimestre", "0.0"));
        notaMediaSegundoBimestre = Double.parseDouble(mediaEscolarPref.getString("notaMediaSegundoBimestre", "0.0"));
        notaMediaTerceiroBimestre = Double.parseDouble(mediaEscolarPref.getString("notaMediaTerceiroBimestre", "0.0"));
        notaMediaQuartoBimestre = Double.parseDouble(mediaEscolarPref.getString("notaMediaQuartoBimestre", "0.0"));

    }

    private void visualizarResultado() {

        // Criar método para montar o texto de apresentação
        if (primeiroBimestre) {
            btnPrimeiroBimestre.setText(materiaPrimeiroBimestre + " - 1º Bimestre " + situacaoPrimeiroBimestre + " - Nota " + formatarValorDecimal(Double.parseDouble(String.valueOf(notaMediaPrimeiroBimestre))));
            btnPrimeiroBimestre.setEnabled(false);
            btnSegundoBimestre.setEnabled(true);
        }

        if (segundoBimestre) {
            btnSegundoBimestre.setText(materiaSegundoBimestre + " - 2º Bimestre " + situacaoSegundoBimestre + " - Nota " + formatarValorDecimal(Double.parseDouble(String.valueOf(notaMediaSegundoBimestre))));
            btnSegundoBimestre.setEnabled(false);
            btnTerceiroBimestre.setEnabled(true);
        }

        if (terceiroBimestre) {
            btnTerceiroBimestre.setText(materiaTerceiroBimestre + " - 3º Bimestre " + situacaoTerceiroBimestre + " - Nota " + formatarValorDecimal(Double.parseDouble(String.valueOf(notaMediaTerceiroBimestre))));
            btnTerceiroBimestre.setEnabled(false);
            btnQuartoBimestre.setEnabled(true);
        }

        if (quartoBimestre) {
            btnQuartoBimestre.setText(materiaQuartoBimestre + " - 4º Bimestre " + situacaoQuartoBimestre + " - Nota " + formatarValorDecimal(Double.parseDouble(String.valueOf(notaMediaQuartoBimestre))));
            btnQuartoBimestre.setEnabled(false);
            btnResultadoFinal.setEnabled(true);

            mediaFinal = 0;
            mediaFinal += Double.parseDouble(String.valueOf(notaMediaPrimeiroBimestre));
            mediaFinal += Double.parseDouble(String.valueOf(notaMediaSegundoBimestre));
            mediaFinal += Double.parseDouble(String.valueOf(notaMediaTerceiroBimestre));
            mediaFinal += Double.parseDouble(String.valueOf(notaMediaQuartoBimestre));

            String mensagemFinal = "";

            mediaFinal = (mediaFinal / 4);

            if ((Double.parseDouble(String.valueOf(notaMediaPrimeiroBimestre)) >= 7)
                    && (Double.parseDouble(String.valueOf(notaMediaSegundoBimestre)) >= 7)
                    && (Double.parseDouble(String.valueOf(notaMediaTerceiroBimestre)) >= 7)
                    && (Double.parseDouble(String.valueOf(notaMediaPrimeiroBimestre)) >= 7)) {

                mensagemFinal = mediaFinal >= 7 ?
                        "Aprovado com Média Final " + formatarValorDecimal(mediaFinal) :
                        "Reprovado com Média Final " + formatarValorDecimal(mediaFinal);

            } else {

                mensagemFinal = "Reprovado por matéria com Média Final " + formatarValorDecimal(mediaFinal);

            }

            btnResultadoFinal.setText(mensagemFinal);

        }
    }

    /**
     * Limpar os dados salvos com os valores
     * das notas de cada bimestre
     */
    private void clearSharedPreferences() {
        SharedPreferences mediaEscolarPref = getSharedPreferences(NOME_SHARED_PREF, 0);
        SharedPreferences.Editor editor = mediaEscolarPref.edit();
        editor.clear();
        editor.commit();

        clearMenu();

    }

    private void clearMenu() {

        btnResultadoFinal.setEnabled(false);
        btnQuartoBimestre.setEnabled(false);
        btnTerceiroBimestre.setEnabled(false);
        btnSegundoBimestre.setEnabled(false);
        btnPrimeiroBimestre.setEnabled(true);

        btnResultadoFinal.setText("Resultado Final");
        btnPrimeiroBimestre.setText("1º Bimestre");
        btnSegundoBimestre.setText("2º Bimestre");
        btnTerceiroBimestre.setText("3º Bimestre");
        btnQuartoBimestre.setText("4º Bimestre");
    }

    public static String formatarValorDecimal(Double valor) {
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        return df.format(valor);
    }


}
