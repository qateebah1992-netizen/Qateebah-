package com.kayanpro.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private TextView welcomeText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("KayanProPrefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("name", "مستخدم");

        welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("مرحباً، " + userName);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Initialize product list
        productList = new ArrayList<>();
        loadProducts();

        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
    }

    private void loadProducts() {
        productList.add(new Product("منتج 1", "299 ريال", "https://via.placeholder.com/200"));
        productList.add(new Product("منتج 2", "199 ريال", "https://via.placeholder.com/200"));
        productList.add(new Product("منتج 3", "399 ريال", "https://via.placeholder.com/200"));
        productList.add(new Product("منتج 4", "499 ريال", "https://via.placeholder.com/200"));
        productList.add(new Product("منتج 5", "599 ريال", "https://via.placeholder.com/200"));
        productList.add(new Product("منتج 6", "349 ريال", "https://via.placeholder.com/200"));
        productList.add(new Product("منتج 7", "449 ريال", "https://via.placeholder.com/200"));
        productList.add(new Product("منتج 8", "259 ريال", "https://via.placeholder.com/200"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_logout) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
