package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ListView listView;
private Button addButton;
private ArrayList<String> todosList;
private ArrayAdapter<String> arrayAdapterlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText todoInput = findViewById(R.id.todoInput);

         addButton = findViewById(R.id.button);
        listView = findViewById(R.id.todoList);

        todosList = new ArrayList<>();

        arrayAdapterlist = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todosList);
        listView.setAdapter(arrayAdapterlist);
        setUpListViewListener();







        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoName = todoInput.getText().toString().trim();

                todosList.add(todoName);

                Toast toast  = Toast.makeText(MainActivity.this, "Clicked on ADD button", Toast.LENGTH_SHORT);
                toast.show();
                todoInput.setText("");

                listView.setAdapter(arrayAdapterlist);
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = "Item"+position+"You have to" +
                        " "+ ((TextView)view).getText().toString();
                Toast toast = Toast.makeText(MainActivity.this,"todo is "+text,Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();

                todosList.remove(position);
                arrayAdapterlist.notifyDataSetChanged();
                return true;

            }
        });
    }


}