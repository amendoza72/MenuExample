package com.example.menuexample

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var text = "";
    lateinit var editText1: EditText
    lateinit var editText2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.editTextText1)
        editText2 = findViewById(R.id.editTextText2)

        registerForContextMenu(editText1)
        registerForContextMenu(editText2)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_exit) {
            finish();
            return true
        }
        else{
            return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.copy_menu, menu)
        if (menu != null) {
            menu.findItem(R.id.menu_item_copy).setActionView(v)
            menu.findItem(R.id.menu_item_paste).setActionView(v)
        }

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val view = item.actionView as EditText
        return when (item.itemId) {
            R.id.menu_item_copy -> {
                copy(view)
                true
            }
            R.id.menu_item_paste -> {
                paste(view)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun copy(view: EditText){
        text = view.text.toString();

    }

    fun paste(view: EditText){
        view.setText(text)
    }
}