package ru.osll.goodtravel.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.adapters.PlanAdapter;
import ru.osll.goodtravel.utils.DBHelper;

public class PlanActivity extends AppCompatActivity
{
    private RecyclerView planRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        init();
    }

    private void init()
    {
        planRecyclerView = (RecyclerView)findViewById(R.id.planRecyclerView);
        initToolbar();
        initPlans();
    }

    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initPlans()
    {
        planRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       // planRecyclerView.setAdapter(new PlanAdapter(PlanService.getAll(realm)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home) onBackPressed();

        return super.onOptionsItemSelected(item);
    }
}
