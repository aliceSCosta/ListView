package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listview.adapter.MeuAdapter;
import com.example.listview.modelos.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Tarefa> tarefas = new ArrayList<>();
    private ArrayAdapter<Tarefa> arrayAdapterTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = findViewById(R.id.list_view_tarefas);
        criarTarefas();

        arrayAdapterTarefa = new MeuAdapter(MainActivity.this,
                (ArrayList<Tarefa>) tarefas);
        listView.setAdapter(arrayAdapterTarefa);

        //Clique curto - chamar outro activity
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, TarefaActivity.class);
                intent.putExtra("TITULO", tarefas.get(i).getNome());
                intent.putExtra("DESCRICAO", tarefas.get(i).getDescricao());
                startActivity(intent);


                //faz alguma coisa
                //Toast toast = Toast.makeText(getApplicationContext(),
                        //"Teste",
                        //Toast.LENGTH_LONG);
                        //toast.show();
            }
        });

         //Clique longo - concluir tarefa
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tarefas.get(i).setStatus(!tarefas.get(i).getStatus());
                listView.invalidateViews();
                return false;
            }
        });
    }

    public void criarTarefas() {
        this.tarefas.add(new Tarefa("1", "Estudar Android", false,
                "Para passar"));
        this.tarefas.add(new Tarefa("1", "Projeto Integrador", false,
                "Curso"));
        this.tarefas.add(new Tarefa("1", "GTA", true,
                "Jogo"));

    }
}

